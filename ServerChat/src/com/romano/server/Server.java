package com.romano.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

/**
 *
 * @author leonardo
 */
public class Server {
    
    private static ServerSocket server;	    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean listening = true;
        int port = 0;

        /*try{
            Message.input("AppChat Port", "Server Port for connection...",
                                        "Enter the server port number for client connections: ");
            port = Integer.parseInt(Message.input("AppChat Port", "Server Port for connection...",
                                        "Enter the server port number for client connections: "));
        }catch(NumberFormatException e){
            Message.show(Alert.AlertType.ERROR, "Invalid port number!\nThe server will be aborted");
            System.exit(0);
        }*/
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Digite o número da porta que será utilizado: ");
        port = scan.nextInt();

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
