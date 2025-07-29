import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VentanaComidas extends JFrame {
    private String pais, tipo;
    private ArrayList<String> carritoComidas = new ArrayList<>();
    private ArrayList<Double> carritoPrecios = new ArrayList<>();

    public VentanaComidas(String pais, String tipo) {
        this.pais = pais;
        this.tipo = tipo;

        setTitle("Comidas de " + pais + " - " + tipo);
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 250, 240));

        JLabel lbl = new JLabel("Seleccione su comida (" + pais + " - " + tipo + ")");
        lbl.setBounds(100, 10, 400, 30);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lbl);

        String[][] comidas = obtenerComidas(pais, tipo);

        int y = 60;
        for (String[] comida : comidas) {
            JLabel lblComida = new JLabel(comida[0] + " - $" + comida[1]);
            lblComida.setBounds(50, y, 250, 30);
            panel.add(lblComida);

            JButton btnAgregar = new JButton("Agregar al carrito");
            btnAgregar.setBounds(320, y, 140, 30);
            panel.add(btnAgregar);

            JButton btnVerComida = new JButton("Ver comida");
            btnVerComida.setBounds(470, y, 100, 30);
            panel.add(btnVerComida);

            String nombre = comida[0];
            String precio = comida[1];

            btnAgregar.addActionListener(e -> {
                agregarAlCarrito(nombre, precio);
                carritoComidas.add(nombre);
                carritoPrecios.add(Double.parseDouble(precio));
                JOptionPane.showMessageDialog(null, nombre + " agregado al carrito.");
            });

            btnVerComida.addActionListener(e -> {
                String rutaImagen = "imagenes/" + nombre.toLowerCase().replace(" ", "_") + ".jpg";
                new VentanaDetalleComida(nombre, rutaImagen);
            });

            y += 50;
        }

        JButton btnVolver = new JButton("⬅ Volver");
        btnVolver.setBounds(30, 370, 100, 30);
        panel.add(btnVolver);

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaTipoComida(pais).setVisible(true);
        });

        JButton btnCarrito = new JButton("🛒 Ver carrito");
        btnCarrito.setBounds(420, 370, 120, 30);
        panel.add(btnCarrito);

        btnCarrito.addActionListener(e -> {
            dispose();
            new VentanaCarrito().setVisible(true);
        });

        add(panel);
    }

    private String[][] obtenerComidas(String pais, String tipo) {
        if (pais.equals("Panamá") && tipo.equals("salado")) {
            return new String[][] {
                    {"Arroz con Pollo", "4.50"},
                    {"Arroz con Guandú", "4.00"},
                    {"Pescado con Patacones", "6.00"},
                    {"Saos", "3.50"}
            };
        } else if (pais.equals("Panamá") && tipo.equals("dulce")) {
            return new String[][] {
                    {"Hojaldras", "2.50"},
                    {"Suspiros", "1.75"},
                    {"Dulce de Guayaba", "3.00"},
                    {"Bienmesabe", "3.25"}
            };
        } else if (pais.equals("México") && tipo.equals("salado")) {
            return new String[][] {
                    {"Tacos al Pastor", "5.00"},
                    {"Enchiladas", "6.50"},
                    {"Chiles Rellenos", "7.00"},
                    {"Pozole", "5.75"}
            };
        } else if (pais.equals("México") && tipo.equals("dulce")) {
            return new String[][] {
                    {"Churros", "3.00"},
                    {"Flan", "4.00"},
                    {"Tres Leches", "4.50"},
                    {"Cajeta", "3.75"}
            };
        } else if (pais.equals("Italia") && tipo.equals("salado")) {
            return new String[][] {
                    {"Pizza Margherita", "8.00"},
                    {"Lasagna", "9.50"},
                    {"Risotto", "10.00"},
                    {"Pasta Carbonara", "8.75"}
            };
        } else if (pais.equals("Italia") && tipo.equals("dulce")) {
            return new String[][] {
                    {"Tiramisú", "6.00"},
                    {"Cannoli", "5.50"},
                    {"Panna Cotta", "6.25"},
                    {"Gelato", "4.75"}
            };
        } else if (pais.equals("Japón") && tipo.equals("salado")) {
            return new String[][] {
                    {"Sushi Variado", "12.00"},
                    {"Ramen", "10.50"},
                    {"Tempura", "9.75"},
                    {"Takoyaki", "8.00"}
            };
        } else if (pais.equals("Japón") && tipo.equals("dulce")) {
            return new String[][] {
                    {"Mochi", "5.00"},
                    {"Dorayaki", "4.50"},
                    {"Taiyaki", "4.75"},
                    {"Matcha Ice Cream", "5.25"}
            };
        }
        return new String[][] {
                {"Comida 1", "5.00"},
                {"Comida 2", "4.50"},
                {"Comida 3", "3.75"},
                {"Comida 4", "4.25"}
        };
    }

    private void agregarAlCarrito(String comida, String precio) {
        try {
            FileWriter fw = new FileWriter("carrito.txt", true);
            fw.write(comida + "," + precio + "\n");
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar en carrito.txt");
        }
    }
}