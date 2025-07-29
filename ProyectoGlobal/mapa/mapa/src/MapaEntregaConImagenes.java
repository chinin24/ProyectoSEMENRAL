import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapaEntregaConImagenes extends JFrame {
    public MapaEntregaConImagenes() {
        setTitle("Entrega con Imágenes - Motorizado");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        MapaPanelConImagenes panel = new MapaPanelConImagenes();
        add(panel);

        setVisible(true);
        panel.iniciarProcesoDeEntrega();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MapaEntregaConImagenes());
    }
}

class MapaPanelConImagenes extends JPanel {
    private int xMotorizado = 100;
    private final int yMotorizado = 250;
    private final int xCliente = 700;
    private final int yCliente = 250;
    private Timer timer;

    private Image imgRestaurante;
    private Image imgCliente;
    private Image imgMotorizado;

    private String mensajeEstado = "";

    public MapaPanelConImagenes() {
        setBackground(new Color(230, 240, 250));
        cargarImagenes();
    }

    private void cargarImagenes() {
        try {
            imgRestaurante = new ImageIcon("imagenes/restaurante.png").getImage();
            imgCliente = new ImageIcon("imagenes/cliente.png").getImage();
            imgMotorizado = new ImageIcon("imagenes/motorizado.png").getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando imágenes. Asegúrate de que estén en la carpeta del proyecto.");
        }
    }

    public void iniciarProcesoDeEntrega() {
        mensajeEstado = "Tu pedido se está preparando...";
        repaint();

        // Simular tiempo en el restaurante (3 segundos)
        Timer esperaRestaurante = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                mensajeEstado = "Tu pedido está en camino...";
                iniciarEntrega();
            }
        });
        esperaRestaurante.setRepeats(false);
        esperaRestaurante.start();
    }

    private void iniciarEntrega() {
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (xMotorizado < xCliente) {
                    xMotorizado += 2;
                    repaint();
                } else {
                    timer.stop();
                    mensajeEstado = "Tu pedido ha llegado.";
                    repaint();
                    JOptionPane.showMessageDialog(MapaPanelConImagenes.this, "¡Pedido entregado con éxito!");
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Ruta
        g.setColor(Color.GRAY);
        g.drawLine(150, yMotorizado + 20, xCliente + 30, yCliente + 20);

        // Restaurante
        if (imgRestaurante != null)
            g.drawImage(imgRestaurante, 100, 220, 60, 60, this);
        g.drawString("Restaurante", 100, 215);

        // Cliente
        if (imgCliente != null)
            g.drawImage(imgCliente, xCliente, yCliente, 50, 50, this);
        g.drawString("Cliente", xCliente, yCliente - 10);

        // Motorizado
        if (imgMotorizado != null)
            g.drawImage(imgMotorizado, xMotorizado, yMotorizado, 40, 40, this);

        // Mensaje de estado
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString(mensajeEstado, 350, 50);
    }
}


