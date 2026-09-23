
public class Libro {

    private String titulo;
    private String autor;
    private String codigo;
    private String genero;
    private int añoPublicacion;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String codigo, String genero, int añoPublicacion, int copiasDisponibles) {

        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.genero = genero;
        this.añoPublicacion = añoPublicacion;
        this.copiasDisponibles = copiasDisponibles;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }
}
