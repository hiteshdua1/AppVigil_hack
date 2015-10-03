package com.example.hiteshdua.appvigil_hack.Fragments;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hiteshdua.appvigil_hack.Adapters.ApplicationAdapter;
import com.example.hiteshdua.appvigil_hack.Adapters.Custom_ListAdapter;
import com.example.hiteshdua.appvigil_hack.MainActivity;
import com.example.hiteshdua.appvigil_hack.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class InstalledFragment extends ListFragment {

    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null,applist1 = null ;
    private ApplicationAdapter listadaptor = null;
    String action="installed";
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootview =inflater.inflate(R.layout.fragment_installed, container, false);

            if(getArguments()!=null)
            {
                Bundle bundle = getArguments();
                action =bundle.getString("action");

            }
            packageManager = getActivity().getPackageManager();
            new LoadApplications().execute();

            return rootview;

        }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

    private class LoadApplications extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {
            applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
            applist1 = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));

            //Sorting
            Collections.sort(applist, new Comparator<ApplicationInfo>() {
                @Override
                public int compare(ApplicationInfo fruite1, ApplicationInfo fruite2) {

                    return fruite1.loadLabel(packageManager).toString().compareTo(fruite2.loadLabel(packageManager).toString());
                }
            });

            try {
                switch (action) {
                    case "installed":
                        listadaptor = new ApplicationAdapter(getActivity(),
                                R.layout.single_list_item, applist,action);
                        break;
                    case "updated":
                        for (int i=0;i<applist1.size();i++) {
                            PackageInfo packageInfo = packageManager.getPackageInfo(applist1.get(i).packageName, PackageManager.GET_PERMISSIONS);
                            if(packageInfo.firstInstallTime==packageInfo.lastUpdateTime)
                            {
                                applist1.remove(applist1.get(i));
                            }
                        }

                        Collections.sort(applist1, new Comparator<ApplicationInfo>() {
                            @Override
                            public int compare(ApplicationInfo var1, ApplicationInfo var2) {
                                try {
                                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd hh:mm aa");
                                    PackageInfo packageInfo1 = packageManager.getPackageInfo(var1.packageName, PackageManager.GET_PERMISSIONS);
                                    PackageInfo packageInfo2 = packageManager.getPackageInfo(var2.packageName, PackageManager.GET_PERMISSIONS);
                                    String time1 = dateformat.format(new Date(packageInfo1.lastUpdateTime));
                                    String time2 = dateformat.format(new Date(packageInfo2.lastUpdateTime));
                                    return time2.toString().compareTo(time1.toString());
                                }
                                catch (Exception e)
                                {
                                    return 0;
                                }
                            }
                        });
                        listadaptor = new ApplicationAdapter(getActivity(),
                                R.layout.single_list_item, applist1,action);
                        break;
                    case "uninstalled":
                        listadaptor = new ApplicationAdapter(getActivity(),
                                R.layout.single_list_item, applist,action);
                        break;
                }
            }
            catch(PackageManager.NameNotFoundException e ){
                e.printStackTrace();

            }



            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            listadaptor.notifyDataSetChanged();
            setListAdapter(listadaptor);
            progress.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(getActivity(), null,
                    "Loading application info...");
            progress.setCancelable(false);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

        }

}
