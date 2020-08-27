package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Dominio.Controladora;

public class pTrivia extends pConexion{
    public pTrivia(Context contexto){super(contexto);}
    private Controladora controladora;
    public int ObtenerUltimoIdTrivia()
    {
        int id;
        try
        {
            this.seleccionarDatos("select id from trivia order by id desc limit 1");

                id = c.getInt(0);

                c.close();

            return id;
        }catch(Exception ex)
        {
            throw new Error(ex.getMessage());
        }
    }

    public boolean AltaTrivia(Trivia pTrivia)
    {

        try
        {
            this.ModificarDatos("insert into trivia(fechayhora,idusuario) values('" + pTrivia.get_fecha() +"'," + pTrivia.get_usuario().get_id() + ");");
            return true;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }
    }
}
