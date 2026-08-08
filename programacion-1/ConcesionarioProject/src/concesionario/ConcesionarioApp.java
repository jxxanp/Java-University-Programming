package concesionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConcesionarioApp extends JFrame {
private static final int CAPACITY = 100;
private Vehicle[] vehicles = new Vehicle[CAPACITY];
private int count = 0;


private DefaultTableModel tableModel;
private JTable table;
private JTextField tfMatricula, tfMarca, tfModelo, tfPrecio;
private JCheckBox cbEstado;
private JTextField tfBuscarMatricula, tfBuscarMarca;

public ConcesionarioApp() {
    super("Concesionario - Gestión de Vehículos");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(950, 600);
    setLocationRelativeTo(null);
    initComponents();
    addSampleData();
    refreshTable();
}

private void initComponents() {
    setLayout(new BorderLayout());

    // --- TABLA ---
    String[] cols = {"Matrícula", "Marca", "Modelo", "Precio", "Estado"};
    tableModel = new DefaultTableModel(cols, 0) {
        public boolean isCellEditable(int row, int column) { return false; }
    };
    table = new JTable(tableModel);
    add(new JScrollPane(table), BorderLayout.CENTER);

    // --- PANEL SUPERIOR ---
    JPanel topPanel = new JPanel(new GridLayout(2, 1));

    // FORMULARIO
    JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
    tfMatricula = new JTextField(8);
    tfMarca = new JTextField(8);
    tfModelo = new JTextField(8);
    tfPrecio = new JTextField(6);
    cbEstado = new JCheckBox("Vendido");
    JButton btnAdd = new JButton("Agregar Vehículo");
    btnAdd.addActionListener(e -> addVehicleFromForm());

    form.add(new JLabel("Matrícula:")); form.add(tfMatricula);
    form.add(new JLabel("Marca:")); form.add(tfMarca);
    form.add(new JLabel("Modelo:")); form.add(tfModelo);
    form.add(new JLabel("Precio:")); form.add(tfPrecio);
    form.add(cbEstado);
    form.add(btnAdd);
    topPanel.add(form);

    // OPERACIONES
    JPanel ops = new JPanel(new GridLayout(3, 3, 5, 5));
    ops.setBorder(BorderFactory.createTitledBorder("Operaciones de Búsqueda y Ordenamiento"));

    tfBuscarMatricula = new JTextField(8);
    JButton btnBuscarMat = new JButton("Buscar por Matrícula (Binaria)");
    btnBuscarMat.addActionListener(e -> buscarPorMatriculaAction());

    tfBuscarMarca = new JTextField(8);
    JButton btnBuscarMarca = new JButton("Buscar por Marca (Secuencial)");
    btnBuscarMarca.addActionListener(e -> buscarPorMarcaAction());

    JButton btnQuicksort = new JButton("Ordenar por Precio (Quicksort - desc)");
    btnQuicksort.addActionListener(e -> {
        if (count > 1) quicksortPriceDesc(0, count - 1);
        refreshTable();
        JOptionPane.showMessageDialog(this, "Ordenado por precio (descendente) con Quicksort.");
    });

    JButton btnSelection = new JButton("Ordenar por Matrícula (Selección - asc)");
    btnSelection.addActionListener(e -> {
        if (count > 1) selectionSortByMatriculaAsc();
        refreshTable();
        JOptionPane.showMessageDialog(this, "Ordenado por matrícula (ascendente) con Selección.");
    });

    JButton btnShell = new JButton("Ordenar por Marca (Shell - desc)");
    btnShell.addActionListener(e -> {
        if (count > 1) shellSortByMarcaDesc();
        refreshTable();
        JOptionPane.showMessageDialog(this, "Ordenado por marca (descendente) con Shell.");
    });

    ops.add(new JLabel("Matrícula:"));
    ops.add(tfBuscarMatricula);
    ops.add(btnBuscarMat);
    ops.add(new JLabel("Marca:"));
    ops.add(tfBuscarMarca);
    ops.add(btnBuscarMarca);
    ops.add(btnQuicksort);
    ops.add(btnSelection);
    ops.add(btnShell);

    topPanel.add(ops);
    add(topPanel, BorderLayout.NORTH);

    // --- PANEL INFERIOR ---
    JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton btnEliminar = new JButton("Eliminar seleccionado");
    btnEliminar.addActionListener(e -> eliminarSeleccionado());
    JButton btnRefrescar = new JButton("Refrescar");
    btnRefrescar.addActionListener(e -> refreshTable());
    bottom.add(btnEliminar);
    bottom.add(btnRefrescar);
    add(bottom, BorderLayout.SOUTH);
}

// ---- MÉTODOS DE OPERACIÓN ----
private boolean addVehicle(Vehicle v) {
    if (count >= CAPACITY) return false;
    vehicles[count++] = v;
    return true;
}

private void addVehicleFromForm() {
    try {
        String m = tfMatricula.getText().trim();
        String mar = tfMarca.getText().trim();
        String mod = tfModelo.getText().trim();
        double p = Double.parseDouble(tfPrecio.getText().trim());
        boolean est = cbEstado.isSelected();

        if (m.isEmpty() || mar.isEmpty() || mod.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete matrícula, marca y modelo.");
            return;
        }
        if (buscarIndicePorMatriculaSecuencial(m) != -1) {
            JOptionPane.showMessageDialog(this, "Ya existe un vehículo con esa matrícula.");
            return;
        }
        if (addVehicle(new Vehicle(m, mar, mod, p, est))) {
            refreshTable();
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Capacidad máxima alcanzada.");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Precio inválido.");
    }
}

private void clearForm() {
    tfMatricula.setText("");
    tfMarca.setText("");
    tfModelo.setText("");
    tfPrecio.setText("");
    cbEstado.setSelected(false);
}

private void eliminarSeleccionado() {
    int sel = table.getSelectedRow();
    if (sel == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        return;
    }
    String matricula = tableModel.getValueAt(sel, 0).toString();
    int idx = findIndexByMatricula(matricula);
    if (idx != -1) {
        for (int i = idx; i < count - 1; i++) vehicles[i] = vehicles[i + 1];
        vehicles[--count] = null;
        refreshTable();
    }
}

private int findIndexByMatricula(String matricula) {
    for (int i = 0; i < count; i++) {
        if (vehicles[i].getMatricula().equalsIgnoreCase(matricula)) return i;
    }
    return -1;
}

private int buscarIndicePorMatriculaSecuencial(String m) {
    for (int i = 0; i < count; i++) {
        if (vehicles[i].getMatricula().equalsIgnoreCase(m)) return i;
    }
    return -1;
}

private void refreshTable() {
    tableModel.setRowCount(0);
    for (int i = 0; i < count; i++) {
        Vehicle v = vehicles[i];
        tableModel.addRow(new Object[]{
                v.getMatricula(), v.getMarca(), v.getModelo(), v.getPrecio(),
                v.isEstado() ? "VENDIDO" : "DISPONIBLE"
        });
    }
}

// ---- BÚSQUEDAS ----
private void buscarPorMarcaAction() {
    String marca = tfBuscarMarca.getText().trim();
    if (marca.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese marca a buscar.");
        return;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
        if (vehicles[i].getMarca().equalsIgnoreCase(marca)) {
            sb.append(vehicles[i].toString()).append("\n");
        }
    }
    if (sb.length() == 0) sb.append("No se encontraron vehículos con marca: ").append(marca);
    JOptionPane.showMessageDialog(this, sb.toString());
}

private void buscarPorMatriculaAction() {
    String matricula = tfBuscarMatricula.getText().trim();
    if (matricula.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingrese matrícula a buscar.");
        return;
    }
    selectionSortByMatriculaAsc();
    int idx = binarySearchByMatricula(matricula, 0, count - 1);
    if (idx != -1) {
        JOptionPane.showMessageDialog(this, "Vehículo encontrado:\n" + vehicles[idx]);
    } else {
        JOptionPane.showMessageDialog(this, "No se encontró vehículo con matrícula: " + matricula);
    }
}

private int binarySearchByMatricula(String key, int low, int high) {
    while (low <= high) {
        int mid = (low + high) / 2;
        String midVal = vehicles[mid].getMatricula();
        int cmp = midVal.compareToIgnoreCase(key);
        if (cmp == 0) return mid;
        else if (cmp < 0) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}

// ---- ORDENAMIENTOS ----
private void selectionSortByMatriculaAsc() {
    for (int i = 0; i < count - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < count; j++) {
            if (vehicles[j].getMatricula().compareToIgnoreCase(vehicles[minIdx].getMatricula()) < 0)
                minIdx = j;
        }
        if (minIdx != i) swapVehicles(i, minIdx);
    }
}

private void quicksortPriceDesc(int low, int high) {
    if (low < high) {
        int p = partition(low, high);
        quicksortPriceDesc(low, p - 1);
        quicksortPriceDesc(p + 1, high);
    }
}

private int partition(int low, int high) {
    double pivot = vehicles[high].getPrecio();
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (vehicles[j].getPrecio() > pivot) {
            i++;
            swapVehicles(i, j);
        }
    }
    swapVehicles(i + 1, high);
    return i + 1;
}

private void shellSortByMarcaDesc() {
    int n = count;
    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; i++) {
            Vehicle temp = vehicles[i];
            int j;
            for (j = i; j >= gap &&
                    vehicles[j - gap].getMarca().compareToIgnoreCase(temp.getMarca()) < 0;
                 j -= gap) {
                vehicles[j] = vehicles[j - gap];
            }
            vehicles[j] = temp;
        }
    }
}

private void swapVehicles(int i, int j) {
    Vehicle tmp = vehicles[i];
    vehicles[i] = vehicles[j];
    vehicles[j] = tmp;
}

// ---- DATOS DE EJEMPLO ----
private void addSampleData() {
    addVehicle(new Vehicle("ABC123", "Toyota", "Corolla", 15000.0, false));
    addVehicle(new Vehicle("XYZ987", "Honda", "Civic", 17000.0, true));
    addVehicle(new Vehicle("LMN456", "Ford", "Focus", 12000.0, false));
    addVehicle(new Vehicle("JHK321", "Nissan", "Sentra", 13000.0, false));
    addVehicle(new Vehicle("TUV852", "Chevrolet", "Cruze", 14000.0, true));
}

// ---- MAIN ----
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ConcesionarioApp app = new ConcesionarioApp();
        app.setVisible(true);
    });
}


}
