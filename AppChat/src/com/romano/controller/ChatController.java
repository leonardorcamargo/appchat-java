/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.controller;

import com.romano.component.ListMessage;
import com.romano.dao.MessageDAO;
import com.romano.dao.TalkDAO;
import com.romano.dao.UserDAO;
import com.romano.model.Message;
import com.romano.model.Talk;
import com.romano.model.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * Classe ChatController, utilizada para manter os métodos e componentes utilizados
 * pela classe Chat.fxml
 * @author Leonardo Camargo
 */
public class ChatController implements Initializable {
    
    private Talk talk = null;
    
    @FXML
    private Button btnEnviar;
    
    @FXML
    private TextArea txtMensagem;  
    
    @FXML
    private ListMessage listMessage;
    
    /**
     * Método utilizado pelo botão Enviar da clase Chat.fxml, responsável por 
     * adicionar a mensagem na base de dados do usuário
     * @param event 
     */
    @FXML
    private void handleBtnEnviarAction(ActionEvent event) {
        listMessage.addMessage(User.getUser(), txtMensagem.getText());
        txtMensagem.setText(null);
        txtMensagem.requestFocus();        
        
    }
    
    /**
     * Método utilizado na caixa de texto da classe Chat.fxml para simular o 
     * pressionamento do botão Enviar automáticamente quando pressionado Enter
     * @param event 
     */
    @FXML
    private void onKeyPressed(KeyEvent event){
        if ((KeyCode.ENTER.equals(event.getCode()))&&(!event.isShiftDown())){
            listMessage.addMessage(User.getUser(), txtMensagem.getText());
            txtMensagem.setText("");
            txtMensagem.requestFocus();
        }else if ((KeyCode.ENTER.equals(event.getCode()))&&(event.isShiftDown())){
            txtMensagem.setText(txtMensagem.getText()+"\n");
            txtMensagem.end();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TalkDAO talkDao = new TalkDAO();
        MessageDAO messageDao = new MessageDAO();
        UserDAO userDao = new UserDAO();
        List<Talk> lt = talkDao.getListTalk(null);
        
        if (lt.size()>0){
            talk = lt.get(0);
            List<Message> lm = messageDao.getListMessage(talk);            
            
            lm.stream().forEach((m) -> {
                listMessage.addMessage(userDao.getUser(m.getIdUser()), m.getText());
            });            
        }
        
    }   
    
}
