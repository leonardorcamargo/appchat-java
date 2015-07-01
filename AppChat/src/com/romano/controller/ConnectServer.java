/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.romano.controller;

import com.romano.common.Functions;
import com.romano.model.ServerMethod;
import com.romano.model.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leonardo
 */
public class ConnectServer {
    private Socket server = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream input = null;
    
    private String serverName = "localhost";
    private int port = 2323;
    
    
    public ConnectServer(){
        try {
            server = new Socket(serverName,port);                                                                    
        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object accept(Method method, Object obj){
        try {    
            out = new ObjectOutputStream(server.getOutputStream());        
            
            ServerMethod sm = new ServerMethod();                                 
            sm.setUserPass(Functions.getUserPass(User.getUser()));
            
            sm.setMethod(method);
            sm.setObject(obj);
            
            out.flush();
            out.writeObject(out);
            
            input = new ObjectInputStream(server.getInputStream());
            return input.readObject();
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void close(){
        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
