package vista;

import modelo.Alumno;
import modelo.Instructor;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    
    // ArrayLists para almacenar objetos
    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    private ArrayList<Instructor> listaInstructores = new ArrayList<>();
    
    // Componentes de la interfaz - PESTAÑA ALUMNO
    private JTextField txtNombreA, txtApellidoA, txtCorreoA;
    private JTextField txtFechaNacA, txtTelefonoA, txtDireccionA;
    private JTextField txtFechaNacAlumnoA, txtIdAlumnoA;
    private JButton btnGuardarA, btnMostrarA, btnValidarA, btnLimpiarA;
    private JTextArea txtAreaAlumno;
    
    // Componentes de la interfaz - PESTAÑA INSTRUCTOR
    private JTextField txtNombreI, txtApellidoI, txtCorreoI;
    private JTextField txtFechaNacI, txtTelefonoI, txtDireccionI;
    private JTextField txtFechaIngresoI, txtIdInstructorI;
    private JButton btnGuardarI, btnMostrarI, btnValidarI, btnLimpiarI;
    private JTextArea txtAreaInstructor;
    
    // Constructor
    public VentanaPrincipal() {
        initComponents();
    }
    
    // Método para inicializar componentes
    private void initComponents() {
        // Configuración de la ventana principal
        setTitle("Sistema de Gestión Académica - Alumnos e Instructores");
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Crear el JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Agregar las dos pestañas
        tabbedPane.addTab("Gestión de Alumnos", crearPanelAlumnos());
        tabbedPane.addTab("Gestión de Instructores", crearPanelInstructores());
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel inferior con información
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblInfo = new JLabel("Sistema de Gestión Académica | Ejercicio 03 - Herencia");
        lblInfo.setFont(new Font("Arial", Font.BOLD, 12));
        panelInferior.add(lblInfo);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    // ==================== PANEL DE ALUMNOS ====================
    private JPanel crearPanelAlumnos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Alumno"));
        
        txtNombreA = new JTextField();
        txtApellidoA = new JTextField();
        txtCorreoA = new JTextField();
        txtFechaNacA = new JTextField();
        txtTelefonoA = new JTextField();
        txtDireccionA = new JTextField();
        txtFechaNacAlumnoA = new JTextField();
        txtIdAlumnoA = new JTextField();
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombreA);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellidoA);
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        panelFormulario.add(txtCorreoA);
        panelFormulario.add(new JLabel("Fecha de Nacimiento:"));
        panelFormulario.add(txtFechaNacA);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefonoA);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccionA);
        panelFormulario.add(new JLabel("Fecha Nac. (Alumno):"));
        panelFormulario.add(txtFechaNacAlumnoA);
        panelFormulario.add(new JLabel("ID Alumno:"));
        panelFormulario.add(txtIdAlumnoA);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardarA = new JButton("Guardar Alumno");
        btnMostrarA = new JButton("Mostrar Información");
        btnValidarA = new JButton("Validar Datos");
        btnLimpiarA = new JButton("Limpiar");
        
        btnGuardarA.setBackground(new Color(76, 175, 80));
        btnGuardarA.setForeground(Color.WHITE);
        btnMostrarA.setBackground(new Color(33, 150, 243));
        btnMostrarA.setForeground(Color.WHITE);
        btnValidarA.setBackground(new Color(255, 152, 0));
        btnValidarA.setForeground(Color.WHITE);
        btnLimpiarA.setBackground(new Color(158, 158, 158));
        btnLimpiarA.setForeground(Color.WHITE);
        
        btnGuardarA.addActionListener(e -> btnGuardarAActionPerformed());
        btnMostrarA.addActionListener(e -> btnMostrarAActionPerformed());
        btnValidarA.addActionListener(e -> btnValidarAActionPerformed());
        btnLimpiarA.addActionListener(e -> btnLimpiarAActionPerformed());
        
        panelBotones.add(btnGuardarA);
        panelBotones.add(btnMostrarA);
        panelBotones.add(btnValidarA);
        panelBotones.add(btnLimpiarA);
        
        // Panel con área de texto
        txtAreaAlumno = new JTextArea(12, 40);
        txtAreaAlumno.setEditable(false);
        txtAreaAlumno.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaAlumno);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Alumnos"));
        
        // Ensamblar panel
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== PANEL DE INSTRUCTORES ====================
    private JPanel crearPanelInstructores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Instructor"));
        
        txtNombreI = new JTextField();
        txtApellidoI = new JTextField();
        txtCorreoI = new JTextField();
        txtFechaNacI = new JTextField();
        txtTelefonoI = new JTextField();
        txtDireccionI = new JTextField();
        txtFechaIngresoI = new JTextField();
        txtIdInstructorI = new JTextField();
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombreI);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellidoI);
        panelFormulario.add(new JLabel("Correo Electrónico:"));
        panelFormulario.add(txtCorreoI);
        panelFormulario.add(new JLabel("Fecha de Nacimiento:"));
        panelFormulario.add(txtFechaNacI);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefonoI);
        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccionI);
        panelFormulario.add(new JLabel("Fecha de Ingreso:"));
        panelFormulario.add(txtFechaIngresoI);
        panelFormulario.add(new JLabel("ID Instructor:"));
        panelFormulario.add(txtIdInstructorI);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        btnGuardarI = new JButton("Guardar Instructor");
        btnMostrarI = new JButton("Mostrar Información");
        btnValidarI = new JButton("Validar Datos");
        btnLimpiarI = new JButton("Limpiar");
        
        btnGuardarI.setBackground(new Color(76, 175, 80));
        btnGuardarI.setForeground(Color.WHITE);
        btnMostrarI.setBackground(new Color(33, 150, 243));
        btnMostrarI.setForeground(Color.WHITE);
        btnValidarI.setBackground(new Color(255, 152, 0));
        btnValidarI.setForeground(Color.WHITE);
        btnLimpiarI.setBackground(new Color(158, 158, 158));
        btnLimpiarI.setForeground(Color.WHITE);
        
        btnGuardarI.addActionListener(e -> btnGuardarIActionPerformed());
        btnMostrarI.addActionListener(e -> btnMostrarIActionPerformed());
        btnValidarI.addActionListener(e -> btnValidarIActionPerformed());
        btnLimpiarI.addActionListener(e -> btnLimpiarIActionPerformed());
        
        panelBotones.add(btnGuardarI);
        panelBotones.add(btnMostrarI);
        panelBotones.add(btnValidarI);
        panelBotones.add(btnLimpiarI);
        
        // Panel con área de texto
        txtAreaInstructor = new JTextArea(12, 40);
        txtAreaInstructor.setEditable(false);
        txtAreaInstructor.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtAreaInstructor);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de Instructores"));
        
        // Ensamblar panel
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.NORTH);
        panelSuperior.add(panelBotones, BorderLayout.CENTER);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ==================== EVENTOS DE ALUMNO ====================
    private void btnGuardarAActionPerformed() {
        try {
            String nombre = txtNombreA.getText().trim();
            String apellido = txtApellidoA.getText().trim();
            String correo = txtCorreoA.getText().trim();
            String fechaNac = txtFechaNacA.getText().trim();
            String telefono = txtTelefonoA.getText().trim();
            String direccion = txtDireccionA.getText().trim();
            String fechaNacAlumno = txtFechaNacAlumnoA.getText().trim();
            String idAlumno = txtIdAlumnoA.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || idAlumno.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Complete los campos obligatorios:\n- Nombre\n- Apellido\n- Correo\n- ID Alumno", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, 
                    "El correo electrónico debe ser válido (contener @)", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Alumno alumno = new Alumno(apellido, correo, nombre, fechaNac, 
                                       telefono, direccion, fechaNacAlumno, idAlumno);
            
            listaAlumnos.add(alumno);
            
            JOptionPane.showMessageDialog(this, 
                "Alumno guardado exitosamente\n\n" +
                "Nombre: " + alumno.getNombre() + " " + alumno.getApellido() + "\n" +
                "ID: " + alumno.getIdAlumno() + "\n" +
                "Total de alumnos: " + listaAlumnos.size(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCamposAlumno();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar el alumno: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnMostrarAActionPerformed() {
        txtAreaAlumno.setText("");
        
        if (listaAlumnos.isEmpty()) {
            txtAreaAlumno.setText("No hay alumnos registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════════╗\n");
        sb.append("║          LISTA DE ALUMNOS REGISTRADOS                 ║\n");
        sb.append("╚═══════════════════════════════════════════════════════╝\n\n");
        
        for (int i = 0; i < listaAlumnos.size(); i++) {
            sb.append("┌─ ALUMNO #").append(i + 1).append(" ").append("─".repeat(44)).append("\n");
            sb.append("│\n");
            sb.append(agregarPrefijo(listaAlumnos.get(i).mostrarInformacion(), "│ "));
            sb.append("│\n");
            sb.append("└").append("─".repeat(54)).append("\n\n");
        }
        
        sb.append("Total de alumnos: ").append(listaAlumnos.size()).append("\n");
        
        txtAreaAlumno.setText(sb.toString());
        txtAreaAlumno.setCaretPosition(0);
    }
    
    private void btnValidarAActionPerformed() {
        if (listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay alumnos para validar", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String[] opciones = new String[listaAlumnos.size()];
        for (int i = 0; i < listaAlumnos.size(); i++) {
            opciones[i] = (i + 1) + ". " + listaAlumnos.get(i).toString();
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione el alumno a validar:",
            "Validar Alumno",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null) return;
        
        int indice = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        Alumno alumno = listaAlumnos.get(indice);
        
        String resultadoValidacion = alumno.toValidate();
        
        JTextArea textArea = new JTextArea(resultadoValidacion);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Resultado de Validación - " + alumno.toString(), 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnLimpiarAActionPerformed() {
        limpiarCamposAlumno();
    }
    
    private void limpiarCamposAlumno() {
        txtNombreA.setText("");
        txtApellidoA.setText("");
        txtCorreoA.setText("");
        txtFechaNacA.setText("");
        txtTelefonoA.setText("");
        txtDireccionA.setText("");
        txtFechaNacAlumnoA.setText("");
        txtIdAlumnoA.setText("");
        txtNombreA.requestFocus();
    }
    
    // ==================== EVENTOS DE INSTRUCTOR ====================
    private void btnGuardarIActionPerformed() {
        try {
            String nombre = txtNombreI.getText().trim();
            String apellido = txtApellidoI.getText().trim();
            String correo = txtCorreoI.getText().trim();
            String fechaNac = txtFechaNacI.getText().trim();
            String telefono = txtTelefonoI.getText().trim();
            String direccion = txtDireccionI.getText().trim();
            String fechaIngreso = txtFechaIngresoI.getText().trim();
            String idInstructor = txtIdInstructorI.getText().trim();
            
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || idInstructor.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Complete los campos obligatorios:\n- Nombre\n- Apellido\n- Correo\n- ID Instructor", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!correo.contains("@")) {
                JOptionPane.showMessageDialog(this, 
                    "El correo electrónico debe ser válido (contener @)", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Instructor instructor = new Instructor(apellido, correo, nombre, fechaNac, 
                                                   telefono, direccion, fechaIngreso, idInstructor);
            
            listaInstructores.add(instructor);
            
            JOptionPane.showMessageDialog(this, 
                "Instructor guardado exitosamente\n\n" +
                "Nombre: " + instructor.getNombre() + " " + instructor.getApellido() + "\n" +
                "ID: " + instructor.getIdInstructor() + "\n" +
                "Fecha de Ingreso: " + instructor.getFechaIngreso() + "\n" +
                "Total de instructores: " + listaInstructores.size(), 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            limpiarCamposInstructor();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar el instructor: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnMostrarIActionPerformed() {
        txtAreaInstructor.setText("");
        
        if (listaInstructores.isEmpty()) {
            txtAreaInstructor.setText("No hay instructores registrados");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("╔═══════════════════════════════════════════════════════╗\n");
        sb.append("║       LISTA DE INSTRUCTORES REGISTRADOS               ║\n");
        sb.append("╚═══════════════════════════════════════════════════════╝\n\n");
        
        for (int i = 0; i < listaInstructores.size(); i++) {
            sb.append("┌─ INSTRUCTOR #").append(i + 1).append(" ").append("─".repeat(40)).append("\n");
            sb.append("│\n");
            sb.append(agregarPrefijo(listaInstructores.get(i).mostrarInformacion(), "│ "));
            sb.append("│\n");
            sb.append("└").append("─".repeat(54)).append("\n\n");
        }
        
        sb.append("Total de instructores: ").append(listaInstructores.size()).append("\n");
        
        txtAreaInstructor.setText(sb.toString());
        txtAreaInstructor.setCaretPosition(0);
    }
    
    private void btnValidarIActionPerformed() {
        if (listaInstructores.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay instructores para validar", 
                "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String[] opciones = new String[listaInstructores.size()];
        for (int i = 0; i < listaInstructores.size(); i++) {
            opciones[i] = (i + 1) + ". " + listaInstructores.get(i).toString();
        }
        
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione el instructor a validar:",
            "Validar Instructor",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null) return;
        
        int indice = Integer.parseInt(seleccion.split("\\.")[0]) - 1;
        Instructor instructor = listaInstructores.get(indice);
        
        String resultadoValidacion = instructor.toValidate();
        
        JTextArea textArea = new JTextArea(resultadoValidacion);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        
        JOptionPane.showMessageDialog(this, scrollPane, 
            "Resultado de Validación - " + instructor.toString(), 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void btnLimpiarIActionPerformed() {
        limpiarCamposInstructor();
    }
    
    private void limpiarCamposInstructor() {
        txtNombreI.setText("");
        txtApellidoI.setText("");
        txtCorreoI.setText("");
        txtFechaNacI.setText("");
        txtTelefonoI.setText("");
        txtDireccionI.setText("");
        txtFechaIngresoI.setText("");
        txtIdInstructorI.setText("");
        txtNombreI.requestFocus();
    }
    
    // ==================== MÉTODO AUXILIAR ====================
    private String agregarPrefijo(String texto, String prefijo) {
        String[] lineas = texto.split("\n");
        StringBuilder resultado = new StringBuilder();
        for (String linea : lineas) {
            resultado.append(prefijo).append(linea).append("\n");
        }
        return resultado.toString();
    }
}