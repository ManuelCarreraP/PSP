package PrimerTrimestre.Thread.Repaso;

public class R3 extends Thread {

    private static boolean paqueteEntregado = false;
    private static final Object buzon = new Object();

    public R3(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        try {
            if (getName().equals("Cliente")) {
                esperarPaquete();
            } else if (getName().equals("Repartidor")) {
                entregarPaquete();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void esperarPaquete() throws InterruptedException {
        synchronized (buzon) {
            System.out.println("[" + getName() + "] - Esperando el paquete...");
            while (!paqueteEntregado) {
                buzon.wait();
            }
            System.out.println("[" + getName() + "] - Paquete recibido");
        }
    }

    private void entregarPaquete() throws InterruptedException {
        int retraso = (int) (Math.random() * 4000) + 1000;
        Thread.sleep(retraso);

        synchronized (buzon) {
            paqueteEntregado = true;
            System.out.println("[" + getName() + "] - Paquete entregado en el buzón");
            buzon.notify();
        }
    }

    public static void main(String[] args) {
        R3 cliente = new R3("Cliente");
        R3 repartidor = new R3("Repartidor");

        cliente.start();
        repartidor.start();

        try {
            cliente.join();
            repartidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa finalizado");
    }
}


