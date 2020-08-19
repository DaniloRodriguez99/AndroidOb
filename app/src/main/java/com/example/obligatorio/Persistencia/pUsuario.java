package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Usuario;

public class pUsuario extends pConexion {
    public pUsuario(Context contexto){super(contexto);}

    public boolean AltaUsuario(Usuario pusuario)
    {
        try
        {
            this.ModificarDatos("Insert into Usuario(usuario,contrasenia,email) values('" + pusuario.get_user() + "','" + pusuario.get_pass() + "', '" + pusuario.get_email() + "');");
            return true;
        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }

    }

    public boolean AltaAdmin(Usuario pusuario)
    {
        try
        {
            this.ModificarDatos("Insert into Usuario(usuario,contrasenia,email,admin) values('" + pusuario.get_user() + "','" + pusuario.get_pass() + "', '" + pusuario.get_email() + "'," + true + ");");
        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return true;
    }

    public boolean BajaUsuario(Usuario pusuario)
    {
        try
        {
            this.ModificarDatos("Delete from Usuario where idusuario = " + pusuario.get_id() + ");");
        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return true;
    }

    public Usuario BuscarUsuarioConContraseña(Usuario pusuario)
    {

        try
        {
            this.seleccionarDatos("Select idusuario,usuario,contrasenia,email,admin from Usuario where usuario = '" + pusuario.get_user() + "' and contrasenia = '" + pusuario.get_pass() + "');");

            while(!c.isAfterLast())
            {
                Usuario unUsuario = new Usuario();
                unUsuario.set_id(c.getInt(0));
                unUsuario.set_user(c.getString(1));
                unUsuario.set_pass(c.getString(2));
                unUsuario.set_email(c.getString(3));
                unUsuario.set_admin(Boolean.parseBoolean(c.getString(4)));
                c.close();
                return unUsuario;
            }

        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return null;
    }
}
