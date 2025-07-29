import javax.swing.*;
import java.util.ArrayList;

public class SeleccionMetodo extends JFrame {

    private ArrayList<String> comidas;
    private ArrayList<Double> precios;

    public SeleccionMetodo(ArrayList<String> comidas, ArrayList<Double> precios) {
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Método de entrega");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Componentes
        JLabel lblSeleccion = new JLabel("Selecciona método:");
        String[] opciones = {"Para comer en el restaurante", "Para llevar", "Delivery a domicilio"};
        JComboBox<String> combo = new JComboBox<>(opciones);

        JButton continuar = new JButton("Continuar ➡");
        JButton regresar = new JButton("⬅ Regresar al carrito");

        // Acción botón continuar
        continuar.addActionListener(e -> {
            String seleccion = (String) combo.getSelectedItem();
            dispose();

            if (seleccion.equals("Delivery a domicilio")) {
                new MapaEntregaConImagenes(comidas, precios);
            } else {
                new TicketRetiro(comidas, precios);
            }
        });

        // Acción botón regresar
        regresar.addActionListener(e -> {
            dispose();
            new VentanaCarrito().setVisible(true);  // ← Asegúrate de tener esta clase disponible
        });

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(regresar);
        panelBotones.add(continuar);

        // Panel general
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lblSeleccion.setAlignmentX(CENTER_ALIGNMENT);
        combo.setAlignmentX(CENTER_ALIGNMENT);
        panelBotones.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(lblSeleccion);
        panel.add(Box.createVerticalStrut(10));
        panel.add(combo);
        panel.add(Box.createVerticalStrut(20));
        panel.add(panelBotones);

        add(panel);
        setVisible(true);
    }
}
