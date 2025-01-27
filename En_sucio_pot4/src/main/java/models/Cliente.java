package models;

import java.util.Scanner;

public class Cliente {
    private String nombre;
    private String apellido;
    private String direccion;
    private String localidad;
    private String provincia;
    private String telefono;
    private String email;
    private String clave;
    private Pedido pedido1;
    private Pedido pedido2;
    private int cuentaPedido;

    //Constructor
    public Cliente(String nombre, String apellido, String direccion, String localidad, String provincia,
                   String telefono, String email, String clave) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        pedido1 = null;
        pedido2 = null;
        //Posiblemente este contador no haga falta, se comprueban los pedidos y ya
        cuentaPedido = 0;
    }

    //Constructor copia
    //Creo que esto no hace falta
    /*public Cliente(Cliente cliente) {
        nombre = cliente.nombre;
        apellido = cliente.apellido;
        direccion = cliente.direccion;
        localidad = cliente.localidad;
        provincia = cliente.provincia;
        telefono = cliente.telefono;
        email = cliente.email;
        clave = cliente.clave;
        pedido1 = cliente.pedido1;
        pedido2 = cliente.pedido2;
    }*/

    //getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    public void setPedido2(Pedido pedido2) {
        this.pedido2 = pedido2;
    }

    public Pedido getPedido1() {
        return pedido1;
    }


    public Pedido getPedido2() {
        return pedido2;
    }

    public int getCuentaPedido() {
        return cuentaPedido;
    }

    public void setCuentaPedido(int cuentaPedido) {
        this.cuentaPedido = cuentaPedido;
    }

    //Otros métodos
    //Dice si el login es correcto o no
    public boolean loginCliente(String email, String clave) {
        return this.email.equals(email) && this.clave.equals(clave);
    }

    //Indica si tiene los pedidos al completo
    public boolean pedidosLleno() {
        return pedido1 != null && pedido2 != null;
    }

    //Método para realizar un pedido
    public boolean realizaPedido(Cliente tempCliente, String opProducto, int cantidad) {
        if (tempCliente.getPedido1() == null) {
            tempCliente.setPedido1(new Pedido());
        } else if (tempCliente.getPedido2() == null){
            tempCliente.setPedido2(new Pedido());
        }
        if (!tempCliente.pedido1.isRealizado()){
            if (tempCliente.pedido1.getP1() == null) {
                return tempCliente.pedido1.anadeProducto(opProducto, cantidad);
            }
            if (tempCliente.pedido1.getP1() != null && tempCliente.pedido1.getP2() == null) {
                return tempCliente.pedido1.anadeProducto(opProducto, cantidad);
            }
            if (tempCliente.pedido1.getP1() != null && tempCliente.pedido1.getP2() != null &&
                    tempCliente.pedido1.getP3() == null) {
                return tempCliente.pedido1.anadeProducto(opProducto, cantidad);
            }
        } else if (!tempCliente.pedido2.isRealizado()) {
            if (tempCliente.pedido2.getP1() == null) {
                return tempCliente.pedido2.anadeProducto(opProducto, cantidad);
            }
            if (tempCliente.pedido2.getP1() != null && tempCliente.pedido2.getP2() == null) {
                return tempCliente.pedido2.anadeProducto(opProducto, cantidad);
            }
            if (tempCliente.pedido2.getP1() != null && tempCliente.pedido2.getP2() != null &&
                    tempCliente.pedido2.getP3() == null) {
                return tempCliente.pedido2.anadeProducto(opProducto, cantidad);
            }
        }
        return false;
    }

    //Cuenta los pedidos que tiene el cliente
    public int cuentaPedido() {
        setCuentaPedido(0);
        if (pedido1 != null) cuentaPedido++;
        if (pedido2 != null) cuentaPedido++;
        return cuentaPedido;
    }

    //Para eliminar un pedido que se ha empezado pero no acabado, comprueba si se ha creado el objeto pero no ha
    //terminado para settearlo a null
    public void cancelaPedido() {
        if (pedido1 != null && !pedido1.isRealizado()) pedido1 = null;
        if (pedido2 != null && !pedido2.isRealizado()) pedido2 = null;
    }


    //Si confirma el pedido, settea el estado en confirmado
    public boolean confirmaPedido() {
        if (pedido1 != null && !pedido1.isRealizado()) {
            pedido1.setRealizado(true);
            return true;
        }
        if (pedido2 != null && !pedido2.isRealizado()) {
            pedido2.setRealizado(true);
            return true;
        }
        return false;
    }

    public void modificaNombre(String nombre) {
        setNombre(nombre);
    }

    public void modificaApellido(String apellido) {
        setApellido(apellido);
    }

    public boolean validaEmail(String email) {
        return email.contains("@") && (email.endsWith(".com") || email.endsWith(".es"));
    }

    public boolean validaTelefono(String telefono) {
        return (telefono.length() == 9);
    }

    public boolean modificaEmail(String email) {
        if (validaEmail(email)) {
            setEmail(email);
            return true;
        }
        return false;
    }

    public boolean modificaTelefono(String telefono) {
        if (validaTelefono(telefono)) {
            setTelefono(telefono);
            return true;
        }
        return false;
    }

    public void modificaDireccion(String localidad, String provincia, String direccion) {
        setLocalidad(localidad);
        setProvincia(provincia);
        setDireccion(direccion);
    }

    public String verCliente() {
        String salida = "==========================\n";
        salida += "PERFIL DEL CLIENTE\n";
        salida += "==========================\n";
        salida += "Nombre: " + nombre + "\n";
        salida += "Apellido" + apellido + "\n";
        salida += "Email: " + email + "\n";
        salida += "Teléfono: " + telefono + "\n";
        salida += "Dirección: " + direccion + ", " + localidad + "(" + provincia + ")" + "\n";
        salida += "==========================\n";
        return salida;
    }


    public double obtenerTotal(Pedido pedido) {
        return pedido.precioTotal();
    }

    //toString
    /*@Override
    public String toString() {
        return "Cliente: " + nombre + "\nEmail: " + email + "\nDirección: " + direccion +
                "\nTeléfono: " + telefono;
    }*/

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", clave='" + clave + '\'' +
                ", pedido1=" + pedido1 +
                ", pedido2=" + pedido2 +
                ", cuentaPedido=" + cuentaPedido +
                '}';
    }


    public String pintaPedido(Cliente cliente) {
        if (pedido1 != null) {
            return pedido1.pintaPedido(cliente);
        }
        if (pedido2 != null) {
            return pedido2.pintaPedido(cliente);
        }
        return "-1";
    }


    public String verPedidos(Cliente tempCliente) {
        String salida = "Tiene " + ((cuentaPedido() == 0 || cuentaPedido() == 1) ? cuentaPedido() + " pedido realizado"
                : " pedidos realizados");
        salida += "\n";
        salida += (pedido1 != null && pedido2 == null) ? "PRIMER PEDIDO:\n" : "";
        salida += (pedido1 != null && pedido2 == null) ? pedido1.pintaPedido(tempCliente) + "\n" : "";
        salida += (pedido1 != null && pedido2 != null) ? "SEGUNDO PEDIDO:\n" : "";
        salida += (pedido1 != null && pedido2 != null) ? pedido2.pintaPedido(tempCliente) + "\n" : "";
        return salida;
    }
}
