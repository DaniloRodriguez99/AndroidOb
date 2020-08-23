package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Mascotas extends AppCompatActivity {

    private ImageView imgMascota;
    private TextView txtNombre;
    private ImageButton adelante;
    private ImageButton atras;
    private FloatingActionButton btnAgua;
    private FloatingActionButton btnComida;
    private FloatingActionButton btnEjercicio;
    private FloatingActionButton btnJugar;
    private FloatingActionButton btnCrear;
    private Button btnTrivia;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);

        /*Asignamos las variables a los elementos del xml*/

        imgMascota = (ImageView) findViewById(R.id.imgMascota_Mascotas);
        txtNombre = (TextView) findViewById(R.id.txtNombre_Mascotas);
        adelante = (ImageButton) findViewById(R.id.adelante_flecha_Mascotas);
        atras = (ImageButton) findViewById(R.id.atras_flecha_Mascotas);
        btnAgua = (FloatingActionButton) findViewById(R.id.btnAgua_Mascotas);
        btnComida = (FloatingActionButton) findViewById(R.id.btnComida_Mascotas);
        btnEjercicio = (FloatingActionButton) findViewById(R.id.btnEjercicio_Mascotas);
        btnJugar = (FloatingActionButton) findViewById(R.id.btnJugar_Mascotas);
        btnTrivia = (Button) findViewById(R.id.btnTrivia_Mascotas);
        btnCrear = (FloatingActionButton) findViewById(R.id.btnCrear_Mascotas);

        /*Inicializamos la session*/
        session = new Session(getApplicationContext());

        btnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mascotas.this, Preguntado.class);
                startActivity(i);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora(getApplicationContext());
                Mascota unaMasscota = new Mascota();
                unaMasscota = control.anteriorMascota();
                if (unaMasscota != null) {
                    CargarMascota(unaMasscota.get_nombre());
                }
            }
        });

        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora(getApplicationContext());
                Mascota unaMasscota = new Mascota();
                unaMasscota = control.siguienteMascota();
                if (unaMasscota != null) {
                    CargarMascota(unaMasscota.get_nombre());
                }
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CrearMascota.class);
                startActivity(i);
            }
        });

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null)
        {
            String nombre = parametros.getString("mascota");
            CargarMascota(nombre);
        }
        else
        {
            CargarMascota();
        }
    }

    private void CargarMascota()
    {
        Controladora control = new Controladora(getBaseContext());
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = control.BuscarMasctoasDeUnUsuario();
        CargarMascota(misMascotas.get(0).get_nombre());
    }

    private void CargarMascota(String mascota)
    {
       Controladora control = new Controladora(getBaseContext());
       Mascota miMascota = new Mascota();

        miMascota = control.BuscarMascotaEspecifica(mascota);
       if (miMascota != null)
       {
           txtNombre.setText(miMascota.get_nombre());
           CargarImagen(miMascota.get_tipo());
           session.setMascota(miMascota.get_id());
       }
    }

    public void CargarImagen(String tipo)
    {
        int gif;
        switch(tipo)
        {
            case "gato":
                gif = R.drawable.gato_quieto;
                break;
            case "perro":
                gif = R.drawable.perro_caminando;
                break;
            default:
                gif = R.drawable.gato_quieto;
                break;
        }
        Glide.with(this).load(gif).into(imgMascota);
    }
}