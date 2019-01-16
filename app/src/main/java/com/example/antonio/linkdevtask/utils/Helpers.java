package com.example.antonio.linkdevtask.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by antonio on 1/16/19.
 */

public class Helpers {


    //this method instead of writing to every time...
    public static void showMessage(Context context, String content) {
        if (context == null || content == null)
            return;
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
