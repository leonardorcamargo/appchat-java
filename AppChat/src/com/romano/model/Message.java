/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.model;

import java.sql.Date;

/**
 *
 * @author leonardo
 */
public class Message {
    
    private int id;
    private int idUser;
    private int idTalk;
    private Date date;
    private String text;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdUser(){
        return this.idUser;
    }
    
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }
    
    public int getIdTalk(){
        return this.idTalk;
    }
    
    public void setIdTalk(int idTalk){
        this.idTalk = idTalk;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public String getText(){
        return this.text;
    }
    
    public void setText(String text){
        this.text = text;
    }
      
}
