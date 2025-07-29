import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaResena extends JFrame {
    private JTextArea campoResena;
    private JButton btnEnviar;
    private JButton btnSaltar;
    private JComboBox<Integer> estrellas;
    private ArrayList<String> comidas;
    private ArrayList<Double> precios;

    public VentanaResena(ArrayList<String> comidas, ArrayList<Double> precios) {
        this.comidas = comidas;
        this.precios = precios;

        setTitle("Escribe tu Reseña");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblEstrellas = new JLabel("¿Cuántas estrellas le das?");
        estrellas = new JComboBox<>();
        for (int i = 1; i <= 5; i++) {
            estrellas.addItem(i);
        }

        JPanel panelEstrellas = new JPanel();
        panelEstrellas.add(lblEstrellas);
        panelEstrellas.add(estrellas);

        campoResena = new JTextArea(5, 30);
        campoResena.setLineWrap(true);
        campoResena.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(campoResena);

        btnEnviar = new JButton("Enviar Reseña");
        btnSaltar = new JButton("Omitir Reseña");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEnviar);
        panelBotones.add(btnSaltar);

        panel.add(panelEstrellas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resenaTexto = campoResena.getText().trim();
                int estrellasSeleccionadas = (int) estrellas.getSelectedItem();

                // Guardar la reseña en un archivo (opcional)
                guardarResena(resenaTexto, estrellasSeleccionadas);

                JOptionPane.showMessageDialog(null, "¡Gracias por tu reseña!");
                mostrarFactura();
            }
        });

        btnSaltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFactura();
            }
        });
    }

    private void guardarResena(String resena, int estrellas) {
        // Implementación opcional para guardar las reseñas en un archivo
        try {
            java.io.FileWriter writer = new java.io.FileWriter("reseñas.txt", true);
            writer.write("Estrellas: " + estrellas + "\n");
            writer.write("Reseña: " + resena + "\n");
            writer.write("--------------------------------\n");
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar la reseña");
        }
    }

    private void mostrarFactura() {
        dispose();
        new Factura("Factura", comidas, precios).setVisible(true);
    }
}