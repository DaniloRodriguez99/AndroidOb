package com.example.obligatorio.Common;

import java.io.Serializable;

public class Usuario extends Persona implements Serializable {

    private int _id;
    private String _email;
    private Boolean _admin;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public Boolean get_admin() {
        return _admin;
    }

    public void set_admin(Boolean _admin) {
        this._admin = _admin;
    }

    public Usuario(int pId, String pUser, String pPass, String pEmail, Boolean pAdmin) {
        super(pUser, pPass);
        this._id = pId;
        this._email = pEmail;
        this._admin = pAdmin;
    }

    public Usuario(String _user, String _pass) {
        super(_user, _pass);
    }

    public Usuario(String _user, String _pass, String pEmail){
        super(_user, _pass);
        this._email = pEmail;
    }

    public Usuario(){
        super();
    }

}
