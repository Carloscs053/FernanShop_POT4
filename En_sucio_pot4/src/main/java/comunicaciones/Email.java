package comunicaciones;

import view.Comunicaciones;
import models.*;

public class Email {
    public static void emailVerificaToken(Cliente cliente) {
        String destino = cliente.getEmail();
        String asunto = "Verifica tu cuenta en FernanShop";
        String mensaje = "<!DOCTYPE html>" +
                "<html lang='es'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta http-equiv='X-UA-Compatible' content='IE=edge'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
                "</head>" +
                "<body style='background-color: #f0f0f0;width: 90%; height: 90%; font-family: Arial, sans-serif;'>" +
                "<div style='width: 33%; height: 90%; background-color: #fff; margin: auto; border-radius: 10px; box-shadow: 0 0 10px rgba(22, 22, 22, 0.1); padding: 14px;'>" +
                "<div style='background-color: #f8f9fa; padding: 3px; text-align: center; border-radius: 5px;'>" +
                "<h1>FernanShop</h1>" +
                "</div>" +
                "<div style='padding: 20px; text-align: justify;'>" +
                "<div style='font-family: monospace; white-space: pre; text-align: center; font-size: 90%; color: #59c9d1;'>" +
                "     .--.                   .---.<br> .---|__|           .-.     |~~~|<br>.--|===|--|_          |_|     |~~~|--.<br> |  |===|  |'\\     .---!~|  .--|   |--|<br> |%%|   |  |.'\\    |===| |--|%%|   |  |<br> |%%|   |  |\\.'\\   |   | |__|  |   |  |<br> |  |   |  | \\  \\  |===| |==|  |   |  |<br> |  |   |__|  \\.'\\ |   |_|__|  |~~~|__|<br> |  |===|--|   \\.'\\|===|~|--|%%|~~~|--|<br>^--^---'--^    `-'`---^-^--^--^---'--<br>" +
                "</div>" +
                "<h2>Codigo de Acceso</h2>" +
                "<p>¡Hola " + cliente.getNombre() + "! Gracias por registrarte en Fernanshop.</p>" +
                "<p>Para activar tu cuenta en Fernanshop, necesitas verificar tu dirección de correo electrónico.</p>" +
                "<div style='background-color: #59c9d1; padding: 2px; text-align: center; border-radius: 5px; color: #000;'>" +
                "<p>Codigo único: <strong>" + cliente.getToken() + "</strong></p>" +
                "</div>" +
                "<div style='margin-top: 20px; margin-bottom: 0px; padding: 10px; border-radius: 5px;'>" +
                "<p style='font-size: 10px; color: #6c757d;'>Ingresa el código en la página de registro para completar tu registro.</p>" +
                "<p style='font-size: 10px; color: #6c757d;'>Nota: Esta invitación estaba dirigida a <strong> " + cliente.getEmail() + "</strong>. Si no esperabas esta invitación, puedes ignorar este correo electrónico. Si Fernanshop te envía demasiados correos electrónicos, puedes bloquearlos o denunciar el abuso.</p>" +
                "</div>" +
                "</div>" +
                "<footer style='background-color: #f8f9fa; text-align: center; border-radius: 5px;'>" +
                "<p style='font-size: 12px; color: #6c757d; padding: 2px;'>© 2025 Fernanshop. Todos los derechos reservados. <br>Desarrollado por Carlos Cañada y Eduardo Cruz</p>" +
                "</footer>" +
                "</div>" +
                "</body>" +
                "</html>";

        Comunicaciones.enviarMensaje(destino, asunto, mensaje);
    }

    public static void emailNuevoCorreo(Cliente cliente) {
        String destino = cliente.getEmail();
        String asunto = "Cambio de correo electrónico en FernanShop";
        String mensaje = "<!DOCTYPE html>" +
                "<html lang='es'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta http-equiv='X-UA-Compatible' content='IE=edge'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
                "</head>" +
                "<body style='background-color: #f0f0f0;width: 90%; height: 90%; font-family: Arial, sans-serif;'>" +
                "<div style='width: 45%; height: 90%; background-color: #fff; margin: auto; border-radius: 10px; box-shadow: 0 0 10px rgba(22, 22, 22, 0.1); padding: 14px;'>" +
                "<div style='background-color: #f8f9fa; padding: 3px; text-align: center; border-radius: 5px;'>" +
                "<h1>FernanShop</h1>" +
                "</div>" +
                "<div style='padding: 20px; text-align: justify;'>" +
                "<h2>Codigo de Acceso</h2>" +
                "<p>¡Hola " + cliente.getNombre() + "! si has modificado tu cuenta en Fernanshop.</p>" +
                "<p>Para activar tu cuenta nueva en Fernanshop, necesitas verificar tu dirección de correo electrónico.</p>" +
                "<div style='background-color: #59c9d1; padding: 2px; text-align: center; border-radius: 5px; color: #000; transition: background-color 0.3s, color 0.3s;' onmouseover=\"this.style.backgroundColor='#44b1a8'; this.style.color='#fff';\" onmouseout=\"this.style.backgroundColor='#59c9d1'; this.style.color='#000';\">" +
                "<p>Codigo único: <strong>" + cliente.getToken() + "</strong></p>" +
                "</div>" +
                "<div style='margin-top: 20px; margin-bottom: 0px; padding: 10px; border-radius: 5px;'>" +
                "<p style='font-size: 10px; color: #6c757d;'>Ingresa el código en la página de modificacion de datos para validar el nuevo correo</p>" +
                "<p style='font-size: 10px; color: #6c757d;'>Nota: Esta invitación estaba dirigida a <strong>" + cliente.getEmail() + "</strong>. Si no esperabas esta invitación, puedes ignorar este correo electrónico. Si Fernanshop te envía demasiados correos electrónicos, puedes bloquearlos o denunciar el abuso.</p>" +
                "</div>" +
                "</div>" +
                "<footer style='background-color: #f8f9fa; text-align: center; border-radius: 5px;'>" +
                "<p style='font-size: 12px; color: #6c757d; padding: 2px;'>© 2025 Fernanshop. Todos los derechos reservados. <br>Desarrollado por Carlos Cañada y Eduardo Cruz</p>" +
                "</footer>" +
                "</div>" +
                "</body>" +
                "</html>";

        Comunicaciones.enviarMensaje(destino, asunto, mensaje);
    }

    public static void emailPedidoModificado(Cliente cliente, Pedido pedido) {
        String destino = cliente.getEmail();
        String asunto = "Pedido " + pedido.getCodigo() + " modificado en FernanShop";
        String mensaje = "<!DOCTYPE html>" +
                "<html lang='es'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta http-equiv='X-UA-Compatible' content='IE=edge'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
                "</head>" +
                "<body style='background-color: #f0f0f0;width: 90%; height: 90%; font-family: Arial, sans-serif;'>" +
                "<div style='width: 45%; height: 90%; border-radius: 5px; background-color: #fff; margin: auto; box-shadow: 0 0 10px rgba(22, 22, 22, 0.1); padding: 14px;'>" +
                "<div style='background-color: #f8f9fa; padding: 3px; text-align: center; border-radius: 5px;'>" +
                "<h1>FernanShop</h1>" +
                "</div>" +
                "<div style='padding: 20px; text-align: justify;'>" +
                "<h2>Modificación de Pedido</h2>" +
                "<p>¡Hola " + cliente.getNombre() + "! Se ha modificando tu pedido en Fernanshop.</p>" +
                "<p>A continuación, encontrarás los detalles actualizados de tu pedido:" + pedido.getCodigo() + "</p>" +
                "<div style='background-color: #59c9d1; padding: 2px; text-align: center; border-radius: 5px; color: #000; '>" +
                "<p>Estado Actual: <strong>" + pedido.getEstado() + "</strong></p>" +
                "<p>Fecha Estimada de Entrega: <strong>"+ pedido.getFechaEntrega() +"</strong></p>" +
                "<p>Comentario:<strong>"+ pedido.getComentario() +"</strong></p>" +
                "</div>" +
                "<div style='margin-top: 20px;  border-radius: 5px;'>" +
                "<p style='font-size: 10px; color: #6c757d;'>Si tienes alguna pregunta o necesitas más información sobre tu pedido, por favor contacta con nuestro servicio de atención al cliente.</p>" +
                "<p style='font-size: 10px; color: #6c757d;'>" +
                "Nota: Esta notificación está dirigida a <strong>" + cliente.getEmail() + "</strong>." +
                "Si no esperabas esta notificación, puedes ignorar este correo electrónico." +
                "Si Fernanshop te envía demasiados correos electrónicos, puedes bloquearlos o denunciar el abuso.</p>" +
                "</div>" +
                "</div>" +
                "<footer style='background-color: #f8f9fa; text-align: center; border-radius: 5px; margin-top: 2px;'>" +
                "<p style='font-size: 12px; color: #6c757d; padding: 4px;'>© 2025 Fernanshop. Todos los derechos reservados. <br>Desarrollado por Carlos Cañada y Eduardo Cruz</p>" +
                "</footer>" +
                "</div>" +
                "</body>" +
                "</html>";

        Comunicaciones.enviarMensaje(destino, asunto, mensaje);
    }
}
