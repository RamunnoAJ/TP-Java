package GUI;

import dominio.control.Reportes;
import dominio.persistencia.Persistencia;
import dominio.zona.Stand;
import dominio.zona.Zona;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReporteFrame extends JFrame {
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
            java.util.List<Zona> zonas = Persistencia.cargarZonas();
            String reporte = Reportes.generarReporteZonas(zonas);
            textArea.setText(reporte);
        } else {
            List<Stand> stands = Persistencia.cargarStands();
            String reporte = Reportes.generarReporteStands(stands);
            textArea.setText(reporte);
        }
    }
}