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

    public Usuario Login(Usuario pUser)
    {
        return usuarioPersistente.Login(pUser);
    }

    public boolean AltaUsuario(Usuario pUser){
        return usuarioPersistente.AltaUsuario(pUser);
    }
}
