package GUI;

import dominio.control.ControlAccesos;
import dominio.persistencia.Persistencia;
import dominio.persona.Persona;
import dominio.zona.Zona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConsultaAccesosFrame extends JFrame {
    private JTextField txtId;
    private JTextArea textArea;
    private ControlAccesos control;

    public ConsultaAccesosFrame() {
        // Utiliza el ControlAccesos inicializado en FestivalApp
        control = FestivalApp.control;  // Evita recargar datos y asegura coherencia

        setTitle("Consulta de Persona");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5,5));

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.addActionListener(e -> {
            new MenuFrame().setVisible(true);
            dispose();
        });

        JPanel pnlNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlNorth.add(new JLabel("ID Persona:"));
        txtId = new JTextField(8);
        pnlNorth.add(txtId);
        JButton btnBuscar = new JButton("Buscar");
        pnlNorth.add(btnBuscar);
        add(pnlNorth, BorderLayout.NORTH);
        add(btnVolver, BorderLayout.SOUTH);

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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
