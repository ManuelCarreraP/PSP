package PrimerTrimestre.Thread.Tarea15;

public class Tarea15 extends Thread {
    private final String nombre;

    public Tarea15(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 8; i++) {
            System.out.println("Soy el " + nombre + " - iteración: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static void main(String[] args) {
        Tarea15 hilo3 = new Tarea15("Hilo 3");
        Tarea15 hilo2 = new Tarea15("Hilo 2");
        Tarea15 hilo1 = new Tarea15("Hilo 1");

        try {
            hilo3.start();
            hilo3.join();

            hilo2.start();
            hilo2.join();

            hilo1.start();
            hilo1.join();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }

        System.out.println("Programa principal terminado.");
    }
}



