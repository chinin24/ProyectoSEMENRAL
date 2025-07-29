import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Registro {

    private JPanel panelBlanco;
    private JPanel panelVerde;
    private JTextField nombreField;
    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton registrarButton;
    private JButton regresarButton;
    private JFrame frame;

    public Registro() {
        frame = new JFrame("¡Registro de Usuario - GlobalEats ToGo!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Colores y fuentes
        Color marron = new Color(110, 52, 0);
        Color marronHover = new Color(90, 40, 0);
        Color blanco = Color.WHITE;
        Color verdeBorde = new Color(180, 220, 140);
        Font fuenteBoton = new Font("Georgia", Font.BOLD, 14);
        Font fuenteCampos = new Font("Georgia", Font.PLAIN, 14);
        Font fuenteTitulo = new Font("Georgia", Font.BOLD, 22);
        Font fuenteElegante = new Font("Georgia", Font.PLAIN, 14);

        // Fondo animado
        JLabel fondoGIF = new JLabel();
        fondoGIF.setLayout(new BorderLayout());
        fondoGIF.setIcon(new ImageIcon(getClass().getResource("/image/familia.gif")));

        // Encabezado madera
        JPanel panelEncabezado = new JPanel() {
            private final Image fondo = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelEncabezado.setLayout(new BorderLayout());
        panelEncabezado.setPreferredSize(new Dimension(600, 70));
        panelEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, verdeBorde));

        JLabel titulo = new JLabel("Regístrate en GlobalEats ToGo", SwingConstants.CENTER);
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(Color.WHITE);
        panelEncabezado.add(titulo, BorderLayout.CENTER);

        // Footer madera
        JPanel footerPanel = new JPanel() {
            private final Image fondo = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setPreferredSize(new Dimension(600, 35));

        JLabel footer = new JLabel("📍 Calle 50, Ciudad de Panamá   ☎ (507) 6789-1234   ✉ globaltogo@gmail.com", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        footerPanel.add(footer, BorderLayout.CENTER);

        // Estilo campos
        nombreField.setFont(fuenteCampos);
        usuarioField.setFont(fuenteCampos);
        passwordField.setFont(fuenteCampos);
        nombreField.setBorder(BorderFactory.createTitledBorder("   👤 Nombre completo"));
        usuarioField.setBorder(BorderFactory.createTitledBorder("   @ Usuario"));
        passwordField.setBorder(BorderFactory.createTitledBorder("   🔒 Contraseña"));

        // Botón Registrarme con textura de madera
        registrarButton.setFont(fuenteBoton);
        registrarButton.setForeground(blanco);
        registrarButton.setContentAreaFilled(false);
        registrarButton.setOpaque(false);
        registrarButton.setFocusPainted(false);
        registrarButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        registrarButton.setHorizontalAlignment(SwingConstants.CENTER);
        Image maderaBtn = new ImageIcon(getClass().getResource("/image/madera.png")).getImage();
        registrarButton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            protected void paintButtonPressed(Graphics g, AbstractButton b) {
                g.setColor(marronHover);
                g.fillRect(0, 0, b.getWidth(), b.getHeight());
            }

            public void paint(Graphics g, JComponent c) {
                g.drawImage(maderaBtn, 0, 0, c.getWidth(), c.getHeight(), c);
                super.paint(g, c);
            }
        });

        // Botón Regresar igual al login
        regresarButton.setBackground(Color.WHITE);
        regresarButton.setForeground(marron);
        regresarButton.setFont(fuenteElegante);
        regresarButton.setFocusPainted(false);
        regresarButton.setBorder(BorderFactory.createLineBorder(marron));
        regresarButton.setHorizontalAlignment(SwingConstants.CENTER);

        // Paneles transparentes
        panelBlanco.setOpaque(false);
        panelVerde.setOpaque(false);
        panelBlanco.setBorder(null); // limpio

        // ✅ Panel "tarjeta" como en login: borde fino y padding interno
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setOpaque(false);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        tarjeta.add(panelBlanco, BorderLayout.CENTER);

        fondoGIF.add(panelEncabezado, BorderLayout.NORTH);
        fondoGIF.add(tarjeta, BorderLayout.CENTER);
        fondoGIF.add(footerPanel, BorderLayout.SOUTH);

        frame.setContentPane(fondoGIF);
        frame.pack();
        frame.setMinimumSize(new Dimension(600, 520)); // para que no sobre espacio blanco
        frame.setVisible(true);

        // Acción registrar
        registrarButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String usuario = usuarioField.getText().trim();
            String contrasena = new String(passwordField.getPassword()).trim();

            if (!nombre.isEmpty() && !usuario.isEmpty() && !contrasena.isEmpty()) {
                guardarRegistro(nombre, usuario, contrasena);
            } else {
                JOptionPane.showMessageDialog(null, "Completa todos los campos.");
            }
        });

        // Acción regresar
        regresarButton.addActionListener(e -> {
            frame.dispose();
            new Pantalla();
        });
    }

    private void guardarRegistro(String nombre, String usuario, String contrasena) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("ProyectoGlobal/ProyectoGlobal/usuarios.txt", true))) {
            pw.println(usuario + "," + contrasena + "," + nombre);
            JOptionPane.showMessageDialog(null, "Registro exitoso.");
            frame.dispose();
            new Pantalla();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
        }
    }
}
