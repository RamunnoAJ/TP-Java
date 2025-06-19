package dominio.control;

import java.util.*;
import java.io.*;
import javax.swing.JTextArea;

import dominio.persona.Persona;
import dominio.zona.Escenario;
import dominio.zona.Evento;
import dominio.zona.Stand;
import dominio.zona.Zona;

public class Reportes implements Serializable {

    public static String generarReporteZonas(List<Zona> zonas, JTextArea area) {
        // Va en orden descendente por concurrencia actual
        Collections.sort(zonas, new Comparator<Zona>() {
            @Override
            public int compare(Zona z1, Zona z2) {
                return Integer.compare(z2.getPersonas().size(), z1.getPersonas().size());
            }
        });

        int totalPersonas = 0;
        StringBuilder sb = new StringBuilder();
        try (PrintWriter writer = new PrintWriter(new FileWriter("reporteZonas.txt"))) {
            for (Zona zona : zonas) {
                int concurrencia = zona.getPersonas().size(); //Creo una variable concurrencia para no usar el getter otra vez mas abajo, menos invocaciones
                totalPersonas += concurrencia;//acumulo para despues mostrarlo porque decia la consigna que al final hay que mostrar la cant de todos los del predio
                String linea = String.format("Zona %s - %s: %d personas",
                        zona.getCodigo(), zona.getDescripcion(), concurrencia);
                sb.append(linea).append("\n");
                writer.println(linea);

                if (zona instanceof Escenario) {
                    sb.append("  Eventos programados:\n");
                    writer.println("  Eventos programados:");
                    for (Evento evento : ((Escenario) zona).getEventos()) {
                        String evLine = String.format("    %s %s - %s",
                                evento.getFecha(), evento.getHora(), evento.getArtista().getNombre());
                        sb.append(evLine).append("\n");
                        writer.println(evLine);
                    }
                }
            }
            String lineaTotal = String.format("Total de personas en todo el predio: %d", totalPersonas);
            sb.append(lineaTotal).append("\n");
            writer.println(lineaTotal);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Mostrar en la interfaz gráfica
        area.setText(sb.toString());
        return sb.toString();
    }

    public static String generarReporteStands(List<Stand> stands, JTextArea area) {
        // Orden alfabético por nombre del responsable como dice la consigna
        Collections.sort(stands, new Comparator<Stand>() {
            @Override
            public int compare(Stand s1, Stand s2) {
                return s1.getResponsable().getNombre()
                        .compareToIgnoreCase(s2.getResponsable().getNombre());
            }
        });

        StringBuilder sb = new StringBuilder();
        try (PrintWriter writer = new PrintWriter(new FileWriter("reporteStands.txt"))) {
            for (Stand stand : stands) {
                String linea = String.format("Stand %s - Ubicación: %s - Responsable: %s",
                        stand.getCodigo(), stand.getUbicacion(), stand.getResponsable().getNombre());
                sb.append(linea).append("\n");
                writer.println(linea);

                sb.append("  Empleados:\n");
                writer.println("  Empleados:");
                for (Persona empleado : stand.getEmpleados()) {
                    String empLine = String.format("    %s", empleado.getNombre());
                    sb.append(empLine).append("\n");
                    writer.println(empLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Mostrar en la interfaz gráfica
        area.setText(sb.toString());
        return sb.toString();
    }
}
