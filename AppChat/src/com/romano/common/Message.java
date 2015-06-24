/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.common;

import com.romano.view.AppChat;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 * Classe Message é utilizada para exibir mensagens tratadas para 
 * o usuário, substituindo o JOptionPane
 * @author Leonardo Camargo
 */
public class Message {
    
    /**
     * Método show, returna uma tela de mensagem de acordo com parâmetros passados
     * para ela.
     * @param alertType
     * Tipo de Alerta usado para diferenciar as mensagens(CONFIRMATION, ERROR,
     * INFORMATION, NONE, WARNING)
     * @param message
     * Descrição da mensagem que será exibida ao usuário
     * @return 
     * Este método retorna uma tela de mensagem ao usuário
     */
    public static Alert show(AlertType alertType, String message){
        Alert alert = new Alert(alertType, message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(AppChat.STAGE);
        alert.getDialogPane().setContentText(message);
        alert.getDialogPane().setHeaderText(null);
        alert.showAndWait()
            .filter(response -> response == ButtonType.OK)
            .ifPresent(response -> System.out.println("The alert was approved"));
        return alert;
    }
    
}
