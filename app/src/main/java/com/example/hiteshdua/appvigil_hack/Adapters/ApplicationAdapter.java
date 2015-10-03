package com.example.hiteshdua.appvigil_hack.Adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiteshdua.appvigil_hack.R;

import org.w3c.dom.Text;

public class ApplicationAdapter extends ArrayAdapter<ApplicationInfo> {
	private List<ApplicationInfo> appsList = null;
	private Context context;
	private PackageManager packageManager;
	String labelName;

	SimpleDateFormat dateformat;

	public ApplicationAdapter(Context context, int textViewResourceId,
			List<ApplicationInfo> appsList, String labelName) {
		super(context, textViewResourceId, appsList);
		this.context = context;
		this.appsList = appsList;
		packageManager = context.getPackageManager();
		this.labelName = labelName;
	}

	@Override
	public int getCount() {
		return ((null != appsList) ? appsList.size() : 0);
	}

	@Override
	public ApplicationInfo getItem(int position) {
		return ((null != appsList) ? appsList.get(position) : null);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (null == view) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.single_list_item, null);
		}
	    dateformat = new SimpleDateFormat("dd/MM/yyyy");

		ApplicationInfo data = appsList.get(position);
		if (null != data) {
			TextView appName = (TextView) view.findViewById(R.id.app_name);
			TextView appTime = (TextView) view.findViewById(R.id.app_time);
			ImageView iconview = (ImageView) view.findViewById(R.id.app_icon);
			TextView label = (TextView) view.findViewById(R.id.label);

			label.setText(labelName.toUpperCase());
			appName.setText(data.loadLabel(packageManager));
			String time;

			try {
				PackageInfo packageInfo = packageManager.getPackageInfo(data.packageName, PackageManager.GET_PERMISSIONS);
				switch(labelName.toLowerCase())
				{
					case "installed":
						time = dateformat.format( new Date( packageInfo.firstInstallTime) );
						break;
					case "updated":
						time = dateformat.format( new Date( packageInfo.lastUpdateTime) );
						break;
					case "uninstalled":
						time = dateformat.format( new Date( packageInfo.lastUpdateTime) );
						break;
					default:
						time="null";

				}
				appTime.setText(time);
			}
			catch ( PackageManager.NameNotFoundException e ) {
				e.printStackTrace();
			}
			iconview.setImageDrawable(data.loadIcon(packageManager));
		}
		return view;
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
};