package PrimerTrimestre.Procesos.Tarea6;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lanzador {
    public int ejecutarPing(String host) {
        int codigoSalida = -1;

        try {
            String sistemaOperativo = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;

            if (sistemaOperativo.contains("win")) {
                pb = new ProcessBuilder("ping", "-n", "4", host);
            } else if (sistemaOperativo.contains("lin")) {
                pb = new ProcessBuilder("ping", "-c", "4", host);
            } else {
                System.out.println("Sistema operativo no soportado.");
                return -1;
            }
            Process proceso = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.contains("tiempo") || linea.contains("time") || linea.contains("bytes")) {
                        System.out.println("[OK] " + linea);
                    } else if (linea.contains("inaccesible") || linea.contains("desconocido")
                            || linea.contains("unreachable") || linea.contains("request timed out")) {
                        System.out.println("[ERROR] " + linea);
                    } else {
                        System.out.println("      " + linea);
                    }
                }
            }

            codigoSalida = proceso.waitFor();

        } catch (IOException e) {
            System.err.println("Error al ejecutar el comando ping: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("La ejecución fue interrumpida.");
            Thread.currentThread().interrupt();
        }

        return codigoSalida;
    }
}

