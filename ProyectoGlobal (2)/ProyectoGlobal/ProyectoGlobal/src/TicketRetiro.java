import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TicketRetiro extends JFrame {

    public TicketRetiro(ArrayList<String> comidas, ArrayList<Double> precios) {
        setTitle("Ticket de Pedido");
        setSize(400, 300);  // ✅ Tamaño suficiente para mostrar todo sin recortes
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Número aleatorio entre 1 y 100
        Random random = new Random();
        int numeroTicket = random.nextInt(100) + 1;

        // Componentes
        JLabel lblTitulo = new JLabel("Ticket de Pedido", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNumero = new JLabel("NÚMERO: " + numeroTicket, SwingConstants.CENTER);
        lblNumero.setFont(new Font("Courier New", Font.BOLD, 34));
        lblNumero.setForeground(Color.BLUE);
        lblNumero.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblLinea1 = new JLabel("Manténgase pendiente al llamado de su número", SwingConstants.CENTER);
        JLabel lblLinea2 = new JLabel("en el mostrador para retirar su pedido con éxito.", SwingConstants.CENTER);
        lblLinea1.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLinea2.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLinea1.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLinea2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSugerencia = new JLabel("Puedes tomarle una captura o anotarlo.", SwingConstants.CENTER);
        lblSugerencia.setFont(new Font("Arial", Font.ITALIC, 12));
        lblSugerencia.setForeground(Color.DARK_GRAY);
        lblSugerencia.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
        btnSiguiente.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSiguiente.addActionListener(e -> {
            dispose();
            new Factura("Factura", comidas, precios);
        });

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));  // Espaciado interno

        // Añadir componentes
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblNumero);
        panel.add(Box.createVerticalStrut(15));
        panel.add(lblLinea1);
        panel.add(lblLinea2);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblSugerencia);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnSiguiente);

        // Mostrar ventana
        add(panel);
        setVisible(true);
    }
}
