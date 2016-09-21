package com.krishna.hashtag.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.krishna.hashtag.R;
import com.krishna.hashtag.models.Media;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Krishna on 21/09/16.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Media> imageList;
    private int itemImageView;
    private LayoutInflater inflater;

    /* ViewHolder class to hold the view */
    private static class ViewHolder {
        public ImageView imageView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.iv_image);
        }
    }

    public CustomAdapter(Context context, ArrayList<Media> imageList, @LayoutRes int itemImageView) {
        this.context = context;
        this.imageList = imageList;
        this.itemImageView = itemImageView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(itemImageView, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        Picasso.with(context).load(imageList.get(position).mediaUrl).into(viewHolder.imageView);
        return view;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Media getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
