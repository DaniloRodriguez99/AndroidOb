package com.example.obligatorio.Persistencia;

import android.content.Context;

public class pTrivia extends pConexion{
    public pTrivia(Context contexto){super(contexto);}

    public int ObtenerUltimoIdTrivia()
    {
        int id;
        try
        {
            this.seleccionarDatos("select id from historial order by id desc limit 1");

                id = c.getInt(0) + 1;

                c.close();

            return id;
        }catch(Exception ex)
        {
            throw new Error(ex.getMessage());
        }
    }
}
