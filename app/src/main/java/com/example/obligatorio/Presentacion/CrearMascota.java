package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CrearMascota extends AppCompatActivity {

    protected ImageView mascota;
    protected FloatingActionButton btnPerro;
    protected FloatingActionButton btnGato;
    protected FloatingActionButton btnOso;
    protected FloatingActionButton btnPez;
    protected int urlGif;
    protected int blanco;
    protected int rojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        /*Asignamos los botones FAB*/
        btnGato = (FloatingActionButton) findViewById(R.id.btnGato_CrearMascota);
        btnPerro = (FloatingActionButton) findViewById(R.id.btnPerro_CrearMascota);
        btnOso = (FloatingActionButton) findViewById(R.id.btnOso_CrearMascota);
        btnPez = (FloatingActionButton) findViewById(R.id.btnPez_CrearMascota);

        mascota = (ImageView) findViewById(R.id.imgMascota_CrearMascota);
        Glide.with(this).load(R.drawable.gato_quieto).into(mascota);

        seleccionar("ninguna");

        btnGato.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("Gato");
                Glide.with(getApplicationContext()).load(R.drawable.gato_quieto).into(mascota);
            }
        });
        btnPerro.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("Perro");
                Glide.with(getApplicationContext()).load(R.drawable.perro_caminando).into(mascota);
            }
        });

    }

    public void seleccionar(String tipoDeMascota) {
        int blanco = getResources().getColor(R.color.Blanco);
        int rojo = getResources().getColor(R.color.rojo);

        int gato = blanco;
        int perro = blanco;
        int oso = blanco;
        int pez = blanco;

        switch(tipoDeMascota) {
            case "Gato":
                gato = rojo;
                break;
            case "Perro":
                perro = rojo;
                break;
            case "Oso":
                oso = rojo;
                break;
            case "Pez":
                pez = rojo;
                break;
            default:
                gato = rojo;
                break;
        }

        btnGato.setBackgroundTintList(ColorStateList.valueOf(gato));
        btnPerro.setBackgroundTintList(ColorStateList.valueOf(perro));
        btnOso.setBackgroundTintList(ColorStateList.valueOf(oso));
        btnPez.setBackgroundTintList(ColorStateList.valueOf(pez));
    }

}