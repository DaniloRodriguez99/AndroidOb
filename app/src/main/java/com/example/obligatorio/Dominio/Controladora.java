package com.example.obligatorio.Dominio;

import com.example.obligatorio.Common.Usuario;

import java.util.ArrayList;

public class Controladora {

    public static ArrayList<Usuario> Usuarios = new ArrayList<>();

    public Usuario BuscarUsuario(Usuario pUser){
        for (Usuario unUsuario: Usuarios) {
            if(unUsuario.get_user().equals(pUser.get_user()))
            {
                return unUsuario;
            }
            if(unUsuario.getEmail().equals(pUser.getEmail()))
            {
                return unUsuario;
            }
        }
        return null;
    }

    public boolean AltaUsuario(Usuario pUser){
        if(BuscarUsuario(pUser) == null){
            Usuarios.add(pUser);
            return true;
        }
        return false;
    }

}
