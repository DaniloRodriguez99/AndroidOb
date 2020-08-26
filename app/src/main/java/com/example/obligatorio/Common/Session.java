package com.example.obligatorio.Common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;


public class Session {
    private SharedPreferences prefs;

    private static List<Historial>listaHistorial;

    public Session(Context cntx)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setIdUsuario(int id)
    {
        prefs.edit().putInt("IdUsuario",id).apply();
    }

    public int getIdUsuario()
    {
        Integer id = prefs.getInt("IdUsuario",-1);
        return id;
    }

    public void setMascota(int id)
    {
        prefs.edit().putInt("IdMascota",id).apply();
    }

    public int getMascota()
    {
        Integer id = prefs.getInt("IdMascota",-1);
        return id;
    }

    public void setEstadoTrivia(int count)
    {
        prefs.edit().putInt("CountTrivia",count).apply();
    }

    public int getEstadoTrivia()
    {
        return prefs.getInt("CountTrivia",0);
    }

    public void setIdTrivia(int id){ prefs.edit().putInt("IdTrivia",id).apply();}

    public int getIdTrivia()
    {
        return prefs.getInt("IdTrivia",0);
    }

    public void setPuntuacion(int pPuntuacion){prefs.edit().putInt("Puntuacion",pPuntuacion).apply();}

    public int getPuntuacion()
    {
        return prefs.getInt("Puntuacion",0);
    }

    public void setHistorial(Historial pHistorial)
    {
        listaHistorial.add(pHistorial);
    }
    public List<Historial>getHistorial()
    {
        return listaHistorial;
    }

    @SuppressLint("CommitPrefEdits")
    public void setIdPreguntas(int id1, int id2, int id3, int id4)
    {
        if(id1 != 0)
        {
            prefs.edit().putInt("IdPregunta1",id1).apply();
        }
        if(id2 != 0)
        {
            prefs.edit().putInt("IdPregunta2",id2).apply();;
        }
        if(id3 != 0)
        {
            prefs.edit().putInt("IdPregunta3",id3).apply();;
        }
        if(id4 != 0)
        {
            prefs.edit().putInt("IdPregunta4",id4).apply();;
        }
    }
    public int getIdPregunta(int num)
    {
        switch(num)
        {
            case 1:return prefs.getInt("IdPregunta1",0);

            case 2:return prefs.getInt("IdPregunta2",0);

            case 3:return prefs.getInt("IdPregunta3",0);

            case 4:return prefs.getInt("IdPregunta4",0);
        }
        return 0;

    }

    @SuppressLint("CommitPrefEdits")
    public void ComenzarTrivia()
    {
        listaHistorial = new ArrayList<>();
        prefs.edit().putInt("IdPregunta1",0).apply();
        prefs.edit().putInt("IdPregunta2",0).apply();
        prefs.edit().putInt("IdPregunta3",0).apply();
        prefs.edit().putInt("IdPregunta4",0).apply();
        prefs.edit().putInt("CountTrivia",0).apply();
        prefs.edit().putInt("Puntuacion",0).apply();
        listaHistorial.clear();
    }


}
