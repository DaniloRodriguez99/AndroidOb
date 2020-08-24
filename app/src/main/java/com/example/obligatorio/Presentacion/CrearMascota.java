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

    protected ImageView atras;
    protected ImageView siguiente;

    protected EditText etNombre;

    protected int urlGif;
    protected int blanco;
    protected int rojo;
    protected String tipo = "gato";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_mascota);

        /*Asignamos los ImageView*/
        mascota = (ImageView) findViewById(R.id.imgMascota_CrearMascota);

        /*Asignamos los botones FAB*/
        btnGato = (FloatingActionButton) findViewById(R.id.btnGato_CrearMascota);
        btnPerro = (FloatingActionButton) findViewById(R.id.btnPerro_CrearMascota);
        btnOso = (FloatingActionButton) findViewById(R.id.btnOso_CrearMascota);
        btnPez = (FloatingActionButton) findViewById(R.id.btnPez_CrearMascota);
        fabCrear = (FloatingActionButton) findViewById(R.id.fabCrear_CrearMascota);

        /*Asignamos el EditText*/
        etNombre = (EditText) findViewById(R.id.etNombre_CrearMascota);

        /*Asignamos las flechas se siguiente y atras*/
        atras = (ImageView) findViewById(R.id.flecha_atras_CrearMascotas);
        siguiente = (ImageView) findViewById(R.id.flecha_siguiente_CrearMascotas);


        seleccionar("ninguna");

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flechas("atras");
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flechas("siguiente");
            }
        });


        fabCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora(getApplicationContext());
                String nombre = etNombre.getText().toString();
                if (control.altaMascota(nombre,tipo))
                {
                    Intent i = new Intent(CrearMascota.this, Mascotas.class);

                    i.putExtra("mascota", nombre);

                    startActivity(i);
                }
            }
        });

        btnGato.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("gato");
            }
        });

        btnPerro.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                seleccionar("perro");
            }
        });

        btnPez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionar("pez");
            }
        });

        btnOso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionar("oso");
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
                Glide.with(getApplicationContext()).load(R.drawable.gato_quieto).into(mascota);
                break;
            case "perro":
                perro = rojo;
                Glide.with(getApplicationContext()).load(R.drawable.perro_caminando).into(mascota);
                break;
            case "oso":
                oso = rojo;
                Glide.with(getApplicationContext()).load(R.drawable.oso_default).into(mascota);
                break;
            case "pez":
                pez = rojo;
                Glide.with(getApplicationContext()).load(R.drawable.pez_default).into(mascota);
                break;
            default:
                gato = rojo;
                Glide.with(getApplicationContext()).load(R.drawable.gato_quieto).into(mascota);
                break;
        }

        btnGato.setBackgroundTintList(ColorStateList.valueOf(gato));
        btnPerro.setBackgroundTintList(ColorStateList.valueOf(perro));
        btnOso.setBackgroundTintList(ColorStateList.valueOf(oso));
        btnPez.setBackgroundTintList(ColorStateList.valueOf(pez));
    }
    public void flechas(String direccion){
        String[] array;
        if (direccion.equals("siguiente")) {
            array = new String[]{"perro", "oso", "pez", "gato"};
        }
        else
        {
            array = new String[]{"pez", "gato", "perro", "oso"};
        }

        switch(tipo)
        {
            case "gato":
                seleccionar(array[0]);
                break;
            case "perro":
                seleccionar(array[1]);
                break;
            case "oso":
                seleccionar(array[2]);
                break;
            case "pez":
                seleccionar(array[3]);
                break;
        }
    }
}