package SegundoTrimestre.Seguridad.Tarea37;

import java.util.Scanner;

public class CifradoCesar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-CIFRADO CÉSAR-");
        System.out.print("Introduce el texto a cifrar: ");
        String texto = sc.nextLine().toUpperCase();

        System.out.print("Introduce el número de desplazamientos: ");
        int desplazamiento = sc.nextInt();

        String textoCifrado = cifrarCesar(texto, desplazamiento);

        System.out.println("\nTexto original: " + texto);
        System.out.println("Texto cifrado: " + textoCifrado);

        sc.close();
    }

    public static String cifrarCesar(String texto, int desplazamiento) {
        StringBuilder resultado = new StringBuilder();

        String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        int longitudAlfabeto = alfabeto.length();

        desplazamiento = desplazamiento % longitudAlfabeto;

        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            int indice = alfabeto.indexOf(caracter);

            if (indice != -1) {

                int nuevoIndice = (indice + desplazamiento) % longitudAlfabeto;
                resultado.append(alfabeto.charAt(nuevoIndice));
            } else {
                resultado.append(caracter);
            }
        }

        return resultado.toString();
    }
}