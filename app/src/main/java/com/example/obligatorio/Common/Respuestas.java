package com.example.obligatorio.Common;

import java.io.Serializable;

public class Respuestas implements Serializable {

    private int _id;
    private String _respuesta;
    private boolean _correcta; /* si es true, es correcta. si es false es incorrecta */
    private Pregunta _pregunta;

    public Respuestas(int _id, String _respuesta, boolean _correcta, Pregunta _pregunta) {
        this._id = _id;
        this._respuesta = _respuesta;
        this._correcta = _correcta;
        this._pregunta = _pregunta;
    }

    public Pregunta get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(Pregunta _pregunta) {
        this._pregunta = _pregunta;
    }

    public boolean is_correcta() {
        return _correcta;
    }

    public void set_correcta(boolean _correcta) {
        this._correcta = _correcta;
    }

    public String get_respuesta() {
        return _respuesta;
    }

    public void set_respuesta(String _respuesta) {
        this._respuesta = _respuesta;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
