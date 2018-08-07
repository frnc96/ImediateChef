package com.example.frens.secondchefv2.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.example.frens.secondchefv2.models.Markers;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMapFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    Toolbar toolbar;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private final int PERMISSIONS_LOCATION = 111;

    public MainMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment MainMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMapFragment newInstance() {
        MainMapFragment fragment = new MainMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_map, container, false);

        toolbar = v.findViewById(R.id.map_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Mappa"); //Load the fragments toolbar

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(),this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }

    /**
     * the map is ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    /**
     * gets triggered when connection fails
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * if the connection is successful
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_LOCATION);
            Log.v("UserMapPermission","Requesting permissions");
        }else{
            Log.v("UserMapPermission","Starting location services from on connected method");
            startLocationServices();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * when the location changes we re-add the current location pin
     * and the downloaded markers from DataServices
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        MarkerOptions currLoc = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()));
        currLoc.icon(BitmapDescriptorFactory.fromResource(R.drawable.currlocpin));
        mMap.addMarker(currLoc);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));

        ArrayList<Markers> locations = new ArrayList<>();

        locations.add(new Markers(41.334742f,19.808994f, "Glow Bar","slo"));
        locations.add(new Markers(41.333010f,19.817373f, "Mon Cheri","slo"));
        locations.add(new Markers(41.332978f,19.810818f, "Prokuroria e Tiranes","slo"));

        for(Markers f : locations){
            MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(f.getLatitude(), f.getLongitude()));
            markerOptions.title(f.getLocationTitle());
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.addMarker(markerOptions);
        }
    }

    /**
     * when the map is assigned to the fragment we connect to the google API
     */
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    /**
     * on destruction of view we stop the connection to the service
     */
    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * here we handle when the user gives permission for the map or not
     * if yes then we start location services
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case PERMISSIONS_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startLocationServices();
                    Log.v("UserMapPermission","Permission granted - starting services");
                }else{
                    //i cant run your location you denied permission
                    Log.v("UserMapPermission","Permissions not granted");
                }
        }
    }

    /**
     * we start location services prioritizing devices battery life
     * by starting this service we can obtain the location of the device
     */
    public void startLocationServices(){
        Log.v("Maps","Starting location services called");

        try{
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
            Log.v("UserMapPermission","Requesting location updates");
        }catch (SecurityException ex){
            //tell user to give the app permission
            Toast.makeText(getContext(), "We need permission to use gps to properly use the map", Toast.LENGTH_SHORT).show();
            Log.v("UserMapPermission",ex.toString());
        }

    }
}
