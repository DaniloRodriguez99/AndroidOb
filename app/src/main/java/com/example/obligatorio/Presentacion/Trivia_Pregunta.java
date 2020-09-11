package com.example.obligatorio.Presentacion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
    Dialog myDialog;

    private TextView txtPregunta;
    private TextView txtTimmer;

    private Button Respuesta1;
    private Button Respuesta2;
    private Button Respuesta3;
    private Respuesta noResponde;
    private ImageButton btnSaltearPregunta;
    private ImageButton btnSalirPreguntado;

    private CountDownTimer countDownTimer;
    private CountDownTimer count;

    private View fondopreguntados;
    private long tiempoRestante;

    private ArrayList<Respuesta> listaRespuestas;


    private MediaPlayer player;

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
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_trivia_pregunta);
        play();
        myDialog = new Dialog(this);
        tiempoRestante  = 60000;
        controladora = new Controladora(getBaseContext());
        i = new Intent(this, Trivia_Ruleta.class);
        session = new Session(getApplicationContext());

        txtPregunta = (TextView)findViewById(R.id.txtPreguntaTrivia);
        txtTimmer = (TextView)findViewById(R.id.txtTimmer);
        btnSaltearPregunta = (ImageButton)findViewById(R.id.btnSaltearPregunta);
        btnSalirPreguntado = (ImageButton)findViewById(R.id.btnCerrarPreguntado);

        Respuesta1 = (Button)findViewById(R.id.boton1);
        Respuesta2 = (Button)findViewById(R.id.boton2);
        Respuesta3 = (Button)findViewById(R.id.boton3);
        fondopreguntados = (View) findViewById(R.id.lytFondoPreguntados);


        countDownTimer = new CountDownTimer(tiempoRestante,1000) {
            @Override
            public void onTick(long l) {
                tiempoRestante = l/1000;
                if(tiempoRestante == 10) { txtTimmer.setBackground(getResources().getDrawable(R.drawable.timmernaranja)); }
                if(tiempoRestante == 5){txtTimmer.setBackground(getResources().getDrawable(R.drawable.timmermasnaranja));}
                if(tiempoRestante == 3){txtTimmer.setBackground(getResources().getDrawable(R.drawable.timmerrojo));}
            txtTimmer.setText(tiempoRestante+"");
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFinish() {
                unaRespuesta = noResponde;//Setea la respuesta final como no respondida
                AgregarAlHistorial();
                AltaTriviayHistorial();
                finish();
            }
        }.start();

        btnSaltearPregunta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                unaRespuesta = noResponde;//Setea la respuesta final como no respondida
                AgregarAlHistorial();
                AltaTriviayHistorial();
                finish();
            }
        });

        btnSalirPreguntado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarAdvertenciaSalir(btnSalirPreguntado);
            }
        });


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
                countDownTimer.cancel();

                Respuesta1.setOnClickListener(null);
                Respuesta2.setOnClickListener(null);
                Respuesta3.setOnClickListener(null);
                btnSaltearPregunta.setOnClickListener(null);

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
                countDownTimer.cancel();

                Respuesta1.setOnClickListener(null);
                Respuesta2.setOnClickListener(null);
                Respuesta3.setOnClickListener(null);
                btnSaltearPregunta.setOnClickListener(null);
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
                countDownTimer.cancel();

                Respuesta1.setOnClickListener(null);
                Respuesta2.setOnClickListener(null);
                Respuesta3.setOnClickListener(null);
                btnSaltearPregunta.setOnClickListener(null);
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
            case 0:i.putExtra("keyEstado","1/5"); setResult(Activity.RESULT_OK, i); break;
            case 1:i.putExtra("keyEstado","2/5"); setResult(Activity.RESULT_OK, i); break;
            case 2:i.putExtra("keyEstado","3/5"); setResult(Activity.RESULT_OK, i); break;
            case 3:i.putExtra("keyEstado","4/5"); setResult(Activity.RESULT_OK, i); break;
            case 4:i.putExtra("keyEstado","5/5"); setResult(Activity.RESULT_OK, i); break;
        }
    }

    public void SetearPuntuacion(long estadoDelContador)
    {
        if(unaRespuesta.is_correcta() && estadoDelContador >= 50000)
        {
            session.setPuntuacion(session.getPuntuacion() + 60);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 50000 && estadoDelContador >= 40000)
        {
            session.setPuntuacion(session.getPuntuacion() + 40);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 40000 && estadoDelContador >= 30000)
        {
            session.setPuntuacion(session.getPuntuacion() + 30);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 30000 && estadoDelContador >= 20000)
        {
            session.setPuntuacion(session.getPuntuacion() + 20);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 20000 && estadoDelContador >= 10000)
        {
            session.setPuntuacion(session.getPuntuacion() + 15);
        }
        if(unaRespuesta.is_correcta() && estadoDelContador < 10000)
        {
            session.setPuntuacion(session.getPuntuacion() + 10);
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
                stop();
                Correcta();
                Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
                count = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();

            } else {
                stop();
                Incorrecta();
                Respuesta1.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
                MostrarPublicidad(Respuesta1);
            }
            break;
            case 2:
                if (listaRespuestas.get(1).is_correcta()) {
                    stop();
                    Correcta();
                    Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
                    count = new CountDownTimer(2000,1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            finish();
                        }
                    }.start();
                } else {
                    stop();
                    Incorrecta();
                    Respuesta2.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
                    MostrarPublicidad(Respuesta2);
                }
                break;
            case 3:
            if (listaRespuestas.get(2).is_correcta()) {
                stop();
                Correcta();
                Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_correcta));
                count = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            } else {
                stop();
                Incorrecta();
                Respuesta3.setBackground(getResources().getDrawable(R.drawable.respuesta_incorrecta));
                MostrarPublicidad(Respuesta3);
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
            unaTrivia.set_fecha(LocalDateTime.now() + "");

            controladora.altaTrivia(unaTrivia);
            List<Historial> lista = session.getHistorial();
            unaTrivia.set_id(controladora.TraerIdUltimaTrivia());
            for(Historial h:lista)
            {
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

    public void play()
    {
        if(player == null)
        {
            player = MediaPlayer.create(this, R.raw.fondotimer);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void Correcta()
    {
        if(player == null)
        {
            player = MediaPlayer.create(this, R.raw.correct_answer);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void Incorrecta()
    {
        if(player == null)
        {
            player = MediaPlayer.create(this, R.raw.wrong_answer);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    public void stop()
    {
        stopPlayer();
    }

    public void stopPlayer()
    {
        if(player != null)
        {
            player.release();
            player = null;
        }
    }

    public void MostrarPublicidad(View v)
    {
        TextView txtCerrar;
        Button btnIrPublicidad;
        myDialog.setContentView(R.layout.publicidad_mostrada);
        txtCerrar = (TextView)myDialog.findViewById(R.id.txtCerrarPublicidad);
        btnIrPublicidad = (Button)myDialog.findViewById(R.id.btnVerPublicidad);

        txtCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                CountDownTimer contador = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        finish();
                    }
                }.start();
            }
        });

        btnIrPublicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://www.stackoverflow.com/"));
                startActivity(viewIntent);
            }
        });
        myDialog.show();
    }

    public void MostrarAdvertenciaSalir(View v)
    {
        Button btnAceptar;
        Button btnCancelar;
        myDialog.setContentView(R.layout.popupsaliraseleccionmascota);
        btnAceptar = (Button)myDialog.findViewById(R.id.btnAceptarSaliraMascota);
        btnCancelar = (Button)myDialog.findViewById(R.id.btnCancelarQuedarse);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Trivia_Pregunta.this, Mascotas.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}

