package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.MainActivity;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Registro extends AppCompatActivity {

    private FloatingActionButton fab;
    private Button btnRegistro;
    private EditText usuario;
    private EditText contra1;
    private EditText contra2;
    private EditText email;
    private TextView msj;
    private LottieAnimationView checkAnimation;
    private LottieAnimationView uncheckAnimation;
    private LottieAnimationView checkloadAnimation;
    private LinearLayout content;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*Inputs*/
        usuario = (EditText) findViewById(R.id.inputUsuarioR);
        contra1 = (EditText) findViewById(R.id.inputContra1R);
        contra2 = (EditText) findViewById(R.id.inputContra2R);
        email = (EditText) findViewById(R.id.inputEmailR);

        /*Boton Flotante*/
        fab = (FloatingActionButton) findViewById(R.id.fabRegistro);

        /*Animation View*/
        checkAnimation = (LottieAnimationView) findViewById(R.id.checkAnimacion);
        uncheckAnimation = (LottieAnimationView) findViewById(R.id.notcheckAnimacion);
        checkloadAnimation = (LottieAnimationView) findViewById(R.id.checkload);

        /*Animaciones*/
        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        /*Linear Layout*/
        content = (LinearLayout) findViewById(R.id.lytContent);


        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora();

                if (contra1.getText().toString().trim().equals(contra2.getText().toString().trim())) {

                    String user = usuario.getText().toString();
                    String contra = contra1.getText().toString();
                    String mail = email.getText().toString();

                    Usuario unUsuario = new Usuario(user, contra, mail);

                    if (control.AltaUsuario(unUsuario)) {

                        content.setVisibility(View.INVISIBLE);
                        checkloadAnimation.setVisibility(View.VISIBLE);
                        checkloadAnimation.playAnimation();

                        final Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                checkloadAnimation.setVisibility(View.INVISIBLE);
                                checkAnimation.setVisibility(View.VISIBLE);
                                checkAnimation.playAnimation();
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        content.setVisibility(View.VISIBLE);
                                        content.startAnimation(fadeIn);
                                        checkAnimation.setVisibility(View.INVISIBLE);
                                    }
                                }, 2500);
                            }
                        }, 1000);
                    }
                    else{
                            content.setVisibility(View.INVISIBLE);
                            checkloadAnimation.setVisibility(View.VISIBLE);
                            checkloadAnimation.playAnimation();

                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkloadAnimation.setVisibility(View.INVISIBLE);
                                    uncheckAnimation.setVisibility(View.VISIBLE);
                                    uncheckAnimation.playAnimation();
                                    h.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            content.setVisibility(View.VISIBLE);
                                            content.startAnimation(fadeIn);
                                            uncheckAnimation.setVisibility(View.INVISIBLE);
                                        }
                                    }, 2500);
                                }
                            }, 1000);
                        }
                    }
                }
            });
        }
    }