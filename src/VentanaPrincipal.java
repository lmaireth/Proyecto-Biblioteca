import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
                    libro.getCopiasDisponibles()});
            }

        scrollTabla = new JScrollPane(tablaLibros);


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

        lblAgregar = new JLabel("Agregar nuevo libro");
        lblAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        lblAgregar.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel panelFormulario = new JPanel(
                new GridLayout(2, 6, 5, 5)
        );

        txtTitulo = new JTextField();
        txtAutor = new JTextField();
        txtCodigo = new JTextField();
        txtGenero = new JTextField();
        txtAño = new JTextField();
        txtCopias = new JTextField();

        panelFormulario.add(new JLabel("Título"));
        panelFormulario.add(new JLabel("Autor"));
        panelFormulario.add(new JLabel("Código"));
        panelFormulario.add(new JLabel("Género"));
        panelFormulario.add(new JLabel("Año"));
        panelFormulario.add(new JLabel("Copias"));

        panelFormulario.add(txtTitulo);
        panelFormulario.add(txtAutor);
        panelFormulario.add(txtCodigo);
        panelFormulario.add(txtGenero);
        panelFormulario.add(txtAño);
        panelFormulario.add(txtCopias);

        btnAgregar = new JButton("Agregar libro");

        JPanel panelBotonAgregar = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        panelBotonAgregar.add(btnAgregar);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));

        panelSuperior.add(lblAgregar, BorderLayout.NORTH);
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotonAgregar, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

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
            "¿Está seguro de eliminar el libro? \n"
                    +libro.getTitulo(),
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

        btnAgregar.addActionListener(e -> {

            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();
            String codigo = txtCodigo.getText().trim();
            String genero = txtGenero.getText().trim();
            String textoAño = txtAño.getText().trim();
            String textoCopias = txtCopias.getText().trim();


            if (titulo.isEmpty()
                    || autor.isEmpty()
                    || codigo.isEmpty()
                    || genero.isEmpty()
                    || textoAño.isEmpty()
                    || textoCopias.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Todos los campos son obligatorios"
                );

                return;
            }


            int año;
            int copias;


            try {

                año = Integer.parseInt(textoAño);
                copias = Integer.parseInt(textoCopias);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "El año y las copias deben ser números"
                );

                return;
            }


            int añoActual = java.time.Year.now().getValue();

            if (año > añoActual) {

                JOptionPane.showMessageDialog(
                        this,
                        "El año no puede ser mayor al año actual: "
                            + añoActual
                );

                return;
            }


            if (copias < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Las copias disponibles no pueden ser negativas"
                );

                return;
            }


            Libro nuevoLibro = new Libro(
                    titulo,
                    autor,
                    codigo,
                    genero,
                    año,
                    copias
            );


            boolean agregado = biblioteca.agregarLibro(nuevoLibro);

            if (agregado) {

                String autorBuscado = txtBuscarAutor.getText().trim();

                if (autorBuscado.isEmpty()
                        || nuevoLibro.getAutor()
                        .trim()
                        .equalsIgnoreCase(autorBuscado)) {

                    modeloTabla.addRow(new Object[]{
                            nuevoLibro.getCodigo(),
                            nuevoLibro.getTitulo(),
                            nuevoLibro.getAutor(),
                            nuevoLibro.getGenero(),
                            nuevoLibro.getAñoPublicacion(),
                            nuevoLibro.getCopiasDisponibles()
                    });
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Libro agregado correctamente"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "El código ya existe"
                );
            }
        });
    }

    private Libro obtenerLibroSeleccionado() {

        int fila = tablaLibros.getSelectedRow();

        if (fila == -1) {
            return null;
        }

        String codigo = modeloTabla
                .getValueAt(fila, 0)
                .toString();

        for (Libro libro : biblioteca.obtenerTodos()) {

            if (libro.getCodigo().equals(codigo)) {
                return libro;
            }
        }

        return null;
    }
}