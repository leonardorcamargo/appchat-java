/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.dao;

import com.romano.controller.Connect;
import com.romano.model.Talk;
import com.romano.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de controle de dados da classe Talk, responsavel por gerenciar os objetos
 * de conversa
 * @author Leonardo Camargo
 */
public class TalkDAO {
    
    private Connect con = null;
    
    /**
     * Método utilizado para retornar a lista de conversas que o usuário atual 
     * possuir
     * @param user
     * @return 
     */
    public List<Talk> getListTalk(User user){
        try{
            con = new Connect();
            try {
                String sql = "SELECT TK.ID, TK.DATE, TK.TYPETALK, TK.ACTIVE FROM TALK TK WHERE ";
                if (user != null){
                    sql = sql+"TK.ID IN (SELECT M.ID_TALK FROM MESSAGE M "
                                + "WHERE M.ID_USER = ? GROUP BY M.ID_TALK) AND ";
                }
                sql = sql + "TK.ACTIVE = TRUE";
                PreparedStatement prmt = con.getConnection().prepareStatement(sql);
                
                if (user != null){
                    prmt.setInt(1, user.getId());
                }
                ResultSet rs = prmt.executeQuery();
                
                if (rs.wasNull()){
                    return null;
                }
                
                List<Talk> lt = new ArrayList<>();
                
                while(rs.next()){
                    Talk t = new Talk();
                    t.setId(rs.getInt("ID"));
                    t.setDate(rs.getDate("DATE"));                     
                    t.setTypeTalk(rs.getString("TYPETALK"));
                    t.setActive(rs.getBoolean("ACTIVE"));
                    
                    lt.add(t);
                }
                return lt;
            } catch (SQLException ex) {
                Logger.getLogger(TalkDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }finally{
            con.closeConnection();
        }                       
    }
    
}
