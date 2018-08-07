package com.example.frens.secondchefv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    Toolbar toolbar;
    Button accediBtn;
    Button faqBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
     * event handlers are atached to the buttons and navigated to the respective fragments
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        toolbar = v.findViewById(R.id.profile_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Profilo"); //Load the fragments toolbar

        faqBtn = v.findViewById(R.id.faqBtn);
        accediBtn = v.findViewById(R.id.accediBtn);

        accediBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * load the Login fragment on button click
             * @param view
             */
            @Override
            public void onClick(View view) {
                AccountLoginFragment loginFragment = AccountLoginFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.menu_container, loginFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return v;
    }

}
