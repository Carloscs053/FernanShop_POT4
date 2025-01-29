package view;

import data.ProductosData;

import jdk.jshell.execution.Util;
import utils.*;
import models.*;

import java.util.Scanner;

import comunicaciones.*;;

public class MainFernanShop {
    final public static Scanner S = new Scanner(System.in);

    public static void main(String[] args) {
        String op;
        boolean logueado = false;
        ProductosData productosData = new ProductosData();
        Tienda tienda = new Tienda();

        tienda.mock();

        while (true) {
            // Printea las opciones del inicio
            op = Menus.menuPrincipal();

            if (!op.equals("1") && !op.equals("2") && !op.equals("3")) {
                System.out.println("Debe introducir un número.");
                Utils.pulseParaContinuar();
            }


            switch (op) {
                case "1":
                    //Reiniciamos el logueado para que al volver a este punto no entre en el perfil anterior
                    logueado = false;
                    // Solicitar nombre y contraseña para iniciar sesión
                    System.out.print("Email: ");
                    String email = S.nextLine();
                    System.out.print("Contraseña: ");
                    String clave = S.nextLine();

                    //Comprueba si es el admin
                    if (tienda.getAdmin().loginAdmin(email, clave)) {
                        logueado = true;
                        Utils.limpiaPantalla();
                        String opAdmin;
                        //Esto va dentro de un do-while y con un switch
                        do {
                            Menus.menuAdmin(tienda);
                            opAdmin = S.nextLine();
                            switch (opAdmin) {
                                case "1":
                                    Menus.menuAsignaPedido(tienda);

                                    break;
                                case "2":
                                    Menus.menuEstado(tienda);
                                    break;
                                case "3":
                                    Menus.altaTrabajador(tienda.getAdmin());
                                    break;
                                case "4":
                                    System.out.println(tienda.verPedidos());
                                    Utils.pulseParaContinuar();
                                    break;
                                case "5":
                                    System.out.println(tienda.verClientes());
                                    Utils.pulseParaContinuar();
                                    break;
                                case "6":
                                    System.out.println(tienda.verTrabajadores());
                                    Utils.pulseParaContinuar();
                                    break;
                                case "7":
                                    // Cerrar sesión
                                    System.out.println("Cerrando sesión...");
                                    Utils.limpiaPantalla();
                                    break;
                                default:
                                    // Opción no válida
                                    System.out.println("Opción no válida. Intente de nuevo.");
                                    Utils.pulseParaContinuar();
                                    Utils.limpiaPantalla();
                            }
                        } while (!opAdmin.equals("7"));
                    } else {
                        //Si no, comprueba si es un trabajador
                        Trabajador tempTrabajador = tienda.loginTrabajador(email, clave);

                        if (tempTrabajador != null) {
                            logueado = true;
                            Utils.limpiaPantalla();
                            String opTrabajador;
                            //Do-while (preguntar por las variables
                            Utils.cargandoPantalla();
                            do {
                                Menus.menuTrabajador(tempTrabajador, productosData, tienda);
                                opTrabajador = S.nextLine();
                                switch (opTrabajador) {
                                    case "1":
                                        // Consultar los pedidos asignados al trabajador
                                        Menus.pedidosTrabajador(tempTrabajador);
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "2":
                                        // Modificar el estado de un pedido
                                        Menus.menuEstado(tienda);
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "3":
                                        // Consultar el catálogo de productos
                                        System.out.println(tienda.verCatalogo());
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "4":
                                        // Modificar un producto del catálogo
                                        Menus.modificarProducto();
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "5":
                                        // Ver el perfil del trabajador
                                        System.out.println(tempTrabajador.verPerfil());
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "6":
                                        // Modificar los datos personales del trabajador
                                        Menus.modificarDatosTrabajador(tempTrabajador);
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                        break;
                                    case "7":
                                        // Cerrar sesión
                                        System.out.println("Cerrando sesión...");
                                        Utils.limpiaPantalla();
                                        break;
                                    default:
                                        // Opción no válida
                                        System.out.println("Opción no válida. Intente de nuevo.");
                                        Utils.pulseParaContinuar();
                                        Utils.limpiaPantalla();
                                }

                            } while (!opTrabajador.equals("7"));

                        } else {
                            //Si no lo es, comprueba si es un cliente
                            Cliente tempCliente = tienda.loginCliente(email, clave);
                            if (tempCliente != null) {
                                if (!tempCliente.isVerificado()) { // Una vez comprobado el cliente, mira si es verificado
                                    System.out.println("Debe verificar su correo electrónico");
                                } else { //Si está verificado, le permite acceder al menú de cliente
                                    logueado = true;
                                    Utils.limpiaPantalla();
                                    //Do-while
                                    String opCliente, opModifica;
                                    do {
                                        Menus.menuCliente(tempCliente);
                                        opCliente = S.nextLine();

                                        switch (opCliente) {
                                            case "1":
                                                //Aquí pinta el catálogo
                                                System.out.println(tienda.pintaCatalogo());
                                                Utils.pulseParaContinuar();
                                                Utils.limpiaPantalla();
                                                break;
                                            case "2":
                                                // Función para realizar un pedido
                                                realizaPedido(tienda, tempCliente);
                                                Utils.pulseParaContinuar();
                                                Utils.limpiaPantalla();
                                                break;
                                            case "3":
                                                //Función que
                                                verPedidos(tempCliente);
                                                Utils.pulseParaContinuar();
                                                Utils.limpiaPantalla();
                                                break;
                                            case "4":
                                                // Función que muestra los datos del cliente
                                                mostrarDatosCliente(tempCliente);
                                                Utils.pulseParaContinuar();
                                                Utils.limpiaPantalla();
                                                break;
                                            case "5":
                                                //Función que modifica los datos del cliente
                                                do {
                                                    mostrarDatosCliente(tempCliente);
                                                    //System.out.println(tempCliente.verCliente());
                                                    System.out.println();
                                                    Menus.modificaCliente();
                                                    opModifica = modificaDatosCliente(tempCliente, tienda);

                                                } while (!opModifica.equals("6"));

                                                break;
                                            case "6":
                                                //Sale de la sesión del usuario
                                                System.out.println("Hasta pronto!");
                                                Utils.pulseParaContinuar();
                                                Utils.limpiaPantalla();
                                                break;
                                            default:
                                                System.out.println("Opción no válida");
                                                break;
                                        }
                                    } while (!opCliente.equals("6"));
                                }
                            } else {
                                //Si no es nada de lo anterior, las credenciales no son correctas
                                System.out.println("Email y/o contraseña incorrectas");
                                Utils.pulseParaContinuar();
                                Utils.limpiaPantalla();
                            }
                        }
                    }
                    break;

                case "2":
                    // Solicitar al usuario que se registre llamando a una función
                    registraUsuario(tienda);
                    Utils.pulseParaContinuar();
                    Utils.limpiaPantalla();
                    break;
                case "3":
                    verificaToken(tienda);
                    Utils.pulseParaContinuar();
                    Utils.limpiaPantalla();
                    break;
                default:
                    // Mostrar mensaje de error si la opción no es válida
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
            Utils.limpiaPantalla();
        }
    }

    private static void verificaToken(Tienda tienda) {
        System.out.print("Introduzca su email:\s");
        String email = S.nextLine();
        if (tienda.existeEmail(email)) {
            if (tienda.compruebaVerificado(email)) {
                System.out.println("Email ya verificado.");
            } else {
                System.out.println("Introduzca el código de verificación");
                int tokenTeclado = 0;
                try {
                    tokenTeclado = Integer.parseInt(S.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Debe introducir un número");
                }
                if (tienda.verificaTokenCliente(email, tokenTeclado)) {
                    System.out.println("Cuenta verificada con éxito.");
                } else {
                    System.out.println("Ha ocurrido un error al verificar la cuenta.");
                }
            }

        } else {
            System.out.println("El email introducido no existe.");
        }
    }

    private static String modificaDatosCliente(Cliente tempCliente, Tienda tienda) {
        String opModifica, email;
        opModifica = S.nextLine();
        //TODO quizás sea más conveniente un método para cada campo
        switch (opModifica) {
            case "1":
                System.out.print("Introduzca el nuevo nombre: ");
                String nombre = S.nextLine();
                tempCliente.modificaNombre(nombre);
                System.out.println("Nombre modificado correctamente.");
                break;
            case "2":
                System.out.print("Introduzca el nuevo apellido: ");
                String apellido = S.nextLine();
                tempCliente.modificaApellido(apellido);
                System.out.println("Apellido modificado correctamente.");
                break;
            case "3":
                System.out.println("Introduzca el nuevo email: ");
                email = S.nextLine();
                if (tempCliente.modificaEmail(email)) {
                    enviaTokenCliente(email, tienda);
                    System.out.println("Email modificado correctamente.");
                } else System.out.println("Email no válido.");
                break;
            case "4":
                System.out.println("Introduzca el nuevo número de teléfono: ");
                String telefono = S.nextLine();
                if (tempCliente.modificaTelefono(telefono)) {
                    System.out.println("Teléfono modificado correctamente.");
                } else System.out.println("Teléfono no permitido.");

                break;
            case "5":
                System.out.print("Indique la nueva localidad: ");
                String localidad = S.nextLine();
                System.out.println();
                System.out.print("Indique la nueva provincia: ");
                String provincia = S.nextLine();
                System.out.println();
                System.out.print("Indique la nueva dirección: ");
                String direccion = S.nextLine();
                System.out.println();
                tempCliente.modificaDireccion(localidad, provincia, direccion);
                System.out.println("Dirección modificada correctamente.");
                break;
            case "6":
                Utils.limpiaPantalla();
                break;
            default:
        }
        return opModifica;
    }

    private static void mostrarDatosCliente(Cliente tempCliente) {
        System.out.println(tempCliente.verCliente());
    }

    private static void verPedidos(Cliente tempCliente) {
        System.out.println(tempCliente.verPedidos(tempCliente));
    }

    private static void realizaPedido(Tienda tienda, Cliente tempCliente) {
        String opProducto;
        int cantidad = 0;
        do {
            System.out.println(tienda.pintaCatalogo());
            System.out.println("6. Terminar pedido");
            System.out.println("7. Cancelar pedido");
            System.out.println();
            System.out.print("Indique el producto que desee (6 para confirmar el pedido y " +
                    "7 para cancelarlo: ");
            opProducto = S.nextLine();
            switch (opProducto) {
                case "1", "2", "3", "4", "5":
                    System.out.print("Indique la cantidad deseada: ");
                    try {
                        cantidad = Integer.parseInt(S.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Debe introducir un número");
                    }
                    if (tempCliente.realizaPedido(tempCliente, opProducto, cantidad)) {
                        System.out.println("Producto añadido correctamente.");
                    } else
                        System.out.println("No se ha podido añadir el producto.");
                    Utils.pulseParaContinuar();
                    break;
                case "6":
                    if (tempCliente.confirmaPedido()) {
                        //tienda.totalPrecio(tempCliente);
                        System.out.println("¡Pedido finalizado con éxito!");
                        System.out.println();
                        if (tempCliente.pintaPedido(tempCliente).equals("-1")) {
                            System.out.println("Se ha producido un error.");
                        } else {
                            System.out.println(tempCliente.pintaPedido(tempCliente));
                        }
                        Utils.pulseParaContinuar();
                        Utils.limpiaPantalla();
                    } else {
                        System.out.println("No puedes realizar un pedido sin productos.");
                        Utils.pulseParaContinuar();
                        Utils.limpiaPantalla();
                    }

                    break;
                case "7":
                    tempCliente.cancelaPedido();
                    System.out.println("Pedido cancelado.");
                    Utils.pulseParaContinuar();
                    Utils.limpiaPantalla();
                    break;
                default:
                    break;
            }
        } while (!opProducto.equals("6") && !opProducto.equals("7"));

    }

    // Hay que enviarle el token que se haya generado al crear el usuario, desde el controlador se puede acceder, o
    // podemos crear un método que lo consulte y se pasa por aquí
    public static void enviaTokenCliente(String email, Tienda tienda) {
        //Hay que insertar el html
        String contenido = "";// Aquí va el contenido del mensaje, es decir, el resultado obtenido

        if (email.equals(tienda.getCliente1().getEmail())) {
            contenido = String.valueOf(tienda.getCliente1().getToken());
        }
        if (email.equals(tienda.getCliente2().getEmail())) {
            contenido = String.valueOf(tienda.getCliente2().getToken());
        }

        //Correo que vayamos a usar de ejemplo
        if (Comunicaciones.enviarMensaje(email, "Código de verificación - FernanShop",
                contenido)) {
            System.out.println("Código de verificación enviado a su correo. Por favor, revise su bandeja de entrada" +
                    " y verifique su cuenta.");
        } else {
            System.out.println("Error al enviar el código de verificación.");
        }
    }

    private static void registraUsuario(Tienda tienda) {
        String nombre, apellido, direccion, localidad, provincia, telefono, clave, email;

        // Comprueba que los registros estén llenos (desaparecerá con los Arrays)
        if (tienda.registrosLlenos()) System.out.println("No se puede dar de alta a más clientes.");
        else { // Si los registros no están llenos, pide la información necesaria para crear un cliente nuevo
            System.out.println("==== Registro de un nuevo cliente ====");
            System.out.println();
            System.out.print("Introduzca su email: ");
            email = S.nextLine();
            if (tienda.existeEmail(email)) { // Antes de continuar, si el email ya existe no permite seguir con el registro
                System.out.println("El email introducido ya está en uso");
            } else { // Si no existe, continuamos con la petición de datos
                System.out.print("Introduzca su nombre: ");
                nombre = S.nextLine();
                System.out.print("Introduzca su apellido: ");
                apellido = S.nextLine();
                System.out.print("Introduzca su dirección: ");
                direccion = S.nextLine();
                System.out.print("Introduzca su localidad: ");
                localidad = S.nextLine();
                System.out.print("Introduzca su provincia: ");
                provincia = S.nextLine();
                System.out.print("Introduzca su teléfono: ");
                telefono = S.nextLine();
                System.out.print("Introduzca su clave: ");
                clave = S.nextLine();
                if (tienda.registraCliente(nombre, apellido, direccion, localidad, provincia, email, telefono,
                        clave)) {
                    enviaTokenCliente(email, tienda);
                    System.out.println("Cliente dado de alta correctamente");
                } else System.out.println("Ha ocurrido un error. Inténtelo de nuevo");
            }
        }
    }
}
