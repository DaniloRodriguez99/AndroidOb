package com.example.obligatorio.Common;

import java.io.Serializable;
import java.util.Date;

public class Mascota implements Serializable {

    private int _id;
    private String _nombre;
    private Usuario _usuario; /* Dueño*/
    private String _ult_comida; /* Ultima vez que comio*/
    private String _ult_bebida; /* Ultima vez que bebio agua*/
    private String _tipo;



    public Mascota(String _nombre, Usuario _usuario, String _tipo) {
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._tipo = _tipo;
    }

    public Mascota(){}

    public String get_ult_bebida() {
        return _ult_bebida;
    }

    public void set_ult_bebida(String _ult_bebida) {
        this._ult_bebida = _ult_bebida;
    }

    public String get_ult_comida() {
        return _ult_comida;
    }

    public void set_ult_comida(String _ult_comida) {
        this._ult_comida = _ult_comida;
    }

    public Usuario get_usuario() {
        return _usuario;
    }

    public void set_usuario(Usuario _usuario) {
        this._usuario = _usuario;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_tipo() {
        return _tipo;
    }

    public void set_tipo(String _tipo) {
        this._tipo = _tipo;
    }

}
