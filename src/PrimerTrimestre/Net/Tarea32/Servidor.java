package PrimerTrimestre.Net.Tarea32;

import java.io.IOException;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        int puerto = 6666;

        try {
            System.out.println("Servidor arrancando en puerto " + puerto + " - Esperando palabras del cliente...");

            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            while (true) {
                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
                datagramSocket.receive(peticion);

                InetAddress direccionCliente = peticion.getAddress();
                int puertoCliente = peticion.getPort();

                String mensajeCliente = new String(peticion.getData(), 0, peticion.getLength());
                System.out.println("Cliente envió: " + mensajeCliente);

                if (mensajeCliente.equalsIgnoreCase("fin")) {
                    System.out.println("Cliente solicitó terminar la conexión");


                    String respuestaFin = "Conexión terminada";
                    byte[] bufferEnvioFin = respuestaFin.getBytes();
                    DatagramPacket respuestaPacket = new DatagramPacket(
                            bufferEnvioFin,
                            bufferEnvioFin.length,
                            direccionCliente,
                            puertoCliente
                    );
                    datagramSocket.send(respuestaPacket);
                    break;
                }

                String[] palabras = mensajeCliente.split(",");

                String palabraMasLarga = encontrarPalabraMasLarga(palabras);
                int longitud = palabraMasLarga.length();

                String respuesta = "Palabra más larga: '" + palabraMasLarga + "' - Longitud: " + longitud;
                byte[] bufferEnvio = respuesta.getBytes();

                DatagramPacket respuestaPacket = new DatagramPacket(
                        bufferEnvio,
                        bufferEnvio.length,
                        direccionCliente,
                        puertoCliente
                );
                datagramSocket.send(respuestaPacket);
                System.out.println("Servidor respondió: " + respuesta);
            }

            datagramSocket.close();
            System.out.println("Servidor cerrado");

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String encontrarPalabraMasLarga(String[] palabras) {
        if (palabras == null || palabras.length == 0) {
            return "";
        }

        String palabraMasLarga = palabras[0].trim();
        for (String palabra : palabras) {
            if (palabra.trim().length() > palabraMasLarga.length()) {
                palabraMasLarga = palabra.trim();
            }
        }
        return palabraMasLarga;
    }
}
