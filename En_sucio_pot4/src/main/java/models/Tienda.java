package models;

import data.ClientesData;
import data.ProductosData;
import data.TrabajadoresData;

public class Tienda {
    // Atributos
    private Admin admin;
    private Cliente cliente1;
    private Cliente cliente2;
    private Trabajador trabajador1;
    private Trabajador trabajador2;
    private Trabajador trabajador3;
    private Pedido pedido1;
    private Pedido pedido2;
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;
    private Producto producto4;
    private Producto producto5;

    // Constructor
    public Tienda() {
        this.cliente1 = null;
        this.cliente2 = null;
        this.trabajador1 = null;
        this.trabajador2 = null;
        this.trabajador3 = null;
        this.pedido1 = null;
        this.pedido2 = null;
        this.admin = null;
        this.producto1 = null;
        this.producto2 = null;
        this.producto3 = null;
        this.producto4 = null;
        this.producto5 = null;
    }

    // Getters y Setters
    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
    }

    public Trabajador getTrabajador1() {
        return trabajador1;
    }

    public void setTrabajador1(Trabajador trabajador1) {
        this.trabajador1 = trabajador1;
    }

    public Trabajador getTrabajador2() {
        return trabajador2;
    }

    public void setTrabajador2(Trabajador trabajador2) {
        this.trabajador2 = trabajador2;
    }

    public Trabajador getTrabajador3() {
        return trabajador3;
    }

    public void setTrabajador3(Trabajador trabajador3) {
        this.trabajador3 = trabajador3;
    }

    public Pedido getPedido1() {
        return pedido1;
    }

    public void setPedido1(Pedido pedido1) {
        this.pedido1 = pedido1;
    }

    public Pedido getPedido2() {
        return pedido2;
    }

    public void setPedido2(Pedido pedido2) {
        this.pedido2 = pedido2;
    }

    public Admin getAdmin() {
        return admin;
    }

    // Otros métodos
    // MOCK de prueba
    public void mock() {
        this.admin = new Admin("admin", "admin", "1234");
        this.cliente1 = ClientesData.cliente1;
        this.cliente2 = null;
        this.trabajador1 = TrabajadoresData.trabajador1;
        this.trabajador2 = TrabajadoresData.trabajador2;
        this.trabajador3 = TrabajadoresData.trabajador3;


        // Inicializar productos
        this.producto1 = ProductosData.Producto1;
        this.producto2 = ProductosData.Producto2;
        this.producto3 = ProductosData.Producto3;
        this.producto4 = ProductosData.Producto4;
        this.producto5 = ProductosData.Producto5;

        // Inicializar pedidos
        /*this.pedido3 = new Pedido(producto4, producto3, "", "Recibido", 0, cliente2);
        this.pedido4 = new Pedido(producto4, producto3, "", "Recibido", 0, cliente2);*/
    }


    public boolean altaCliente(Cliente cliente) {
        if (cliente1 == null) {
            this.cliente1 = cliente;
            return true;
        }
        if (cliente2 == null) {
            this.cliente2 = cliente;
            return true;
        }
        return false;
    }


    public Admin loginAdmin(String email, String clave) {
        if (admin != null && admin.loginAdmin(email, clave)) return admin;
        return null;
    }

    public Cliente loginCliente(String email, String clave) {
        if (cliente1 != null && cliente1.loginCliente(email, clave)) return cliente1;
        if (cliente2 != null && cliente2.getEmail().equals(email) && cliente2.getClave().equals(clave)) return cliente2;
        return null;
    }


    public Trabajador loginTrabajador(String email, String clave) {
        if (trabajador1 != null && trabajador1.loginTrabajador(email, clave)) return trabajador1;
        if (trabajador2 != null && trabajador2.loginTrabajador(email, clave)) return trabajador2;
        if (trabajador3 != null && trabajador3.loginTrabajador(email, clave)) return trabajador3;
        return null;
    }

    public static Trabajador getTrabajadorByEmail(String email) {
        if (TrabajadoresData.trabajador1.getEmail().equals(email)) {
            return TrabajadoresData.trabajador1;
        } else if (TrabajadoresData.trabajador2.getEmail().equals(email)) {
            return TrabajadoresData.trabajador2;
        } else if (TrabajadoresData.trabajador3.getEmail().equals(email)) {
            return TrabajadoresData.trabajador3;
        } else {
            return null;
        }
    }

    // Metodo para generar el catálogo de productos
    public String verCatalogo(ProductosData productosData) {
        String catalogo = "";
        catalogo += ((producto1 != null) ? producto1.pintaDatosProducto() : "") + "\n";
        catalogo += ((producto2 != null) ? producto2.pintaDatosProducto() : "") + "\n";
        catalogo += ((producto3 != null) ? producto3.pintaDatosProducto() : "") + "\n";
        catalogo += ((producto4 != null) ? producto4.pintaDatosProducto() : "") + "\n";
        catalogo += ((producto5 != null) ? producto5.pintaDatosProducto() : "") + "\n";
        return catalogo;
    }
    //Lama a los pintaProducto
    public String pintaCatalogo() {
        String catalogo = "";
        catalogo += ((producto1 != null) ? "1. " + producto1.pintaProducto() : "\n");
        catalogo += ((producto2 != null) ? "2. " + producto2.pintaProducto() : "\n");
        catalogo += ((producto3 != null) ? "3. " + producto3.pintaProducto() : "\n");
        catalogo += ((producto4 != null) ? "4. " + producto4.pintaProducto() : "\n");
        catalogo += ((producto5 != null) ? "5. " + producto5.pintaProducto() : "\n");
        return catalogo;
    }

    // Metodo para comprobar si existe un producto con un código determinado
    public boolean existeCodigoProducto(String codigo) {
        return (producto1 != null && producto1.getCodigo().equalsIgnoreCase(codigo)) ||
                (producto2 != null && producto2.getCodigo().equalsIgnoreCase(codigo)) ||
                (producto3 != null && producto3.getCodigo().equalsIgnoreCase(codigo)) ||
                (producto4 != null && producto4.getCodigo().equalsIgnoreCase(codigo)) ||
                (producto5 != null && producto5.getCodigo().equalsIgnoreCase(codigo));
    }


    //Metodo que comprueba si todos los slots de clientes están ocupados
    public boolean registrosLlenos() {
        if (cliente1 != null && cliente2 != null) return true;
        return false;
    }


    public boolean registraCliente(String nombre, String apellido, String direccion, String localidad, String provincia,
                                   String email, String telefono, String clave) {
        if (cliente1 == null) {
            cliente1 = new Cliente(nombre, apellido, direccion, localidad, provincia, email, telefono, clave);
            if (!cliente1.validaEmail(email)) {
                cliente1 = null;
                return false;
            } else if (cliente1.validaTelefono(telefono)) {
                cliente1 = null;
                return false;
            } else {
                return cliente1.validaEmail(email) && cliente1.validaTelefono(telefono);
            }
        } else if (cliente1 != null && cliente2 == null) {
            cliente2 = new Cliente(nombre, apellido, direccion, localidad, provincia, email, telefono, clave);
            if (!cliente2.validaEmail(email)) {
                cliente2 = null;
                return false;
            } else if (!cliente2.validaTelefono(telefono)) {
                cliente2 = null;
                return false;
            } else {
                return cliente2.validaEmail(email) && cliente2.validaTelefono(telefono);
            }
        }
        return false;
    }

    // Método que calcula los pedidos que no están asignados
    public int calcularPedidosNoAsignados() {
        int count = 0;
        if (pedido1 != null && pedido1.getTrabajador() == null) count++;
        if (pedido2 != null && pedido2.getTrabajador() == null) count++;
        return count;
    }

    public String verPedidos() {
        String pedidos = "";
        pedidos += ((pedido1 != null) ? getPedido1().toString() + "\n" : "\n");
        pedidos += ((pedido2 != null) ? getPedido2().toString() + "\n" : "\n");
        return pedidos;
    }

    public String verClientes() {
        String clientes = "";
        clientes += ((cliente1 != null) ? getCliente1().toString() + "\n" : "\n");
        clientes += ((cliente2 != null) ? getCliente2().toString() + "\n": "\n");
        return clientes;
    }
    public String verTrabajadores() {
        String trabajadores = "";
        trabajadores += ((trabajador1 != null) ? getTrabajador1().toString() + "\n" : "\n");
        trabajadores += ((trabajador2 != null) ? getTrabajador2().toString() + "\n": "\n");
        trabajadores += ((trabajador3 != null) ? getTrabajador3().toString() + "\n": "\n");
        return trabajadores;
    }



    // toString
    @Override
    public String toString() {
        return "Tienda{" +
                "cliente1=" + cliente1 +
                ", cliente2=" + cliente2 +
                ", trabajador1=" + trabajador1 +
                ", trabajador2=" + trabajador2 +
                ", trabajador3=" + trabajador3 +
                ", pedido1=" + pedido1 +
                ", pedido2=" + pedido2 +
                '}';
    }
}