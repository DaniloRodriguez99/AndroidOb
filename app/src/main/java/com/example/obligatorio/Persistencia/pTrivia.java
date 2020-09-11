package com.example.obligatorio.Persistencia;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Dominio.Controladora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class pTrivia extends pConexion{
    public pTrivia(Context contexto){super(contexto);}

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
            this.ModificarDatos("insert into trivia(puntuacion,fechayhora,idusuario) values(" + pTrivia.get_puntuacion() + ",'" + pTrivia.get_fecha() +"'," + pTrivia.get_usuario().get_id() + ");");
            return true;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public ArrayList<Trivia>TraerTriviasDeUnUsuario(Usuario unUsuario)
    {
        ArrayList<Trivia>lista = new ArrayList<>();
        try
        {
                this.seleccionarDatos("select id,puntuacion,fechayhora from trivia where idusuario = " + unUsuario.get_id() + ";");

                while(!c.isAfterLast())
                {
                    Trivia unaTrivia = new Trivia();
                    unaTrivia.set_id(c.getInt(0));
                    unaTrivia.set_puntuacion(c.getInt(1));
                    unaTrivia.set_fecha(c.getString(2));
                    lista.add(unaTrivia);
                    c.moveToNext();
                }
                c.close();
                return lista;
        }catch(Exception ex)
        {
            throw  new Error(ex.getMessage());
        }
    }
}
