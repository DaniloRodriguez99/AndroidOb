package com.example.obligatorio.Common;

import java.io.Serializable;

public class Preguntas implements Serializable {

    private int _id;
    private String _pregunta;


    public Preguntas(int id, String pregunta) {
        _id = id;
        _pregunta = pregunta;
    }

    public int get_id() {
        return _id;
    }

    public String get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(String _pregunta) {
        this._pregunta = _pregunta;
    }
}
