package PrimerTrimestre.Net.Tarea24;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Tarea24 {
    public static void main(String[] args) throws UnknownHostException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un nombre de dominio: ");
        String dominio = sc.next();

        System.out.println("\nLa dirección IP del dominio '" + dominio + "' es: ");
        try{
            InetAddress ip = InetAddress.getByName(dominio);
            System.out.println(ip.getHostAddress());

        }catch (UnknownHostException e){
            e.printStackTrace();
        }

        System.out.println("\nLa dirección IP del equipo y su nombre es: ");
        try{
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("· IP: " + localHost.getHostAddress());
            System.out.println("· Nombre: " + localHost.getHostName());

        }catch (UnknownHostException e){
            e.printStackTrace();
        }

    }
}
