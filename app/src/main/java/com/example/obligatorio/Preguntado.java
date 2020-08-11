package com.example.obligatorio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class Preguntado extends AppCompatActivity implements Animation.AnimationListener {
boolean blnButtonRotation = true;
int intNumber = 4;
int NumeroTipo;
long lngDegrees = 0;
FloatingActionButton btnGirar;

ImageView imageRoulette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntado);

        btnGirar = (FloatingActionButton)findViewById(R.id.btnGirar);
        imageRoulette = (ImageView)findViewById(R.id.imgRuleta);
    }

    //Al iniciarse la animacion se setea el boolean auxiliar en falso.
    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        btnGirar.setVisibility(View.VISIBLE);
    }

    //Se Calcula el valor obtenido, y se setea del boolean auxiliar en true
    @Override
    public void onAnimationEnd(Animation animation) {

        this.blnButtonRotation = true;
        this.NumeroTipo = (int)(((double)this.intNumber) - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.intNumber))));
        Toast toast = Toast.makeText(this,TipoPregunta(NumeroTipo) ,Toast.LENGTH_SHORT);
        toast.setGravity(49,0,0);
        toast.show();
        btnGirar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //Ejecucion del giro mediante el boton Si el boolean es true osea(una vez terminada esta).
    public void onClickButtonRotation(View v)
    {
        if(this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees,(float)(this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);
            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);
        }
    }

    public String TipoPregunta(int num)
    {
        switch (num) {
            case 1:
                return "Perro";
            case 2:
                return "Pescado";
            case 3:
                return "Oso";
            case 4:
                return "Gato";
            default:
                return null;
        }
    }
}