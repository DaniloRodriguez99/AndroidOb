package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Historial;
import com.example.obligatorio.Common.Pregunta;
import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Common.Usuario;

import java.util.ArrayList;


public class pHistorial extends pConexion{
    public pHistorial(Context contexto){super(contexto);}

    public boolean altaHistorial(Historial pHistorial)
    {
        try {
            this.ModificarDatos("insert into historial(idtrivia,idrespuesta) values(" + pHistorial.get_Trivia().get_id() + "," + pHistorial.get_Respuesta().get_id() + ");");
            return true;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }
    }

    public ArrayList<Respuesta> TraerPreguntaYRespuestaDeUsuario(Trivia pTrivia)
    {
        Respuesta unaRespuestaConPregunta;
        Pregunta unaPregunta;
        ArrayList<Respuesta> ListaRespuestaConPregunta = new ArrayList<>();
        int x = 0;
        int y = 0;
        try
        {
            this.seleccionarDatos("select p.pregunta,r.respuesta,r.correcta from historial h inner join respuesta r on h.idrespuesta = r.id inner join pregunta p on r.idpregunta = p.id inner join trivia t on h.idtrivia = t.id where t.id =" + pTrivia.get_id() + ";");

            while(!c.isAfterLast())
            {
                unaRespuestaConPregunta = new Respuesta();
                unaPregunta = new Pregunta();

                unaPregunta.set_pregunta(c.getString(0));

                unaRespuestaConPregunta.set_pregunta(unaPregunta);
                unaRespuestaConPregunta.set_respuesta(c.getString(1));
                if(c.getString(2).equals("1")) { unaRespuestaConPregunta.set_correcta(true); }
                else if(c.getString(2).equals("0")){unaRespuestaConPregunta.set_correcta(false); }

                ListaRespuestaConPregunta.add(unaRespuestaConPregunta);
                c.moveToNext();
            }
            c.close();
            return ListaRespuestaConPregunta;
        }catch (Exception ex){
            throw new Error(ex.getMessage());
        }

    }
}
