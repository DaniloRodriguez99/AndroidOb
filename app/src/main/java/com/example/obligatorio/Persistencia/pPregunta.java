package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Pregunta;

public class pPregunta extends pConexion{
    public pPregunta(Context contexto){super(contexto);}

    public Pregunta TraerPregunta(String Categoria,int id1, int id2, int id3, int id4)
    {
        Pregunta unaPregunta;
        try {
            if (id1 == 0) {
                this.seleccionarDatos("select id,pregunta,tipo from pregunta where tipo ='" + Categoria + "' ORDER BY RANDOM() LIMIT 1);");
            }
            if (id1 != 0 && id2 == 0) {
                this.seleccionarDatos("select id,pregunta,tipo from pregunta where tipo ='" + Categoria + "', and id !=" + id1 + " ORDER BY RANDOM() LIMIT 1);");
            }
            if (id1 != 0 && id2 != 0 && id3 == 0) {
                this.seleccionarDatos("select id,pregunta,tipo from pregunta where tipo ='" + Categoria + "' and id !=" + id1 + " and id != " + id2 + "); ORDER BY RANDOM() LIMIT 1);");
            }
            if (id1 != 0 && id2 != 0 && id3 != 0 && id4 == 0) {
                this.seleccionarDatos("select id,pregunta,tipo from pregunta where tipo ='" + Categoria + "' and id !=" + id1 + " and id != " + id2 + " and id != " + id3 + " ORDER BY RANDOM() LIMIT 1);");
            }
            if (id1 != 0 && id2 != 0 && id3 != 0 && id4 != 0) {
                this.seleccionarDatos("select id,pregunta,tipo from pregunta where tipo ='" + Categoria + "' and id !=" + id1 + " and id != " + id2 + " and id != " + id3 + " and id != " + id4 + " ORDER BY RANDOM() LIMIT 1);");
            }
            while(!c.isAfterLast())
            {
                unaPregunta = new Pregunta();
                unaPregunta.set_id(c.getInt(0));
                unaPregunta.set_pregunta(c.getString(1));
                unaPregunta.set_tipo(c.getString(2));
                return unaPregunta;
            }
        }
        catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return null;
    }
}
