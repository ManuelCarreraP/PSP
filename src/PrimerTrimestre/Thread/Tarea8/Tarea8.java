package PrimerTrimestre.Thread.Tarea8;

public class Tarea8 extends Thread {

    private int limitePaciencia;

    public Tarea8(String nombre, int limitePaciencia) {
        super(nombre);
        this.limitePaciencia = limitePaciencia;
    }

    @Override
    public void run() {
        for (int i = 1; i <= limitePaciencia; i++) {
            if (i == limitePaciencia) {
                System.out.println("[" + getName() + "] Cabreo nivel: " + i + "... ¡He llegado a mi límite!");
            } else {
                System.out.println("[" + getName() + "] Cabreo nivel: " + i);
            }
        }
    }

    public static void main(String[] args) {
        new Tarea8("Diego", 2).start();
        new Tarea8("Manuel", 5).start();
        new Tarea8("Damian", 3).start();
        new Tarea8("Araujo", 5).start();
        new Tarea8("Elena", 8).start();
        new Tarea8("Iago", 6).start();
        new Tarea8("Taxa", 8).start();

        System.out.println("Programa principal terminado.");
    }
}

