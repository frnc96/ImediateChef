package com.example.frens.secondchefv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.frens.secondchefv2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PricesPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PricesPagerFragment extends Fragment {

    Spinner dropdown;

    public static final String ARG_OBJECT = "object";
    public static final String ARG_DESC = "desc";
    public static final String ARG_PREZZO_PIATTO = "prezzo_piatto";
    public static final String ARG_PREZZO_BOX = "prezzo_box";
    public static final String EURO= "€ ";

    TextView piattoTxt;
    TextView boxTxt;

    public PricesPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment PricesPagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PricesPagerFragment newInstance() {
        PricesPagerFragment fragment = new PricesPagerFragment();
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
        View v = inflater.inflate(R.layout.fragment_prices_pager, container, false);

        final Bundle args = getArguments();

        TextView descTextView = v.findViewById(R.id.descTextView);
        descTextView.setText(args.getString(ARG_DESC));

        piattoTxt = v.findViewById(R.id.piattoTxt);
        piattoTxt.setText(EURO+Double.toString(args.getDouble(ARG_PREZZO_PIATTO)));

        boxTxt = v.findViewById(R.id.boxTxt);
        boxTxt.setText(Integer.toString(args.getInt(ARG_PREZZO_BOX)));

        dropdown = v.findViewById(R.id.dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_text, (String[])args.get(ARG_OBJECT));
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * when we select an item inside the spinner
             * the values get updated accordingly
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double mul = 0;

                switch (i){
                    case 0:
                        mul = 1;
                        break;
                    case 1:
                        mul = 0.9;
                        break;
                    case 2:
                        mul = 0.8;
                        break;
                    case 3:
                        mul = 0.7;
                        break;
                    default:
                        mul = 0.5;
                }

                piattoTxt.setText(EURO + String.format("%.2f",(args.getDouble(ARG_PREZZO_PIATTO) * mul)));
                boxTxt.setText(EURO + String.format("%.2f",(args.getInt(ARG_PREZZO_BOX) * mul)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

}
