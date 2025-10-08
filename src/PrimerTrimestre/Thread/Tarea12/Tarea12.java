package PrimerTrimestre.Thread.Tarea12;

// fichero: tarea12.java

public class Tarea12 {
    public static void main(String[] args) {
        SumaHilo hPares = new SumaHilo(1);
        SumaHilo hImpares = new SumaHilo(2);
        SumaHilo hTerm34 = new SumaHilo(3);

        hPares.start();
        hImpares.start();
        hTerm34.start();

        try {
            hPares.join();
            hImpares.join();
            hTerm34.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("\nTodos los hilos han finalizado.");
        System.out.println("Suma pares: " + hPares.getSuma());
        System.out.println("Suma impares: " + hImpares.getSuma());
        System.out.println("Suma terminados en 3 o 4: " + hTerm34.getSuma());
    }
}

class SumaHilo extends Thread {
    private final int tipo;
    private long suma;

    public SumaHilo(int tipo) {
        this.tipo = tipo;
        this.suma = 0;
        if (tipo == 1) setName("Hilo - Pares");
        else if (tipo == 2) setName("Hilo - Impares");
        else setName("Hilo - Terminados en 3 o 4");
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1923; i++) {
            if (tipo == 1) { // pares
                if (i % 2 == 0) suma += i;
            } else if (tipo == 2) { // impares
                if (i % 2 != 0) suma += i;
            } else { // terminados en 3 o 4
                int ultimo = i % 10;
                if (ultimo == 3 || ultimo == 4) suma += i;
            }
        }
        System.out.println("[" + getName() + "] suma total: " + suma);
    }

    public long getSuma() {
        return suma;
    }
}
