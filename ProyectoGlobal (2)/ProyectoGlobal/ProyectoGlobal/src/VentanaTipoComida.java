import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaTipoComida extends JFrame {

    private String paisSeleccionado;

    public VentanaTipoComida(String pais) {
        this.paisSeleccionado = pais;

        setTitle("Tipo de comida - " + paisSeleccionado);
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 240, 240));

        JLabel lbl = new JLabel("¿Qué tipo de comida desea ver?");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setBounds(100, 30, 400, 30);
        panel.add(lbl);

        JButton btnSalado = new JButton("Comida Salada");
        btnSalado.setBounds(150, 80, 200, 40);
        panel.add(btnSalado);

        JButton btnDulce = new JButton("Comida Dulce");
        btnDulce.setBounds(150, 140, 200, 40);
        panel.add(btnDulce);

        JButton btnAtras = new JButton("⬅ Volver");
        btnAtras.setBounds(10, 210, 100, 30);
        panel.add(btnAtras);

        btnSalado.addActionListener(e -> {
            dispose();
            new VentanaComidas(paisSeleccionado, "salado").setVisible(true);
        });

        btnDulce.addActionListener(e -> {
            dispose();
            new VentanaComidas(paisSeleccionado, "dulce").setVisible(true);
        });

        btnAtras.addActionListener(e -> {
            dispose();
            new VentanaPrincipal().setVisible(true);
        });

        add(panel);
    }
}
