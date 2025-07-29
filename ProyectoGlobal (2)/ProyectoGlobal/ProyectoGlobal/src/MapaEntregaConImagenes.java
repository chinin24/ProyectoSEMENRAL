import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapaEntregaConImagenes extends JFrame {
    private ArrayList<String> comidas;
    private ArrayList<Double> precios;

    public MapaEntregaConImagenes(ArrayList<String> comidas, ArrayList<Double> precios) {
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Entrega con Imágenes - Motorizado");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        MapaPanelConImagenes panel = new MapaPanelConImagenes(comidas, precios);
        add(panel);

        setVisible(true);
        panel.iniciarProcesoDeEntrega();
    }

    public static void main(String[] args) {
        // Datos de prueba
        ArrayList<String> comidas = new ArrayList<>();
        comidas.add("Pizza Margherita");
        comidas.add("Lasaña");

        ArrayList<Double> precios = new ArrayList<>();
        precios.add(8.50);
        precios.add(10.00);

        SwingUtilities.invokeLater(() -> new MapaEntregaConImagenes(comidas, precios));
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

    private ArrayList<String> comidas;
    private ArrayList<Double> precios;

    public MapaPanelConImagenes(ArrayList<String> comidas, ArrayList<Double> precios) {
        this.comidas = comidas;
        this.precios = precios;
        setBackground(new Color(230, 240, 250));
        cargarImagenes();
    }

    private void cargarImagenes() {
        try {
            imgRestaurante = new ImageIcon(getClass().getResource("/imagenes/restaurante.png")).getImage();
            imgCliente = new ImageIcon(getClass().getResource("/imagenes/cliente.png")).getImage();
            imgMotorizado = new ImageIcon(getClass().getResource("/imagenes/motorizado.png")).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando imágenes. Asegúrate de que estén en la carpeta 'imagenes' dentro de 'src' y marcada como Resources.");
        }
    }

    public void iniciarProcesoDeEntrega() {
        mensajeEstado = "Tu pedido se está preparando...";
        repaint();

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

                    // Mostrar mensaje final
                    JOptionPane.showMessageDialog(MapaPanelConImagenes.this, "¡Pedido entregado con éxito!");

                    // Cerrar ventana actual
                    SwingUtilities.getWindowAncestor(MapaPanelConImagenes.this).dispose();

                    // ✅ Ahora abre directamente la factura
                    new Factura("Factura", comidas, precios);
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.drawLine(150, yMotorizado + 20, xCliente + 30, yCliente + 20);

        if (imgRestaurante != null)
            g.drawImage(imgRestaurante, 100, 220, 60, 60, this);
        g.drawString("Restaurante", 100, 215);

        if (imgCliente != null)
            g.drawImage(imgCliente, xCliente, yCliente, 50, 50, this);
        g.drawString("Cliente", xCliente, yCliente - 10);

        if (imgMotorizado != null)
            g.drawImage(imgMotorizado, xMotorizado, yMotorizado, 40, 40, this);
        else {
            g.setColor(Color.RED);
            g.fillRect(xMotorizado, yMotorizado, 40, 40);
        }

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString(mensajeEstado, 350, 50);
    }
}
