package com.example.obligatorio.Common;

import java.io.Serializable;

public class Publicidad implements Serializable {

    private String _titulo;
    private String _descripcion;
    private byte[] _imagen;

    public Publicidad(String _titulo, String _descripcion, byte[] _imagen) {
        this._titulo = _titulo;
        this._descripcion = _descripcion;
        this._imagen = _imagen;
    }

    public String get_titulo() {
        return _titulo;
    }

    public void set_titulo(String _titulo) {
        this._titulo = _titulo;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

    public byte[] get_imagen() {
        return _imagen;
    }

    public void set_imagen(byte[] _imagen) {
        this._imagen = _imagen;
    }
}
