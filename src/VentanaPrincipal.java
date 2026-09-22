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
