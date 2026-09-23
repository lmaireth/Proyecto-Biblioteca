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