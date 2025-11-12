package PrimerTrimestre.Net.Tarea28;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Servidor iniciado - Esperando conexiones en " + dir);

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress().getHostAddress());

                ClienteHandler clienteHandler = new ClienteHandler(socket);
                Thread hiloCliente = new Thread(clienteHandler);
                hiloCliente.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClienteHandler implements Runnable {
    private Socket socket;
    private BufferedReader lector;
    private PrintWriter escritor;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            lector = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            escritor = new PrintWriter(socket.getOutputStream(), true);

            String direccionCliente = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();
            System.out.println("Iniciando comunicación ECO con cliente: " + direccionCliente);

            String mensajeCliente;
            while ((mensajeCliente = lector.readLine()) != null) {
                System.out.println("Cliente " + direccionCliente + " dice: " + mensajeCliente);

                if (mensajeCliente.equalsIgnoreCase("adios")) {
                    System.out.println("Cliente " + direccionCliente + " se desconecta. Cerrando conexión...");
                    break;
                }

                String respuesta = "ECO: " + mensajeCliente;
                escritor.println(respuesta);
                System.out.println("Servidor responde a " + direccionCliente + ": " + respuesta);
            }

            System.out.println("Conexión finalizada con cliente: " + direccionCliente);
            socket.close();

        } catch (Exception e) {
            System.out.println("Error con cliente " + socket.getInetAddress().getHostAddress() + ": " + e.getMessage());
        }
    }
}