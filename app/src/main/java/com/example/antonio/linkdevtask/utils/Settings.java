package com.example.antonio.linkdevtask.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.antonio.linkdevtask.R;

/**
 * Created by antonio on 1/17/19.
 */

public class Settings {

    private static RequestOptions requestOptions;

    public static void loadImageWithGlide(final Context context, final ImageView imageView, final String URL) {

        if (context == null || imageView == null || URL == null)
            return;//early if found one of them with null value

        if (requestOptions == null)
            requestOptions = new RequestOptions();

        requestOptions.error(R.drawable.placeholder);
        requestOptions.placeholder(R.drawable.placeholder);

        Glide.with(context)
                .load(URL)
                .apply(requestOptions)
                .into(imageView);


    }
}
