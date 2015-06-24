/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.component;

import com.romano.model.User;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Classe que extende de ListView, utilizada para exibir as mensagens ao usuário
 * @author Leonardo Camargo
 */
public class ListMessage extends ListView {
    
    private Label txtUser;
    private Label txtMessage;
    private VBox box;
    
    private static final String[] s = {
                        "linear-gradient(from 0% 0% to 100% 0%, #E1F5FF 0%, #66CCFF 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #E1FFF3 0%, #2FFFAC 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #F3FFCA 0%, #C6FF00 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #FFDCB9 0%, #FF8D1C 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #FFBBBB 0%, #FF4A4A 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #FFD7D8 0%, #FF171D 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #FFDFFC 0%, #FF6AF0 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #E9D5FF 0%, #BD82FF 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #DDDEFF 0%, #888BFF 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #CBF1F8 0%, #5CD3E9 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #E6EED2 0%, #A6C15E 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #DCE2ED 0%, #8DA0C5 100%)",
                        "linear-gradient(from 0% 0% to 100% 0%, #E1CEC6 0%, #AD7963 100%)"};
    
    private final List<User> USERS = new ArrayList();
    
    /**
     * Create da classe
     */
    public ListMessage(){
        super(); 
        if (!USERS.contains(User.getUser())){
            USERS.add(User.getUser());
        }
    }
    
    /**
     * Método addMessage, utilizado para adicionar uma mensagem a lista de mensagens
     * que é exibida ao usuário
     * @param user
     * Usuário que digitou a mensagem
     * @param message 
     * Mensagem digitada pelo usuário.
     */
    public void addMessage(User user, String message){
        if (!USERS.contains(user)){
            USERS.add(user);
        }
        
        txtUser = new Label();
        txtMessage = new Label();
        box = new VBox();
        
        txtUser.setFont(new Font("Arial Black",12));
        
        txtMessage.setFont(new Font("Arial Unicode MS", 16));
        txtMessage.setMaxWidth(getWidth()-20);
        txtMessage.setWrapText(true);
        
        txtUser.setText(getFormatDate()+" - "+user.getName()+":");         
        txtMessage.setText(message);                  
        box.getChildren().add(txtUser);
        box.getChildren().add(txtMessage);         
        box.setBackground(new Background(new BackgroundFill(Paint.valueOf(s[USERS.indexOf(user)]),null,null)));
        box.autosize();        
        getItems().add(box);
        scrollTo(box);
    }
    
    /**
     * Método que formata a data e hora atual para ser exibida junto com a mensagem.
     * @return 
     * Retorna a data atual fomatada
     */
    public String getFormatDate(){
        Format formatter = new SimpleDateFormat("dd/MM HH:mm:ss");
        return formatter.format(new Date());
    }
        
}
