package PrimerTrimestre.Net.Tarea34;

import java.util.Scanner;

public class ComparadorWebs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce dos URLs distintas (por ejemplo, https://www.google.com y https://www.bing.com)");
        System.out.print("Introduce la primera URL: ");
        String url1 = scanner.nextLine().trim();

        System.out.print("Introduce la segunda URL: ");
        String url2 = scanner.nextLine().trim();
        scanner.close();

        // Obtener tiempo y tamaño de cada web
        ResultadoWeb resultado1 = ResultadoWeb.analizarURL(url1);
        ResultadoWeb resultado2 = ResultadoWeb.analizarURL(url2);

        if (resultado1.getTiempoMs() < resultado2.getTiempoMs()) {
            System.out.println("La web más rápida ha sido: " + url1 + " con " + resultado1.getTiempoMs() + " ms.");
        } else {
            System.out.println("La web más rápida ha sido: " + url2 + " con " + resultado2.getTiempoMs() + " ms.");
        }

        if (resultado1.getTamanoCaracteres() > resultado2.getTamanoCaracteres()) {
            System.out.println("La web con más contenido ha sido: " + url1 + " con " + resultado1.getTamanoCaracteres() + " caracteres.");
        } else {
            System.out.println("La web con más contenido ha sido: " + url2 + " con " + resultado2.getTamanoCaracteres() + " caracteres.");
        }
    }
}