package com.example.oyun;

import static com.example.oyun.oyunGorunumu.ekranBuyuklukX;
import static com.example.oyun.oyunGorunumu.ekranBuyuklukY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class kursun {

    int x,y,genislik,yukseklik;
    Bitmap kursun;

    kursun(Resources res){
        kursun = BitmapFactory.decodeResource(res,R.drawable.kursun);

         genislik = kursun.getWidth();
         yukseklik = kursun.getHeight();

        genislik /=4;
        yukseklik /=4;

        genislik *=(int) ekranBuyuklukX;
        yukseklik *= (int) ekranBuyuklukY;

        kursun = Bitmap.createScaledBitmap(kursun,genislik,yukseklik,false);

    }
    Rect getCarpismaKontrol(){
        return new Rect(x,y,x+yukseklik,y+yukseklik);


    }

}
