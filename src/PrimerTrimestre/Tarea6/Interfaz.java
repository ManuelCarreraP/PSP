package PrimerTrimestre.Tarea6;

import java.util.Scanner;

public class Interfaz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lanzador lanzador = new Lanzador();
        String host;

        while (true) {
            System.out.println("Introduce el host o IP (o 'salir' para terminar):");
            System.out.print("> ");
            host = scanner.nextLine().trim();

            if (host.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa");
                break;
            }

            int codigoSalida = lanzador.ejecutarPing(host);

            if (codigoSalida == 2){
                System.out.println("[ERROR] ping: " + host + ": Nombre o servicio desconocido");
            }
            System.out.println("Operación Completada. Código de salida: " + codigoSalida + "\n");

        }

        scanner.close();
    }
}

