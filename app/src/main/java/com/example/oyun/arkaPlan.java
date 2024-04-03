package com.example.oyun;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class arkaPlan {
    int x=0,y=0;
    Bitmap arkaplan;

    arkaPlan(int ekranX, int ekranY, Resources res){
        arkaplan= BitmapFactory.decodeResource(res,R.drawable.arkaplan);
        arkaplan=Bitmap.createScaledBitmap(arkaplan,ekranX,ekranY,false);
    }
}
