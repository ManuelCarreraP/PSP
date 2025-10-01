package PrimerTrimestre.Thread.Tarea11;

import java.util.Random;
import java.util.Scanner;

public class Tarea11 extends Thread {
    private final int id, total;
    private Tarea11 siguiente;
    private static final Random random = new Random();

    public Tarea11(int id, int total) {
        this.id = id;
        this.total = total;
        setName("Hilo-" + id);
    }

    @Override
    public void run() {
        if (id < total) {
            siguiente = new Tarea11(id + 1, total);
            siguiente.start();
        }

        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + getName() + "] Iteración " + i);
            try { Thread.sleep(100 + random.nextInt(501));
            } catch (InterruptedException error) {
                System.out.println("Error: " + error);
            }
        }

        if (siguiente != null) {
            try { siguiente.join();
            } catch (InterruptedException error) {
                System.out.println("Error: " + error);
            }
        }

        System.out.println("Acabó hilo " + getName());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;

        System.out.print("Introduce el número de hilos (0 para 5 por defecto): ");
        while (true) {
            try {
                N = sc.nextInt();
                if (N == 0) {
                    N = 5;
                    break;
                } else if (N > 0) {
                    break;
                } else {
                    System.out.print("Número inválido, debe ser ≥ 1: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada no válida. Introduce un número: ");
            }
        }

        new Tarea11(1, N).start();
    }
}

