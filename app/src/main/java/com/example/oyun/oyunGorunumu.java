package com.example.oyun;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class oyunGorunumu extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying,oyunBitti=false;
    private  int ekranX,ekranY;
    public static float ekranBuyuklukX,ekranBuyuklukY;
    private arkaPlan arkaplan1,arkaplan2;
    private ucak ucak;
    private Paint paint;
    private kus[] kuslar;
    private Random rastgele;
    private List<kursun>kursunlar;

    public oyunGorunumu(Context context,int ekranX,int ekranY){
        super(context);
        this.ekranX = ekranX;
        this.ekranY = ekranY;
        ekranBuyuklukX = 1920f/ekranX;
        ekranBuyuklukY = 1000f/ekranY;
        arkaplan1=new arkaPlan(ekranX,ekranY,getResources());
        arkaplan2=new arkaPlan(ekranX,ekranY,getResources());

        ucak = new ucak(this,ekranY,getResources());
        kursunlar = new ArrayList<>();

        arkaplan2.x=ekranX;
        paint = new Paint();

        kuslar = new kus[4];
        for(int i =0; i < 4; i++){
            kus kus = new kus(getResources());
            kuslar[i] = kus;


        }
    }

    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();

        }

    }
    private void update(){
        arkaplan1.x -= 10 * ekranBuyuklukX;
        arkaplan2.x -= 10 * ekranBuyuklukX;

        if(arkaplan1.x + arkaplan1.arkaplan.getWidth()<0){
            arkaplan1.x=ekranX;
        }
        if(arkaplan2.x + arkaplan2.arkaplan.getWidth()<0){
            arkaplan2.x=ekranX;
        }
        if (ucak.yukariGit)
            ucak.y -= 30 * ekranBuyuklukY;
        else
            ucak.y += 30 * ekranBuyuklukY;
        if (ucak.y < 0)
            ucak.y = 0;
        if (ucak.y >= ekranY - ucak.yukseklik)
            ucak.y = ekranY - ucak.yukseklik;

        List<kursun> sacma = new ArrayList<>();
        for (kursun kursun: kursunlar){
            if (kursun.x > ekranX)
                sacma.add(kursun);
            kursun.x +=50 * ekranBuyuklukX;

            for(kus kus : kuslar){
                if(Rect.intersects(kus.getCarpismaKontrol(), kursun.getCarpismaKontrol())) {
                    kus.x = -500;
                    kursun.x = ekranX + 500;
                    kus.vurulduKus = true;
                }
            }
        }
        for (kursun kursun: sacma)
            kursunlar.remove(kursun);

        for (kus kus : kuslar){
            kus.x -=kus.hiz;
            if(kus.x + kus.genislik < 0) {
                if(!kus.vurulduKus){
                    oyunBitti = true;
                    return;
                }

                int rastgeleyeBagli =(int)(30 * ekranBuyuklukX);

                Random rastgle = new Random();
                kus.hiz = rastgle.nextInt(rastgeleyeBagli);

                if(kus.hiz < 10 * ekranBuyuklukX)
                    kus.hiz = (int) (10 * ekranBuyuklukX);
                kus.x = ekranX;
                kus.y = rastgle.nextInt(ekranY-kus.yukseklik);

                kus.vurulduKus = false;
            }
            if (Rect.intersects(kus.getCarpismaKontrol(),ucak.getCarpismaKontrol())){
                oyunBitti= true;
                return;
            }
        }
    }
    private void  draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canva = getHolder().lockCanvas();
            RecordingCanvas canvas = null;
            canvas.drawBitmap(arkaplan1.arkaplan,arkaplan1.x,arkaplan2.y,paint);
            canvas.drawBitmap(arkaplan2.arkaplan,arkaplan2.x,arkaplan1.y,paint);
            if(oyunBitti){
                isPlaying = false;
                canvas.drawBitmap(ucak.getKaza(), ucak.x,ucak.y,paint);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
                return;
            }
            for (kus kus : kuslar)
                canvas.drawBitmap(kus.getkus(),kus.x,kus.y,paint);

            canvas.drawBitmap(ucak.getucak(),ucak.x,ucak.y,paint);

            for (kursun kursun: kursunlar)
                canvas.drawBitmap(kursun.kursun,kursun.x,kursun.y,paint);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                getHolder().unlockCanvasAndPost(canvas);
            }
        }

    }
    private void sleep(){
        try {
            Thread.sleep(17);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();


    }
    public void pause(){
        try{
            isPlaying = false;
        thread.join();}
        catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < ekranX / 2){
                    ucak.yukariGit = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                ucak.yukariGit = false;
                if (event.getX() > ekranX /2)
                    ucak.atesEt++;
                break;

        }
        return true;
    }

    public void kursun() {

        kursun kursun = new kursun(getResources());
        kursun.x = ucak.x + ucak.genislik;
        kursun.y = ucak.y + (ucak.yukseklik/2);
        kursunlar.add(kursun);

    }
}
