import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class carrito {
    // Componentes principales
    private JPanel PanelPrincipal;
    private JList<String> pedido;
    private JButton seguirPidiendoButton;
    private JButton irPagarButton;
    private JScrollPane scrollPedido;
    private JLabel subTotal;
    private JLabel total;
    private JPanel Panelletras;
    private JLabel titulo;
    private JLabel letraPedido;
    private JPanel Panelinfo;
    private JPanel Panelacciones;
    private JPanel atras;
    private JPanel Paneltotales;
    private JPanel Panelpagar;

    // Modelo de datos
    private DefaultListModel<String> modeloPedido;

    public carrito() {
        // Inicializar modelo
        modeloPedido = new DefaultListModel<>();
        pedido.setModel(modeloPedido);

        // Configurar interfaz
        configurarInterfaz();
        configurarBotonesDefinitivos();
        agregarEjemplos();
    }

    private void configurarInterfaz() {
        // Panel principal
        PanelPrincipal.setBackground(new Color(240, 240, 240));
        PanelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Encabezado
        Panelletras.setBackground(new Color(70, 130, 180));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        letraPedido.setFont(new Font("Segoe UI", Font.BOLD, 14));
        letraPedido.setForeground(Color.WHITE);

        // Lista de pedidos
        pedido.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pedido.setBackground(Color.WHITE);
        pedido.setSelectionBackground(new Color(230, 230, 250));
        scrollPedido.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                new EmptyBorder(5, 5, 5, 5)
        ));

        // Totales
        subTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        total.setFont(new Font("Segoe UI", Font.BOLD, 16));
        total.setForeground(new Color(0, 100, 0));
    }

    // SOLUCIÓN DEFINITIVA PARA BOTONES VISIBLES
    private void configurarBotonesDefinitivos() {
        // 1. Configuración explícita del botón "Seguir Pidiendo"
        seguirPidiendoButton.setText("SEGUIR PIDIENDO");
        seguirPidiendoButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        seguirPidiendoButton.setBackground(new Color(30, 120, 200)); // Azul intenso
        seguirPidiendoButton.setForeground(Color.WHITE);
        seguirPidiendoButton.setOpaque(true);
        seguirPidiendoButton.setBorderPainted(false);
        seguirPidiendoButton.setFocusPainted(false);
        seguirPidiendoButton.setBorder(new EmptyBorder(12, 25, 12, 25));
        seguirPidiendoButton.setContentAreaFilled(true);

        // 2. Configuración explícita del botón "Ir a Pagar"
        irPagarButton.setText("IR A PAGAR");
        irPagarButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        irPagarButton.setBackground(new Color(40, 160, 70)); // Verde intenso
        irPagarButton.setForeground(Color.WHITE);
        irPagarButton.setOpaque(true);
        irPagarButton.setBorderPainted(false);
        irPagarButton.setFocusPainted(false);
        irPagarButton.setBorder(new EmptyBorder(12, 30, 12, 30));
        irPagarButton.setContentAreaFilled(true);

        // 3. Acciones de los botones
        seguirPidiendoButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(PanelPrincipal, "Volviendo al menú...");
        });

        irPagarButton.addActionListener(e -> {
            if (modeloPedido.isEmpty()) {
                JOptionPane.showMessageDialog(PanelPrincipal,
                        "Carrito vacío", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(PanelPrincipal,
                        "Procesando pago...");
            }
        });
    }

    private void agregarEjemplos() {
        agregarItem("Hamburguesa - $5.00");
        agregarItem("Papas fritas - $2.00");
        agregarItem("Refresco - $1.50");
    }

    private void agregarItem(String item) {
        modeloPedido.addElement(item);
        calcularTotales();
    }

    private void calcularTotales() {
        double subtotal = 0.0;

        for (int i = 0; i < modeloPedido.size(); i++) {
            subtotal += extraerPrecio(modeloPedido.get(i));
        }

        double totalCalculado = subtotal * 1.07; // 7% de impuesto

        subTotal.setText(String.format("Sub total: $%.2f", subtotal));
        total.setText(String.format("Total: $%.2f", totalCalculado));
    }

    private double extraerPrecio(String item) {
        try {
            String[] partes = item.split("\\$");
            return Double.parseDouble(partes[1]);
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static void main(String[] args) {
        // Configuración previa para evitar problemas de renderizado
        UIManager.put("Button.background", UIManager.get("Panel.background"));
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JFrame frame = new JFrame("Carrito de Compras");
            frame.setContentPane(new carrito().PanelPrincipal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}