package PrimerTrimestre.Net.Tarea26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

        try {
            Socket socket = new Socket();
            socket.connect(dir);

            System.out.println("Conectado al servidor");

            PrintWriter escritor = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            for (int i = 1; i <= 3; i++) {
                escritor.println("Mensaje " + i);
            }

            System.out.println("Mensajes enviados al servidor");

            for (int i = 1; i <= 3; i++) {
                String mensajeServidor = lector.readLine();
                System.out.println("Servidor dice: " + mensajeServidor);
            }

            System.out.println("Conversación finalizada");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}