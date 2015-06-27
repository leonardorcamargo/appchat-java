package com.romano.server;

import com.romano.common.Functions;
import com.romano.common.Type;
import com.romano.model.ServerMethod;
import com.romano.model.ServerMethodReturn;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MultiServerThread extends Thread {
    private Socket client = null;    

    public MultiServerThread(Socket client) {
        super("MultiServerThread");
        this.client = client;
    }
    
    @Override
    public void run() {

    	try{
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());         			    	

            ObjectInputStream input = new ObjectInputStream(client.getInputStream());       
            ServerMethod sm = (ServerMethod) input.readObject();                                 
            ServerMethodReturn returnObject = new ServerMethodReturn();
            
            if (!Functions.getUserPassCheck(sm.getUserPass()) ){
                returnObject.setTypeMethod(Type.TypeMethod.ERROR_AUTENTICATION);
                returnObject.setObject(null);                   
            }else{                
                try{                    
                    returnObject.setObject(sm.getFunction().apply(sm.getObject()));
                    returnObject.setTypeMethod(Type.TypeMethod.OK);
                }catch(Exception e){
                    returnObject.setTypeMethod(Type.TypeMethod.ERROR_RETURN);
                    e.printStackTrace();
                }
            }

            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());    

            out.flush();           
            out.writeObject(returnObject);        

            client.close();
	}catch(Exception e) {        
            System.out.println("Erro: " + e.getMessage());     
        }
    	
    }
}