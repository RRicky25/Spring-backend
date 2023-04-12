package com.DAO.Admin_Dao;

public class Admin {

    String username;
    String password;
    
    public Admin(String _username, String _password) {
        setUsername(_username);
        setPassword(_password);
    }
    

    //getters and setters
    public String getUsername() {return username;}
    public void setUsername(String _username) {username = _username;}

    public String getPassword() {return password;}
    public void setPassword(String _password) {password = _password;}

}
