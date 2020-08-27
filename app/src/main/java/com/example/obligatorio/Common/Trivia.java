package com.example.obligatorio.Common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Trivia implements Serializable {

    private int _id;
    private int _puntuacion;
    private Usuario _usuario;
    private LocalDateTime _fecha;


    public Trivia(int pId, int pPuntuacion, Usuario pUsuario, LocalDateTime pFecha) {
        _id = pId;
        _puntuacion = pPuntuacion;
        _usuario = pUsuario;
        _fecha = pFecha;
    }

    public Trivia(){}

    public int get_id(){ return _id; }

    public void set_id(int _id){this._id = _id; }

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

    public LocalDateTime get_fecha() {
        return _fecha;
    }

    public void set_fecha(LocalDateTime _fecha) {
        this._fecha = _fecha;
    }
}
