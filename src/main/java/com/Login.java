package com;

import com.DAO.Admin_Dao.Admin;

public class Login {
    

    public Admin admin = null;
    public String user;

    public Login(String _username, String _password, String _user) {
        admin = new Admin(_username, _password);
        setuser(_user);
    }

    public void setuser(String _user) { user = _user; }
    public String getuser() {return user;}

}