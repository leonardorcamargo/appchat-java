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
    
    /**
     * Método utilizado pelo server para buscar user pelo login e senha.
     * @param login
     * Login do user que será localizado no banco.
     * @param password
     * Senha do user que será localizado no banco.
     * @return 
     * Este método retorna o usuário que foi encontrado, ou null caso não encontre.
     */    
    private User getUserSocket(String login, String password){
        try{
            con = new Connect();
            try {
                PreparedStatement pstmt = con.getConnection().prepareStatement("SELECT ID, NAME, LOGIN, PASSWORD FROM USER WHERE LOGIN = ? AND PASSWORD = ?");
                pstmt.setString(1, login);
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
    /**
     * Método chamado pelo client para localizar um user no servidor.
     * @param login
     * Login do user que será localizado no banco.
     * @param password
     * Password do user que será localido no banco.
     * @return 
     * Este método retorna o user que foi encontrado no server, ou null caso não encontre.
     */
    public User getUser(String login, String password){
        if(login.equals("") || password.equals(""))
            return null;
        
        return getUserSocket(login,password);
        
    }
    
    /**
     * Método utilizado pelo server para localizar e retornar user pelo id.
     * @param id
     * Id que será utilizado para localizar user.
     * @return 
     * Este método retorna o user localizado no banco, ou null caso não encontre.
     */
    private User getUserSocket(int id){
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
    
    /**
     * Método utilizado pelo client para localizar um user no server.
     * @param id
     * Id utilizado para localizar o user no server.
     * @return 
     * Este método retorna o user que foi localizado, ou null caso não encontre.
     */
    public User getUser(int id){
        if(id == 0)
            return null;
        
        return getUserSocket(id);
    }
    
}
