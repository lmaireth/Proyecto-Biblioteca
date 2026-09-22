import java.util.ArrayList;
import java.util.HashMap;

public class Biblioteca {

    private ArrayList<Libro> libros;
    private HashMap<String, Libro> librosPorCodigo;

    public Biblioteca() {
        libros = new ArrayList<>();
        librosPorCodigo = new HashMap<>();
    }

    public boolean agregarLibro(Libro libro) {

        if (librosPorCodigo.containsKey(libro.getCodigo())) {
            return false;
        }

        libros.add(libro);
        librosPorCodigo.put(libro.getCodigo(), libro);

        return true;
    }

    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
        librosPorCodigo.remove(libro.getCodigo());
    }

    public ArrayList<Libro> obtenerTodos() {
        return libros;
    }

    public ArrayList<Libro> filtrarPorAutor(String autor) {

        ArrayList<Libro> resultado = new ArrayList<>();

        for (Libro libro : libros) {

            if (libro.getAutor().equals(autor)) {
                resultado.add(libro);
            }
        }

        return resultado;
    }
}
