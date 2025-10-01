package PrimerTrimestre.Thread.Tarea10;

import java.util.Random;

public class Tarea10 extends Thread{

    private static final Random random = new Random();


    public Tarea10(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i == 10) {
                System.out.println("[" + getName() + "] Interaccion: " + i + " - Finalizado");
            } else {
                System.out.println("[" + getName() + "] Interaccion: " + i);
                try { Thread.sleep(1000 * (1 + random.nextInt(10)));
                } catch (InterruptedException error) {
                    System.out.println("Error: " + error);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Tarea10("Cristiano").start();
        new Tarea10("Messi").start();
        new Tarea10("Neymar").start();
        new Tarea10("Mbappe").start();

    }
}