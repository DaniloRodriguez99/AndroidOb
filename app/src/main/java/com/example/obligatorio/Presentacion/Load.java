package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.obligatorio.MainActivity;
import com.example.obligatorio.Preguntado;
import com.example.obligatorio.R;


public class Load extends AppCompatActivity {

    private final int tiempo = 3000;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        animationView = (LottieAnimationView) findViewById(R.id.animationView);

        play();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                load();
            }


        }, tiempo);

    }

    public void load() {

        Intent i = new Intent(this, Preguntado.class);
        startActivity(i);
    }
    public void play(){
        animationView.playAnimation();
    }

}