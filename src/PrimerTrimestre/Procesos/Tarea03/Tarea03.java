package PrimerTrimestre.Procesos.Tarea03;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tarea03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre del archivo o su ruta completa (incluyendo la extensión):");
        String rutaArchivo = scanner.nextLine().trim();

        if (rutaArchivo.isEmpty()) {
            System.out.println("Debe introducir algún nombre de archivo.");
            return;
        }

        File archivo = new File(rutaArchivo);

        if (archivo.exists()) {
            System.out.println("El archivo existe. Se abrirá en el editor.");
        } else {
            System.out.println("El archivo no existe. Se creará uno nuevo en: " + archivo.getAbsolutePath());
        }

        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        String editor;

        if (sistemaOperativo.contains("win")) {
            editor = "notepad";
        } else if (sistemaOperativo.contains("lin")) {
            editor = "gnome-text-editor";
        } else {
            System.out.println("Sistema operativo no encontrado.");
            return;
        }

        try {
            ProcessBuilder constructor;
            constructor = new ProcessBuilder(editor, archivo.getAbsolutePath());

            System.out.println("Abriendo archivo con el comando: " + String.join(" ", constructor.command()));

            constructor.start();

        } catch (IOException e) {
            System.out.println("Fallo al intentar abrir el editor: " + e.getMessage());
        }
    }
}
