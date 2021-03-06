package com.example.obligatorio.Presentacion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obligatorio.Common.Session;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Random;

public class Trivia_Ruleta extends AppCompatActivity implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    int intNumber = 4;
    int NumeroTipo;
    long lngDegrees = 0;
    private TextView lblEstado;
    private TextView lblPuntuacion;

    private FloatingActionButton btnvolverDesdeRuleta;
    private FloatingActionButton btnGirar;
    private FloatingActionButton btnHistorial;

    private Session session;

    private Intent i;
    private Intent Data = null;
    private Bundle extras;

    ImageView imageRoulette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_ruleta);

        session = new Session(getApplicationContext());
        lblEstado = (TextView)findViewById(R.id.lblVecesTrivia);
        lblPuntuacion = (TextView)findViewById(R.id.lblPuntuacion_Trivia);


        btnHistorial = (FloatingActionButton)findViewById(R.id.fabHistorial);
        btnGirar = (FloatingActionButton)findViewById(R.id.btnGirar);
        btnvolverDesdeRuleta = (FloatingActionButton)findViewById(R.id.btnvolverDesdeRuleta);
        imageRoulette = (ImageView)findViewById(R.id.imgRuleta);

        btnvolverDesdeRuleta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Trivia_Ruleta.this,Mascotas.class);
                startActivity(i);
            }
        });

        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Trivia_Ruleta.this, Historial_Puntuaciones.class);
                startActivity(i);
            }
        });
    }

    //Al iniciarse la animacion se setea el boolean auxiliar en falso.
    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        btnGirar.setVisibility(View.VISIBLE);
    }


    //Se Calcula el valor obtenido, y se setea del boolean auxiliar en true
    @Override
    public void onAnimationEnd(Animation animation) {

        this.blnButtonRotation = true;
        this.NumeroTipo = (int)(((double)this.intNumber) - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.intNumber))));
        /*Toast toast = Toast.makeText(this,TipoPregunta(NumeroTipo) ,Toast.LENGTH_SHORT);
        toast.setGravity(49,0,0);
        toast.show();*/
        btnGirar.setVisibility(View.VISIBLE);

        i = new Intent(this, Trivia_Pregunta.class);
        i.putExtra("keyCategoria",TipoPregunta(NumeroTipo));
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//Al matar a la Activity Trivia_Pregunta se ejecuta esta funcion que trae datos de la anterior.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1)
        {
            if(resultCode == RESULT_OK)
            {
                lblEstado.setText(data.getStringExtra("keyEstado"));
                lblPuntuacion.setText("Puntuacion: " + session.getPuntuacion() + " puntos");
                Data = data;
            }
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //Ejecucion del giro mediante el boton Si el boolean es true osea(una vez terminada esta).
    public void onClickButtonRotation(View v)
    {
        if(this.blnButtonRotation)
        {
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees,(float)(this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);
            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

            if(Data != null && Data.getStringExtra("keyEstado").equals("5/5")){
                lblEstado.setText("0/5");
                lblPuntuacion.setText("Puntuacion: 0 puntos");
                session.ComenzarTrivia(); //Reinicia los valores, para si juega otra inicie todo de 0
            }
        }
    }

    public String TipoPregunta(int num)
    {
        switch (num) {
            case 1:
                return "Perro";
            case 2:
                return "Pescado";
            case 3:
                return "Oso";
            case 4:
                return "Gato";
            default:
                return null;
        }
    }
}