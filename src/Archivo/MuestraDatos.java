package Archivo;
import dominio.acceso.Acceso;
import dominio.persona.*;
import dominio.zona.*;
import java.util.List;

public class MuestraDatos {
    public static void mostrarZonas(List<Zona> zonas) {
        for (Zona z : zonas) {
            System.out.println("=========================================");
            System.out.println("Zona: " + z.getCodigo() + " - " + z.getDescripcion());
            System.out.println("Tipo: " + z.getClass().getSimpleName());

            if (z instanceof ZonaRestringida zr) {
                System.out.println("Capacidad Máxima: " + zr.getCapMax());
            }

            if (z instanceof Escenario esc) {
                System.out.println("Eventos:");
                if (esc.getEventos() != null) {
                    for (Evento e : esc.getEventos()) {
                        System.out.println("  - " + e);
                    }
                }
            }

            if (z instanceof Stand stand) {
                System.out.println("Ubicación: " + stand.getUbicacion());
                System.out.println("Zona común asociada: " + (stand.getZonacomun() != null ? stand.getZonacomun().getCodigo() : "No asignada"));
                System.out.println("Responsable: " + (stand.getResponsable() != null ? stand.getResponsable().getNombre() : "Sin responsable"));
                System.out.println("Empleados:");
                for (Comerciante c : stand.getEmpleados()) {
                    System.out.println("  - " + c.getNombre() + " (ID: " + c.getId() + ")");
                }
            }

            System.out.println("Personas:");
            for (Persona p : z.getPersonas()) {
                System.out.print("  - " + p.getNombre() + " (ID: " + p.getId() + ") - ");
                if (p instanceof Artista) {
                    System.out.println("Artista");
                } else if (p instanceof Comerciante) {
                    System.out.println("Comerciante");
                } else if (p instanceof StaffOrganizador) {
                    System.out.println("Staff Organizador");
                } else if (p instanceof Asistente) {
                    System.out.println("Asistente");
                } else {
                    System.out.println("Otro");
                }

                System.out.println("    Accesos:");
                if (p.getAccesos() != null) {
                    for (Acceso a : p.getAccesos()) {
                        System.out.println("      - " + a);
                    }
                }
            }

            System.out.println();
        }
    }
}

