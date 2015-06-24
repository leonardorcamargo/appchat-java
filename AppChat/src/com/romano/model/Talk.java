/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.model;

import com.romano.common.Type.TypeTalk;
import java.sql.Date;

/**
 * 
 * @author leonardo
 */
public class Talk {
   
    private int id;
    private Date date;  
    private TypeTalk typeTalk;
    private boolean active;
    
    public int getId(){
        return this.id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public Date getDate(){
        return this.date;        
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public TypeTalk getTypeTalk(){
        return this.typeTalk;
    }
    
    public void setTypeTalk(TypeTalk typeTalk){
        this.typeTalk = typeTalk;
    }
    
    public boolean isActive(){
        return this.active;
    }
    
    public void setActive(boolean active){
        this.active = active;
    }
}
