package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FestivalApp {
    public static void main(String[] args) {
        InicioFrame inicioFrame = new InicioFrame();
        inicioFrame.setVisible(true);
    }
}

class InicioFrame extends JFrame {
    public InicioFrame() {
        setTitle("FESTIVAL APP");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(50, 0, 100);   // Morado oscuro
                Color color2 = new Color(150, 30, 0);     // Rojo oscuro
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // En la clase InicioFrame, reemplaza la creación del logoLabel con:

        JLabel logoLabel;
        try {
            // Cargar la imagen (ajusta la ruta)
            ImageIcon originalIcon = new ImageIcon("imagenF.png");

            // Redimensionar si es necesario (opcional)
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledImage);

            logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        } catch (Exception e) {
            // Si hay error al cargar la imagen, mostrar texto alternativo
            logoLabel = new JLabel("LOGO NO ENCONTRADO", SwingConstants.CENTER);
            logoLabel.setForeground(Color.RED);
            System.err.println("Error al cargar la imagen: " + e.getMessage());
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JButton entrarButton = new JButton("ENTRAR AL MENÚ");
        entrarButton.setFont(new Font("Arial", Font.BOLD, 18));
        entrarButton.setForeground(Color.WHITE);
        entrarButton.setBackground(new Color(70, 70, 70));
        entrarButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                MenuFrame menuFrame = new MenuFrame();
                menuFrame.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(entrarButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        mainPanel.add(logoLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
}

class MenuFrame extends JFrame {
    public MenuFrame() {
        setTitle("FESTIVAL APP");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(0, 50, 100);   // Azul oscuro
                Color color2 = new Color(0, 100, 50);     // Verde oscuro
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        JPanel optionsPanel = new JPanel();
        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new GridLayout(2, 2, 20, 20));

        String[] opciones = {"Artistas", "Programación", "Mapa", "Información"};
        for (String opcion : opciones) {
            JButton button = new JButton(opcion);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(70, 70, 70, 150));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setPreferredSize(new Dimension(200, 100));
            optionsPanel.add(button);
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(optionsPanel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
