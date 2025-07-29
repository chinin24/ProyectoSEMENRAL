import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class VentanaDetalleComida extends JFrame {

    public VentanaDetalleComida(String nombreComida, String imagenPath) {
        setTitle("Detalle de la comida");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel nombre = new JLabel(nombreComida, SwingConstants.CENTER);
        nombre.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel imagenLabel = new JLabel("Imagen no disponible", SwingConstants.CENTER);

        if (!imagenPath.isEmpty()) {
            try {
                URL imgUrl = getClass().getClassLoader().getResource(imagenPath);
                if (imgUrl != null) {
                    ImageIcon imagen = new ImageIcon(imgUrl);
                    imagenLabel.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH)));
                    imagenLabel.setText(""); // Limpiar mensaje si se carga imagen
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar imagen: " + imagenPath);
            }
        }

        panel.add(nombre, BorderLayout.NORTH);
        panel.add(imagenLabel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}
