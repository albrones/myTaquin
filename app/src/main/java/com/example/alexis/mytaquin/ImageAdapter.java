package com.example.alexis.mytaquin;

/**
 * Created by Alexis on 25/06/2017.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return imagesArray.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400, 400));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(imagesArray[position]);
        return imageView;
    }

    public Integer[] imagesArray = {
        R.drawable.shit,
        R.drawable.bossfight,
        R.drawable.content,
        R.drawable.microsoft,
        R.drawable.twitter,
        R.drawable.youtube,
        R.drawable.square_bear
    };
}