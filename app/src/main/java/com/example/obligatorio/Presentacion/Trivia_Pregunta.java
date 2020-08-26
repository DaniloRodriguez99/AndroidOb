package com.example.obligatorio.Presentacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.obligatorio.Common.Historial;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.R;

import java.util.ArrayList;

public class Trivia_Pregunta extends AppCompatActivity {

    private Session session;

    private TextView txtPregunta;

    private Button Respuesta1;
    private Button Respuesta2;
    private Button Respuesta3;

    private ArrayList<Respuesta> listaRespuestas;

    private Controladora controladora;

    private com.example.obligatorio.Common.Pregunta unaPregunta;
    private com.example.obligatorio.Common.Respuesta unaRespuesta;

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_trivia_pregunta);
        txtPregunta = (TextView)findViewById(R.id.txtPreguntaTrivia);
        View fondopreguntados = (View) findViewById(R.id.lytFondoPreguntados);

        session = new Session(getApplicationContext());

        Respuesta1 = (Button)findViewById(R.id.boton1);
        Respuesta2 = (Button)findViewById(R.id.boton2);
        Respuesta3 = (Button)findViewById(R.id.boton3);
        //loadDatos();

        controladora = new Controladora(getBaseContext());
        i = new Intent(this, Trivia_Ruleta.class);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        String categoria = extras.getString("keyCategoria");

        //Elige Fondo según Categoria
        assert categoria != null;
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
        switch (session.getEstadoTrivia())
        {
            case(0):
                int idPregunta1 = session.getIdPregunta(1);//Retorna 0 de default, y todas las otras session.getIdPregunta tambien retornan 0
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,session.getIdPregunta(2),session.getIdPregunta(3),session.getIdPregunta(4));
                session.setIdTrivia(controladora.TraerIdUltimaTrivia());

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(0);


                session.setIdPreguntas(unaPregunta.get_id(),0,0,0);
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(1):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                int idPregunta2 = session.getIdPregunta(2);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,session.getIdPregunta(3),session.getIdPregunta(4));

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(1);

                session.setIdPreguntas(0,unaPregunta.get_id(),0,0);
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(2):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                idPregunta2 = session.getIdPregunta(2);//Retorna el id de la Pregunta anterior
                int idPregunta3 = session.getIdPregunta(3);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,session.getIdPregunta(4));

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(2);

                session.setIdPreguntas(0,0,unaPregunta.get_id(),0);
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(3):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                idPregunta2 = session.getIdPregunta(2);//Retorna el id de la Pregunta anterior
                idPregunta3 = session.getIdPregunta(3);//Retorna el id de la Pregunta anterior
                int idPregunta4 = session.getIdPregunta(4);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,idPregunta4);

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(3);

                session.setIdPreguntas(0,0,0,unaPregunta.get_id());
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(4):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                idPregunta2 = session.getIdPregunta(2);//Retorna el id de la Pregunta anterior
                idPregunta3 = session.getIdPregunta(3);//Retorna el id de la Pregunta anterior
                idPregunta4 = session.getIdPregunta(4);//Retorna el id de la Pregunta anterior

                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,idPregunta4);

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(4);
               // Toast.makeText(this,"Última Pregunta!!",Toast.LENGTH_LONG).show();
                break;
        }
        Respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DarColorRespuesta();
                unaRespuesta = listaRespuestas.get(0);
                SetearPuntuacion();
                AgregarAlHistorial();
                startActivity(i);
            }
        });

        Respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DarColorRespuesta();
                unaRespuesta = listaRespuestas.get(1);
                SetearPuntuacion();
                AgregarAlHistorial();
                startActivity(i);
            }
        });

        Respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DarColorRespuesta();
                unaRespuesta = listaRespuestas.get(2);
                SetearPuntuacion();
                AgregarAlHistorial();
                startActivity(i);
            }
        });


    }

    public void SetearPregunta()//Setea el texto de la pregunta en la activity
    {
        txtPregunta.setText(unaPregunta.get_pregunta());
    }

    public void TraerRespuestas()//Trae las respuestas dependiendo la pregunta, y las setea en los botones
    {
        listaRespuestas = controladora.traerRespuestas(unaPregunta);

        Respuesta1.setText(listaRespuestas.get(0).get_respuesta());
        Respuesta2.setText(listaRespuestas.get(1).get_respuesta());
        Respuesta3.setText(listaRespuestas.get(2).get_respuesta());
    }

    public void SetearEstadoDeJugadas(int estadoSwitch)//Setea en la activity Preguntado el estado de preguntas respondidas.
    {
        switch(estadoSwitch)
        {
            case 0:i.putExtra("keyEstado","1/5");break;
            case 1:i.putExtra("keyEstado","2/5");break;
            case 2:i.putExtra("keyEstado","3/5");break;
            case 3:i.putExtra("keyEstado","4/5");break;
            case 4:i.putExtra("keyEstado","5/5");break;
        }
    }

    public void SetearPuntuacion()
    {
        if(unaRespuesta.is_correcta())
        {
            session.setPuntuacion(session.getPuntuacion() + 60);
        }
    }

    public void AgregarAlHistorial()
    {
        Historial unHistorial = new Historial();
        unHistorial.set_idRespuesta(unaRespuesta.get_id());
        unHistorial.set_idTrivia(session.getIdTrivia());
        session.setHistorial(unHistorial);
    }

    public void DarColorRespuesta()
    {
        if(listaRespuestas.get(0).is_correcta())
        {
            Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
            Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
        }
        if(listaRespuestas.get(1).is_correcta())
        {
            Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
            Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
        }
        if(listaRespuestas.get(2).is_correcta())
        {
            Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
        }
    }
}

