package GUI;

import javax.swing.*;
import java.awt.*;

public class InicioFrame extends JFrame {
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
                Color color1 = new Color(0, 0, 0);
                Color color2 = new Color(255, 255, 255);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JLabel logoLabel;
        try {
            ImageIcon originalIcon = new ImageIcon("imagenF.png");
            Image scaledImage = originalIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            ImageIcon logoIcon = new ImageIcon(scaledImage);
            logoLabel = new JLabel(logoIcon, SwingConstants.CENTER);
        } catch (Exception e) {
            logoLabel = new JLabel("LOGO NO ENCONTRADO", SwingConstants.CENTER);
            logoLabel.setForeground(Color.RED);
        }
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton entrarButton = new JButton("ENTRAR AL MENÚ");
        entrarButton.setFont(new Font("Arial", Font.BOLD, 18));
        entrarButton.setForeground(Color.WHITE);
        entrarButton.setBackground(new Color(66, 64, 64));
        entrarButton.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        entrarButton.addActionListener(e -> {
            dispose();
            new MenuFrame().setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(entrarButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

        mainPanel.add(logoLabel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }
}