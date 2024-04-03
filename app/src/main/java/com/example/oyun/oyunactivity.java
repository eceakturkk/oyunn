package com.example.oyun;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class oyunactivity extends AppCompatActivity {
    private oyunGorunumu oyunGorunumu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

       oyunGorunumu = new oyunGorunumu(this,point.x,point.y);

       setContentView(oyunGorunumu);

    }
    protected void onPause(){
        super.onPause();
        oyunGorunumu.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        oyunGorunumu.resume();
    }
}