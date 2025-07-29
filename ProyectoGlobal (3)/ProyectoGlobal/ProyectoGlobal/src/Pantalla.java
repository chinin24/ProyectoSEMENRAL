import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Pantalla {

    private JPanel panel1;
    private JPanel panelVerde;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton ingresarButton;
    private JButton registrarseButton;
    private JFrame frame;

    public Pantalla() {
        frame = new JFrame("GlobalEats ToGo - Inicio de Sesión");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // 🎨 Colores y fuentes
        Color marron = new Color(110, 52, 0);
        Color marronHover = new Color(90, 40, 0);
        Color blanco = Color.WHITE;
        Color textoSuave = new Color(0, 0, 0);
        Color bordeVerde = new Color(180, 220, 140);

        Font fuenteElegante = new Font("Georgia", Font.PLAIN, 14);
        Font fuenteBoton = new Font("Georgia", Font.BOLD, 14);
        Font fuenteTitulo = new Font("Georgia", Font.BOLD, 22);

        // 🖼 Fondo con GIF animado
        JLabel fondoGif = new JLabel(new ImageIcon(getClass().getResource("/image/familia.gif")));
        fondoGif.setLayout(new BorderLayout());

        // 🌳 Encabezado con textura de madera
        JPanel panelEncabezado = new JPanel() {
            private final Image fondoMadera = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoMadera, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelEncabezado.setLayout(new BorderLayout());
        panelEncabezado.setPreferredSize(new Dimension(600, 70));
        panelEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, bordeVerde));

        JLabel titulo = new JLabel("GlobalEats ToGo - Good taste Good Life", SwingConstants.CENTER);
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelEncabezado.add(titulo, BorderLayout.CENTER);

        // 🪟 Panel central con campos
        panel1.setOpaque(false);
        panelVerde.setOpaque(false);
        textField1.setFont(fuenteElegante);
        passwordField1.setFont(fuenteElegante);

        textField1.setBorder(BorderFactory.createTitledBorder("   @ Usuario o Correo electrónico"));
        passwordField1.setBorder(BorderFactory.createTitledBorder("   🔒 Contraseña"));

        // 🪵 Botón Ingresar con textura madera
        Image maderaBtn = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
        ingresarButton.setFont(fuenteBoton);
        ingresarButton.setForeground(Color.WHITE);
        ingresarButton.setContentAreaFilled(false);
        ingresarButton.setOpaque(false);
        ingresarButton.setFocusPainted(false);
        ingresarButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        ingresarButton.setHorizontalAlignment(SwingConstants.CENTER);
        ingresarButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(marronHover);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }
            public void paint(Graphics g, JComponent c) {
                g.drawImage(maderaBtn, 0, 0, c.getWidth(), c.getHeight(), c);
                super.paint(g, c);
            }
        });

        // ⬅ Botón Registrarse
        registrarseButton.setBackground(Color.WHITE);
        registrarseButton.setForeground(marron);
        registrarseButton.setFont(fuenteElegante);
        registrarseButton.setFocusPainted(false);
        registrarseButton.setBorder(BorderFactory.createLineBorder(marron));

        // 🧾 Footer con textura de madera
        JPanel footerPanel = new JPanel() {
            private final Image fondoMadera = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoMadera, 0, 0, getWidth(), getHeight(), this);
            }
        };
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 40));

        JLabel footer = new JLabel("📍 Calle 50, Ciudad de Panamá   ☎ (507) 6789-1234   ✉ globaltogo@gmail.com", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footerPanel.add(footer, BorderLayout.CENTER);

        // 💳 Tarjeta central
        JPanel tarjetaCentral = new JPanel(new BorderLayout());
        tarjetaCentral.setOpaque(false);
        tarjetaCentral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        tarjetaCentral.add(panel1, BorderLayout.CENTER);

        // 📦 Layout general sobre GIF
        fondoGif.add(panelEncabezado, BorderLayout.NORTH);
        fondoGif.add(tarjetaCentral, BorderLayout.CENTER);
        fondoGif.add(footerPanel, BorderLayout.SOUTH);

        frame.setContentPane(fondoGif);
        frame.pack();
        frame.setMinimumSize(new Dimension(600, 440));
        frame.setVisible(true);

        // ✅ Acciones
        ingresarButton.addActionListener(e -> {
            String usuario = textField1.getText().trim();
            String contrasena = new String(passwordField1.getPassword()).trim();
            if (!usuario.isEmpty() && !contrasena.isEmpty()) {
                if (verificarLogin(usuario, contrasena)) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido a Global Eats ToGo!");
                    frame.dispose();
                    new VentanaPrincipal().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Completa todos los campos.");
            }
        });

        registrarseButton.addActionListener(e -> {
            frame.dispose();
            new Registro();
        });
    }

    private boolean verificarLogin(String usuario, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader("ProyectoGlobal/ProyectoGlobal/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 2) {
                    if (partes[0].trim().equals(usuario) && partes[1].trim().equals(contrasena)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer archivo: " + e.getMessage());
        }
        return false;
    }
}
