package com.example.obligatorio.Presentacion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Dominio.Controladora;
import com.example.obligatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    private FloatingActionButton fabVolver;

    private ListView listview;
    private ArrayList<Trivia>ArrayTrivias = new ArrayList<>();
     ArrayList<Integer> mPuntuacionesTrivias;
     ArrayList<String> mFechas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        TraerDatos();
        ModificarDatos();

        listview = (ListView)findViewById(R.id.lstViewHistorial);
        fabVolver = (FloatingActionButton)findViewById(R.id.fabvolverDesdeHistorial);

        fabVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //now create an adapter class
        MyAdapter adapter = new MyAdapter(this,mPuntuacionesTrivias,mFechas);
        listview.setAdapter(adapter);

        //now set item click on list view
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(Historial.this,"Trivia " + (position + 1),Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        ArrayList<Integer> rTriviapuntuacion;
        ArrayList<String> rTrivifecha;

        MyAdapter (Context c,ArrayList<Integer>triviapuntuacion,ArrayList<String> triviafecha)
        {
            super(c, R.layout.filashistorial,R.id.txtFechaTriviaHistorial,triviafecha);
            this.context = c;
            this.rTriviapuntuacion = triviapuntuacion;
            this.rTrivifecha = triviafecha;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View fila = layoutInflater.inflate(R.layout.filashistorial, parent, false);
            TextView txtNombreTriviaHistorial = fila.findViewById(R.id.txtNombreTriviaHistorial);
            TextView txtPuntuacionHistorial = fila.findViewById(R.id.txtPuntuacionTriviaHistorial);
            TextView txtFechaHistorial = fila.findViewById(R.id.txtFechaTriviaHistorial);

//now set our resource on views

            txtNombreTriviaHistorial.setText("Trivia " + (position + 1));
            txtPuntuacionHistorial.setText(rTriviapuntuacion.get(position) + "");
            txtFechaHistorial.setText(rTrivifecha.get(position));

            return fila;
        }
    }

    public void TraerDatos()
    {
        Controladora unaControladora = new Controladora(getBaseContext());
        ArrayTrivias = unaControladora.TraerTriviasdeunUsuario();
    }

    public void ModificarDatos()
    {
        mPuntuacionesTrivias = new ArrayList<>();
        mFechas = new ArrayList<>();
        String fecha;
        for(int i=0; i<ArrayTrivias.size();i++)
        {
            fecha = "Fecha:" + ArrayTrivias.get(i).get_fecha();
            fecha = fecha.replace("T", " Hora:");
            fecha = removeLastChar(fecha);
            ArrayTrivias.get(i).set_fecha(fecha);
            mFechas.add(ArrayTrivias.get(i).get_fecha());
            mPuntuacionesTrivias.add(ArrayTrivias.get(i).get_puntuacion());
        }
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() == 27) {
            return s;
        }
         s = s.substring(0,s.length() - 1);
        return removeLastChar(s);
    }
}