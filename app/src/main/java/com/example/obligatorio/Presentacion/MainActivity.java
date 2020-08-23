package com.example.obligatorio.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText txtUsuario;
    private EditText txtContra;
    private FloatingActionButton fab;
    private LinearLayout lytContent;
    private LinearLayout lytCheck;
    private LottieAnimationView check;

    private Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Layouts*/
        lytCheck = (LinearLayout) findViewById(R.id.lytLoginCheck);
        lytContent = (LinearLayout) findViewById(R.id.lytContent);

        /*Floatin Action Button*/
        fab = (FloatingActionButton) findViewById(R.id.fabLogin);

        /*Boton*/
        btnLogin = (Button) findViewById(R.id.btnLogin);

        /*Inputs*/
        txtUsuario = (EditText) findViewById(R.id.txtUsuarioLogin);
        txtContra = (EditText) findViewById(R.id.txtContraLogin);

        /*Animaciones*/
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        /*Lottie Animaciones*/
        check = (LottieAnimationView) findViewById(R.id.animacionCheck);

        /*Handler*/
        handler = new Handler();

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = txtUsuario.getText().toString().trim();
                String contra = txtContra.getText().toString().trim();
                final Controladora control = new Controladora(getBaseContext());
                Usuario unUsuario = new Usuario(usuario, contra);
                unUsuario = control.Login(unUsuario);
                if(unUsuario != null) {

                    lytContent.startAnimation(fadeOut);
                    lytContent.setVisibility(View.INVISIBLE);
                    lytCheck.startAnimation(fadeIn);
                    lytCheck.setVisibility(View.VISIBLE);

                    check.playAnimation();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lytContent.startAnimation(fadeIn);
                            lytContent.setVisibility(View.VISIBLE);
                            lytCheck.startAnimation(fadeOut);
                            lytCheck.setVisibility(View.INVISIBLE);
                            if(control.BuscarMasctoasDeUnUsuario().size() > 0) {
                                Intent i = new Intent(MainActivity.this, Mascotas.class);
                                startActivity(i);
                            }
                            else
                            {
                                Intent i = new Intent(MainActivity.this, CrearMascota.class);
                                startActivity(i);
                            }
                        }
                    }, 2500);

                }

            }
        });
    }
}