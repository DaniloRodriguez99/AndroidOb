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
import android.view.WindowManager;
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

public class Alta_Publicidad extends AppCompatActivity {


    private FloatingActionButton btnSeleccionarImagen;
    private Button btnAltaPublicidad;
    private ImageButton btnGirarPublicidad;
    private ImageButton btnCerrarAltaPublicidad;
    private EditText txtTitulo;
    private EditText txtDescripcion;
    private ImageView imagen;
    Controladora unaControladora;
    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__publicidad);
        btnSeleccionarImagen = (FloatingActionButton)findViewById(R.id.btnSeleccionarImagen);
        btnAltaPublicidad = (Button)findViewById(R.id.btnAltaPublicidad);
        btnGirarPublicidad = (ImageButton)findViewById(R.id.btnGirarPublicidad);
        btnCerrarAltaPublicidad = (ImageButton)findViewById(R.id.btnCerrarAltaPublicidad);
        txtTitulo = (EditText)findViewById(R.id.txtTituloPublicidad);
        txtDescripcion = (EditText)findViewById(R.id.txtDescripcionPublicidad);
        imagen = (ImageView)findViewById(R.id.imvPublicidad);

        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                Alta_Publicidad.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        btnCerrarAltaPublicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGirarPublicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagen.setRotation(imagen.getRotation() + 90);
            }
        });

        btnAltaPublicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    unaControladora = new Controladora(getBaseContext());
                    String Titulo = txtTitulo.getText().toString().trim();
                    String Descripcion = txtDescripcion.getText().toString().trim();
                    byte[] Imagen = imagentoByte(imagen);
                    Publicidad unaPublicidad = new Publicidad(Titulo,Descripcion,Imagen);
                    if(unaControladora.AltaPublicidad(unaPublicidad) && !Titulo.equals("") && !Descripcion.equals("")) {
                        Toast.makeText(getBaseContext(), "Agregada con éxito", Toast.LENGTH_SHORT).show();
                        txtTitulo.setText("");
                        txtDescripcion.setText("");
                        imagen.setImageResource(R.drawable.imgaqui);
                    }else{Toast.makeText(getBaseContext(), "No se ha podido agregar, asegurese de estar rellenando bien los campos", Toast.LENGTH_SHORT).show();}
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}