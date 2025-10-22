package PrimerTrimestre.Thread.Repaso;

public class R2 extends Thread {

    private static boolean piezaCogida = false;
    private static boolean piezaPintada = false;
    private static final Object monitor = new Object();

    public R2(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        try {
            if (getName().equals("Operario1")) {
                cogerPieza();
            } else if (getName().equals("Operario2")) {
                pintarPieza();
            } else if (getName().equals("Operario3")) {
                embalarPieza();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cogerPieza() throws InterruptedException {
        synchronized (monitor) {
            System.out.println("[" + getName() + "] - Cogiendo pieza...");
            Thread.sleep(2000);
            piezaCogida = true;
            System.out.println("[" + getName() + "] - Pieza cogida.");
            monitor.notifyAll();
        }
    }

    private void pintarPieza() throws InterruptedException {
        synchronized (monitor) {
            while (!piezaCogida) {
                monitor.wait();
            }
            System.out.println("[" + getName() + "] - Pintando la pieza...");
            Thread.sleep(1000);
            piezaPintada = true;
            System.out.println("[" + getName() + "] - Pieza pintada.");
            monitor.notifyAll();
        }
    }

    private void embalarPieza() throws InterruptedException {
        synchronized (monitor) {
            while (!piezaPintada) {
                monitor.wait();
            }
            System.out.println("[" + getName() + "] - Embalando la pieza...");
            Thread.sleep(1000);
            System.out.println("[" + getName() + "] - Pieza embalada.");
        }
    }

    public static void main(String[] args) {
        R2 op1 = new R2("Operario1");
        R2 op2 = new R2("Operario2");
        R2 op3 = new R2("Operario3");

        op1.start();
        op2.start();
        op3.start();

        try {
            op3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Pieza lista para envío");
    }
}

