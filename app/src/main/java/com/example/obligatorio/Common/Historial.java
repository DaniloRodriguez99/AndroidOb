package com.example.obligatorio.Common;

import java.io.Serializable;

public class Historial implements Serializable {

    private int _id;
    private int _idTrivia;
    private int _idRespuesta;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_idTrivia() {
        return _idTrivia;
    }

    public void set_idTrivia(int _idTrivia) {
        this._idTrivia = _idTrivia;
    }

    public int get_idRespuesta() {
        return _idRespuesta;
    }

    public void set_idRespuesta(int _idRespuesta) {
        this._idRespuesta = _idRespuesta;
    }
}
