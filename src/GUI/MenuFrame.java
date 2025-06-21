package GUI;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
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
                Color color1 = new Color(255, 255, 255);
                Color color2 = new Color(0, 0, 0);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(0, 0, 0));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        JButton btnBuscarPersona = new JButton("Busqueda de Persona");
        JButton btnMoverPersona = new JButton("Mover Persona");
        JButton btnReporteZonas = new JButton("Reporte de Zonas");
        JButton btnReporteStands = new JButton("Reporte de los Stands");

        btnBuscarPersona.addActionListener(e -> {
            new ConsultaAccesosFrame().setVisible(true);
            dispose();
        });

        btnMoverPersona.addActionListener(e -> {
            new MoverPersonaFrame().setVisible(true);
            dispose();
        });

        btnReporteZonas.addActionListener(e -> {
            new ReporteFrame("ZONAS").setVisible(true);
            dispose();
        });

        btnReporteStands.addActionListener(e -> {
            new ReporteFrame("STANDS").setVisible(true);
            dispose();
        });

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        optionsPanel.setOpaque(false);
        optionsPanel.add(btnBuscarPersona);
        optionsPanel.add(btnMoverPersona);
        optionsPanel.add(btnReporteZonas);
        optionsPanel.add(btnReporteStands);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(optionsPanel);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}