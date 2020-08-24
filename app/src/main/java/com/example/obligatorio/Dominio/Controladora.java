package com.example.obligatorio.Dominio;

import android.content.Context;

import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Common.Pregunta;
import com.example.obligatorio.Persistencia.pMascota;
import com.example.obligatorio.Persistencia.pUsuario;
import com.example.obligatorio.Persistencia.pPregunta;


import java.util.ArrayList;

public class Controladora {

    private pUsuario usuarioPersistente;
    private pMascota mascotaPersistente;
    private pPregunta preguntaPersistente;
    private Session miSession;

    public Controladora(Context contexto)
    {
        usuarioPersistente = new pUsuario(contexto);
        mascotaPersistente = new pMascota(contexto);
        preguntaPersistente = new pPregunta(contexto);
        miSession = new Session(contexto);
    }
    public Controladora(){}

    public static ArrayList<Usuario> Usuarios = new ArrayList<>();

    public Usuario Login(Usuario pUser)
    {
        Usuario unUsuario = usuarioPersistente.Login(pUser);
        if(unUsuario != null)
        {
            miSession.setIdUsuario(unUsuario.get_id());
            return unUsuario;
        }
        return null;
    }

    public boolean AltaUsuario(Usuario pUser){
        return usuarioPersistente.AltaUsuario(pUser);
    }


    public boolean altaMascota(String mascotaNombre, String tipo)
    {
        Mascota unaMascota = new Mascota(mascotaNombre,buscarUsuarioPorId(miSession.getIdUsuario()),tipo);
        return mascotaPersistente.altaMascota(unaMascota);
    }

    public Usuario buscarUsuarioPorId(int pId)
    {
       return usuarioPersistente.buscarUsuarioPorId(pId);
    }

    public ArrayList<Mascota> BuscarMasctoasDeUnUsuario()
    {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(miSession.getIdUsuario()));
    }

    public ArrayList<Mascota> BuscarMasctoasDeUnUsuario(Usuario unUser)
    {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(unUser.get_id()));
    }

    public ArrayList<Mascota> BuscarMasctoasDeUnUsuario(int pId)
    {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(pId));
    }

    public Mascota BuscarMascotaEspecifica(String nombre)
    {
        return mascotaPersistente.buscarMascotaEspecifica(nombre, buscarUsuarioPorId(miSession.getIdUsuario()));
    }

    public Mascota anteriorMascota(){
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = BuscarMasctoasDeUnUsuario();

        for ( Mascota m : misMascotas) {
            if (m.get_id() == miSession.getMascota())
            {
                if (misMascotas.indexOf(m) != 0)
                {
                    return misMascotas.get(misMascotas.indexOf(m) -1);
                }
                else
                {
                    return misMascotas.get(misMascotas.size() -1);
                }
            }
        }
        return null;
    }

    public Mascota siguienteMascota()
    {
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = BuscarMasctoasDeUnUsuario();

        for ( Mascota m : misMascotas) {
            if (m.get_id() == miSession.getMascota())
            {
                if (misMascotas.indexOf(m) + 1  == misMascotas.size())
                {
                    return misMascotas.get(0);
                }
                else
                {
                    return misMascotas.get(misMascotas.indexOf(m) + 1);
                }
            }
        }
        return null;
    }

    public Pregunta traerPreguntasTrivia(String pCategoria, int IdP1, int IdP2, int IdP3, int IdP4)
    {
        return preguntaPersistente.TraerPregunta(pCategoria,IdP1,IdP2,IdP3,IdP4);
    }

}
