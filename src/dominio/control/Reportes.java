package dominio.control;

import java.util.*;
import java.io.*;

import dominio.persona.Persona;
import dominio.zona.Escenario;
import dominio.zona.Evento;
import dominio.zona.Stand;
import dominio.zona.Zona;

/**
 * Utilidad para generar reportes de concurrencia y actividad del festival.
 * Provee métodos estáticos para:
 * Generar un reporte de zonas, ordenando descendentemente por número de asistentes
 * e incluyendo eventos programados en los escenarios.
 * Generar un reporte de stands, ordenando alfabéticamente por el nombre del responsable
 * e incluyendo la lista de empleados.
 *
 * Cada metodo escribe también un archivo de texto con el reporte correspondiente.
 */

public class Reportes implements Serializable {

    /**
     * Genera un reporte de concurrencia por zona, ordenado descendentemente
     * por la cantidad de personas en cada zona, e incluye detalle de eventos en escenarios.
     *
     * @param zonas lista de zonas a incluir en el reporte
     * @return texto formateado del reporte de zonas
     */
    public static String generarReporteZonas(List<Zona> zonas) {
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
                int concurrencia = zona.getPersonas().size();
                totalPersonas += concurrencia;
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
        return sb.toString();
    }

    /**
     * Genera un reporte de todos los stands, ordenándolos alfabéticamente
     * por el nombre de su responsable e incluyendo la lista de empleados.
     *
     * @param stands lista de stands a incluir en el reporte
     * @return texto formateado del reporte de stands
     */
    public static String generarReporteStands(List<Stand> stands) {
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
        return sb.toString();
    }
}
