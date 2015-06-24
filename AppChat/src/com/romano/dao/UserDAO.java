/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.dao;

import com.romano.controller.Connect;
import com.romano.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Camargo
 */
public class UserDAO {
    
    private Connect con = null;
    
    public User getUser(String name, String password){
        if(name.equals("") || password.equals(""))
            return null;
        
        try{
            con = new Connect();
            try {
                PreparedStatement pstmt = con.getConnection().prepareStatement("SELECT ID, NAME, LOGIN, PASSWORD FROM USER WHERE LOGIN = ? AND PASSWORD = ?");
                pstmt.setString(1, name);
                pstmt.setString(2, password);
                ResultSet result = pstmt.executeQuery();
                result.first();
                if (result.getRow()== 0){
                    return null;
                }
                User user = new User();
                
                user.setId(result.getInt("ID"));
                user.setName(result.getString("NAME"));
                user.setLogin(result.getString("LOGIN"));
                user.setPassword(result.getString("PASSWORD"));
                
                return user;
                
            } catch (SQLException ex) {                
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);                
                return null;
            }
        }finally{
            con.closeConnection();
        }
    }
    
    public User getUser(int id){
        if(id == 0)
            return null;
        
        try{
            con = new Connect();
            try {
                PreparedStatement pstmt = con.getConnection().prepareStatement("SELECT ID, NAME, LOGIN, PASSWORD FROM USER WHERE ID = ?");
                pstmt.setInt(1, id);
                ResultSet result = pstmt.getResultSet();
                
                if (result.wasNull()){
                    return null;
                }
                result.first();
                User user = new User();
                
                user.setId(result.getInt("ID"));
                user.setName(result.getString("NAME"));
                user.setLogin(result.getString("LOGIN"));
                user.setPassword(result.getString("PASSWORD"));
                
                return user;
                
            } catch (SQLException ex) {                
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);                
                return null;
            }
        }finally{
            con.closeConnection();
        }
    }
    
}
