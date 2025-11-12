package PrimerTrimestre.Examen;

public class Ej1 extends Thread{

    public Ej1(String nombre) {
        super(nombre);
    }

    @Override
    public void run(){
        for (int i = 1 ; i <= 8 ; i++){
            System.out.println("Soy el " + getName() + " - interación: " + i);

            try{
                Thread.sleep(100);

            } catch (InterruptedException e){
                System.out.println("Error: " + e);
            }
        }
    }

    public static void main(String[] args) {
        Ej1 hilo1 = new Ej1("Hilo 1");
        Ej1 hilo2 = new Ej1("Hilo 2");
        Ej1 hilo3 = new Ej1("Hilo 3");

        try{
            hilo3.start();
            hilo3.join();

            hilo2.start();
            hilo2.join();

            hilo1.start();
            hilo1.join();

        }  catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal terminado");
    }
}
