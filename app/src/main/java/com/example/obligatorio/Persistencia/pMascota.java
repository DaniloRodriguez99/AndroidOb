package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Common.Usuario;

import java.util.ArrayList;

public class pMascota extends pConexion {
    public pMascota(Context contexto) {
        super(contexto);
    }

    public boolean altaMascota(Mascota mascota) {
        this.ModificarDatos("Insert into mascota (nombre,iduser,tipo) values ('" + mascota.get_nombre() + "','" + mascota.get_usuario().get_id() + "','" + mascota.get_tipo() + "')");
        return true;
    }

    public ArrayList<Mascota> buscarMascotasDeUnUsuario(Usuario pUsuario) {
        try {
            this.seleccionarDatos("select nombre, iduser, ultvcomio, ultvtomo, idmascota, tipo from mascota where iduser = " + pUsuario.get_id());
            ArrayList<Mascota> retorno = new ArrayList<>();
            while (!c.isAfterLast()) {
                Mascota unaMascota = new Mascota();
                unaMascota.set_nombre(c.getString(0));
                unaMascota.set_usuario(pUsuario);
                unaMascota.set_ult_comida(c.getString(2));
                unaMascota.set_ult_bebida(c.getString(3));
                unaMascota.set_id(c.getInt(4));
                unaMascota.set_tipo(c.getString(5));
                retorno.add(unaMascota);
                c.moveToNext();
            }
            c.close();
            return retorno;
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public Mascota buscarMascotaEspecifica(String nombre, Usuario pUsuario)
    {
        try {
            Mascota unaMascota = new Mascota();
            this.seleccionarDatos("select nombre, iduser, ultvcomio, ultvtomo, idmascota, tipo from mascota where iduser = " + pUsuario.get_id() + " and nombre = '" + nombre + "'");
            while (!c.isAfterLast()) {
                unaMascota.set_nombre(c.getString(0));
                unaMascota.set_usuario(pUsuario);
                unaMascota.set_ult_comida(c.getString(2));
                unaMascota.set_ult_bebida(c.getString(3));
                unaMascota.set_id(c.getInt(4));
                unaMascota.set_tipo(c.getString(5));
                c.moveToNext();
            }
            c.close();
            return unaMascota;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public Mascota buscarMascotaEspecifica(int idmascota, Usuario pUsuario)
    {
        try {
            Mascota unaMascota = new Mascota();
            this.seleccionarDatos("select nombre, iduser, ultvcomio, ultvtomo, idmascota, tipo from mascota where iduser = " + pUsuario.get_id() + " and idmascota = '" + idmascota + "'");
            while (!c.isAfterLast()) {
                unaMascota.set_nombre(c.getString(0));
                unaMascota.set_usuario(pUsuario);
                unaMascota.set_ult_comida(c.getString(2));
                unaMascota.set_ult_bebida(c.getString(3));
                unaMascota.set_id(c.getInt(4));
                unaMascota.set_tipo(c.getString(5));
                c.moveToNext();
            }
            c.close();
            return unaMascota;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
