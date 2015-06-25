/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.dao;

import com.romano.controller.Connect;
import com.romano.model.Message;
import com.romano.model.Talk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilizada para gerenciar os dados da classe Message
 * @author leonardo
 */
public class MessageDAO {
    
    private Connect con = null;
    
    /**
     * Método que retorno todas as mensagens de uma conversa específica
     * @param talk
     * Parâmetro que indica qual conversa deve ser filtrada
     * @return 
     * Retorna uma lista de mensagens ordenadas da conversa selecionada
     */
    public List<Message> getListMessage(Talk talk){
        try{
            con = new Connect();
            
            try {
                PreparedStatement prmt = con.getConnection().prepareStatement(
                        "SELECT M.ID, M.ID_USER, M.ID_TALK, M.DATE, M.TEXT FROM MESSAGE M"
                                + "WHERE M.ID_TALK = ? ORDER BY M.ID");
                prmt.setInt(1, talk.getId());
                ResultSet rs = prmt.executeQuery();
                if (rs.wasNull()){
                    return null;
                }
                List<Message> lm = new ArrayList<>();
                
                while(rs.next()){
                    Message m = new Message();
                    m.setId(rs.getInt("ID"));
                    m.setIdUser(rs.getInt("ID_USER"));
                    m.setIdTalk(rs.getInt("ID_TALK"));
                    m.setDate(rs.getDate("DATE"));
                    m.setText(rs.getString("TEXT"));
                    
                    lm.add(m);
                }
                return lm;
            } catch (SQLException ex) {
                Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }finally{
            con.closeConnection();
        }
    }
    
   
    public List<Message> getLastListMessage(Message message){
        try{
            con = new Connect();
            
            try {
                PreparedStatement prmt = con.getConnection().prepareStatement(
                        "SELECT M.ID, M.ID_USER, M.ID_TALK, M.DATE, M.TEXT FROM MESSAGE M"
                                + "WHERE M.ID_TALK = ? AND M.ID > ? ORDER BY M.ID");
                prmt.setInt(1, message.getIdTalk());
                prmt.setInt(2, message.getId());
                ResultSet rs = prmt.getResultSet();
                if (rs.wasNull()){
                    return null;
                }
                List<Message> lm = new ArrayList<>();
                
                while(rs.next()){
                    Message m = new Message();
                    m.setId(rs.getInt("ID"));
                    m.setIdUser(rs.getInt("ID_USER"));
                    m.setIdTalk(rs.getInt("ID_TALK"));
                    m.setDate(rs.getDate("DATE"));
                    m.setText(rs.getString("TEXT"));
                    
                    lm.add(m);
                }
                return lm;
            } catch (SQLException ex) {
                Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }finally{
            con.closeConnection();
        }
    }
    
    /**
     * Método utilizado para gravar mensagem na base de dados.
     * @param message
     * Parâmetro com dados da mensagem que será persistida no banco.
     */
    public void setMessage(Message message) {
        if (message == null)
            throw new ExceptionInInitializerError("Não foi possível localizar a mensagem...");               
        
        try{
            con = new Connect();
            try {
                PreparedStatement prmt = con.getConnection().prepareStatement("INSERT INTO MESSAGE(ID_USER, ID_TALK, DATE, TEXT) VALUES(:ID_USER, :ID_TALK, :DATE, :TEXT)");
                prmt.setInt(1, message.getIdUser());
                prmt.setInt(2, message.getIdTalk());                
                prmt.setDate(3, message.getDate());
                prmt.setString(4, message.getText());
                prmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            con.closeConnection();
        }
    }
}
