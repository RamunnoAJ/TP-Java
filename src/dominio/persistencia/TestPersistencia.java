package dominio.persistencia;

import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.persona.Artista;
import dominio.persona.Comerciante;
import dominio.persona.Persona;
import dominio.persona.StaffOrganizador;
import dominio.zona.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestPersistencia {
    public static void main(String[] args) {
        List<Zona> zonas = new ArrayList<>();
        List<Persona> personas = new LinkedList<>();
        List<Acceso> accesos = new ArrayList<>();
        List<Evento> eventos = new ArrayList<>();

        zonas.add(new Escenario("asd", "Escenario re loco", 20));
        zonas.add(new Stand(
                "qwe" ,
                "Nuevo stand",
                30,
                "Norte",
                new PatioDeComidas("comida", "Patio de comidas"),
                new Comerciante(10, "roberto", new ArrayList<>(), new ArrayList<>(), null)
        ));

        personas.add(new StaffOrganizador(1, "Luis", new ArrayList<>()));
        personas.add(new StaffOrganizador(2, "Ana", new ArrayList<>()));

        accesos.add(new Acceso(new Escenario("asd", "Escenario re loco", 20), 0, Estado.DENEGADO));
        accesos.add(new Acceso(new Escenario("asd", "Escenario re loco", 20), 10, Estado.AUTORIZADO));

        eventos.add(new Evento("2025-01-01", "14:40", new Artista(2, "Emilia mernes", new ArrayList<>(), new ArrayList<>(), null)));
        eventos.add(new Evento("2025-01-01", "18:40", new Artista(3, "Tini tini tini", new ArrayList<>(), new ArrayList<>(), null)));

        Persistencia.guardarZonas(zonas);
        System.out.println("Zonas persistidas exitosamente.");

        Persistencia.guardarPersonas(personas);
        System.out.println("Personas persistidas exitosamente.");

        Persistencia.guardarAccesos(accesos);
        System.out.println("Accesos persistidas exitosamente.");

        Persistencia.guardarEventos(eventos);
        System.out.println("Eventos persistidas exitosamente.");

        List<Persona> personasLeidas = Persistencia.cargarPersonas();
        System.out.println("Personas leídas:");

        for (Persona p : personasLeidas) {
            System.out.println(p);
        }

        List<Zona> zonasLeidas = Persistencia.cargarZonas();
        System.out.println("Zonas leídas:");

        for (Zona z : zonasLeidas) {
            System.out.println(z);
        }

        List<Acceso> accesosLeidos = Persistencia.cargarAccesos();
        System.out.println("Accesos leídas:");

        for (Acceso a : accesosLeidos) {
            System.out.println(a);
        }

        List<Evento> eventosLeidos = Persistencia.cargarEventos();
        System.out.println("Eventos leídas:");

        for (Evento e : eventosLeidos) {
            System.out.println(e);
        }
    }
}
