import javax.swing.*;
import java.util.ArrayList;

public class SeleccionMetodo extends JFrame {

    private ArrayList<String> comidas;
    private ArrayList<Double> precios;

    public SeleccionMetodo(ArrayList<String> comidas, ArrayList<Double> precios) {
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Método de entrega");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] opciones = {"Para comer en el restaurante", "Para llevar", "Delivery a domicilio"};
        JComboBox<String> combo = new JComboBox<>(opciones);
        JButton continuar = new JButton("Continuar");

        continuar.addActionListener(e -> {
            String seleccion = (String) combo.getSelectedItem();
            dispose();
            new MetodoPago(seleccion, comidas, precios).setVisible(true);
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecciona método:"));
        panel.add(combo);
        panel.add(continuar);

        add(panel);
        setVisible(true);
    }
}
