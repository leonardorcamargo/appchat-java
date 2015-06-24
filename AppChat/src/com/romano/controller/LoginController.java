/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.controller;

import com.romano.dao.UserDAO;
import com.romano.common.Message;
import com.romano.model.User;
import com.romano.view.AppChat;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Leonardo Camargo
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label txtTitulo;
    
    @FXML
    private TextField txtLogin;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML 
    private Button btnEntrar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(txtLogin.getText().equals("")){
            Message.show(Alert.AlertType.WARNING, "Informe o Login!");
            txtLogin.requestFocus();
        }else if (txtPassword.getText().equals("")){
            Message.show(Alert.AlertType.WARNING, "Informe o PassWord!");
            txtPassword.requestFocus();
        }else{
            UserDAO dao = new UserDAO();
            User user = dao.getUser(txtLogin.getText(), txtPassword.getText());            
            
            if ( user != null){            
                User.setUser(user);
                try {
                    AppChat.showScreen(FXMLLoader.load(getClass().getResource("/com/romano/view/Chat.fxml")));
                } catch (IOException ex) {                
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                Message.show(Alert.AlertType.WARNING,"Login ou password não encontrados!");
                txtPassword.clear();
                txtLogin.clear();
                txtLogin.requestFocus();
            }
        }        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
