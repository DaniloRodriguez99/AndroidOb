package com.example.obligatorio.Presentacion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;

import java.util.ArrayList;

public class Historial_Respuestas extends AppCompatActivity {

    private TextView txtPregunta1;
    private TextView txtPregunta2;
    private TextView txtPregunta3;
    private TextView txtPregunta4;
    private TextView txtPregunta5;

    private TextView txtRespuesta1;
    private TextView txtRespuesta2;
    private TextView txtRespuesta3;
    private TextView txtRespuesta4;
    private TextView txtRespuesta5;

    private ImageButton btnCerrarHistorial;

    private View lytRespuesta1;
    private View lytRespuesta2;
    private View lytRespuesta3;
    private View lytRespuesta4;
    private View lytRespuesta5;

    private Session session;
    private ArrayList<Respuesta> historialDeRespuestaYPregunta;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_respuestas);
        session = new Session(getApplicationContext());

        btnCerrarHistorial = (ImageButton)findViewById(R.id.btnCerrarHistorialRespuestas);

        txtPregunta1 = (TextView)findViewById(R.id.txtPregunta1);
        txtPregunta2 = (TextView)findViewById(R.id.txtPregunta2);
        txtPregunta3 = (TextView)findViewById(R.id.txtPregunta3);
        txtPregunta4 = (TextView)findViewById(R.id.txtPregunta4);
        txtPregunta5 = (TextView)findViewById(R.id.txtPregunta5);

        txtRespuesta1 = (TextView)findViewById(R.id.txtRespuesta1);
        txtRespuesta2 = (TextView)findViewById(R.id.txtRespuesta2);
        txtRespuesta3 = (TextView)findViewById(R.id.txtRespuesta3);
        txtRespuesta4 = (TextView)findViewById(R.id.txtRespuesta4);
        txtRespuesta5 = (TextView)findViewById(R.id.txtRespuesta5);

        lytRespuesta1 = (View)findViewById(R.id.lytRespuesta1);
        lytRespuesta2 = (View)findViewById(R.id.lytRespuesta2);
        lytRespuesta3 = (View)findViewById(R.id.lytRespuesta3);
        lytRespuesta4 = (View)findViewById(R.id.lytRespuesta4);
        lytRespuesta5 = (View)findViewById(R.id.lytRespuesta5);

        btnCerrarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TraerPreguntasYRespuestas();
        SetearPreguntasYRespuestas();


    }

    public void TraerPreguntasYRespuestas()
    {
        Controladora unaControladora = new Controladora(getBaseContext());
        Trivia unaTriviaSeleccionada = new Trivia();

        unaTriviaSeleccionada.set_id(session.getIdTrivia());

        historialDeRespuestaYPregunta = unaControladora.TraerPreguntaYRespuesta(unaTriviaSeleccionada);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void SetearPreguntasYRespuestas()
    {

        for(int i=0; i<historialDeRespuestaYPregunta.size();i++)
        {
            switch(i)
            {
                case 0: txtPregunta1.setText(historialDeRespuestaYPregunta.get(i).get_pregunta().get_pregunta());
                    txtRespuesta1.setText(historialDeRespuestaYPregunta.get(i).get_respuesta());
                    if(historialDeRespuestaYPregunta.get(i).is_correcta())
                    {
                        lytRespuesta1.getBackground().setTint(lytRespuesta1.getResources().getColor(R.color.verde));
                    }
                    break;

                case 1:txtPregunta2.setText(historialDeRespuestaYPregunta.get(i).get_pregunta().get_pregunta());
                    txtRespuesta2.setText(historialDeRespuestaYPregunta.get(i).get_respuesta());
                    if(historialDeRespuestaYPregunta.get(i).is_correcta())
                    {
                        lytRespuesta2.getBackground().setTint(lytRespuesta1.getResources().getColor(R.color.verde));
                    }
                    break;

                case 2:txtPregunta3.setText(historialDeRespuestaYPregunta.get(i).get_pregunta().get_pregunta());
                    txtRespuesta3.setText(historialDeRespuestaYPregunta.get(i).get_respuesta());
                    if(historialDeRespuestaYPregunta.get(i).is_correcta())
                    {
                        lytRespuesta3.getBackground().setTint(lytRespuesta1.getResources().getColor(R.color.verde));
                    }
                    break;

                case 3:txtPregunta4.setText(historialDeRespuestaYPregunta.get(i).get_pregunta().get_pregunta());
                    txtRespuesta4.setText(historialDeRespuestaYPregunta.get(i).get_respuesta());
                    if(historialDeRespuestaYPregunta.get(i).is_correcta())
                    {
                        lytRespuesta4.getBackground().setTint(lytRespuesta1.getResources().getColor(R.color.verde));
                    }
                    break;

                case 4:txtPregunta5.setText(historialDeRespuestaYPregunta.get(i).get_pregunta().get_pregunta());
                    txtRespuesta5.setText(historialDeRespuestaYPregunta.get(i).get_respuesta());
                    if(historialDeRespuestaYPregunta.get(i).is_correcta())
                    {
                        lytRespuesta5.getBackground().setTint(lytRespuesta1.getResources().getColor(R.color.verde));
                    }
                    break;
            }
        }


    }
}