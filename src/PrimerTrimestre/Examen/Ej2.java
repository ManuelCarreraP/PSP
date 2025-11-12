package PrimerTrimestre.Examen;

import java.util.Scanner;

public class Ej2 extends Thread{
    private final char digito;
    private final String cadena;
    private static int total = 0;
    private static final Object lock = new Object();
    private int contadorLocal = 0;

    public Ej2(char digito, String cadena) {
        this.digito = digito;
        this.cadena = cadena;
    }

    @Override
    public void run() {
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == digito) {
                contadorLocal++;
            }
        }

        synchronized(lock) {
            total += contadorLocal;
        }

        System.out.println("Digito " + digito + ": " + contadorLocal);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cadena de texto: ");
        String cadena = scanner.nextLine();
        scanner.close();

        System.out.println("\nResultados: ");
        System.out.println("Frecuencia de cada dígito par:");

        Ej2 Digito_2 = new  Ej2('2', cadena);
        Ej2 Digito_4 = new  Ej2('4', cadena);
        Ej2 Digito_6 = new  Ej2('6', cadena);
        Ej2 Digito_8 = new  Ej2('8', cadena);

        Digito_2.start();
        Digito_4.start();
        Digito_6.start();
        Digito_8.start();

        try {
            Digito_2.join();
            Digito_4.join();
            Digito_6.join();
            Digito_8.join();

        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
            Thread.currentThread().interrupt();
        }

        System.out.println("Total de dígitos pares encontrados: " + total);
    }
}