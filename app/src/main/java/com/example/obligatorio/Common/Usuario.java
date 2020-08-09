package com.example.obligatorio.Common;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable {

    private String email;

    public Usuario(String _user, String _pass, String _email) {
        super(_user, _pass);
        this.email = _email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
