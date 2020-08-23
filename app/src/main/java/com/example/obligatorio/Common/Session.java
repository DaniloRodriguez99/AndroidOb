package com.example.obligatorio.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.PreferenceChangeEvent;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx)
    {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setIdUsuario(int id)
    {
        prefs.edit().putInt("IdUsuario",id).commit();
    }

    public int getIdUsuario()
    {
        Integer id = prefs.getInt("IdUsuario",-1);
        return id;
    }

    public void setMascota(int id)
    {
        prefs.edit().putInt("IdMascota",id).commit();
    }

    public int getMascota()
    {
        Integer id = prefs.getInt("IdMascota",-1);
        return id;
    }
}
