import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Menú de Países");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);

        // 🔠 Título
        JLabel lblTitulo = new JLabel("Seleccione un país", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        panelPrincipal.add(lblTitulo);

        // 📷 Imagen escalada y centrada
        ImageIcon original = new ImageIcon(getClass().getResource("/image/ahoragg.png"));
        Image imagenEscalada = original.getImage().getScaledInstance(750, 160, Image.SCALE_SMOOTH);
        JLabel imagen = new JLabel(new ImageIcon(imagenEscalada));
        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(imagen);

        // 🪵 Panel de botones alineados con FlowLayout centrado
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 15));
        panelBotones.setBackground(Color.WHITE);

        String[] paises = {"MEXICO", "FRANCIA", "JAPON", "PANAMA"};
        for (String pais : paises) {
            JButton boton = crearBotonMadera(pais);
            boton.addActionListener(e -> {
                dispose();
                new VentanaTipoComida(pais).setVisible(true);
            });
            panelBotones.add(boton);
        }

        panelPrincipal.add(panelBotones);
        add(panelPrincipal);
    }

    private JButton crearBotonMadera(String texto) {
        JButton boton = new JButton(texto) {
            Image madera = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(madera, 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g);
            }
        };
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(false);
        boton.setPreferredSize(new Dimension(140, 45));
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
