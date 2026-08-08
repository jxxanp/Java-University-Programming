package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    // Datos en memoria
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    // Componentes comunes
    private DefaultListModel<Cliente> modelListaClientes = new DefaultListModel<>();
    private JList<Cliente> listaClientes = new JList<>(modelListaClientes);

    private DefaultListModel<Producto> modelListaProductos = new DefaultListModel<>();
    private JList<Producto> listaProductos = new JList<>(modelListaProductos);

    private DefaultListModel<Vendedor> modelListaVendedores = new DefaultListModel<>();
    private JList<Vendedor> listaVendedores = new JList<>(modelListaVendedores);

    private DefaultListModel<Pedido> modelListaPedidos = new DefaultListModel<>();
    private JList<Pedido> listaPedidos = new JList<>(modelListaPedidos);

    // Constructor
    public VentanaPrincipal() {
        setTitle("Sistema de Gestión de Pedidos - Ejercicio 04");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Clientes", crearPanelClientes());
        pestañas.addTab("Productos", crearPanelProductos());
        pestañas.addTab("Vendedores", crearPanelVendedores());
        pestañas.addTab("Pedidos", crearPanelPedidos());

        add(pestañas);
        inicializarDatosDePrueba();
        actualizarListas();
    }

    private JPanel crearPanelClientes() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtDireccion = new JTextField();
        JTextField txtTelefono = new JTextField();
        JComboBox<String> tipoCliente = new JComboBox<>(new String[]{"Personal", "Corporativo"});
        JTextField txtTarjetaOCContrato = new JTextField();
        JTextField txtLimite = new JTextField();

        form.setBorder(BorderFactory.createTitledBorder("Registrar Cliente"));
        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Dirección:")); form.add(txtDireccion);
        form.add(new JLabel("Teléfono:")); form.add(txtTelefono);
        form.add(new JLabel("Tipo:")); form.add(tipoCliente);
        form.add(new JLabel("Tarjeta / Contrato:")); form.add(txtTarjetaOCContrato);
        form.add(new JLabel("Límite (solo corp):")); form.add(txtLimite);

        JButton btnGuardar = new JButton("Guardar Cliente");
        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nom = txtNombre.getText().trim();
            String dir = txtDireccion.getText().trim();
            String tel = txtTelefono.getText().trim();
            String extra = txtTarjetaOCContrato.getText().trim();
            String tipo = (String) tipoCliente.getSelectedItem();
            if(id.isEmpty() || nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombre son obligatorios.");
                return;
            }
            if(tipo.equals("Personal")) {
                ClientePersonal cp = new ClientePersonal(id, nom, dir, tel, extra);
                clientes.add(cp);
            } else {
                double limite = 0;
                try { limite = Double.parseDouble(txtLimite.getText().trim()); } catch(Exception ex) { limite = 0; }
                ClienteCorporativo cc = new ClienteCorporativo(id, nom, dir, tel, extra, limite);
                clientes.add(cc);
            }
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Cliente guardado.");
            txtId.setText(""); txtNombre.setText(""); txtDireccion.setText(""); txtTelefono.setText("");
            txtTarjetaOCContrato.setText(""); txtLimite.setText("");
        });

        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.add(form, BorderLayout.NORTH);
        izquierda.add(btnGuardar, BorderLayout.SOUTH);

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));
        JScrollPane sp = new JScrollPane(listaClientes);
        derecha.add(sp, BorderLayout.CENTER);

        p.add(izquierda, BorderLayout.WEST);
        p.add(derecha, BorderLayout.CENTER);

        return p;
    }

    private JPanel crearPanelProductos() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        form.setBorder(BorderFactory.createTitledBorder("Registrar Producto"));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Precio:")); form.add(txtPrecio);

        JButton btnGuardar = new JButton("Guardar Producto");
        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nom = txtNombre.getText().trim();
            double precio = 0;
            try { precio = Double.parseDouble(txtPrecio.getText().trim()); } catch(Exception ex) { precio = 0; }
            if(id.isEmpty() || nom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "ID y Nombre son obligatorios.");
                return;
            }
            productos.add(new Producto(id, nom, precio));
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Producto guardado.");
            txtId.setText(""); txtNombre.setText(""); txtPrecio.setText("");
        });

        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.add(form, BorderLayout.NORTH);
        izquierda.add(btnGuardar, BorderLayout.SOUTH);

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.setBorder(BorderFactory.createTitledBorder("Lista de Productos"));
        JScrollPane sp = new JScrollPane(listaProductos);
        derecha.add(sp, BorderLayout.CENTER);

        p.add(izquierda, BorderLayout.WEST);
        p.add(derecha, BorderLayout.CENTER);
        return p;
    }

    private JPanel crearPanelVendedores() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0,2,6,6));
        form.setBorder(BorderFactory.createTitledBorder("Registrar Vendedor"));
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        form.add(new JLabel("ID:")); form.add(txtId);
        form.add(new JLabel("Nombre:")); form.add(txtNombre);

        JButton btnGuardar = new JButton("Guardar Vendedor");
        btnGuardar.addActionListener(e -> {
            String id = txtId.getText().trim();
            String nom = txtNombre.getText().trim();
            if(id.isEmpty() || nom.isEmpty()){
                JOptionPane.showMessageDialog(this, "ID y Nombre obligatorios.");
                return;
            }
            vendedores.add(new Vendedor(id, nom));
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Vendedor guardado.");
            txtId.setText(""); txtNombre.setText("");
        });

        JPanel izquierda = new JPanel(new BorderLayout());
        izquierda.add(form, BorderLayout.NORTH);
        izquierda.add(btnGuardar, BorderLayout.SOUTH);

        JPanel derecha = new JPanel(new BorderLayout());
        derecha.setBorder(BorderFactory.createTitledBorder("Lista de Vendedores"));
        JScrollPane sp = new JScrollPane(listaVendedores);
        derecha.add(sp, BorderLayout.CENTER);

        p.add(izquierda, BorderLayout.WEST);
        p.add(derecha, BorderLayout.CENTER);

        return p;
    }

    private JPanel crearPanelPedidos() {
        JPanel p = new JPanel(new BorderLayout());
        JPanel arriba = new JPanel(new GridLayout(0,2,6,6));
        arriba.setBorder(BorderFactory.createTitledBorder("Crear Pedido"));

        JComboBox<Cliente> cbClientes = new JComboBox<>();
        JComboBox<Producto> cbProductos = new JComboBox<>();
        JTextField txtCantidad = new JTextField("1");
        JTextField txtPedidoId = new JTextField();

        arriba.add(new JLabel("ID Pedido:")); arriba.add(txtPedidoId);
        arriba.add(new JLabel("Cliente:")); arriba.add(cbClientes);
        arriba.add(new JLabel("Producto:")); arriba.add(cbProductos);
        arriba.add(new JLabel("Cantidad:")); arriba.add(txtCantidad);

        DefaultListModel<LineaPedido> modelLineas = new DefaultListModel<>();
        JList<LineaPedido> listaLineas = new JList<>(modelLineas);

        JButton btnAgregarLinea = new JButton("Agregar Línea");
        btnAgregarLinea.addActionListener(e -> {
            Producto prod = (Producto) cbProductos.getSelectedItem();
            int cant = 1;
            try { cant = Integer.parseInt(txtCantidad.getText().trim()); } catch(Exception ex) { cant = 1; }
            if(prod == null) { JOptionPane.showMessageDialog(this, "Seleccione un producto."); return; }
            modelLineas.addElement(new LineaPedido(prod, cant));
        });

        JButton btnCrearPedido = new JButton("Crear Pedido");
        btnCrearPedido.addActionListener(e -> {
            String id = txtPedidoId.getText().trim();
            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            if(id.isEmpty() || cliente == null) {
                JOptionPane.showMessageDialog(this, "ID pedido y cliente son obligatorios.");
                return;
            }
            Pedido ped = new Pedido(id, cliente);
            for(int i=0;i<modelLineas.size();i++){
                ped.agregarLinea(modelLineas.get(i));
            }
            pedidos.add(ped);
            modelLineas.clear();
            txtPedidoId.setText("");
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Pedido creado. Total: $" + ped.getTotal());
        });

        JButton btnMostrarPedido = new JButton("Mostrar Pedido Seleccionado");
        JTextArea areaDetalles = new JTextArea();
        areaDetalles.setEditable(false);

        btnMostrarPedido.addActionListener(e -> {
            Pedido sel = listaPedidos.getSelectedValue();
            if(sel == null) { JOptionPane.showMessageDialog(this, "Seleccione un pedido de la lista."); return; }
            StringBuilder sb = new StringBuilder();
            sb.append("Pedido: ").append(sel.getId()).append("\n");
            sb.append("Cliente: ").append(sel.getCliente().getNombre()).append("\n");
            sb.append("Fecha: ").append(sel.getFechaFormateada()).append("\n");
            sb.append("Lineas:\n");
            for(LineaPedido lp : sel.getLineas()){
                sb.append(" - ").append(lp.toString()).append("\n");
            }
            sb.append("Total: $").append(sel.getTotal()).append("\n");
            areaDetalles.setText(sb.toString());
        });

        JPanel botonesLineas = new JPanel(new GridLayout(1,2,6,6));
        botonesLineas.add(btnAgregarLinea);
        botonesLineas.add(btnCrearPedido);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(arriba, BorderLayout.NORTH);
        panelCentro.add(botonesLineas, BorderLayout.CENTER);
        panelCentro.add(new JScrollPane(listaLineas), BorderLayout.SOUTH);

        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setBorder(BorderFactory.createTitledBorder("Pedidos"));
        panelDerecha.add(new JScrollPane(listaPedidos), BorderLayout.CENTER);
        JPanel abajo = new JPanel(new BorderLayout());
        abajo.add(btnMostrarPedido, BorderLayout.NORTH);
        abajo.add(new JScrollPane(areaDetalles), BorderLayout.CENTER);
        panelDerecha.add(abajo, BorderLayout.SOUTH);

        p.add(panelCentro, BorderLayout.CENTER);
        p.add(panelDerecha, BorderLayout.EAST);

        // sincronizar comboboxes con listas
        // método local para actualizar comboboxes cada vez que se actualizan las listas
        Runnable actualizarCombos = () -> {
            cbClientes.removeAllItems();
            cbProductos.removeAllItems();
            for(Cliente c : clientes) cbClientes.addItem(c);
            for(Producto pr : productos) cbProductos.addItem(pr);

            modelListaPedidos.clear();
            for(Pedido ped : pedidos) modelListaPedidos.addElement(ped);
        };

        // actualizador a usar desde actualizarListas()
        this._pedidoActualizador = actualizarCombos;
        return p;
    }

    // referencia para que crearPanelPedidos pueda registrar el actualizador
    private Runnable _pedidoActualizador = null;

    private void inicializarDatosDePrueba() {
        // Productos
        productos.add(new Producto("P001", "Camiseta", 25.0));
        productos.add(new Producto("P002", "Gorra", 10.0));
        productos.add(new Producto("P003", "Mug", 8.5));

        // Vendedores
        Vendedor v1 = new Vendedor("V001", "Ana Vendedor");
        Vendedor v2 = new Vendedor("V002", "Luis Vendedor");
        vendedores.add(v1); vendedores.add(v2);

        // Clientes
        ClientePersonal cp = new ClientePersonal("C001", "Juan Perez", "Calle 1", "300111222", "4111-XXXX-XXXX-1111");
        ClienteCorporativo cc = new ClienteCorporativo("C002", "Empresa XYZ", "Av 2", "300222333", "CONTR-001", 10000);
        cc.setVendedor(v1);
        v1.agregarCliente(cc);

        clientes.add(cp); clientes.add(cc);
    }

    private void actualizarListas() {
        // clientes
        modelListaClientes.clear();
        for(Cliente c : clientes) modelListaClientes.addElement(c);
        // productos
        modelListaProductos.clear();
        for(Producto p : productos) modelListaProductos.addElement(p);
        // vendedores
        modelListaVendedores.clear();
        for(Vendedor v : vendedores) modelListaVendedores.addElement(v);
        // pedidos (model de la lista de pedidos de la pestaña pedido)
        modelListaPedidos.clear();
        for(Pedido ped : pedidos) modelListaPedidos.addElement(ped);

        // trigger combos en panel pedidos si existen
        if(_pedidoActualizador != null) _pedidoActualizador.run();
    }
}
