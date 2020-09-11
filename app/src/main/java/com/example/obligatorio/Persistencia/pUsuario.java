package com.example.obligatorio.Persistencia;

import android.content.Context;

import com.example.obligatorio.Common.Usuario;

public class pUsuario extends pConexion {
    public pUsuario(Context contexto){super(contexto);}

    public boolean AltaUsuario(Usuario pusuario)
    {
        try
        {
            this.ModificarDatos("Insert into usuario(user,pass,email) values('" + pusuario.get_user() + "','" + pusuario.get_pass() + "', '" + pusuario.get_email() + "');");
            return true;
        }
        catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
    }
    public Usuario buscarUsuarioPorId(int pId)
    {
        try
        {
            this.seleccionarDatos("Select id,user,pass,email,admin from usuario where id = " + pId + "");

            while(!c.isAfterLast())
            {
                Usuario unUsuario = new Usuario();
                unUsuario.set_id(c.getInt(0));
                unUsuario.set_user(c.getString(1));
                unUsuario.set_pass(c.getString(2));
                unUsuario.set_email(c.getString(3));
                if(c.getString(4).equals("1"))
                {
                    unUsuario.set_admin(true);
                }else
                if(c.getString(4).equals("0")){unUsuario.set_admin(false); }
                c.close();
                return unUsuario;
            }

        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return null;
    }
    public Usuario buscarUsuarioPorUser(String pUsuario)
    {
        try
        {
            this.seleccionarDatos("Select id,user,pass,email,admin from usuario where user = '" + pUsuario + "';");

            while(!c.isAfterLast())
            {
                Usuario unUsuario = new Usuario();
                unUsuario.set_id(c.getInt(0));
                unUsuario.set_user(c.getString(1));
                unUsuario.set_pass(c.getString(2));
                unUsuario.set_email(c.getString(3));
                if(c.getString(4).equals("1"))
                {
                    unUsuario.set_admin(true);
                }else
                if(c.getString(4).equals("0")){unUsuario.set_admin(false); }
                c.close();
                return unUsuario;
            }

        }catch (Exception ex)
        {
            throw new Error(ex.getMessage());
        }
        return null;
    }
    public Usuario Login (Usuario pusuario)
    {

        try
        {
            this.seleccionarDatos("Select id,user,pass,email,admin from usuario where user = '" + pusuario.get_user() + "' and pass = '" + pusuario.get_pass() + "'");

            while(!c.isAfterLast())
            {
                Usuario unUsuario = new Usuario();
                unUsuario.set_id(c.getInt(0));
                unUsuario.set_user(c.getString(1));
                unUsuario.set_pass(c.getString(2));
                unUsuario.set_email(c.getString(3));
                if(c.getString(4).equals("1"))
                {
                    unUsuario.set_admin(true);
                }else
                    if(c.getString(4).equals("0")){unUsuario.set_admin(false); }
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
