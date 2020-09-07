package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Publicidad;

public class pPublicidad extends pConexion{
    public pPublicidad(Context contexto){super(contexto);}

    public boolean AltaPublicidad(Publicidad pPublicidad)
    {
        try {
            this.ModificarDatos("insert into Publicidad(titulo,descripcion,imagen) values('" + pPublicidad.get_titulo() + "','" + pPublicidad.get_descripcion() + "'," + pPublicidad.get_imagen() + ";");

        return true;
        }catch(Exception ex){
            throw new Error(ex.getMessage());
        }
    }
}
