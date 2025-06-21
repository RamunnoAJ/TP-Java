package GUI;

import dominio.control.Reportes;
import dominio.zona.Stand;
import dominio.zona.Zona;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Frame que muestra un reporte, ya sea de zonas o de stands,
 * según el tipo indicado ("ZONAS" o cualquier otro valor para stands).
 */
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
            List<Zona> zonas = FestivalApp.control.getZonas();
            String reporte = Reportes.generarReporteZonas(zonas);
            textArea.setText(reporte);
        } else {
            List<Stand> stands = FestivalApp.control.getStands();
            String reporte = Reportes.generarReporteStands(stands);
            textArea.setText(reporte);
        }
    }
}