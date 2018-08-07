package com.example.frens.secondchefv2.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.frens.secondchefv2.fragments.PricesPagerFragment;

public class PersonsPagerAdapter extends FragmentStatePagerAdapter {

    String[] descs;

    public PersonsPagerAdapter(FragmentManager fm, String[] descs) {
        super(fm);
        this.descs = descs;
    }

    /**
     * inserts all the arguments as parameters to the fragment
     * which it is creating
     * @param i
     * @return
     */
    @Override
    public Fragment getItem(int i) {
        String[] page1 = new String[]{"2 RICETTE", "3 RICETTE", "4 RICETTE", "5 RICETTE"};
        String[] page2 = new String[]{"2 RICETTE", "3 RICETTE", "4 RICETTE"};
        String[] page3 = new String[]{"1 RICETTA"};

        Fragment page = PricesPagerFragment.newInstance();
        Bundle args = new Bundle();

        switch (i){
            case 0:
                args.putStringArray(PricesPagerFragment.ARG_OBJECT ,page1);
                args.putString(PricesPagerFragment.ARG_DESC, descs[0]);
                args.putDouble(PricesPagerFragment.ARG_PREZZO_PIATTO, 8.50);
                args.putInt(PricesPagerFragment.ARG_PREZZO_BOX, 34);
                break;
            case 1:
                args.putStringArray(PricesPagerFragment.ARG_OBJECT ,page2);
                args.putString(PricesPagerFragment.ARG_DESC, descs[1]);
                args.putDouble(PricesPagerFragment.ARG_PREZZO_PIATTO, 6.13);
                args.putInt(PricesPagerFragment.ARG_PREZZO_BOX, 49);
                break;
            case 2:
                args.putStringArray(PricesPagerFragment.ARG_OBJECT ,page3);
                args.putString(PricesPagerFragment.ARG_DESC, descs[2]);
                args.putDouble(PricesPagerFragment.ARG_PREZZO_PIATTO, 7.33);
                args.putInt(PricesPagerFragment.ARG_PREZZO_BOX, 44);
                break;
        }

        page.setArguments(args);

        return page;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
