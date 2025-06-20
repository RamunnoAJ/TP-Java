package GUI;

import javax.swing.*;
import java.awt.*;
import dominio.control.Reportes;
import dominio.persistencia.TestPersistencia;
import dominio.zona.Zona;
import dominio.zona.Stand;
import dominio.persistencia.Persistencia;
import java.util.List;
import java.awt.event.*;
import dominio.persona.Persona;
import dominio.excepciones.ControlInvalidoException;
import dominio.control.ControlAccesos;
import GUI.ConsultaAccesosFrame;


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

class ReporteFrame extends JFrame {
    public ReporteFrame(String tipo) {
        setTitle("Reporte " + tipo);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.addActionListener(e -> {
            new MenuFrame().setVisible(true);
            dispose();
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        JPanel south = new JPanel();
        south.add(btnVolver);
        panel.add(south, BorderLayout.SOUTH);
        setContentPane(panel);

        if ("ZONAS".equals(tipo)) {
            List<Zona> zonas = Persistencia.cargarZonas();
            String reporte = Reportes.generarReporteZonas(zonas);
            textArea.setText(reporte);
        } else {
            List<Stand> stands = Persistencia.cargarStands();
            String reporte = Reportes.generarReporteStands(stands);
            textArea.setText(reporte);
        }
    }
}

class ConsultaAccesosFrame extends JFrame {
    private JTextField txtId;
    private JTextArea textArea;
    private ControlAccesos control;

    public ConsultaAccesosFrame() {
        control = new ControlAccesos();

        setTitle("Consulta de Persona");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5,5));

        JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNorth.add(new JLabel("ID Persona:"));
        txtId = new JTextField(8);
        pnlNorth.add(txtId);
        JButton btnBuscar = new JButton("Buscar");
        pnlNorth.add(btnBuscar);
        add(pnlNorth, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        btnBuscar.addActionListener((ActionEvent e) -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Persona p = control.obtenerPersona(id);
                textArea.setText(control.mostrarDatos(p));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ControlInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
