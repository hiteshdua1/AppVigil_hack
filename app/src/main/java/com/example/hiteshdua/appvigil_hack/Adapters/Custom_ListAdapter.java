package com.example.hiteshdua.appvigil_hack.Adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiteshdua.appvigil_hack.R;
import java.util.List;


public class Custom_ListAdapter extends ArrayAdapter<String> {

//    ImageLoader imageLoader = ImageLoader.getInstance();

    private Context context;
    private List<String> postsList;
//    private DisplayImageOptions displayOptions;


    public Custom_ListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.postsList = objects;

//        displayOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.logo)
//                .showImageForEmptyUri(R.drawable.login)
//                .showImageOnFail(R.drawable.login)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .displayer(new RoundedBitmapDisplayer(0)).build();
//        ImageLoaderConfiguration configs = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheSize(41943040).defaultDisplayImageOptions(displayOptions)
//                .threadPoolSize(10).build();
//
//        imageLoader.init(configs);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_list_item, parent, false);
        //Display tricks name in the TextView widget
//        final Posts post = postsList.get(position);
//        TextView Title = (TextView) view.findViewById(R.id.title);
//        Title.setText(Html.fromHtml(post.getTitle()).toString());
//
//        ImageView ShowImage = (ImageView) view.findViewById(R.id.soapimage);
//        imageLoader.displayImage(post.getImage(), ShowImage);
//
//        TextView Date_Text = (TextView) view.findViewById(R.id.date);
//        String reformattedStr="" ;
//
//        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy/MM/dd");
//        SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM, yyyy");
//
//        try {
//            reformattedStr = myFormat.format(fromUser.parse(post.getDate()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        Date_Text.setText(reformattedStr);
//
////        TextView CommentCount = (TextView) view.findViewById(R.id.commentcount);
////        CommentCount.setText(post.getCommentcount());
//

        return view;
    }



}
