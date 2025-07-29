import javax.swing.*;
import java.util.ArrayList;

public class MetodoPago extends JFrame {

    private ArrayList<String> comidas;
    private ArrayList<Double> precios;
    private String metodoEntrega;

    public MetodoPago(String metodoEntrega, ArrayList<String> comidas, ArrayList<Double> precios) {
        this.metodoEntrega = metodoEntrega;
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Método de pago");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] metodos = {"Pagar en el lugar", "Pagar con tarjeta"};
        JComboBox<String> comboPago = new JComboBox<>(metodos);
        JButton finalizar = new JButton("Finalizar");

        finalizar.addActionListener(e -> {
            String pago = (String) comboPago.getSelectedItem();
            if (pago.equals("Pagar con tarjeta")) {
                dispose();
                new PagoTarjeta(metodoEntrega, comidas, precios).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Has seleccionado: " + metodoEntrega + "\nPago: " + pago);
                dispose();
                // Cambiamos esto para que llame a VentanaResena primero
                new VentanaResena(comidas, precios).setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecciona pago:"));
        panel.add(comboPago);
        panel.add(finalizar);

        add(panel);
        setVisible(true);
    }
}