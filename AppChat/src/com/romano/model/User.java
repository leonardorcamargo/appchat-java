package com.romano.model;

/**
 *
 * @author Leonardo Camargo
 */
public class User {
    private static User USER = null;
    private int id;
    private String name;
    private String login;
    private String password;
    
    public static User getUser(){
        return USER;
    }
    
    public static void setUser(User user){
        USER = user;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getLogin(){
        return this.login;
    }
    
    public void setLogin(String login){
        this.login = login;       
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
