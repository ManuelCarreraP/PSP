package PrimerTrimestre.Net.Tarea32;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        int puertoServidor = 6666;
        Scanner scanner = new Scanner(System.in);

        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket datagramSocket = new DatagramSocket();

            System.out.println("Cliente iniciado.");
            System.out.println("Escribe palabras separadas por comas ',' (o 'fin' para terminar):");

            while (true) {
                System.out.print("Ingresa palabras: ");
                String entrada = scanner.nextLine();

                byte[] bufferEnvio = entrada.getBytes();
                DatagramPacket pregunta = new DatagramPacket(
                        bufferEnvio,
                        bufferEnvio.length,
                        direccionServidor,
                        puertoServidor
                );
                datagramSocket.send(pregunta);
                System.out.println("Enviado al servidor: " + entrada);

                if (entrada.equalsIgnoreCase("fin")) {
                    break;
                }

                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                datagramSocket.receive(respuesta);

                String respuestaServidor = new String(respuesta.getData(), 0, respuesta.getLength());
                System.out.println("Servidor responde: " + respuestaServidor);
                System.out.println("---");
            }

            datagramSocket.close();
            scanner.close();
            System.out.println("Cliente cerrado");

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}