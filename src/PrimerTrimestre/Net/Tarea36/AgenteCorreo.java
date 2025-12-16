package PrimerTrimestre.Net.Tarea36;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class AgenteCorreo {
    public static void main(String[] args) {
        String usuario = "6cb45354c4b91b";
        String pass = "2c14d44662b0ad";
        String nombre = "Manuel Carrera Pazó";

        try {
            System.out.println("Enviando correo con SMTP...");
            enviarCorreo(usuario, pass, nombre);
            System.out.println("Correo enviado correctamente");

            Thread.sleep(2000);

            System.out.println("Leyendo bandeja con POP3...");
            leerCorreosPOP3(usuario, pass);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void enviarCorreo(String usuario, String password, String nombre)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        MimeMessage mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress("notificaciones@java.com"));
        mensaje.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("destino@ejemplo.com"));
        mensaje.setSubject("Prueba de Agente - " + nombre);
        mensaje.setText("El sistema de notificaciones está activo.");

        Transport.send(mensaje);
    }

    public static void leerCorreosPOP3(String usuario, String password)
            throws MessagingException {

        Properties props = new Properties();
        props.put("mail.pop3.host", "pop3.mailtrap.io");
        props.put("mail.pop3.port", "1100");
        props.put("mail.pop3.auth", "true");

        Session sesion = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        Store almacen = sesion.getStore("pop3");
        almacen.connect(usuario, password);

        Folder bandeja = almacen.getFolder("INBOX");
        bandeja.open(Folder.READ_ONLY);

        Message[] mensajes = bandeja.getMessages();

        System.out.println("Mensajes en bandeja: " + mensajes.length);
        System.out.println("--------------------------------------");

        int totalMensajes = mensajes.length;
        int mensajesAMostrar = Math.min(5, totalMensajes);

        int inicio = Math.max(0, totalMensajes - mensajesAMostrar);

        System.out.println("Mostrando los últimos " + mensajesAMostrar + " mensajes:\n");

        for (int i = inicio; i < totalMensajes; i++) {
            try {
                Message m = mensajes[i];

                String de = "Desconocido";
                String asunto = m.getSubject();

                if (asunto == null) asunto = "(Sin asunto)";

                if (m.getFrom() != null && m.getFrom().length > 0) {
                    de = m.getFrom()[0].toString();
                }

                System.out.println("Correo " + (i + 1) + " (de " + totalMensajes + "):");
                System.out.println("  De: " + de);
                System.out.println("  Asunto: " + asunto);
                System.out.println("--------------------------------------");

            } catch (Exception e) {
                System.out.println("Correo " + (i + 1) + ": [Error al leer]");
                System.out.println("--------------------------------------");
            }
        }

        bandeja.close(false);
        almacen.close();
    }
}
