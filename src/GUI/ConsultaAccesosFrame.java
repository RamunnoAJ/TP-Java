package GUI;

import dominio.control.ControlAccesos;
import dominio.persona.Persona;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Frame para consultar y mostrar los accesos de una persona por su ID.
 * Utiliza el ControlAccesos de la aplicación principal para obtener y formatear los datos.
 */
public class ConsultaAccesosFrame extends JFrame {
    private JTextField txtId;
    private JTextArea textArea;
    private ControlAccesos control;

    /**
     * Construye la ventana de consulta de accesos.
     * Se apoya en el ControlAccesos inicializado en FestivalApp para mantener coherencia de datos.
     */
    public ConsultaAccesosFrame() {
        control = FestivalApp.control;

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
