package Archivo;
import Acceso.Acceso;
import Zona.Zona;
import Zona.ZonaRestringida;
import Zona.Escenario;
import Zona.Evento;
import Zona.Stand;
import Zona.ZonaComun;
import Persona.Persona;
import Persona.Artista;
import Persona.Comerciante;
import Persona.PersonaConAccesoRestringido;
import Persona.Asistente;
import Persona.Empleado;
import Persona.StaffOrganizador;
import java.util.List;

public class lectura {
    public static void main(Festival args) {
        System.out.println("Festival cargado correctamente.");
        System.out.println(" Total de zonas: " + Festival.getZonas().size());

        for (Zona zona : Festival.getZonas()) {
            System.out.println("---------------------------------------------------");
            System.out.println("Zona: " + zona.getCodigo());
            System.out.println("Tipo: " + zona.getClass().getSimpleName());
            System.out.println("Descripción: " + zona.getDescripcion());
            System.out.println("Capacidad máxima: " + zona.getCapMax());

            List<Persona> personas = zona.getPersonas();
            if (personas != null && !personas.isEmpty()) {
                System.out.println(" Personas en la zona:");

                for (Persona p : personas) {
                    System.out.println("---------------------------------------------------");
                    System.out.println(" ID: " + p.getId());
                    System.out.println(" Nombre: " + p.getNombre());
                    System.out.println(" Tipo: " + p.getClass().getSimpleName());

                    if (p.getAccesos() != null) {
                        System.out.println("   Accesos:");
                        for (Acceso a : p.getAccesos()) {
                            System.out.println("     ▶ Zona: " + a.getZona() +
                                    " | Fecha: " + a.getFecha() +
                                    " | Hora: " + a.getHora() +
                                    " | Minutos: " + a.getCantMinutos() +
                                    " | Estado: " + a.getEstado());
                        }
                    }

                    if (p instanceof PersonaConAccesoRestringido par) {
                        if (par.getZonaspermitidas() != null) {
                            System.out.println("   Zonas permitidas:");
                        }
                    }

                    if (p instanceof Comerciante c) {
                        System.out.println("   Stand: " + c.getStand());
                        if (c.getEmpleados() != null) {
                            System.out.println("   Empleados (IDs):");
                            for (Empleado emp : c.getEmpleados()) {
                                System.out.println("     - " + emp.getId());
                            }
                        }
                    }
                }
            }

            if (zona instanceof Escenario escenario) {
                System.out.println(" Eventos:");
                for (Evento e : escenario.getEventos()) {
                    System.out.println("   - " + e.getFecha() + " " + e.getHora() + ": " + e.getArtista());
                }
            }

            if (zona instanceof Stand stand && stand.getComerciante() != null) {
                Comerciante com = stand.getComerciante();
                System.out.println("Comerciante del Stand: " + com.getNombre() + " (ID: " + com.getId() + ")");
                System.out.println("   Stand: " + com.getStand());

            }

            System.out.println();
        }
    }
}

