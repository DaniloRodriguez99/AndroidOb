package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.obligatorio.R;

public class CrearMascota extends AppCompatActivity {

    private ImageView mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        mascota = (ImageView) findViewById(R.id.imgMascota_CrearMascota);
        String gifUrl = "file:///android_res/drawable/gato_quieto.gif";
        Glide.with(this).load(R.drawable.gato_quieto).into(mascota);
    }
}