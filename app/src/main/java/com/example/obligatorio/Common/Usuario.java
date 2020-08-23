package com.example.obligatorio.Common;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int _id;
    private String _user;
    private String _pass;
    private String _email;
    private Boolean _admin;

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
        this._id = pId;
        this._user = pUser;
        this._pass = pPass;
        this._email = pEmail;
        this._admin = pAdmin;
    }

    public Usuario(String pUser, String pPass, String pEmail, Boolean pAdmin) {
        this._user = pUser;
        this._pass = pPass;
        this._email = pEmail;
        this._admin = pAdmin;
    }

    public Usuario(String pUser, String pPass)
    {
        this._user = pUser;
        this._pass = pPass;
    }

    public Usuario(String pUser, String pPass, String pEmail){
        this._user = pUser;
        this._pass = pPass;
        this._email = pEmail;
    }

    public Usuario()
    {
    }

}
