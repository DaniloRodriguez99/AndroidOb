package com.example.obligatorio.Presentacion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Mascotas extends AppCompatActivity {

    Dialog myDialog;
    private ImageView imgMascota;
    private TextView txtNombre;
    private ImageButton adelante;
    private ImageButton atras;
    private FloatingActionButton btnAgua;
    private FloatingActionButton btnComida;
    private FloatingActionButton btnEjercicio;
    private FloatingActionButton btnJugar;
    private FloatingActionButton btnCrear;
    private FloatingActionButton btnSalir;
    private Button btnTrivia;
    private Session session;
    private LinearLayout lytContent;
    private LinearLayout lytMuerte;
    private Animation fadeOut;
    private Animation fadeIn;

    private int idconstante = 0;

    private TextView msjMuerte;
    private Button btnVolver;
    private ImageView imgMascotaMuerte;


    private boolean CounterRunning = false;

    private CountDownTimer countdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);

        /*Asignamos las variables a los elementos del xml*/

        myDialog = new Dialog(this);
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
        btnSalir = (FloatingActionButton)findViewById(R.id.btn_SalirALogin);
        lytContent = (LinearLayout) findViewById(R.id.lytContenido_Mascotas);


        msjMuerte = (TextView) findViewById(R.id.msjMuerte_Mascotas);
        lytMuerte = (LinearLayout) findViewById(R.id.mensajeMuerte_Mascotas);
        btnVolver = (Button) findViewById(R.id.btnVolver_Mascota);
        imgMascotaMuerte = (ImageView) findViewById(R.id.imgMuerte_Mascotas);

        /*Inicializamos la session*/
        session = new Session(getApplicationContext());

        /*Animaciones*/
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lytMuerte.startAnimation(fadeOut);
                lytMuerte.setVisibility(View.INVISIBLE);
                lytContent.startAnimation(fadeIn);
                lytContent.setVisibility(View.VISIBLE);
                CargarMascotas();
            }
        });


        btnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Mascotas.this, Trivia_Ruleta.class);
                session.ComenzarTrivia();
                startActivity(i);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controladora control = new Controladora(getApplicationContext());
                Mascota unaMasscota;
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

        Controladora control = new Controladora(getApplicationContext());
        if (control.NroMascotasDeUnUsuario() > 3) {
            btnCrear.setVisibility(View.INVISIBLE);
        } else {
            btnCrear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), CrearMascota.class);
                    startActivity(i);
                }
            });
        }

        btnEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gif = HacerEjercicio();
                Handler h = new Handler();
                Glide.with(getApplicationContext()).load(gif).into(imgMascota);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CargarMascota();
                    }
                }, 10000);
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gif = Jugar();
                Handler h = new Handler();
                Glide.with(getApplicationContext()).load(gif).into(imgMascota);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CargarMascota();
                    }
                }, 10000);
            }
        });

        btnComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gif = Comer();
                Controladora control = new Controladora(getApplicationContext());
                control.DarComida();
                CounterRunning = false;
                Handler h = new Handler();
                Glide.with(getApplicationContext()).load(gif).into(imgMascota);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CargarMascotas();
                    }
                }, 5000);
            }
        });

        btnAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int gif = Tomar();
                Controladora control = new Controladora(getApplicationContext());
                control.DarAgua();
                CounterRunning = false;
                Handler h = new Handler();
                Glide.with(getApplicationContext()).load(gif).into(imgMascota);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CargarMascota();
                    }
                }, 5000);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button aceptarSalir;
                Button cancelarSalir;

                myDialog.setContentView(R.layout.popupcerrarsesion);
                aceptarSalir = (Button) myDialog.findViewById(R.id.btnAceptarCerrarSesion);
                cancelarSalir = (Button) myDialog.findViewById(R.id.btnCancelarSalirSesion);

                aceptarSalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Mascotas.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                cancelarSalir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myDialog.dismiss();
                    }
                });
                myDialog.show();

                /*

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

                 */
            }
        });

        Bundle parametros = this.getIntent().getExtras();
        if (parametros != null) {
            String nombre = parametros.getString("mascota");
            CargarMascota(nombre);
        } else {
            CargarMascotas();
        }
    }

    private void TiempoDeVida() {
        final Controladora control = new Controladora(getApplicationContext());
        long tiempo = 180000 - control.TiempoDeVida();
        final Mascota mascotaActual = control.BuscarMascotaEspecifica(session.getMascota());
        final Animation fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        final Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        CounterRunning = true;

        if (tiempo > 0 && !mascotaActual.get_nombre().equals(null)) {
            countdownTimer = new CountDownTimer(tiempo, 1000) {
                boolean notificado = false;
                @Override
                public void onTick(long l) {

                    if (!CounterRunning)
                    {
                        this.cancel();
                    }
                    if (l < 170000 && !notificado) {
                        crearCanalDeNotificaciones();
                        Notificacion(mascotaActual, l);
                        notificado = true;
                    }
                }

                @Override
                public void onFinish() {
                    lytContent.startAnimation(fadeOut);
                    lytContent.setVisibility(View.INVISIBLE);
                    lytMuerte.startAnimation(fadeIn);
                    lytMuerte.setVisibility(View.VISIBLE);

                    CargarImagen(mascotaActual.get_tipo());
                    msjMuerte.setText("Su mascota " + mascotaActual.get_nombre() + " a.... fallecido");
                    control.bajaMascota(mascotaActual.get_id());
                }
            }.start();
        } else {
            lytContent.startAnimation(fadeOut);
            lytContent.setVisibility(View.INVISIBLE);
            lytMuerte.startAnimation(fadeIn);
            lytMuerte.setVisibility(View.VISIBLE);


            CargarImagen(mascotaActual.get_tipo());
            msjMuerte.setText("Su mascota " + mascotaActual.get_nombre() + " a.... fallecido");
            control.bajaMascota(mascotaActual.get_id());
        }
    }


    private void CargarMascotas() {
        Controladora control = new Controladora(getBaseContext());
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = control.BuscarMascotasDeUnUsuario();
        if (misMascotas.size() > 0) {
            CargarMascota(misMascotas.get(0).get_nombre());
        } else {
            Intent i = new Intent(this, CrearMascota.class);
            startActivity(i);
        }
    }

    private void CargarMascota() {
        Controladora control = new Controladora(getBaseContext());
        Mascota mascota = control.BuscarMascotaEspecifica(session.getMascota());
        CargarMascota(mascota.get_nombre());
    }

    private void CargarMascota(String mascota) {
        Controladora control = new Controladora(getBaseContext());
        Mascota miMascota = new Mascota();

        miMascota = control.BuscarMascotaEspecifica(mascota);
        if (miMascota != null) {
            txtNombre.setText(miMascota.get_nombre());
            CargarImagen(miMascota.get_tipo());
            session.setMascota(miMascota.get_id());
            TiempoDeVida();
        }
    }

    public void CargarImagen(String tipo) {
        int gif;
        switch (tipo) {
            case "gato":
                gif = R.drawable.gato_quieto;
                break;
            case "perro":
                gif = R.drawable.perro_default;
                break;
            case "oso":
                gif = R.drawable.oso_default;
                break;
            case "pez":
                gif = R.drawable.pez_default;
                break;
            default:
                gif = R.drawable.gato_quieto;
                break;
        }
        Glide.with(this).load(gif).into(imgMascota);
        Glide.with(this).load(gif).into(imgMascotaMuerte);
    }

    public int HacerEjercicio() {
        Controladora control = new Controladora(getApplicationContext());
        Mascota mascota = control.BuscarMascotaEspecifica(session.getMascota());
        int gif;
        switch (mascota.get_tipo()) {
            case "gato":
                gif = R.drawable.gato_ejercicio;
                break;
            case "perro":
                gif = R.drawable.perro_ejercicio;
                break;
            case "oso":
                gif = R.drawable.oso_ejercicio;
                break;
            case "pez":
                gif = R.drawable.pez_ejercicio;
                break;
            default:
                gif = R.drawable.gato_ejercicio;
                break;
        }
        return gif;
    }

    public int Jugar() {
        Controladora control = new Controladora(getApplicationContext());
        Mascota mascota = control.BuscarMascotaEspecifica(session.getMascota());
        int gif;
        switch (mascota.get_tipo()) {
            case "gato":
                gif = R.drawable.gato_jugando;
                break;
            case "perro":
                gif = R.drawable.perro_jugando;
                break;
            case "oso":
                gif = R.drawable.oso_jugando;
                break;
            case "pez":
                gif = R.drawable.pez_jugando;
                break;
            default:
                gif = R.drawable.gato_jugando;
                break;
        }
        return gif;
    }

    public int Comer() {
        Controladora control = new Controladora(getApplicationContext());
        Mascota mascota = control.BuscarMascotaEspecifica(session.getMascota());
        int gif;
        switch (mascota.get_tipo()) {
            case "gato":
                gif = R.drawable.gato_comiendo;
                break;
            case "perro":
                gif = R.drawable.perro_comiendo;
                break;
            case "oso":
                gif = R.drawable.oso_comiendo;
                break;
            case "pez":
                gif = R.drawable.pez_comiendo;
                break;
            default:
                gif = R.drawable.gato_comiendo;
                break;
        }
        return gif;
    }

    public int Tomar() {
        Controladora control = new Controladora(getApplicationContext());
        Mascota mascota = control.BuscarMascotaEspecifica(session.getMascota());

        int gif;
        switch (mascota.get_tipo()) {
            case "gato":
                gif = R.drawable.gato_tomando;
                break;
            case "perro":
                gif = R.drawable.perro_tomando;
                break;
            case "oso":
                gif = R.drawable.oso_tomando;
                break;
            case "pez":
                gif = R.drawable.pez_tomando;
                break;
            default:
                gif = R.drawable.gato_tomando;
                break;
        }
        return gif;
    }

    public void crearCanalDeNotificaciones() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            {
                CharSequence nombre = "Notificacion";
                NotificationChannel canal = new NotificationChannel("notificacion", nombre, NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager notificacion = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificacion.createNotificationChannel(canal);
            }
        }
        idconstante++;
    }

    public void Notificacion(Mascota mascota, long l) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "notificacion");
            builder.setSmallIcon(R.drawable.ic_notify_logo);
            builder.setColor(Color.RED);
            builder.setContentTitle("Hey!!! tu mascota " + mascota.get_nombre() + " esta por morir");
            builder.setContentText("Tu mascota " + mascota.get_nombre() + " le quedan " + l/1000 + " segundos de vida");
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManagerCompat notification = NotificationManagerCompat.from(getApplicationContext());
            notification.notify(idconstante, builder.build());

        idconstante++;
    }
}