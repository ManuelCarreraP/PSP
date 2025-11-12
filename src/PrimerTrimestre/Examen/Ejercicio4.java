package PrimerTrimestre.Examen;

public class Ejercicio4{
    public static void main(String[] args) {
        Thread hiloAltaPrioridad = new Thread(new Tarea(), "Hilo Alta Prioridad");
        Thread hiloBajaPrioridad = new Thread(new Tarea(), "Hilo Baja Prioridad");

        hiloAltaPrioridad.setPriority(Thread.MAX_PRIORITY);
        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

        hiloBajaPrioridad.start();
        hiloAltaPrioridad.start();
    }

    static class Tarea implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " está ejecutándose.");
        }
    }
}