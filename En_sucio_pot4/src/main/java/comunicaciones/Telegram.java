package comunicaciones;

import view.*;
import models.*;


public class Telegram {
    public static void mensajePedidoAsignado(Trabajador trabajador) {
        String mensaje = String.format("""
        ···  %s   ···
        ------------------
        Pedidos asignados:
        %s

        Pedidos pendientes:
        %s
        """, trabajador.getNombre(), trabajador.getP1(), trabajador.getP2()); 
        // Aquí se debería de enviar el mensaje a través de la API de Telegram
        Comunicaciones.enviaMensajeTelegram(mensaje, trabajador.getChat_id());
    }
    
    public static void mesajePedidoModificado(Trabajador trabajador) {
        String mensaje = String.format("""
        ···  %s   ···
------------------
El estado del pedido %s ha sido modificado.
Nuevo estado: %s
Comentario: %s
--------------------------
Por favor, revisa la información actualizada.
""",trabajador.getNombre(), trabajador.getP1().getCodigo(), trabajador.getP1().getEstado(), trabajador.getP1().getComentario()); 
        // Aquí se debería de enviar el mensaje a través de la API de Telegram
        Comunicaciones.enviaMensajeTelegram(mensaje, trabajador.getChat_id());
    }
}