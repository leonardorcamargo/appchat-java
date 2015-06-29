package com.romano.server;

import com.romano.common.Message;
import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Server extends Application {

    private static ServerSocket server;	    

    @Override
    public void start(Stage primaryStage) throws Exception {
        boolean listening = true;
        int port = 0;

        try{
            Message.input("AppChat Port", "Server Port for connection...",
                                        "Enter the server port number for client connections: ");
            port = Integer.parseInt(Message.input("AppChat Port", "Server Port for connection...",
                                        "Enter the server port number for client connections: "));
        }catch(NumberFormatException e){
            Message.show(Alert.AlertType.ERROR, "Invalid port number!\nThe server will be aborted");
            System.exit(0);
        }

        try{
            // Instancia o ServerSocket ouvindo a porta 12345
            server = new ServerSocket(port);

            System.out.println("Server hearing port "+port);

            while (listening) {
                    // o método accept() bloqueia a execução até que         
                            // o servidor receba um pedido de conexão
                new MultiServerThread(server.accept()).start();
            }
            } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }        
    }
    
}
