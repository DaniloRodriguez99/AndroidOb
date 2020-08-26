package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Pregunta;
import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.Dominio.Controladora;

import java.util.ArrayList;

public class pRespuesta extends pConexion{
    public pRespuesta(Context contexto){super(contexto);}

    public ArrayList<Respuesta> TraerRespuesta(Pregunta pPregunta)
    {
        Respuesta unaRespuesta;
        ArrayList<Respuesta> ListaRespuestas = new ArrayList<>();
        try
        {
            seleccionarDatos("select id,idpregunta,correcta,respuesta from respuesta where idpregunta =" + pPregunta.get_id() + ";");

            while(!c.isAfterLast())
            {
                unaRespuesta = new Respuesta();
                unaRespuesta.set_id(c.getInt(0));
                unaRespuesta.set_pregunta(pPregunta);
                if(c.getString(2).equals("1"))
                {
                    unaRespuesta.set_correcta(true);
                }else
                if(c.getString(2).equals("0")){unaRespuesta.set_correcta(false); }

                ListaRespuestas.add(unaRespuesta);
                unaRespuesta.set_respuesta(c.getString(3));
                c.moveToNext();
            }
            c.close();
            return ListaRespuestas;
        }catch(Exception ex)
        {
            throw new Error(ex.getMessage());
        }
    }
}

