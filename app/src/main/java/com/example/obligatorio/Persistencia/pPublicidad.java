package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Publicidad;

import java.util.ArrayList;

public class pPublicidad extends pConexion{
    public pPublicidad(Context contexto){super(contexto);}

    public ArrayList<Publicidad>TraerPublicidades()
    {
        Publicidad unaPublicidad;
        ArrayList<Publicidad> lista = new ArrayList<>();
        try{
            this.seleccionarDatos("select id,titulo,descripcion,imagen from publicidad");
            while(!c.isAfterLast())
            {
                unaPublicidad = new Publicidad();
                unaPublicidad.set_id(c.getInt(0));
                unaPublicidad.set_titulo(c.getString(1));
                unaPublicidad.set_descripcion(c.getString(2));
                unaPublicidad.set_imagen(c.getBlob(3));
                lista.add(unaPublicidad);
                c.moveToNext();
            }
            c.close();
            return lista;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public Publicidad TraerPublicidadRandom()
    {
        Publicidad unaPublicidad;
        try{
            this.seleccionarDatos("select id,titulo,descripcion,imagen from publicidad order by RANDOM() LIMIT 1");

                unaPublicidad = new Publicidad();
                unaPublicidad.set_id(c.getInt(0));
                unaPublicidad.set_titulo(c.getString(1));
                unaPublicidad.set_descripcion(c.getString(2));
                unaPublicidad.set_imagen(c.getBlob(3));
            c.close();
            return unaPublicidad;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public boolean AltaPublicidad(Publicidad pPublicidad)
    {
        try {
            this.ModificarDatos("insert into publicidad(titulo,descripcion,imagen) values('" + pPublicidad.get_titulo() + "','" + pPublicidad.get_descripcion() + "','" + pPublicidad.get_imagen() + "');");

        return true;
        }catch(Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public boolean BajaPublicidad(Publicidad pPublicidad)
    {
        try {
            this.ModificarDatos("delete from publicidad where id = " + pPublicidad.get_id() + ";");

            return true;
        }catch(Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public boolean ModificarPublicidad(Publicidad pPublicidad)
    {
        try {
            this.ModificarDatos("update publicidad set titulo = '" + pPublicidad.get_titulo() + "',descripcion = '" + pPublicidad.get_descripcion() + "',imagen = '" + pPublicidad.get_imagen() + "' where id = " + pPublicidad.get_id() + ";");

            return true;
        }catch(Exception ex){
            throw new Error(ex.getMessage());
        }
    }
}
