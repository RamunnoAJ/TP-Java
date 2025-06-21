package GUI;


import cargador.CargadorXML;
import cargador.InformeErrores;
import dominio.acceso.Acceso;
import dominio.control.ControlAccesos;
import dominio.persistencia.Persistencia;
import dominio.persona.Persona;
import dominio.zona.Zona;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase principal de la aplicación del festival.
 * Permite al usuario elegir entre cargar datos desde un XML o desde archivos de persistencia,
 * inicializa el ControlAccesos con los datos resultantes y muestra la ventana de inicio.
 */
public class FestivalApp {
    public static ControlAccesos control;
    /**
     * Punto de entrada de la aplicación.
     * Pregunta al usuario cómo desea cargar los datos, realiza la carga correspondiente,
     * persiste los datos si se obtuvo del XML, y arranca la ventana de inicio.
     *
     * @param args argumentos de línea de comando (no utilizados)
     */
    public static void main(String[] args) {
        // Pregunta al usuario cómo cargar los datos
        String[] options = {"Cargar XML", "Cargar Persistencia"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "¿Cómo desea cargar los datos del festival?",
                "Cargar datos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        List<Zona> zonas;
        List<Persona> personas;
        if (choice == 0) {
            var cargador = new CargadorXML("src/festival.xml");
            var informe = new InformeErrores();
            var resultado = cargador.cargar(informe);
            informe.imprimir();
            zonas = resultado.getZonas();
            personas = resultado.getPersonas();

            Persistencia.guardarZonas(zonas);
            Persistencia.guardarPersonas(personas);

            List<Acceso> accesos = new ArrayList<>();
            for (var p : personas)
                accesos.addAll(p.getAccesos());
            Persistencia.guardarAccesos(accesos);
        } else {
            zonas = Persistencia.cargarZonas();
            personas = Persistencia.cargarPersonas();
        }
        control = new ControlAccesos(zonas, personas);
        new InicioFrame().setVisible(true);

    }
}


