import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Factura extends JFrame {

    public Factura(String titulo, ArrayList<String> comidasSeleccionadas, ArrayList<Double> precios) {
        setTitle(titulo);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblNombreEmpresa = new JLabel("GlobalEatsToGo");
        lblNombreEmpresa.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombreEmpresa.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblNombreEmpresa);

        JLabel lblDireccion = new JLabel("Calle 50, Ciudad de Panamá");
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDireccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblDireccion);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        JLabel lblFecha = new JLabel("Fecha: " + ahora.format(formato));
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 14));
        lblFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblFecha);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblResena = new JLabel("Detalle de la compra");
        lblResena.setFont(new Font("Arial", Font.BOLD, 16));
        lblResena.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(lblResena);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel panelItems = new JPanel();
        panelItems.setLayout(new BoxLayout(panelItems, BoxLayout.Y_AXIS));
        panelItems.setAlignmentX(Component.LEFT_ALIGNMENT);

        double total = 0;
        if (comidasSeleccionadas != null && precios != null &&
                comidasSeleccionadas.size() == precios.size()) {

            for (int i = 0; i < comidasSeleccionadas.size(); i++) {
                String comida = comidasSeleccionadas.get(i);
                double precio = precios.get(i);
                total += precio;

                JLabel lblComida = new JLabel(comida + " - B/." + String.format("%.2f", precio));
                lblComida.setFont(new Font("Arial", Font.PLAIN, 14));
                lblComida.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelItems.add(lblComida);
                panelItems.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        } else {
            JLabel lblError = new JLabel("Error al cargar los ítems del pedido");
            lblError.setFont(new Font("Arial", Font.PLAIN, 14));
            panelItems.add(lblError);
        }

        JScrollPane scrollItems = new JScrollPane(panelItems);
        scrollItems.setMaximumSize(new Dimension(380, 200));
        panelPrincipal.add(scrollItems);

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel lblTotal = new JLabel("Total: B/." + String.format("%.2f", total));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panelPrincipal.add(lblTotal);

        // Botón para cerrar factura y continuar a reseña
        JButton btnCerrar = new JButton("Cerrar factura");
        btnCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaResena(comidasSeleccionadas, precios).setVisible(true);
        });

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPrincipal.add(btnCerrar);

        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);
        setVisible(true);
    }
}

