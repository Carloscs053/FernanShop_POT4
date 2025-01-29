package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import models.Trabajador;

import java.util.Properties;

import static jakarta.mail.Transport.send;

public class Comunicaciones {
    private static final String host = "smtp.gmail.com";
    private static final String user = "carlos.canada.0503@fernando3martos.com";
    private static final String pass = "kflg urur nutt mpua";

    public static boolean enviarMensaje(String destino, String asunto, String mensaje) {

        // Creamos nuestra variable de propiedades cpn los datos de nuestro servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Obtenemos la sesión en nuestro servidor de correo
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Creamos un mensaje de correo por defecto
            Message message = new MimeMessage(session);

            // En el mensaje, establecemos el receptor
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));

            // Establecemos el Asunto
            message.setSubject(asunto);

            // Añadimos el contenido del mensaje
            message.setContent(mensaje, "text/html");

            // Intentamos mandar el mensaje
            send(message);

        } catch (Exception e) { // Si entra aquí, hemos tenido fallo
            throw new RuntimeException(e);
        }
        return true;
    }

    public static boolean enviaMensajeTelegram(String mensaje, String chat_id) {
        String direccion;
        String fijo = "https://api.telegram.org/bot7963528612:AAG9FEU66LIfpvjjAtYbfWK8G886hIikt3s/sendMessage?chat_id" + chat_id + "=&text=";
        boolean dev = false;

        try {
            // Codificamos el mensaje para que se interprete correctamente en la URL
            String mensajeCodificado = URLEncoder.encode(mensaje, StandardCharsets.UTF_8.toString());
            direccion = fijo + mensajeCodificado;

            URL url = new URL(direccion);
            URLConnection con = url.openConnection();

            dev = true;
        } catch (IOException e) {
            System.out.println("Error al enviar el mensaje: " + e.getMessage());
        }
        return dev;
    }
}