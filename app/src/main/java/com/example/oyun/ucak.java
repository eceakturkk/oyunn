package com.example.oyun;

import static com.example.oyun.oyunGorunumu.ekranBuyuklukX;
import static com.example.oyun.oyunGorunumu.ekranBuyuklukY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ucak {
    public boolean yukariGit=false;
    int atesEt=0,atesSay =1;
    int x,y,genislik,yukseklik,kanatHareketi=0;
    Bitmap ucak1,ucak2,ucakates1,ucakates2,ucakates3,ucakates4,ucakates5,kaza;
    private oyunGorunumu oyunGorunumu;

    ucak(oyunGorunumu oyunGorunumu,int ekranY, Resources res){
        this.oyunGorunumu=oyunGorunumu;

        ucak1 = BitmapFactory.decodeResource(res,R.drawable.ucak1);
        ucak2 = BitmapFactory.decodeResource(res,R.drawable.ucak2);

        genislik = ucak1.getWidth();
        yukseklik = ucak1.getHeight();

        genislik /=4;
        yukseklik /=4;

        genislik *=(int) ekranBuyuklukX;
        yukseklik *=(int) ekranBuyuklukY;

        ucak1 = Bitmap.createScaledBitmap(ucak1,genislik,yukseklik,false);
        ucak2 = Bitmap.createScaledBitmap(ucak2,genislik,yukseklik,false);

        ucakates1 =  BitmapFactory.decodeResource(res,R.drawable.ucakates1);
        ucakates2 =  BitmapFactory.decodeResource(res,R.drawable.ucakates2);
        ucakates3 =  BitmapFactory.decodeResource(res,R.drawable.ucakates3);
        ucakates4 =  BitmapFactory.decodeResource(res,R.drawable.ucakates4);
        ucakates5 =  BitmapFactory.decodeResource(res,R.drawable.ucakates5);

        ucakates1 =Bitmap.createScaledBitmap(ucakates1,genislik,yukseklik,false);
        ucakates2 =Bitmap.createScaledBitmap(ucakates2,genislik,yukseklik,false);
        ucakates3 =Bitmap.createScaledBitmap(ucakates3,genislik,yukseklik,false);
        ucakates4 =Bitmap.createScaledBitmap(ucakates4,genislik,yukseklik,false);
        ucakates5 =Bitmap.createScaledBitmap(ucakates5,genislik,yukseklik,false);

        kaza = BitmapFactory.decodeResource(res,R.drawable.kaza);
        kaza = Bitmap.createScaledBitmap(kaza,genislik,yukseklik,false);



        y = ekranY /2;
        x = (int) (64 * ekranBuyuklukX);


    }
    Bitmap getucak(){

        if (atesEt != 0) {

            if(atesSay == 1){
                atesSay++;
                return ucakates1;
            }
            if(atesSay == 2){
                atesSay++;
                return ucakates2;
            }
            if(atesSay == 3){
                atesSay++;
                return ucakates3;
            }
            if(atesSay == 4){
                atesSay++;
                return ucakates4;
            }
            atesSay = 1;
            atesEt--;
            oyunGorunumu.kursun();
            return ucakates5;

        }
        if (kanatHareketi==0){
            kanatHareketi++;
            return ucak1;
        }
        kanatHareketi--;
        return ucak2;

    }
    Rect getCarpismaKontrol() {
        return new Rect(x, y, x + yukseklik, y + yukseklik);
    }
    Bitmap getKaza(){
        return kaza;
    }

    }
