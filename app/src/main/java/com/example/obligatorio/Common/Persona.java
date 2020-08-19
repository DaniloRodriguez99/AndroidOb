package com.example.obligatorio.Common;

import java.io.Serializable;

public class Persona implements Serializable {

    private String _user;
    private String _pass;

    public Persona(String _user, String _pass) {
        this._user = _user;
        this._pass = _pass;
    }

    public Persona() {

    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String _pass) {
        this._pass = _pass;
    }



}
