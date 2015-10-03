package com.example.hiteshdua.appvigil_hack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hiteshdua.appvigil_hack.Adapters.TabbedPagerAdapter;
import com.example.hiteshdua.appvigil_hack.layout.SlidingTabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TabbedPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Installed","Updated","Uninstalled"};
    int Numboftabs =3;
    String cat_id;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.relTopNavbar);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            ActionBar a = getSupportActionBar();
            a.setDisplayShowTitleEnabled(false);
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));



        pager = (ViewPager) findViewById(R.id.pager);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new TabbedPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs,cat_id);
        // Assigning ViewPager View and setting the adapter

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.MainBasecolor);
            }
        });

        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    private BroadcastReceiver onNotice= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String pack = intent.getStringExtra("package");
            String title = intent.getStringExtra("title");
            String text = intent.getStringExtra("text");

            System.out.println(pack);
            System.out.println(title);
            System.out.println(text);

                pager.getAdapter().notifyDataSetChanged();



        }
    };


}
