package PrimerTrimestre.Procesos.Tarea02;

public class Tarea02 {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long mb = 1024 * 1024;

        System.out.println("Memoria total: " + rt.totalMemory() / mb + " MiB");
        System.out.println("Memoria libre: " + rt.freeMemory() / mb + " MiB");
        System.out.println("Memoria en uso: " + (rt.totalMemory() - rt.freeMemory()) / mb + " MiB");

        System.out.println("\n Número de procesadores para JVM: " + rt.availableProcessors());

        System.out.println("\n Propiedades de la clase System");
        System.getProperties().list(System.out);
    }
}
