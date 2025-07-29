import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PagoTarjeta extends JFrame {

    private ArrayList<String> comidas;
    private ArrayList<Double> precios;
    private String metodoEntrega;

    public PagoTarjeta(String metodoEntrega, ArrayList<String> comidas, ArrayList<Double> precios) {
        this.metodoEntrega = metodoEntrega;
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Pago con tarjeta");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextField campoNumero = new JTextField(16);
        JTextField campoNombre = new JTextField();
        JTextField campoCVV = new JTextField(3);

        JButton pagar = new JButton("Pagar");

        pagar.addActionListener(e -> {
            String numero = campoNumero.getText().trim();
            String cvv = campoCVV.getText().trim();

            if (numero.length() != 16 || !numero.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Número de tarjeta inválido");
                return;
            }

            if (cvv.length() != 3 || !cvv.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "CVV inválido");
                return;
            }

            JOptionPane.showMessageDialog(null, "Pago exitoso");
            dispose();
            // Cambiamos esto para que llame a VentanaResena primero
            new VentanaResena(comidas, precios).setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Número de tarjeta:"));
        panel.add(campoNumero);
        panel.add(new JLabel("Nombre en tarjeta:"));
        panel.add(campoNombre);
        panel.add(new JLabel("CVV:"));
        panel.add(campoCVV);
        panel.add(pagar);

        add(panel);
        setVisible(true);
    }
}