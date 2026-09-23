import javax.swing.JFrame;
public class VentanaPrincipal extends JFrame {

    private Biblioteca biblioteca;

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