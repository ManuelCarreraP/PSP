package PrimerTrimestre.Tarea04;

import java.io.File;
import java.io.IOException;

public class Tarea04 {
    public static void main(String[] args) {
        try {

            String os = System.getProperty("os.name").toLowerCase();
            String comando;

            if (os.contains("win")) {
                comando = "cmd /c dir";
            } else {
                comando = "ls";
            }

            System.out.println("1: Directorio de trabajo por defecto");
            String userDir = System.getProperty("user.dir");
            System.out.println("Directorio actual: " + new File(".").getAbsolutePath());
            System.out.println("Propiedad user.dir: " + userDir);


            ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
            pb.directory(new File(userDir));
            pb.start();


            String homeDir = System.getProperty("user.home");
            System.setProperty("user.dir", homeDir);
            System.out.println("\n 2: Después de cambiar user.dir a home");
            System.out.println("Directorio actual: " + new File(".").getAbsolutePath());
            System.out.println("Propiedad user.dir: " + System.getProperty("user.dir"));

            pb.directory(new File(homeDir));
            pb.start();


            String tempDir = os.contains("win") ? "C:\\temp" : "/tmp";
            File tempFile = new File(tempDir);

            if (!tempFile.exists()) {
                System.out.println("\nEl directorio temporal no existe, se creará: " + tempDir);
                tempFile.mkdirs();
            }

            System.setProperty("user.dir", tempDir);
            System.out.println("\n 3: Después de cambiar al directorio temporal");
            System.out.println("Directorio actual: " + new File(".").getAbsolutePath());
            System.out.println("Propiedad user.dir: " + System.getProperty("user.dir"));

            pb.directory(tempFile);
            pb.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
