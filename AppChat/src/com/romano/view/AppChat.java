/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Leonardo Camargo
 */
public class AppChat extends Application {
    
    public static Stage STAGE;
    
    public static void showScreen(Parent root){
        Scene scene = new Scene(root);
        AppChat.STAGE.setScene(scene);        
    }
    
    @Override
    public void start(Stage stage) throws Exception{        
        AppChat.STAGE = stage;    
        AppChat.showScreen(FXMLLoader.load(getClass().getResource("/com/romano/view/Login.fxml")));
        stage.setTitle("wChat LEO");
        stage.show();
    }
    
}
