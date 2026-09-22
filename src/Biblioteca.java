import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Libro> libros;

    public Biblioteca() {

        libros = new ArrayList<>();
    }

    public boolean agregarLibro(Libro libro) {

        return libros.add(libro);
    }

    public void eliminarLibro(Libro libro) {

        libros.remove(libro);

    }

    public ArrayList<Libro> obtenerTodos() {
        return libros;
    }

    public ArrayList<Libro> filtrarPorAutor(String autor) {

        return libros;
    }
}
