package view;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

import static jakarta.mail.Transport.send;

public class Comunicaciones {
    private static final String host = "smtp.gmail.com";
    private static final String user = "carlos.canada.0503@fernando3martos.com";
    private static final String pass = "kflg urur nutt mpua";

    public static boolean enviarMensaje(String destino, String asunto, String mensaje) {

        String contenido;

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
            message.setText(mensaje);

            // Intentamos mandar el mensaje
            send(message);

        } catch (Exception e) { // Si entra aquí, hemos tenido fallo
            throw new RuntimeException(e);
        }
        return true;
    }
}
