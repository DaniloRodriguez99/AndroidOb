package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Historial;
import com.example.obligatorio.Dominio.Controladora;

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
}
