package com.example.obligatorio.Common;

import java.io.Serializable;
import java.util.Date;

public class Preguntados implements Serializable {

    private int _puntuacion;
    private Usuario _usuario;
    private Date _fecha;


    public Preguntados(int puntuacion, Usuario usuario, Date fecha) {
        _puntuacion = puntuacion;
        _usuario = usuario;
        _fecha = fecha;
    }

    public int get_puntuacion() {
        return _puntuacion;
    }

    public void set_puntuacion(int _puntuacion) {
        this._puntuacion = _puntuacion;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public Date get_fecha() {
        return _fecha;
    }

    public void set_fecha(Date _fecha) {
        this._fecha = _fecha;
    }
}
