package com.example.frens.secondchefv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainInfoFragment extends Fragment {

    Toolbar toolbar;

    public MainInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment MainInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainInfoFragment newInstance() {
        MainInfoFragment fragment = new MainInfoFragment();
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

    /**
     * here we use the Youtube API to display the tutorial video in the fragment
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_info, container, false);

        toolbar = v.findViewById(R.id.prezzi_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Come funziona"); //Load the fragments toolbar

        YouTubePlayerSupportFragment tutorialVideo = new YouTubePlayerSupportFragment();
        tutorialVideo.initialize("AIzaSyCJpDIv80DfxaY3z2OM01L5x-bpBurgDC4", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo("rXlgOIUzFZ4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

        getChildFragmentManager().beginTransaction().replace(R.id.tutorialFragment, tutorialVideo).commit();

        return v;
    }

}
