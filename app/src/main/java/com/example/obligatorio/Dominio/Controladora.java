package com.example.obligatorio.Dominio;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.obligatorio.Common.Historial;
import com.example.obligatorio.Common.Mascota;
import com.example.obligatorio.Common.Publicidad;
import com.example.obligatorio.Common.Respuesta;
import com.example.obligatorio.Common.Session;
import com.example.obligatorio.Common.Trivia;
import com.example.obligatorio.Common.Usuario;
import com.example.obligatorio.Common.Pregunta;
import com.example.obligatorio.Persistencia.pHistorial;
import com.example.obligatorio.Persistencia.pMascota;
import com.example.obligatorio.Persistencia.pPublicidad;
import com.example.obligatorio.Persistencia.pRespuesta;
import com.example.obligatorio.Persistencia.pTrivia;
import com.example.obligatorio.Persistencia.pUsuario;
import com.example.obligatorio.Persistencia.pPregunta;


import java.util.ArrayList;

public class Controladora {

    private pUsuario usuarioPersistente;
    private pMascota mascotaPersistente;
    private pPregunta preguntaPersistente;
    private pRespuesta respuestaPersistente;
    private pTrivia triviaPersistente;
    private pHistorial historialPersistente;
    private pPublicidad publicidadPersistente;
    private Session miSession;

    public Controladora(Context contexto) {
        usuarioPersistente = new pUsuario(contexto);
        mascotaPersistente = new pMascota(contexto);
        preguntaPersistente = new pPregunta(contexto);
        respuestaPersistente = new pRespuesta(contexto);
        triviaPersistente = new pTrivia(contexto);
        historialPersistente = new pHistorial(contexto);
        publicidadPersistente = new pPublicidad(contexto);
        miSession = new Session(contexto);
    }
    public Controladora(){}

    public Usuario Login(Usuario pUser) {
        Usuario unUsuario = usuarioPersistente.Login(pUser);
        if (unUsuario != null) {
            miSession.setIdUsuario(unUsuario.get_id());
            return unUsuario;
        }
        return null;
    }

    public boolean AltaUsuario(Usuario pUser) {
        return usuarioPersistente.AltaUsuario(pUser);
    }


    public boolean altaMascota(String mascotaNombre, String tipo) {
        Mascota unaMascota = new Mascota(mascotaNombre, buscarUsuarioPorId(miSession.getIdUsuario()), tipo);
        return mascotaPersistente.altaMascota(unaMascota);
    }

    public boolean altaHistorial(Historial pHistorial){return historialPersistente.altaHistorial(pHistorial);}

    public boolean altaTrivia(Trivia pTrivia){return triviaPersistente.AltaTrivia(pTrivia);}

    public Usuario buscarUsuarioPorId(int pId) {
        return usuarioPersistente.buscarUsuarioPorId(pId);
    }
    public Usuario buscarUsuarioPorUser(String pUser) {
        return usuarioPersistente.buscarUsuarioPorUser(pUser);
    }

    public ArrayList<Mascota> BuscarMascotasDeUnUsuario() {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(miSession.getIdUsuario()));
    }

    public ArrayList<Mascota> BuscarMascotasDeUnUsuario(Usuario unUser) {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(unUser.get_id()));
    }

    public ArrayList<Mascota> BuscarMascotasDeUnUsuario(int pId) {
        return mascotaPersistente.buscarMascotasDeUnUsuario(buscarUsuarioPorId(pId));
    }

    public Mascota BuscarMascotaEspecifica(String nombre) {
        return mascotaPersistente.buscarMascotaEspecifica(nombre, buscarUsuarioPorId(miSession.getIdUsuario()));
    }
    public Mascota BuscarMascotaEspecifica(int idmascota) {
        return mascotaPersistente.buscarMascotaEspecifica(idmascota, buscarUsuarioPorId(miSession.getIdUsuario()));
    }

    public boolean bajaMascota(int id){
        return mascotaPersistente.bajaMascota(BuscarMascotaEspecifica(id));
    }

    public boolean bajaPublicidad(Publicidad pPublicidad)
    {
        return publicidadPersistente.BajaPublicidad(pPublicidad);
    }

    public boolean modificarPublicidad(Publicidad pPublicidad)
    {
        return publicidadPersistente.ModificarPublicidad(pPublicidad);
    }

    public long TiempoDeVida()
    {
        long tomo = this.mascotaPersistente.TiempoDesdeQueTomo(miSession.getMascota());
        long comio = this.mascotaPersistente.TiempoDesdeQueComio(miSession.getMascota());

        if (tomo > comio)
        {
            return tomo;
        }
        else
        {
            return comio;
        }
    }

    public int NroMascotasDeUnUsuario() {
        return BuscarMascotasDeUnUsuario().size();
    }

    public Mascota anteriorMascota() {
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = BuscarMascotasDeUnUsuario();

        for (Mascota m : misMascotas) {
            if (m.get_id() == miSession.getMascota()) {
                if (misMascotas.indexOf(m) != 0) {
                    return misMascotas.get(misMascotas.indexOf(m) - 1);
                } else {
                    return misMascotas.get(misMascotas.size() - 1);
                }
            }
        }
        return null;
    }

    public Mascota siguienteMascota() {
        ArrayList<Mascota> misMascotas = new ArrayList<>();
        misMascotas = BuscarMascotasDeUnUsuario();

        for (Mascota m : misMascotas) {
            if (m.get_id() == miSession.getMascota()) {
                if (misMascotas.indexOf(m) + 1 == misMascotas.size()) {
                    return misMascotas.get(0);
                } else {
                    return misMascotas.get(misMascotas.indexOf(m) + 1);
                }
            }
        }
        return null;
    }

    public void DarComida()
    {
        Mascota miMascota = BuscarMascotaEspecifica(miSession.getMascota());
        mascotaPersistente.DarComida(miMascota);
    }

    public void DarAgua()
    {
        Mascota miMascota = BuscarMascotaEspecifica(miSession.getMascota());
        mascotaPersistente.DarAgua(miMascota);
    }

    public Pregunta traerPreguntasTrivia(String pCategoria, int IdP1, int IdP2, int IdP3, int IdP4) {
        return preguntaPersistente.TraerPregunta(pCategoria, IdP1, IdP2, IdP3, IdP4);
    }

    public ArrayList<Respuesta> traerRespuestas(Pregunta pPregunta)
    {
        return respuestaPersistente.TraerRespuesta(pPregunta);
    }

    public int TraerIdUltimaTrivia()
    {
        return triviaPersistente.ObtenerUltimoIdTrivia();
    }

    public ArrayList<Trivia>TraerTriviasdeunUsuario()
    {
        return triviaPersistente.TraerTriviasDeUnUsuario(buscarUsuarioPorId(miSession.getIdUsuario()));
    }

    public ArrayList<Respuesta>TraerPreguntaYRespuesta(Trivia pTrivia)
    {
        return historialPersistente.TraerPreguntaYRespuestaDeUsuario(pTrivia);
    }

    public boolean AltaPublicidad(Publicidad pPublicidad)
    {
        return publicidadPersistente.AltaPublicidad(pPublicidad);
    }

    public ArrayList<Publicidad>TraerPublicidades()
    {
        return publicidadPersistente.TraerPublicidades();
    }

    public Publicidad TraerPublicidadRandom()
    {
        return publicidadPersistente.TraerPublicidadRandom();
    }

}
