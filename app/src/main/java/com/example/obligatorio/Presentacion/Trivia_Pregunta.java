package com.example.obligatorio.Presentacion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.obligatorio.Common.Historial;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.R;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trivia_Pregunta extends AppCompatActivity {

    private Session session;

    private TextView txtPregunta;
    private TextView txtTimmer;

    private Button Respuesta1;
    private Button Respuesta2;
    private Button Respuesta3;
    private Respuesta noResponde;

    private CountDownTimer countDownTimer;
    private View fondopreguntados;
    private long tiempoRestante;

    private ArrayList<Respuesta> listaRespuestas;

    private int idPregunta1;
    private int idPregunta2;
    private int idPregunta3;
    private int idPregunta4;

    private String categoria;
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

        tiempoRestante  = 30000;
        controladora = new Controladora(getBaseContext());
        i = new Intent(this, Trivia_Ruleta.class);
        session = new Session(getApplicationContext());

        txtPregunta = (TextView)findViewById(R.id.txtPreguntaTrivia);
        txtTimmer = (TextView)findViewById(R.id.txtTimmer);

        Respuesta1 = (Button)findViewById(R.id.boton1);
        Respuesta2 = (Button)findViewById(R.id.boton2);
        Respuesta3 = (Button)findViewById(R.id.boton3);

        fondopreguntados = (View) findViewById(R.id.lytFondoPreguntados);


        countDownTimer = new CountDownTimer(tiempoRestante,1000) {
            @Override
            public void onTick(long l) {
                tiempoRestante = l/1000;
            txtTimmer.setText(tiempoRestante+"");
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFinish() {
                unaRespuesta = noResponde;//Setea la respuesta final como no respondida
                SetearPuntuacion(tiempoRestante*1000);
                AgregarAlHistorial();
                AltaTriviayHistorial();
                startActivity(i);
            }
        }.start();


        this.fondoSegunCategoria();


        //Según Estado de la Trivia Aplica Determinado llamado a la persistencia de la pregunta, aplica llamado a funciones que guardan datos en la session,
        // mediante pase por cada estado se guardara un Estado de la Trivia
        //y de un id extra de pregunta que no ira aplicado al llamado de traer pregunta en la persistencia. Al llegar al final se reinicia la instancia volviendo a poner todos los valores en 0 para asi.
        //poder iniciar otra trivia.
        switch (session.getEstadoTrivia())
        {
            case(0):
                idPregunta1 = session.getIdPregunta(1);//Retorna 0 de default, y todas las otras session.getIdPregunta tambien retornan 0
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,session.getIdPregunta(2),session.getIdPregunta(3),session.getIdPregunta(4));

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(0);


                session.setIdPreguntas(unaPregunta.get_id(),0,0,0);
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(1):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                idPregunta2 = session.getIdPregunta(2);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,session.getIdPregunta(3),session.getIdPregunta(4));//Trae Pregunta con 1 no repetida

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(1);

                session.setIdPreguntas(0,unaPregunta.get_id(),0,0);
                session.setEstadoTrivia(session.getEstadoTrivia() + 1);

                break;
            case(2):
                idPregunta1 = session.getIdPregunta(1);//Retorna el id de la Pregunta anterior
                idPregunta2 = session.getIdPregunta(2);//Retorna el id de la Pregunta anterior
                idPregunta3 = session.getIdPregunta(3);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,session.getIdPregunta(4));//Trae Pregunta con 2 no repetidas

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
                idPregunta4 = session.getIdPregunta(4);//Retorna 0 de default
                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,idPregunta4);//Trae Pregunta con 3 no repetidas

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

                unaPregunta = controladora.traerPreguntasTrivia(categoria,idPregunta1,idPregunta2,idPregunta3,idPregunta4);//Trae Pregunta con 4 no repetidas

                SetearPregunta();
                TraerRespuestas();
                SetearEstadoDeJugadas(4);
               // Toast.makeText(this,"Última Pregunta!!",Toast.LENGTH_LONG).show();
                break;
        }

        Respuesta1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DarColorRespuesta(1);
                unaRespuesta = listaRespuestas.get(0);
                SetearPuntuacion(tiempoRestante*1000);
                AgregarAlHistorial();
                AltaTriviayHistorial();
                startActivity(i);
                countDownTimer.cancel();
            }
        });

        Respuesta2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DarColorRespuesta(2);
                unaRespuesta = listaRespuestas.get(1);
                SetearPuntuacion(tiempoRestante*1000);
                AgregarAlHistorial();
                AltaTriviayHistorial();
                startActivity(i);
                countDownTimer.cancel();
            }
        });

        Respuesta3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DarColorRespuesta(3);
                unaRespuesta = listaRespuestas.get(2);
                SetearPuntuacion(tiempoRestante*1000);
                AgregarAlHistorial();
                AltaTriviayHistorial();
                startActivity(i);
                countDownTimer.cancel();
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
        noResponde = listaRespuestas.get(3);
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

    public void SetearPuntuacion(long estadoDelContador)
    {
        if(unaRespuesta.is_correcta() && estadoDelContador > 15000)
        {
            session.setPuntuacion(session.getPuntuacion() + 60);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 15000 && estadoDelContador > 5000)
        {
            session.setPuntuacion(session.getPuntuacion() + 30);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 5000)
        {
            session.setPuntuacion(session.getPuntuacion() + 15);
        }
    }

    public void AgregarAlHistorial()
    {
        Historial unHistorial = new Historial(unaRespuesta);
        session.setHistorial(unHistorial);
    }

    public void DarColorRespuesta(int botonPresionado)
    {
        switch(botonPresionado) {
            case 1:
            if (listaRespuestas.get(0).is_correcta()) {
                Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
            } else {
                Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            }
            break;
            case 2:
                if (listaRespuestas.get(1).is_correcta()) {
                    Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
                } else {
                    Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
                }
                break;
            case 3:
            if (listaRespuestas.get(2).is_correcta()) {
                Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
            } else {
                Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
            }
            break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AltaTriviayHistorial()
    {
        if(idPregunta1 != 0 && idPregunta2 != 0 && idPregunta3 != 0 && idPregunta4 != 0)//Controla estemos en la ultima instancia, ya que se setiaron previamente jugando el juego estos id.
        {
            Trivia unaTrivia = new Trivia();
            unaTrivia.set_puntuacion(session.getPuntuacion());
            unaTrivia.set_usuario(controladora.buscarUsuarioPorId(session.getIdUsuario()));
            unaTrivia.set_fecha(LocalDateTime.now());

            controladora.altaTrivia(unaTrivia);
            List<Historial> lista = session.getHistorial();
            for(Historial h:lista)
            {
                unaTrivia.set_id(controladora.TraerIdUltimaTrivia());
                h.set_Trivia(unaTrivia);
                controladora.altaHistorial(h);
            }
        }
    }

    public void fondoSegunCategoria()
    {
        //Carga la categoria desde Trivia_Ruleta
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        categoria = extras.getString("keyCategoria");
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
    }
}

