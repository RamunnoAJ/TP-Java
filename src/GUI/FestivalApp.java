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

public class FestivalApp {
    public static ControlAccesos control;
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


