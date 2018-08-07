package com.example.frens.secondchefv2.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frens.secondchefv2.R;
import com.example.frens.secondchefv2.adapters.MainPagerAdapter;
import com.example.frens.secondchefv2.fragments.MainInfoFragment;
import com.example.frens.secondchefv2.fragments.MainMapFragment;
import com.example.frens.secondchefv2.fragments.MainPricesFragment;
import com.example.frens.secondchefv2.fragments.MainRecipesFragment;
import com.example.frens.secondchefv2.fragments.ProfileFragment;
import com.facebook.AccessToken;

public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    ViewPager mainViewPager;
    BottomNavigationView navView;
    static MainActivity mainActivity;

    /**
     *
     * @return actual instance of the activity
     */
    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    /**
     * Saves the actual instance of the activity
     * @param mainActivity
     */
    private static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         * Load required pages when we click an icon in the navigation bar
         * @param item
         * @return true if the item will be selected
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_recipes:
                    //recipes page
                    mainViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_prices:
                    //prices page
                    mainViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_info:
                    //info page
                    mainViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_map:
                    //map page
                    mainViewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_profile:
                    //profile page
                    mainViewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMainActivity(this);//record the instance of the activity

        fm = getSupportFragmentManager();
        navView = findViewById(R.id.navigation);
        mainViewPager = findViewById(R.id.main_pager);

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            /**
             * Select the navbar icon when we swipe pages
             * @param i
             */
            @Override
            public void onPageSelected(int i) {
                navView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        addPages();
        mainViewPager.setOffscreenPageLimit(3);//Save up to 3 pages in memory to avoid glitches

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /**
         * Check if the user is logged in and greet him
         */
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(!isLoggedIn)
            Toast.makeText(this, "You are not logged in" ,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Welcome!" ,Toast.LENGTH_LONG).show();
    }

    /**
     * Initializes and assigns the pages to the adapter and also sets the adapter to the view
     */
    private void addPages(){
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(fm);
        mainPagerAdapter.addPage(MainRecipesFragment.newInstance());
        mainPagerAdapter.addPage(MainPricesFragment.newInstance());
        mainPagerAdapter.addPage(MainInfoFragment.newInstance());
        mainPagerAdapter.addPage(MainMapFragment.newInstance());
        mainPagerAdapter.addPage(ProfileFragment.newInstance());
        mainViewPager.setAdapter(mainPagerAdapter);
    }

    /**
     * The fragment gives the activity the toolbar
     * and title of the toolbar and assigns it to the activity
     * @param toolbar
     * @param title
     */
    public void loadToolBar(Toolbar toolbar, String title){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back arrow is enabled
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
