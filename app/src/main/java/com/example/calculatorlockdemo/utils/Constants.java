package com.example.calculatorlockdemo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.calculatorlockdemo.R;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    //method to convert your text to image---------------------------------------
    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.0f); // round
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }


    //create a country  flag list-----------------
    public static List<Integer> countryFlagList() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.argentina);
        list.add(R.drawable.armenia);
        list.add(R.drawable.australia);
        list.add(R.drawable.azerbaijan);
        list.add(R.drawable.bangladesh);
        list.add(R.drawable.brazil);
        list.add(R.drawable.belgium);
        list.add(R.drawable.cameroon);
        list.add(R.drawable.canada);
        list.add(R.drawable.china);
        list.add(R.drawable.costar_ica);
        list.add(R.drawable.croatia);
        list.add(R.drawable.denmark);
        list.add(R.drawable.france);
        return list;
    }

    //Home fragment all files icon list ---------------
    public static List<Integer> fileIconList() {
        List<Integer> filesIconList = new ArrayList<>();
        filesIconList.add(R.drawable.cal_gallery_icon);
        filesIconList.add(R.drawable.cal_video_icon);
        filesIconList.add(R.drawable.cal_audio_icon);
        filesIconList.add(R.drawable.cal_notes_icon);
        filesIconList.add(R.drawable.cal_document_icon);
        filesIconList.add(R.drawable.cal_app_lock_icon);
        filesIconList.add(R.drawable.cal_recycler_bin_icon);
        filesIconList.add(R.drawable.cal_settings_icon);
        return filesIconList;
    }

}
