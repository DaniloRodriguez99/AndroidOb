package com.example.obligatorio.Presentacion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;

public class Pregunta extends AppCompatActivity {

    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String Saved_id1 = "shared_id1";
    private static final String Saved_id2 = "shared_id2";
    private static final String Saved_id3 = "shared_id3";
    private static final String Saved_id4 = "shared_id4";
    private static final String Saved_id5 = "shared_id5";
    private static final String Saved_Count = "shared_count";

    private String categoria;
    private TextView txtPregunta;
    private View fondopreguntados;

    private Button boton1;
    private Button boton2;
    private Button boton3;

    private com.example.obligatorio.Common.Pregunta unaPregunta;
    private int id1;
    private int id2;
    private int id3;
    private int id4;
    private int id5;
    private int count;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pregunta);
        txtPregunta = (TextView)findViewById(R.id.txtPreguntaTrivia);
        fondopreguntados = (View)findViewById(R.id.lytFondoPreguntados);
        boton1 = (Button)findViewById(R.id.boton1);
        boton2 = (Button)findViewById(R.id.boton2);
        boton3 = (Button)findViewById(R.id.boton3);
        loadDatos();
        Controladora controladora = new Controladora(getBaseContext());
        i = new Intent(this,Preguntado.class);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        categoria = extras.getString("keyCategoria");

        //Elige Fondo según Categoria
        switch(categoria)
        {
            case("Perro"):
                fondopreguntados.setBackgroundResource(R.drawable.preguntados_fondoperro);
                break;
            case("Gato"):
                fondopreguntados.setBackgroundResource(R.drawable.preguntados_fondogato);
                break;
            case("Oso"):
                fondopreguntados.setBackgroundResource(R.drawable.preguntados_fondooso);
                break;
            case("Pescado"):
                fondopreguntados.setBackgroundResource(R.drawable.preguntados_fondopescado);
                break;

        }

        //Según Contador Aplica Determinado llamado a la persistencia de la pregunta, aplica llamado a funcion guardar datos, mediante pase por cada contador se guardara un contador++
        //y un id extra de pregunta que no ira. Al llegar al final se reinicia la instancia volviendo a poner todos los valores en 0.
        switch (count)
        {
            case(0):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id1 = unaPregunta.get_id();
                saveDatos(count);
                i.putExtra("keyEstado","1/5");
                break;
            case(1):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id2 = unaPregunta.get_id();
                saveDatos(count);
                i.putExtra("keyEstado","2/5");
                break;
            case(2):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id3 = unaPregunta.get_id();
                saveDatos(count);
                i.putExtra("keyEstado","3/5");
                break;
            case(3):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id4 = unaPregunta.get_id();
                i.putExtra("keyEstado","4/5");
                saveDatos(count);
                break;
            case(4):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id5 = unaPregunta.get_id();
                saveDatos(count);
                ReiniciarInstancia();
                i.putExtra("keyEstado","5/5");
                Toast.makeText(this,"Última Pregunta!!",Toast.LENGTH_LONG).show();
                break;
        }
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(i);
            }
        });
    }
    public void saveDatos(int num) {
        switch(num)
        {
            //Guarda Id de la primera pregunta y contador +1, para que cuando pase a la siguiente activity y vuelva no se pierda el contexto
            case(0):
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Saved_id1,id1);
                editor.putInt(Saved_Count,num + 1);
                editor.apply();
                break;
            case(1):
                SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putInt(Saved_id2,id2);
                editor2.putInt(Saved_Count,num + 1);
                editor2.apply();
                break;
            case(2):
                SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedPreferences3.edit();
                editor3.putInt(Saved_id3,id3);
                editor3.putInt(Saved_Count,num + 1);
                editor3.apply();
                break;
            case(3):
                SharedPreferences sharedPreferences4 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor4 = sharedPreferences4.edit();
                editor4.putInt(Saved_id4,id4);
                editor4.putInt(Saved_Count,num + 1);
                editor4.apply();
                break;
            case(4):
                SharedPreferences sharedPreferences5 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor5 = sharedPreferences5.edit();
                editor5.putInt(Saved_id5,id5);
                editor5.putInt(Saved_Count,num + 1);
                editor5.apply();
                break;

        }

    }

    public void loadDatos(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        id1 = sharedPreferences.getInt(Saved_id1,0);
        id2 = sharedPreferences.getInt(Saved_id2,0);
        id3 = sharedPreferences.getInt(Saved_id3,0);
        id4 = sharedPreferences.getInt(Saved_id4,0);
        id5 = sharedPreferences.getInt(Saved_id5,0);
        count = sharedPreferences.getInt(Saved_Count,0);
    }

    public void ReiniciarInstancia() {
        SharedPreferences sharedPreferences5 = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences5.edit();
        editor.putInt(Saved_id1,0);
        editor.putInt(Saved_id2,0);
        editor.putInt(Saved_id3,0);
        editor.putInt(Saved_id4,0);
        editor.putInt(Saved_id5,0);
        editor.putInt(Saved_Count,0);
        editor.apply();
    }
}

