/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.controller;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de conexão
 * @author leonardo
 */
public class Connect {
    
    private static Connection CONNECT = null;
        
    public Connect(){
       try {
            if ((CONNECT == null) || (CONNECT.isClosed())){
                try {
                    Class.forName("org.h2.Driver");
                    CONNECT = DriverManager.getConnection("jdbc:h2:tcp://leonardo-pc/~/chat", "admin", "admin");
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);                           
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        return CONNECT;
    }
    
    public void closeConnection(){
        try {
            CONNECT.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
