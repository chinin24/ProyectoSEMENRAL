import javax.swing.*;
import java.awt.*;

public class VentanaDetalleComida extends JFrame {

    public VentanaDetalleComida(String nombreComida, String imagenPath) {
        setTitle("Detalle de la comida");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel nombre = new JLabel(nombreComida, SwingConstants.CENTER);
        nombre.setFont(new Font("Arial", Font.BOLD, 24));

        ImageIcon imagen = new ImageIcon(imagenPath);
        JLabel imagenLabel = new JLabel(imagen);
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(nombre, BorderLayout.NORTH);
        panel.add(imagenLabel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
