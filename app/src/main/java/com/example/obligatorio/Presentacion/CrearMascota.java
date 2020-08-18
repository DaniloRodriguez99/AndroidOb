package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.obligatorio.R;

public class CrearMascota extends AppCompatActivity {

    private ImageView imagenMascota;
    private AnimationDrawable animacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        imagenMascota = (ImageView) findViewById(R.id.imgMascota_CrearMascota);
        imagenMascota.setBackgroundResource(R.drawable.gato_quieto_animacion);
        animacion = (AnimationDrawable) imagenMascota.getBackground();
        animacion.start();
    }
}