package PrimerTrimestre.Net.Tarea26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Esperando conexión....");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");

            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);


            for (int i = 1; i <= 3; i++) {
                String mensajeCliente = lector.readLine();
                System.out.println("Cliente dice: " + mensajeCliente);
            }

            for (int i = 1; i <= 3; i++) {
                escritor.println("Respuesta " + i);
            }

            System.out.println("Conversación finalizada");

            socket.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}