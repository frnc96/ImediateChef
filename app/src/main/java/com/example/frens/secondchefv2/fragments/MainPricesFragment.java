package com.example.frens.secondchefv2.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.activities.MainActivity;
import com.example.frens.secondchefv2.adapters.PersonsPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainPricesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainPricesFragment extends Fragment implements View.OnClickListener{

    Toolbar toolbar;
    private Button twoPersonBtn;
    private Button fourPersonBtn;
    private Button sixPersonBtn;

    ViewPager personsPager;

    public MainPricesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment MainPricesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainPricesFragment newInstance() {
        MainPricesFragment fragment = new MainPricesFragment();
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
        View v = inflater.inflate(R.layout.fragment_main_prices, container, false);

        toolbar = v.findViewById(R.id.prezzi_toolbar);
        MainActivity.getMainActivity().loadToolBar(toolbar, "Prezzi"); //Load the fragments toolbar

        twoPersonBtn = v.findViewById(R.id.twoPersonBtn);
        fourPersonBtn = v.findViewById(R.id.fourPersonBtn);
        sixPersonBtn = v.findViewById(R.id.sixPersonBtn);

        twoPersonBtn.setOnClickListener(this);
        fourPersonBtn.setOnClickListener(this);
        sixPersonBtn.setOnClickListener(this);

        twoPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected)); //select the first button

        personsPager = v.findViewById(R.id.personPager);

        personsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * select the corresponding button when we swipe pages
             * @param i
             */
            @Override
            public void onPageSelected(int i) {
                resetButtonColor();

                switch (i){
                    case 0:
                        twoPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));
                        break;
                    case 1:
                        fourPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));
                        break;
                    case 2:
                        sixPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        String[] descs = new String[]{getActivity().getResources().getString(R.string.tab_view_desc1),
                getActivity().getResources().getString(R.string.tab_view_desc2),
                getActivity().getResources().getString(R.string.tab_view_desc3)};

        PersonsPagerAdapter adapter = new PersonsPagerAdapter(getActivity().getSupportFragmentManager(), descs);
        personsPager.setAdapter(adapter);

        return v;
    }

    /**
     * unselect all the page navigation buttons
     */
    private void resetButtonColor(){
        twoPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_selected));
        fourPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_selected));
        sixPersonBtn.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_selected));
    }

    /**
     * navigate through pager on button click
     * @param view
     */
    @Override
    public void onClick(View view) {

        resetButtonColor();
        view.setBackground(getActivity().getResources().getDrawable(R.drawable.tabs_button_not_selected));

        switch(view.getId()){
            case R.id.twoPersonBtn:
                personsPager.setCurrentItem(0);
                break;
            case R.id.fourPersonBtn:
                personsPager.setCurrentItem(1);
                break;
            case R.id.sixPersonBtn:
                personsPager.setCurrentItem(2);
                break;
        }

    }
}
