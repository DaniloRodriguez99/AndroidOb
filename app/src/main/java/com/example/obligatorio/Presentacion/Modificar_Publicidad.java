package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.obligatorio.Common.Publicidad;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;

public class Modificar_Publicidad extends AppCompatActivity {

    private EditText titulo;
    private EditText descripcion;
    private ImageButton btnCerrarModificarPublicidades;
    private Button btnModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__publicidad);

        titulo = (EditText) findViewById(R.id.txtTituloPublicidadAMod);
        descripcion = (EditText) findViewById(R.id.txtDescripcionPublicidadAMod);
        btnCerrarModificarPublicidades = (ImageButton)findViewById(R.id.btnCerrarModificarPublicidades);
        btnModificar = (Button)findViewById(R.id.btnModificarPublicidad);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        final int id = extras.getInt("keyPublicidadId",0);
        titulo.setText(extras.getString("keyPublicidadTitulo"));
        descripcion.setText(extras.getString("keyPublicidadDescripcion"));
        final byte[] imagen = extras.getByteArray("keyPublicidadImagen");
        btnCerrarModificarPublicidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Publicidad unaPublicidad = new Publicidad();

                unaPublicidad.set_id(id);
                unaPublicidad.set_titulo(titulo.getText().toString());
                unaPublicidad.set_descripcion(descripcion.getText().toString());
                unaPublicidad.set_imagen(imagen);
                Controladora unaControladora = new Controladora(getBaseContext());
                if(unaControladora.modificarPublicidad(unaPublicidad))
                {
                    Toast.makeText(Modificar_Publicidad.this,"Modificado con éxito", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Modificar_Publicidad.this,Administracion.class);
                    finish();
                    startActivity(i);
                }
            }
        });


    }
}