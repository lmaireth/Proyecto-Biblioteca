import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private Biblioteca biblioteca;

    private JTable tablaLibros;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;

    private JButton btnAgregar;
    private JButton btnFiltrar;
    private JButton btnMostrarTodos;
    private JButton btnEliminar;

    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCodigo;
    private JTextField txtGenero;
    private JTextField txtAño;
    private JTextField txtCopias;

    private JTextField txtBuscarAutor;

    private JLabel lblCatalogo;
    private JLabel lblAgregar;

    public VentanaPrincipal(Biblioteca biblioteca) {

        this.biblioteca = biblioteca;

        setTitle("Biblioteca");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

String[] columnas = {
        "Código",
        "Título",
        "Autor",
        "Género",
        "Año",
        "Copias"
};

modeloTabla = new DefaultTableModel(columnas, 0);
tablaLibros = new JTable(modeloTabla);


    for (Libro libro : biblioteca.obtenerTodos()) {

        modeloTabla.addRow(new Object[]{
            libro.getCodigo(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getGenero(),
            libro.getAñoPublicacion(),
            libro.getCopiasDisponibles()
        });
    }

scrollTabla = new JScrollPane(tablaLibros);

add(scrollTabla);


private JTextField txtBuscarAutor;

txtBuscarAutor = new JTextField(15);

btnFiltrar = new JButton("Filtrar");
btnMostrarTodos = new JButton("Mostrar todos");
btnEliminar = new JButton("Eliminar libro");

JPanel panelFiltro = new JPanel(
        new FlowLayout(FlowLayout.CENTER)
);

    panelFiltro.add(new JLabel("Buscar por autor:"));
    panelFiltro.add(txtBuscarAutor);
    panelFiltro.add(btnFiltrar);
    panelFiltro.add(btnMostrarTodos);
    panelFiltro.add(btnEliminar);


    lblCatalogo = new JLabel("Catálogo de libros");
    lblCatalogo.setFont(new Font("Arial", Font.BOLD, 18));
    lblCatalogo.setHorizontalAlignment(SwingConstants.CENTER);


JPanel panelCatalogo = new JPanel(new BorderLayout());

    panelCatalogo.add(lblCatalogo, BorderLayout.NORTH);
    panelCatalogo.add(scrollTabla, BorderLayout.CENTER);
    panelCatalogo.add(panelFiltro, BorderLayout.SOUTH);

add(panelCatalogo, BorderLayout.CENTER);


btnFiltrar.addActionListener(e -> {

String autor = txtBuscarAutor.getText().trim();

    modeloTabla.setRowCount(0);

    for (Libro libro : biblioteca.filtrarPorAutor(autor)) {

        modeloTabla.addRow(new Object[]{
    libro.getCodigo(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getGenero(),
            libro.getAñoPublicacion(),
            libro.getCopiasDisponibles()
});
        }
        });


        btnMostrarTodos.addActionListener(e -> {

        modeloTabla.setRowCount(0);

    for (Libro libro : biblioteca.obtenerTodos()) {

        modeloTabla.addRow(new Object[]{
            libro.getCodigo(),
            libro.getTitulo(),
            libro.getAutor(),
            libro.getGenero(),
            libro.getAñoPublicacion(),
            libro.getCopiasDisponibles()
});
        }
        });

        btnEliminar.addActionListener(e -> {

Libro libro = obtenerLibroSeleccionado();

    if (libro != null) {

int respuesta = JOptionPane.showConfirmDialog(
        this,
        "¿Está seguro de eliminar el libro?\n"
                + libro.getTitulo(),
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION
);

        if (respuesta == JOptionPane.YES_OPTION) {

        biblioteca.eliminarLibro(libro);

            modeloTabla.removeRow(
        tablaLibros.getSelectedRow()
            );

            JOptionPane.showMessageDialog(
                    this,
                            "Libro eliminado correctamente"
);
        }

    } else {

            JOptionPane.showMessageDialog(
                this,
                        "Seleccione un libro para eliminar"
);
    }
        });

btnAgregar = new JButton("Agregar libro");

JPanel panelBotonAgregar = new JPanel(
        new FlowLayout(FlowLayout.RIGHT)
);

    panelBotonAgregar.add(btnAgregar);


// Panel superior
JPanel panelSuperior = new JPanel(
        new BorderLayout(5, 5)
);

    panelSuperior.add(lblAgregar, BorderLayout.NORTH);
    panelSuperior.add(panelFormulario, BorderLayout.CENTER);
    panelSuperior.add(panelBotonAgregar, BorderLayout.SOUTH);

add(panelSuperior, BorderLayout.NORTH);