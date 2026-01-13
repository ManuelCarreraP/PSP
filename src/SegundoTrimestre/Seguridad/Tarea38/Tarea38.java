package SegundoTrimestre.Seguridad.Tarea38;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class Tarea38 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-REGISTRO-");
        System.out.print("Cree su contraseña: ");
        String pass = sc.nextLine();

        System.out.println("Usuario registrado. Inicie sesión para probar.");

        String pass1 = calcularHash(pass);

        System.out.println("=".repeat(50));

        System.out.println("Inicie sesión para probar.");
        System.out.print("Introduzca su contraseña: ");
        String Vpass = sc.nextLine();

        String pass2 = calcularHash(Vpass);

        System.out.println("Verificando credenciales...");

        if (pass1.equals(pass2)){
            System.out.println("ACCESO CONCEDIDO");
        } else {
            System.out.println("ERROR: Credenciales inválidas.");
        }

    }

    private static String calcularHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte[] resumen = md.digest();

        String hex = HexFormat.of().formatHex(resumen);
        return hex;
    }

}
