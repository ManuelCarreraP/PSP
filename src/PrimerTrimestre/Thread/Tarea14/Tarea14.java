package PrimerTrimestre.Thread.Tarea14;

public class Tarea14 {

    static class Caja {
        public static double capital = 1000.0;

        public static synchronized void agregar(double cantidad) {
            capital += cantidad;
        }

        public static synchronized void restar(double cantidad) {
            capital -= cantidad;
        }

        public static synchronized double getCapital() {
            return capital;
        }
    }
    static class HiloIngresos extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) {
                double saldo = Caja.getCapital();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Caja.agregar(10.0);
            }
        }
    }

    static class HiloExtracciones extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3000; i++) {
                double saldo = Caja.getCapital();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Caja.restar(10.0);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Capital inicial: " + Caja.getCapital() + "€");

        Thread ingresos = new HiloIngresos();
        Thread extracciones = new HiloExtracciones();

        ingresos.start();
        extracciones.start();

        try {
            ingresos.join();
            extracciones.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Capital final: " + Caja.getCapital() + "€");


        double esperado = 1000.0 + (5000 * 10) - (3000 * 10);
        System.out.println("Capital esperado: " + esperado + "€");

        if (Caja.getCapital() == esperado) {
            System.out.println("Resultado correcto.");
        } else {
            System.out.println("Resultado incorrecto (problemas de concurrencia).");
        }
    }
}
