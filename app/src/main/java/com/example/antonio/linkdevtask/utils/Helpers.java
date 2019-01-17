package com.example.antonio.linkdevtask.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    //this method valid string and value can never be null
    public static String validString(String string){
        if (string==null)
            return "";

        return string;

    }




    public static String parseDate(String oldDateFormat) {
        if (oldDateFormat==null)
            return "";
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        try {
            Date date = inputFormat.parse(oldDateFormat);
          return outputFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}
