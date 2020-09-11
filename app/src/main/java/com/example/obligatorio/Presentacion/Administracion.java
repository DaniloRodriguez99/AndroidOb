package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.obligatorio.Common.Publicidad;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Administracion extends AppCompatActivity {
    public MyAdapterPublicidad adaptador;
    public ListView listaDePublicidades;
    private ArrayList<Publicidad>ListaDePublicidades;
    private Controladora controladora;
    private ImageButton btnCerrarListadoPublicidades;
    private FloatingActionButton fbaIrAltaPublicidad;
    public Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);
        c = getApplicationContext();
        controladora = new Controladora(getBaseContext());

        ListaDePublicidades = controladora.TraerPublicidades();
        listaDePublicidades = (ListView)findViewById(R.id.lstViewPublicidades);
        btnCerrarListadoPublicidades = (ImageButton)findViewById(R.id.btnCerrarListadoPublicidades);
        fbaIrAltaPublicidad = (FloatingActionButton)findViewById(R.id.btnIrAltaPublicidad);


        btnCerrarListadoPublicidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent i = new Intent(Administracion.this, MainActivity.class);
                startActivity(i);
            }
        });


        fbaIrAltaPublicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Administracion.this,Alta_Publicidad.class);
                startActivity(i);
            }
        });

        adaptador = new MyAdapterPublicidad(this, ListaDePublicidades);

        listaDePublicidades.setAdapter(adaptador);
    }
}