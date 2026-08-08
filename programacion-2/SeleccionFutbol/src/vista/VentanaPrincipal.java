package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
    // Listas para almacenar miembros de la selección
    private ArrayList<Futbolista> futbolistas;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Masajista> masajistas;
    
    // Componentes comunes
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtEdad;
    
    // Componentes específicos - FUTBOLISTA
    private JTextField txtDorsal;
    private JComboBox<String> cmbDemarcacion;
    
    // Componentes específicos - ENTRENADOR
    private JTextField txtIdFederacion;
    
    // Componentes específicos - MASAJISTA
    private JTextField txtTitulacion;
    private JTextField txtAniosExp;
    
    // Paneles específicos
    private JPanel panelFutbolista;
    private JPanel panelEntrenador;
    private JPanel panelMasajista;
    
    // RadioButtons
    private JRadioButton rbFutbolista;
    private JRadioButton rbEntrenador;
    private JRadioButton rbMasajista;
    private ButtonGroup grupoTipo;
    
    // Botones
    private JButton btnAgregar;
    private JButton btnListarFutbolistas;
    private JButton btnListarEntrenadores;
    private JButton btnListarMasajistas;
    private JButton btnListarTodos;
    private JButton btnAcciones;
    private JButton btnLimpiar;
    private JButton btnSalir;
    
    // Área de resultados
    private JTextArea txtResultados;
    
    // Constructor
    public VentanaPrincipal() {
        futbolistas = new ArrayList<>();
        entrenadores = new ArrayList<>();
        masajistas = new ArrayList<>();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Configuración de la ventana
        setTitle("⚽ SISTEMA DE GESTIÓN - SELECCIÓN DE FÚTBOL ⚽");
        setSize(950, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior (título)
        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central (formulario)
        JPanel panelCentral = crearPanelFormulario();
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior (botones y resultados)
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 102, 51)); // Verde fútbol
        panel.setPreferredSize(new Dimension(0, 70));
        
        JLabel lblTitulo = new JLabel("⚽ GESTIÓN DE SELECCIÓN DE FÚTBOL ⚽");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        panel.add(lblTitulo);
        
        return panel;
    }
    
    private JPanel crearPanelFormulario() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // ===== PANEL TIPO DE MIEMBRO =====
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panelTipo.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            "Seleccionar Tipo de Miembro",
            0, 0, new Font("Arial", Font.BOLD, 14)
        ));
        
        rbFutbolista = new JRadioButton("⚽ FUTBOLISTA");
        rbFutbolista.setFont(new Font("Arial", Font.BOLD, 14));
        rbFutbolista.setSelected(true);
        rbFutbolista.addActionListener(e -> mostrarPanelFutbolista());
        
        rbEntrenador = new JRadioButton("🎓 ENTRENADOR");
        rbEntrenador.setFont(new Font("Arial", Font.BOLD, 14));
        rbEntrenador.addActionListener(e -> mostrarPanelEntrenador());
        
        rbMasajista = new JRadioButton("💆 MASAJISTA");
        rbMasajista.setFont(new Font("Arial", Font.BOLD, 14));
        rbMasajista.addActionListener(e -> mostrarPanelMasajista());
        
        grupoTipo = new ButtonGroup();
        grupoTipo.add(rbFutbolista);
        grupoTipo.add(rbEntrenador);
        grupoTipo.add(rbMasajista);
        
        panelTipo.add(rbFutbolista);
        panelTipo.add(rbEntrenador);
        panelTipo.add(rbMasajista);
        
        // ===== PANEL DATOS COMUNES =====
        JPanel panelDatosComunes = new JPanel(new GridBagLayout());
        panelDatosComunes.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
            "📋 Datos Generales",
            0, 0, new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // ID
        gbc.gridx = 0; gbc.gridy = 0;
        panelDatosComunes.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        txtId = new JTextField(20);
        panelDatosComunes.add(txtId, gbc);
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        panelDatosComunes.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        panelDatosComunes.add(txtNombre, gbc);
        
        // Apellidos
        gbc.gridx = 0; gbc.gridy = 2;
        panelDatosComunes.add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        txtApellidos = new JTextField(20);
        panelDatosComunes.add(txtApellidos, gbc);
        
        // Edad
        gbc.gridx = 0; gbc.gridy = 3;
        panelDatosComunes.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        txtEdad = new JTextField(20);
        panelDatosComunes.add(txtEdad, gbc);
        
        // ===== CREAR PANELES ESPECÍFICOS =====
        panelFutbolista = crearPanelFutbolista();
        panelEntrenador = crearPanelEntrenador();
        panelMasajista = crearPanelMasajista();
        
        // ===== PANEL CONTENEDOR DE DATOS =====
        JPanel panelContenidoCompleto = new JPanel();
        panelContenidoCompleto.setLayout(new BoxLayout(panelContenidoCompleto, BoxLayout.Y_AXIS));
        panelContenidoCompleto.add(panelDatosComunes);
        panelContenidoCompleto.add(Box.createVerticalStrut(10));
        panelContenidoCompleto.add(panelFutbolista);
        panelContenidoCompleto.add(panelEntrenador);
        panelContenidoCompleto.add(panelMasajista);
        
        // Agregar todo al panel principal
        panelPrincipal.add(panelTipo, BorderLayout.NORTH);
        panelPrincipal.add(panelContenidoCompleto, BorderLayout.CENTER);
        
        // Mostrar panel futbolista por defecto
        panelEntrenador.setVisible(false);
        panelMasajista.setVisible(false);
        
        return panelPrincipal;
    }
    
    private JPanel crearPanelFutbolista() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 153, 51), 2),
            "⚽ Datos de Futbolista",
            0, 0, new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // Dorsal
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Dorsal:"), gbc);
        gbc.gridx = 1;
        txtDorsal = new JTextField(20);
        panel.add(txtDorsal, gbc);
        
        // Demarcación
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Demarcación:"), gbc);
        gbc.gridx = 1;
        String[] demarcaciones = {"Portero", "Defensa", "Centrocampista", "Delantero"};
        cmbDemarcacion = new JComboBox<>(demarcaciones);
        panel.add(cmbDemarcacion, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelEntrenador() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            "🎓 Datos de Entrenador",
            0, 0, new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // ID Federación
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("ID Federación:"), gbc);
        gbc.gridx = 1;
        txtIdFederacion = new JTextField(20);
        panel.add(txtIdFederacion, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelMasajista() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(230, 126, 34), 2),
            "💆 Datos de Masajista",
            0, 0, new Font("Arial", Font.BOLD, 12)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // Titulación
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Titulación:"), gbc);
        gbc.gridx = 1;
        txtTitulacion = new JTextField(20);
        panel.add(txtTitulacion, gbc);
        
        // Años de Experiencia
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Años de Experiencia:"), gbc);
        gbc.gridx = 1;
        txtAniosExp = new JTextField(20);
        panel.add(txtAniosExp, gbc);
        
        return panel;
    }
    
    private JPanel crearPanelInferior() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 10, 10));
        
        btnAgregar = crearBoton("➕ Agregar", new Color(46, 204, 113));
        btnAgregar.addActionListener(e -> agregarMiembro());
        
        btnListarFutbolistas = crearBoton("⚽ Futbolistas", new Color(0, 153, 51));
        btnListarFutbolistas.addActionListener(e -> listarFutbolistas());
        
        btnListarEntrenadores = crearBoton("🎓 Entrenadores", new Color(41, 128, 185));
        btnListarEntrenadores.addActionListener(e -> listarEntrenadores());
        
        btnListarMasajistas = crearBoton("💆 Masajistas", new Color(230, 126, 34));
        btnListarMasajistas.addActionListener(e -> listarMasajistas());
        
        btnListarTodos = crearBoton("📋 Listar Todos", new Color(142, 68, 173));
        btnListarTodos.addActionListener(e -> listarTodos());
        
        btnAcciones = crearBoton("🎬 Ver Acciones", new Color(52, 152, 219));
        btnAcciones.addActionListener(e -> mostrarAcciones());
        
        btnLimpiar = crearBoton("🗑️ Limpiar", new Color(241, 196, 15));
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        btnSalir = crearBoton("❌ Salir", new Color(231, 76, 60));
        btnSalir.addActionListener(e -> salir());
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnListarFutbolistas);
        panelBotones.add(btnListarEntrenadores);
        panelBotones.add(btnListarMasajistas);
        panelBotones.add(btnListarTodos);
        panelBotones.add(btnAcciones);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);
        
        // Panel de resultados
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        
        txtResultados = new JTextArea(10, 50);
        txtResultados.setEditable(false);
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(txtResultados);
        panelResultados.add(scrollPane, BorderLayout.CENTER);
        
        panelPrincipal.add(panelBotones, BorderLayout.NORTH);
        panelPrincipal.add(panelResultados, BorderLayout.CENTER);
        
        return panelPrincipal;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setFocusPainted(false);
        return boton;
    }
    
    // Métodos de control de visibilidad
    private void mostrarPanelFutbolista() {
        panelFutbolista.setVisible(true);
        panelEntrenador.setVisible(false);
        panelMasajista.setVisible(false);
        limpiarCamposEspecificos();
    }
    
    private void mostrarPanelEntrenador() {
        panelFutbolista.setVisible(false);
        panelEntrenador.setVisible(true);
        panelMasajista.setVisible(false);
        limpiarCamposEspecificos();
    }
    
    private void mostrarPanelMasajista() {
        panelFutbolista.setVisible(false);
        panelEntrenador.setVisible(false);
        panelMasajista.setVisible(true);
        limpiarCamposEspecificos();
    }
    
    // Método para agregar miembro
    private void agregarMiembro() {
        try {
            // Validar datos comunes
            if (!validarDatosComunes()) {
                return;
            }
            
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String apellidos = txtApellidos.getText().trim();
            int edad = Integer.parseInt(txtEdad.getText().trim());
            
            if (rbFutbolista.isSelected()) {
                // Agregar Futbolista
                if (!validarDatosFutbolista()) {
                    return;
                }
                
                int dorsal = Integer.parseInt(txtDorsal.getText().trim());
                String demarcacion = (String) cmbDemarcacion.getSelectedItem();
                
                Futbolista fut = new Futbolista(id, nombre, apellidos, edad, dorsal, demarcacion);
                futbolistas.add(fut);
                
                JOptionPane.showMessageDialog(this,
                    "✅ Futbolista agregado exitosamente\n" +
                    "Dorsal: #" + dorsal + " - " + demarcacion,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } else if (rbEntrenador.isSelected()) {
                // Agregar Entrenador
                if (!validarDatosEntrenador()) {
                    return;
                }
                
                int idFed = Integer.parseInt(txtIdFederacion.getText().trim());
                
                Entrenador ent = new Entrenador(id, nombre, apellidos, edad, idFed);
                entrenadores.add(ent);
                
                JOptionPane.showMessageDialog(this,
                    "✅ Entrenador agregado exitosamente\n" +
                    "ID Federación: " + idFed,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
            } else if (rbMasajista.isSelected()) {
                // Agregar Masajista
                if (!validarDatosMasajista()) {
                    return;
                }
                
                String titulacion = txtTitulacion.getText().trim();
                int anios = Integer.parseInt(txtAniosExp.getText().trim());
                
                Masajista mas = new Masajista(id, nombre, apellidos, edad, titulacion, anios);
                masajistas.add(mas);
                
                JOptionPane.showMessageDialog(this,
                    "✅ Masajista agregado exitosamente\n" +
                    "Titulación: " + titulacion,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
            limpiarCampos();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "❌ Error: Verifique que los valores numéricos sean correctos",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Validaciones
    private boolean validarDatosComunes() {
        if (txtId.getText().trim().isEmpty() ||
            txtNombre.getText().trim().isEmpty() ||
            txtApellidos.getText().trim().isEmpty() ||
            txtEdad.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this,
                "❌ Complete todos los datos generales",
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean validarDatosFutbolista() {
        if (txtDorsal.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "❌ Complete el dorsal del futbolista",
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean validarDatosEntrenador() {
        if (txtIdFederacion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "❌ Complete el ID de Federación",
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean validarDatosMasajista() {
        if (txtTitulacion.getText().trim().isEmpty() ||
            txtAniosExp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "❌ Complete todos los datos del masajista",
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    // Métodos de listado
    private void listarFutbolistas() {
        if (futbolistas.isEmpty()) {
            txtResultados.setText("No hay futbolistas registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("              ⚽ LISTA DE FUTBOLISTAS ⚽\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        for (Futbolista f : futbolistas) {
            sb.append(f.mostrarInfo()).append("\n\n");
        }
        
        sb.append("Total: ").append(futbolistas.size()).append(" futbolista(s)");
        txtResultados.setText(sb.toString());
    }
    
    private void listarEntrenadores() {
        if (entrenadores.isEmpty()) {
            txtResultados.setText("No hay entrenadores registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("              🎓 LISTA DE ENTRENADORES 🎓\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        for (Entrenador e : entrenadores) {
            sb.append(e.mostrarInfo()).append("\n\n");
        }
        
        sb.append("Total: ").append(entrenadores.size()).append(" entrenador(es)");
        txtResultados.setText(sb.toString());
    }
    
    private void listarMasajistas() {
        if (masajistas.isEmpty()) {
            txtResultados.setText("No hay masajistas registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("              💆 LISTA DE MASAJISTAS 💆\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        for (Masajista m : masajistas) {
            sb.append(m.mostrarInfo()).append("\n\n");
        }
        
        sb.append("Total: ").append(masajistas.size()).append(" masajista(s)");
        txtResultados.setText(sb.toString());
    }
    
    private void listarTodos() {
        int total = futbolistas.size() + entrenadores.size() + masajistas.size();
        
        if (total == 0) {
            txtResultados.setText("No hay miembros registrados en la selección");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("          🏆 TODA LA SELECCIÓN DE FÚTBOL 🏆\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        if (!futbolistas.isEmpty()) {
            sb.append("⚽ FUTBOLISTAS (" + futbolistas.size() + "):\n");
            sb.append("───────────────────────────────────────────────────────\n");
            for (Futbolista f : futbolistas) {
                sb.append(f.mostrarInfo()).append("\n\n");
            }
        }
        
        if (!entrenadores.isEmpty()) {
            sb.append("🎓 ENTRENADORES (" + entrenadores.size() + "):\n");
            sb.append("───────────────────────────────────────────────────────\n");
            for (Entrenador e : entrenadores) {
                sb.append(e.mostrarInfo()).append("\n\n");
            }
        }
        
        if (!masajistas.isEmpty()) {
            sb.append("💆 MASAJISTAS (" + masajistas.size() + "):\n");
            sb.append("───────────────────────────────────────────────────────\n");
            for (Masajista m : masajistas) {
                sb.append(m.mostrarInfo()).append("\n\n");
            }
        }
        
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("TOTAL DE MIEMBROS: ").append(total);
        txtResultados.setText(sb.toString());
    }
    
    private void mostrarAcciones() {
        int total = futbolistas.size() + entrenadores.size() + masajistas.size();
        
        if (total == 0) {
            JOptionPane.showMessageDialog(this,
                "❌ No hay miembros registrados",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════════════════\n");
        sb.append("          🎬 ACCIONES DE LOS MIEMBROS 🎬\n");
        sb.append("═══════════════════════════════════════════════════════\n\n");
        
        System.out.println("\n" + sb.toString());
        
        // Mostrar acciones de futbolistas
        if (!futbolistas.isEmpty()) {
            System.out.println("⚽ ACCIONES DE FUTBOLISTAS:");
            System.out.println("───────────────────────────────────────────────────────");
            for (Futbolista f : futbolistas) {
                System.out.println("\nFutbolista: " + f.getNombre() + " " + f.getApellidos());
                f.viajar();
                f.concentrarse();
                f.entrenar();
                f.partidoFutbol();
                f.entrevista();
            }
        }
        
        // Mostrar acciones de entrenadores
        if (!entrenadores.isEmpty()) {
            System.out.println("\n🎓 ACCIONES DE ENTRENADORES:");
            System.out.println("───────────────────────────────────────────────────────");
            for (Entrenador e : entrenadores) {
                System.out.println("\nEntrenador: " + e.getNombre() + " " + e.getApellidos());
                e.viajar();
                e.concentrarse();
                e.entrenar();
                e.partidoFutbol();
                e.planificarEntrenamiento();
            }
        }
        
        // Mostrar acciones de masajistas
        if (!masajistas.isEmpty()) {
            System.out.println("\n💆 ACCIONES DE MASAJISTAS:");
            System.out.println("───────────────────────────────────────────────────────");
            for (Masajista m : masajistas) {
                System.out.println("\nMasajista: " + m.getNombre() + " " + m.getApellidos());
                m.viajar();
                m.concentrarse();
                m.entrenar();
                m.partidoFutbol();
                m.darMasaje();
            }
        }
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        
        txtResultados.setText("✅ Acciones ejecutadas correctamente.\n\n" +
                             "Revise la CONSOLA de NetBeans para ver todos los mensajes.\n\n" +
                             "Total de acciones ejecutadas: " + (total * 5));
        
        JOptionPane.showMessageDialog(this,
            "✅ Acciones ejecutadas exitosamente\n\n" +
            "Revise la CONSOLA de NetBeans (Output)\n" +
            "para ver todos los mensajes detallados.",
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Métodos auxiliares
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        limpiarCamposEspecificos();
        txtResultados.setText("");
    }
    
    private void limpiarCamposEspecificos() {
        txtDorsal.setText("");
        cmbDemarcacion.setSelectedIndex(0);
        txtIdFederacion.setText("");
        txtTitulacion.setText("");
        txtAniosExp.setText("");
    }
    
    private void salir() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea salir?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
           System.out.println("\n═══════════════════════════════════════════");
            System.out.println("⚽ SISTEMA CERRADO CORRECTAMENTE ⚽");
            System.out.println("═══════════════════════════════════════════\n");
            System.exit(0);
        }
    }
}
            