package com.example.obligatorio.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;

import java.util.ArrayList;

public class Pregunta extends AppCompatActivity {

    private String categoria;
    private TextView txtPregunta;
    com.example.obligatorio.Common.Pregunta unaPregunta;
    int id1 = 0;
    int id2 = 0;
    int id3 = 0;
    int id4 = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        txtPregunta = (TextView)findViewById(R.id.txtPreguntaTrivia);

        Controladora controladora = new Controladora(getBaseContext());
        Bundle extras = getIntent().getExtras();

        assert extras != null;
        categoria = extras.getString("keyCategoria");
        switch (count)
        {
            case(0):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                count++;
                id1 = unaPregunta.get_id();
                break;
            case(1):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                id2 = unaPregunta.get_id();
                break;
            case(3):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                count++;
                id3 = unaPregunta.get_id();
                break;
            case(4):
                unaPregunta = controladora.traerPreguntasTrivia(categoria,id1,id2,id3,id4);
                txtPregunta.setText(unaPregunta.get_pregunta());
                count++;
                id4 = unaPregunta.get_id();
                break;


        }






    }
}