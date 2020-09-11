package com.example.obligatorio.Presentacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.obligatorio.Common.Publicidad;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Modificar_Publicidad extends AppCompatActivity {

    private EditText titulo;
    private EditText descripcion;
    private ImageButton btnCerrarModificarPublicidades;
    private ImageView imvPublicidadAModificar;
    private ImageButton btnRotarImagen;
    private FloatingActionButton btnSeleccionarImagen;
    private Button btnModificar;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar__publicidad);

        titulo = (EditText) findViewById(R.id.txtTituloPublicidadAMod);
        descripcion = (EditText) findViewById(R.id.txtDescripcionPublicidadAMod);
        btnCerrarModificarPublicidades = (ImageButton)findViewById(R.id.btnCerrarModificarPublicidades);
        btnModificar = (Button)findViewById(R.id.btnModificarPublicidad);
        imvPublicidadAModificar = (ImageView)findViewById(R.id.imvPublicidadModificar);
        btnRotarImagen = (ImageButton)findViewById(R.id.btnGirarPublicidadModificar);
        btnSeleccionarImagen = (FloatingActionButton)findViewById(R.id.btnSeleccionarImagenAModificar);

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
                byte[] Imagen = imagentoByte(imvPublicidadAModificar);
                unaPublicidad.set_imagen(Imagen);
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

        btnRotarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imvPublicidadAModificar.setRotation(imvPublicidadAModificar.getRotation() + 90);
            }
        });

        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        Modificar_Publicidad.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });
        /*
        tnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                Alta_Publicidad.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });
         */


    }

    private byte[] imagentoByte(ImageView imagen) {
        Bitmap bitmap = ((BitmapDrawable)imagen.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No tienes Permisos para subir imagenes",Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override//ACA SE SETEA LA IMAGEN SELECCIONADA
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imvPublicidadAModificar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}