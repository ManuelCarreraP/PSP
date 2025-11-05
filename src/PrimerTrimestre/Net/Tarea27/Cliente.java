package PrimerTrimestre.Net.Tarea27;

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
            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in)
            );

            System.out.println("Escribe mensajes para enviar al servidor (escribe 'adios' para terminar):");

            String mensajeUsuario;
            while (true) {
                System.out.print("Tú: ");
                mensajeUsuario = teclado.readLine();

                escritor.println(mensajeUsuario);

                if (mensajeUsuario.equalsIgnoreCase("adios")) {
                    System.out.println("Finalizando conexión...");
                    break;
                }

                String respuestaServidor = lector.readLine();
                if (respuestaServidor != null) {
                    System.out.println("Servidor: " + respuestaServidor);
                } else {
                    System.out.println("Servidor cerró la conexión");
                    break;
                }
            }

            System.out.println("Conexión finalizada");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}