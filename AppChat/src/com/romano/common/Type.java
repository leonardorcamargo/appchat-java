/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.common;

import com.sun.jmx.snmp.Enumerated;

/**
 * Classe onde é armazenado os types utilizados pela aplicação
 * @author Leonardo Camargo
 */
public class Type {
 
    
    /**     
     * TypeTalk
     * Tipo utilizado na classe user, para definir o tipo da conversa
     */
    public enum TypeTalk{
        GRUPO("G"),
        PARTICULAR("P");
        
        private final String name;
        
        TypeTalk(String name){
            this.name = name;
        }
        
        @Override
        public String toString(){
            return this.name;
        }
    }
    
}