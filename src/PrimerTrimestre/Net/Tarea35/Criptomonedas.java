package PrimerTrimestre.Net.Tarea35;

import java.util.Scanner;

public class Criptomonedas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Introduce el nombre o simbolo de la criptomoneda:");
            System.out.println("Ejemplos: Bitcoin (BTC), Ethereum (ETH), Dogecoin (DOGE)");
            System.out.print("Nombre: ");
            String entrada = scanner.nextLine().trim();
            System.out.println("");


            if (!entrada.isEmpty()) {
                CryptoAPI.consultarCriptomoneda(entrada);
            } else {
                System.out.println("No se introdujo ninguna entrada.");
            }

            System.out.print("\n¿Desea buscar otra criptomoneda? (s/n): ");
            String respuesta = scanner.nextLine().toLowerCase();
            continuar = respuesta.equals("s") || respuesta.equals("si");
        }

        System.out.println("\nPrograma finalizado.");
        scanner.close();
    }
}