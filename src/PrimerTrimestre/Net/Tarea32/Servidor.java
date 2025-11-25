package PrimerTrimestre.Net.Tarea32;

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

            String mensajeCliente;
            while ((mensajeCliente = lector.readLine()) != null) {

                System.out.println("Lista recibida: " + mensajeCliente);

                if (mensajeCliente.equalsIgnoreCase("adios")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                String[] palabras = mensajeCliente.split("[ ,/]+");

                String masLarga = "";
                for (String p : palabras) {
                    if (p.length() > masLarga.length()) {
                        masLarga = p;
                    }
                }

                String respuesta = "Palabra más larga: " + masLarga + " (longitud: " + masLarga.length() + ")";

                escritor.println(respuesta);

                System.out.println("Servidor responde: " + respuesta);
            }

            System.out.println("Conexión finalizada");
            socket.close();
            servidor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}