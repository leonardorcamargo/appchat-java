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
     * Método que retorna todas as mensagens de uma conversa específica.
     * @param talk
     * Parâmetro que indica qual conversa deve ser filtrada.
     * @return 
     * Retorna uma lista de mensagens ordenadas da conversa selecionada ou null
     * caso não encontre.
     */      
    private List<Message> getListMessageSocket(Talk talk){
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
   
    /**
     * Método utilizado pelo client para retornar uma lista de mensagens do server
     * de uma determinada conversa.
     * @param talk
     * Talk utililizada para localizar as mensagens no server.
     * @return 
     * Este método retorna uma lista de mensagens ou null caso não encontre.
     */
    public List<Message> getListMessage(Talk talk){
        return getListMessageSocket(talk);
    }
        
    /**
     * Método utilizado pelo server para retornar uma lista de message a partir 
     * de um determinado id.
     * @param message
     * Message utilizada como parâmetro, para que todas as messages retornadas sejam
     * mais recentes que esta.
     * @return 
     * Este método retorna uma lista de message ou null caso não encontre nenhuma
     */
    private List<Message> getLastListMessageSocket(Message message){
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
     * Método utilizado pelo client para retornar do server uma lista de messages
     * apartir de uma determinada posição.
     * @param message
     * Message utilizada como parâmetro para que as messages retornada sejam mais
     * novas que esta.
     * @return 
     * Este método retorna uma lista de message ou null caso não encontre nada.
     */
    public List<Message> getLastListMessage(Message message){
        return getLastListMessageSocket(message);
    }
    
    /**
     * Método utilizado para gravar mensagem na base de dados.
     * @param message
     * Parâmetro com dados da mensagem que será persistida no banco.
     * @return 
     * Este método retorna True caso a inserção ocorra corretamente ou false caso contrário.
     */
    private boolean setMessageSocket(Message message) {                             
        try{
            con = new Connect();
            try {
                PreparedStatement prmt = con.getConnection().prepareStatement("INSERT INTO MESSAGE(ID_USER, ID_TALK, DATE, TEXT) VALUES(:ID_USER, :ID_TALK, :DATE, :TEXT)");
                prmt.setInt(1, message.getIdUser());
                prmt.setInt(2, message.getIdTalk());                
                prmt.setDate(3, message.getDate());
                prmt.setString(4, message.getText());
                prmt.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }finally{
            con.closeConnection();
        }
    }
    
    /**
     * Método utilizado pelo client para gravar mensagem no server.
     * @param message
     * Parâmetro com dados da mensagem que será persistida no banco.
     */
    public void setMessage(Message message) {
        if (message == null)
            throw new ExceptionInInitializerError("Não foi possível localizar a mensagem..."); 
        
        if (setMessageSocket(message)){
            
        }
    }
}
