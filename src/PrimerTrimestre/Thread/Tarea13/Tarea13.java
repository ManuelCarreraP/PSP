package PrimerTrimestre.Thread.Tarea13;

import java.util.Random;

public class Tarea13 extends Thread{

    private static final Random random = new Random();


    public Tarea13(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i == 10) {
                System.out.println("[" + getName() + "] Interaccion: " + i + " - Finalizado");
            } else {
                System.out.println("Nombre del hilo: " + getName() + ", Interaccion: " + i + ", Prioridad: " + getPriority());
                try { Thread.sleep(1000 * (1 + random.nextInt(10)));
                } catch (InterruptedException error) {
                    System.out.println("Error: " + error);
                }
            }
        }
    }

    public static void main(String[] args) {
        Tarea13 hilo1 = new Tarea13("Hilo - 1");
        Tarea13 hilo2 = new Tarea13("Hilo - 2");
        Tarea13 hilo3 = new Tarea13("Hilo - 3");
        Tarea13 hilo4 = new Tarea13("Hilo - 4");

        System.out.println("[Hilo - 1] -> Prioridad mínima");
        hilo1.setPriority(Thread.MIN_PRIORITY);
        System.out.println("[Hilo - 3] -> Prioridad máxima");
        hilo3.setPriority(Thread.MAX_PRIORITY);
        System.out.println("[Hilo - 2 y 4] -> Prioridad normal");
        hilo2.setPriority(Thread.NORM_PRIORITY);
        // Tiene la misma prioridad el hilo 4 aunque no se le asigne "NORM_PRIORITY"

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        // La prioridad no funciona

    }
}
