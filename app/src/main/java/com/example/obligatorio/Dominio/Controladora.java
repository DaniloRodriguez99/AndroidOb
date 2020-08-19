package com.example.obligatorio.Dominio;

import android.content.Context;

import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Persistencia.pUsuario;

import java.util.ArrayList;

public class Controladora {

    private pUsuario usuarioPersistente;

    public Controladora(Context contexto)
    {
        usuarioPersistente = new pUsuario(contexto);
    }
    public Controladora(){}

    public static ArrayList<Usuario> Usuarios = new ArrayList<>();

    public Usuario BuscarUsuario(Usuario pUser)
    {
        return usuarioPersistente.BuscarUsuarioConContraseña(pUser);
    }

    public boolean AltaUsuario(Usuario pUser){
        return usuarioPersistente.AltaUsuario(pUser);
    }

    public boolean Login(Usuario pUsuario){
        for (Usuario unUsuario: Usuarios) {
            if(unUsuario.get_pass().equals(pUsuario.get_pass()) && unUsuario.get_user().equals(pUsuario.get_user()))
            {
                return true;
            }
        }
        return false;
    }
}
