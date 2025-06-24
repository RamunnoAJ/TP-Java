package GUI;

import dominio.control.ControlAccesos;
import dominio.excepciones.AccesoNoAutorizadoException;
import dominio.excepciones.CapacidadAlcanzadaException;
import dominio.excepciones.NULLdestinoException;
import dominio.persona.Persona;
import dominio.zona.Zona;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Frame para mover una persona de su zona actual a otra zona seleccionada.
 * Muestra dos desplegables (persona y destino) y botones para ejecutar o cancelar la acción.
 */
public class MoverPersonaFrame extends JFrame {
    private JComboBox<Zona> cbDestino;
    private JComboBox<Persona> cbPersona;
    private ControlAccesos control;

    /**
     * Construye el frame configurando los componentes UI y cargando
     * las listas de personas y zonas del controlador compartido.
     */
    public MoverPersonaFrame() {
        control = FestivalApp.control;
        List<Zona> zonas = control.getZonas();
        List<Persona> personas = control.getPersonas();

        setTitle("Mover Persona");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        cbPersona = new JComboBox<>(personas.toArray(new Persona[0]));
        centerPanel.add(new JLabel("Persona:"));
        centerPanel.add(cbPersona);

        cbDestino = new JComboBox<>(zonas.toArray(new Zona[0]));
        centerPanel.add(new JLabel("Zona Destino:"));
        centerPanel.add(cbDestino);

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnMover = new JButton("Mover");
        JButton btnVolver = new JButton("Volver");

        southPanel.add(btnMover);
        southPanel.add(btnVolver);
        add(southPanel, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            new MenuFrame().setVisible(true);
            dispose();
        });

        btnMover.addActionListener(e -> {
            try {
                Persona p = (Persona) cbPersona.getSelectedItem();
                Zona origen = control.obtenerZonaActual(p);
                Zona destino = (Zona) cbDestino.getSelectedItem();
                control.moverPersona(p, origen, destino);

                String codOrigen = (origen != null ? origen.getCodigo() : "ninguna");
                JOptionPane.showMessageDialog(this,
                        "Persona movida de \"" + codOrigen +
                                "\" a \"" + destino.getCodigo() + "\" exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new MenuFrame().setVisible(true);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "El ID debe ser un número entero",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (AccesoNoAutorizadoException |
                     CapacidadAlcanzadaException |
                     NULLdestinoException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error de acceso",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error inesperado: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
