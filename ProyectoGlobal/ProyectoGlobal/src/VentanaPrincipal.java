import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Menú de Países");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo
        JPanel panelFondo = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon fondo = new ImageIcon("imagenes/fondo.png*"); // CREA ESTA CARPETA Y AGREGA LA IMAGEN
                g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(null);

        // Texto de bienvenida
        JLabel lblTitulo = new JLabel("Seleccione un país");
        lblTitulo.setBounds(200, 20, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        panelFondo.add(lblTitulo);

        // Botones de países
        String[] paises = {"Panamá", "México", "Italia", "Japón"};
        int y = 80;

        for (String pais : paises) {
            JButton btnPais = new JButton(pais);
            btnPais.setBounds(200, y, 200, 40);
            btnPais.setFont(new Font("Arial", Font.PLAIN, 16));
            panelFondo.add(btnPais);

            btnPais.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Lógica para abrir la ventana de comidas
                    dispose(); // Cierra esta ventana
                    new VentanaTipoComida(pais).setVisible(true);
                }
            });
            y += 60;
        }

        add(panelFondo);
    }

    public static void main(String[] args) {
        new VentanaPrincipal().setVisible(true);
    }
}
