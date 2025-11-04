package PrimerTrimestre.Procesos.Tarea5;

import java.io.IOException;
import java.util.Scanner;

public class Tarea5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String comando;

        System.out.println("Si quiere finalizar el programa ingrese \"salir\"");

        while (true) {
            System.out.print("Introduce el comando que quiera ejecutar: ");
            comando = scanner.nextLine().trim();

            if (comando.equalsIgnoreCase("salir")) {
                System.out.println("Programa finalizado con código de salida = 0");
                System.exit(0);
            }

            String[] partes = comando.split(" ");
            try {
                ProcessBuilder pb = new ProcessBuilder(partes);
                pb.inheritIO();
                Process proceso = pb.start();

                int salida = proceso.waitFor();

                System.out.println("\nPrograma ejecutado: " + partes[0]);
                System.out.println("Código de finalización: " + salida + "\n");

            } catch (IOException e) {
                System.err.println("\nError: El comando no se pudo ejecutar. Verifica el nombre o la ruta.");
                System.err.println("Detalle: " + e.getMessage() + "\n");
            } catch (InterruptedException e) {
                System.err.println("\nError: El proceso fue interrumpido.");
                Thread.currentThread().interrupt();
            }
        }
    }
}