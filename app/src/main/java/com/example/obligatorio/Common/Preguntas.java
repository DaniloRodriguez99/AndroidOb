package com.example.obligatorio.Common;

import java.io.Serializable;
import java.util.ArrayList;

public class Preguntas implements Serializable {

    private int _id;
    private String _pregunta;
    private String _tipo;
    private  ArrayList<Integer>_listaDeIds = new ArrayList<>();


    public Preguntas(int pId, String pPregunta, String pTipo) {
        _id = pId;
        _pregunta = pPregunta;
        _tipo = pTipo;
    }

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

    public ArrayList<Integer> getPreguntasQueDejanDeIr(){return _listaDeIds;}

    public void setPreguntasQueDejanDeIr(int pId)
    {
        _listaDeIds.add(pId);
    }



    }
