package com.example.obligatorio.Common;

import java.io.Serializable;
import java.util.Date;

public class Mascota implements Serializable {

    private String _nombre;
    private Usuario _usuario; /* Dueño*/
    private Date _ult_comida; /* Ultima vez que comio*/
    private Date _ult_bebida; /* Ultima vez que bebio agua*/

    public Mascota(String _nombre, Usuario _usuario, Date _ult_comida, Date _ult_bebida) {
        this._nombre = _nombre;
        this._usuario = _usuario;
        this._ult_comida = _ult_comida;
        this._ult_bebida = _ult_bebida;
    }

    public Date get_ult_bebida() {
        return _ult_bebida;
    }

    public void set_ult_bebida(Date _ult_bebida) {
        this._ult_bebida = _ult_bebida;
    }

    public Date get_ult_comida() {
        return _ult_comida;
    }

    public void set_ult_comida(Date _ult_comida) {
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
}
