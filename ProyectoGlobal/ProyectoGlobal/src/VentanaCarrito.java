import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class VentanaCarrito extends JFrame {

    private double total = 0;
    private ArrayList<String> comidas = new ArrayList<>();
    private ArrayList<Double> precios = new ArrayList<>();
    private JTextArea areaCarrito;

    public VentanaCarrito() {
        setTitle("Carrito de Compras");
        setSize(500, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 255, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("Artículos en el carrito", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(lblTitulo, BorderLayout.NORTH);

        areaCarrito = new JTextArea();
        areaCarrito.setEditable(false);
        areaCarrito.setFont(new Font("Arial", Font.PLAIN, 14));
        areaCarrito.setMargin(new Insets(10, 50, 10, 50)); // Márgenes amplios para simular centrado

        JScrollPane scroll = new JScrollPane(areaCarrito);
        panel.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel lblTotal = new JLabel("Total: $0.00", SwingConstants.CENTER);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        panelInferior.add(lblTotal, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnVolver = new JButton("⬅ Volver al Menú");
        JButton btnPagar = new JButton("Pagar 🛒");
        btnPagar.setBackground(new Color(100, 200, 100));
        btnPagar.setForeground(Color.WHITE);

        panelBotones.add(btnVolver);
        panelBotones.add(btnPagar);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        panel.add(panelInferior, BorderLayout.SOUTH);
        add(panel);

        cargarCarrito(lblTotal);

        btnVolver.addActionListener(e -> volverAlMenu());
        btnPagar.addActionListener(e -> procesarPago());
    }

    private void cargarCarrito(JLabel lblTotal) {
        comidas.clear();
        precios.clear();
        total = 0;
        areaCarrito.setText("");

        try (BufferedReader br = new BufferedReader(new FileReader("carrito.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    String comida = partes[0].trim();
                    try {
                        double precio = Double.parseDouble(partes[1].trim());
                        comidas.add(comida);
                        precios.add(precio);
                        areaCarrito.append("• " + comida + " - $" + String.format("%.2f", precio) + "\n\n");
                        total += precio;
                    } catch (NumberFormatException e) {
                        System.err.println("Error en formato de precio: " + linea);
                    }
                }
            }
            lblTotal.setText("Total: $" + String.format("%.2f", total));

            if (comidas.isEmpty()) {
                mostrarMensajeCarritoVacio();
            }
        } catch (IOException e) {
            areaCarrito.setText("No se pudo cargar el carrito.\n\nError: " + e.getMessage());
        }
    }

    private void mostrarMensajeCarritoVacio() {
        String mensajeCentrado = "\n\n        El carrito está vacío.\n\n        Agrega productos desde el menú.";
        areaCarrito.setFont(new Font("Arial", Font.ITALIC, 14));
        areaCarrito.setText(mensajeCentrado);
    }

    private void volverAlMenu() {
        dispose();
        new VentanaPrincipal().setVisible(true);
    }

    private void procesarPago() {
        if (comidas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El carrito está vacío.",
                    "Carrito vacío",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Botones personalizados en español
        Object[] opciones = {"Sí, pagar", "No, cancelar"};
        int confirmacion = JOptionPane.showOptionDialog(this,
                "¿Desea proceder al pago de $" + String.format("%.2f", total) + "?",
                "Confirmar pago",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (confirmacion == JOptionPane.YES_OPTION) {
            vaciarArchivoCarrito();
            dispose();
            new SeleccionMetodo(comidas, precios).setVisible(true);
        }
    }

    private void vaciarArchivoCarrito() {
        try {
            new FileWriter("carrito.txt", false).close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al vaciar el carrito",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
