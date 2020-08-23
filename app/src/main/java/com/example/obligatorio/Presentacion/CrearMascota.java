package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CrearMascota extends AppCompatActivity {

    protected ImageView mascota;
    protected FloatingActionButton fabCrear;
    protected FloatingActionButton btnPerro;
    protected FloatingActionButton btnGato;
    protected FloatingActionButton btnOso;
    protected FloatingActionButton btnPez;
    protected EditText etNombre;
    protected int urlGif;
    protected int blanco;
    protected int rojo;
    protected String tipo = "gato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        /*Asignamos los botones FAB*/
        btnGato = (FloatingActionButton) findViewById(R.id.btnGato_CrearMascota);
        btnPerro = (FloatingActionButton) findViewById(R.id.btnPerro_CrearMascota);
        btnOso = (FloatingActionButton) findViewById(R.id.btnOso_CrearMascota);
        btnPez = (FloatingActionButton) findViewById(R.id.btnPez_CrearMascota);
        fabCrear = (FloatingActionButton) findViewById(R.id.fabCrear_CrearMascota);

        /*Asignamos el EditText*/
        etNombre = (EditText) findViewById(R.id.etNombre_CrearMascota);

        fabCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora(getBaseContext());
                String nombre = etNombre.getText().toString();
                if (control.altaMascota(nombre,tipo))
                {
                    Intent i = new Intent(CrearMascota.this, Mascotas.class);

                    i.putExtra("mascota", nombre);

                    startActivity(i);
                }
            }
        });


        mascota = (ImageView) findViewById(R.id.imgMascota_CrearMascota);
        Glide.with(this).load(R.drawable.gato_quieto).into(mascota);

        seleccionar("ninguna");

        btnGato.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("gato");
                Glide.with(getApplicationContext()).load(R.drawable.gato_quieto).into(mascota);
            }
        });

        btnPerro.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("perro");
                Glide.with(getApplicationContext()).load(R.drawable.perro_caminando).into(mascota);
            }
        });

    }

    public void seleccionar(String tipoDeMascota) {
        int blanco = getResources().getColor(R.color.Blanco);
        int rojo = getResources().getColor(R.color.rojo);

        tipo = tipoDeMascota;

        int gato = blanco;
        int perro = blanco;
        int oso = blanco;
        int pez = blanco;

        switch(tipoDeMascota) {
            case "gato":
                gato = rojo;
                break;
            case "perro":
                perro = rojo;
                break;
            case "oso":
                oso = rojo;
                break;
            case "pez":
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