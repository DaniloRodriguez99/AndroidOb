package com.example.obligatorio.Common;

import java.io.Serializable;

public class Pregunta implements Serializable {

    private int _id;
    private String _pregunta;
    private String _tipo;


    public Pregunta(int pId, String pPregunta, String pTipo) {
        _id = pId;
        _pregunta = pPregunta;
        _tipo = pTipo;
    }
    public Pregunta(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_pregunta() {
        return _pregunta;
    }

    public void set_pregunta(String _pregunta) {
        this._pregunta = _pregunta;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }



    }
