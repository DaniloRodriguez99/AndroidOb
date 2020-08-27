package com.example.obligatorio.Common;

import java.io.Serializable;

public class Historial implements Serializable {

    private int _id;
    private Trivia _trivia;
    private Respuesta _respuesta;

    public Historial(int pId,Trivia pTrivia, Respuesta pRespuesta)
    {
        this._id = pId;
        this._trivia = pTrivia;
        this._respuesta = pRespuesta;
    }

    public Historial(Respuesta pRespuesta)
    {
        this._respuesta = pRespuesta;
    }

    public Historial(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Trivia get_Trivia() {
        return _trivia;
    }

    public void set_Trivia(Trivia _trivia) {
        this._trivia = _trivia;
    }

    public Respuesta get_Respuesta() {
        return _respuesta;
    }

    public void set_Respuesta(Respuesta _respuesta) {
        this._respuesta = _respuesta;
    }
}
