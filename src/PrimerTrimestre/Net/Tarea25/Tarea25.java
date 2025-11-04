package PrimerTrimestre.Net.Tarea25;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Tarea25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String confirmacion;

        do {
            System.out.print("Introduce una dirección IP (o 'localhost'): ");
            String ip = sc.next();


            if (ip.equalsIgnoreCase("localhost")) {
                ip = "127.0.0.1";
            }

            System.out.println("\nESCANEANDO PUERTOS FAMOSOS EN " + ip);
            int[] puertos = {21, 22, 80, 443};
            String[] servicios = {"FTP", "SSH", "HTTP", "HTTPS"};

            for (int i = 0; i < puertos.length; i++) {
                escanearPuerto(ip, puertos[i], servicios[i]);
            }

            System.out.print("\n¿Quieres escanear un puerto específico? (-1 para no): ");
            int puerto = sc.nextInt();

            if (puerto != -1 && puerto > 0 && puerto <= 65535) {
                escanearPuerto(ip, puerto, "ESPECÍFICO");
            } else if (puerto != -1) {
                System.out.println("Puerto inválido. Debe estar entre 1 y 65535");
            }

            System.out.print("\n¿Desea continuar? (escribe 'salir' para terminar): ");
            confirmacion = sc.next();

        } while (!confirmacion.equalsIgnoreCase("salir"));

        System.out.println("Programa terminado.");
        sc.close();
    }

    public static void escanearPuerto(String ip, int puerto, String servicio) {
        try {
            Socket socket = new Socket();
            socket.connect(new java.net.InetSocketAddress(ip, puerto), 3000);
            socket.close();
            System.out.println("Puerto " + puerto + " (" + servicio + ") - ABIERTO");
        } catch (IOException e) {
            System.out.println("Puerto " + puerto + " (" + servicio + ") - CERRADO");
        }
    }
}