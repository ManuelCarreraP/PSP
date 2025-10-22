package PrimerTrimestre.Thread.Repaso;

import java.util.Random;

public class R1 extends Thread {
    private static int totalEspectadores = 0;

    private static final Object lock = new Object();

    private static final Random random = new Random();

    public R1(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try {
                Thread.sleep(random.nextInt(1));
            } catch (InterruptedException e) {
                System.out.println("Error en " + getName() + ": " + e);
            }

            synchronized (lock) {
                totalEspectadores++;
            }

            System.out.println("[" + getName() + "] - Nº personas: " + i);

            if (i == 1000){
                System.out.println("[" + getName() + "] Finalizado. Total personas procesadas: " + i);
            }
        }


    }

    public static void main(String[] args) {
        R1 t1 = new R1("Torno 1");
        R1 t2 = new R1("Torno 2");
        R1 t3 = new R1("Torno 3");
        R1 t4 = new R1("Torno 4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar los hilos: " + e);
        }


        System.out.println("Total de espectadores en el estadio: " + totalEspectadores);

    }
}

