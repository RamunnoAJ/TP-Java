package dominio.persistencia;

import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.persona.*;
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
        List<Stand> stands = new ArrayList<>();

        StaffOrganizador organizador1 = new StaffOrganizador(201, "María", new ArrayList<>());
        StaffOrganizador organizador2 = new StaffOrganizador(202, "José", new ArrayList<>());
        Artista artista1 = new Artista(301, "Duki", new ArrayList<>(), new ArrayList<>(), null);
        Artista artista2 = new Artista(302, "Bizarrap", new ArrayList<>(), new ArrayList<>(), null);
        Comerciante comerciante1 = new Comerciante(101, "Don Empanada", new ArrayList<>(), new ArrayList<>(), null);
        Comerciante comerciante2 = new Comerciante(102, "La Pizza Loca", new ArrayList<>(), new ArrayList<>(), null);
        Concurrente concurrente1 = new Concurrente(401, "Lautaro", new ArrayList<>(), new ArrayList<>());
        Concurrente concurrente2 = new Concurrente(402, "Camila", new ArrayList<>(), new ArrayList<>());

        Escenario escenarioPrincipal = new Escenario(
                "ESC001", "Escenario Principal", new LinkedList<>(), 100, new LinkedList<>());

        PatioDeComidas patio = new PatioDeComidas(
                "PAT001", "Patio Norte", new LinkedList<>());

        Stand stand1 = new Stand(
                "ST001", "Stand de Empanadas", new LinkedList<>(), 10, "Zona Norte", patio,
                comerciante1, new ArrayList<>());

        Stand stand2 = new Stand(
                "ST002", "Stand de Pizzas", new LinkedList<>(), 8, "Zona Norte", patio,
                comerciante2, new ArrayList<>());

        Acceso acceso1 = new Acceso(escenarioPrincipal, 90, Estado.AUTORIZADO);
        Acceso acceso2 = new Acceso(escenarioPrincipal, 0, Estado.DENEGADO);
        Acceso acceso3 = new Acceso(stand1, 10, Estado.AUTORIZADO);
        Acceso acceso4 = new Acceso(stand2, 15, Estado.AUTORIZADO);
        Acceso acceso5 = new Acceso(patio, 50, Estado.AUTORIZADO);
        Acceso acceso6 = new Acceso(escenarioPrincipal, 0, Estado.DENEGADO);
        Acceso acceso7 = new Acceso(escenarioPrincipal, 30, Estado.AUTORIZADO);

        accesos.add(acceso1);
        accesos.add(acceso2);
        accesos.add(acceso3);
        accesos.add(acceso4);
        accesos.add(acceso5);
        accesos.add(acceso6);
        accesos.add(acceso7);

        Evento evento1 = new Evento("2025-10-10", "20:00", artista1);
        Evento evento2 = new Evento("2025-10-10", "21:30", artista2);

        escenarioPrincipal.agregarEventoMusical(evento1);
        escenarioPrincipal.agregarEventoMusical(evento2);


        escenarioPrincipal.agregarPersona(concurrente1);
        escenarioPrincipal.agregarPersona(concurrente2);
        stand1.agregarEmpleado(comerciante1);
        stand1.agregarPersona(organizador1);
        stand2.agregarEmpleado(comerciante1);
        stand2.agregarEmpleado(comerciante2);
        stand2.agregarPersona(organizador2);

        concurrente1.agregarAcceso(acceso1);
        concurrente1.agregarAcceso(acceso3);
        concurrente1.agregarAcceso(acceso5);
        concurrente1.agregarAcceso(acceso7);
        concurrente2.agregarAcceso(acceso2);
        concurrente2.agregarAcceso(acceso4);
        concurrente2.agregarAcceso(acceso6);

        personas.add(organizador1);
        personas.add(organizador2);
        personas.add(artista1);
        personas.add(artista2);
        personas.add(comerciante1);
        personas.add(comerciante2);
        personas.add(concurrente1);
        personas.add(concurrente2);

        stands.add(stand1);
        stands.add(stand2);

        eventos.add(evento1);
        eventos.add(evento2);

        zonas.add(escenarioPrincipal);
        zonas.add(patio);
        zonas.add(stand1);
        zonas.add(stand2);

        Persistencia.guardarZonas(zonas);
        Persistencia.guardarPersonas(personas);
        Persistencia.guardarAccesos(accesos);
        Persistencia.guardarEventos(eventos);
        Persistencia.guardarStands(stands);
    }
}
