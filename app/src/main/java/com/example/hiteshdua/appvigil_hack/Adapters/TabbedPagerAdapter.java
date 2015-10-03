package com.example.hiteshdua.appvigil_hack.Adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hiteshdua.appvigil_hack.Fragments.InstalledFragment;


public class TabbedPagerAdapter  extends FragmentStatePagerAdapter {
    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    String cat_id;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public TabbedPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb , String cat_id) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.cat_id=cat_id;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                Bundle bundle0 = new Bundle();
                bundle0.putString("action", "installed");
                InstalledFragment frag = new InstalledFragment();
                frag.setArguments(bundle0);
                return frag;
            case 1:
                Bundle bundle1 = new Bundle();
                bundle1.putString("action", "updated");
                InstalledFragment frag1 = new InstalledFragment();
                frag1.setArguments(bundle1);
                return frag1;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putString("action", "uninstalled");
                InstalledFragment frag2 = new InstalledFragment();
                frag2.setArguments(bundle2);
                return frag2;
            default:
                return null;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
