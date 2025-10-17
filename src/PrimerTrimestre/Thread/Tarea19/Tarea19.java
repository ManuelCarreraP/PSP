package PrimerTrimestre.Thread.Tarea19;

import java.util.Scanner;

public class Tarea19 extends Thread {
    private final char vocal;
    private final String texto;
    private static int totalVocales = 0;
    private static final Object lock = new Object();
    private int contadorLocal = 0;

    public Tarea19(char vocal, String texto) {
        this.vocal = vocal;
        this.texto = texto.toLowerCase();
    }

    @Override
    public void run() {
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == vocal) {
                contadorLocal++;
            }
        }

        // Sincronizar solo una vez
        synchronized(lock) {
            totalVocales += contadorLocal;
        }

        System.out.println("[Hilo - " + vocal + "] ➜ Vocales contadas: " + contadorLocal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el texto a analizar: ");
        String texto = scanner.nextLine();
        scanner.close();

        Tarea19[] hilos = {
                new Tarea19('a', texto),
                new Tarea19('e', texto),
                new Tarea19('i', texto),
                new Tarea19('o', texto),
                new Tarea19('u', texto)
        };

        // Iniciar todos los hilos
        for (Tarea19 hilo : hilos) {
            hilo.start();
        }

        // Esperar a que todos terminen
        try {
            for (Tarea19 hilo : hilos) {
                hilo.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
            Thread.currentThread().interrupt();
        }

        System.out.println("\nTotal de vocales contadas: " + totalVocales);
    }
}