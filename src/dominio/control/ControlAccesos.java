package dominio.control;


import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.excepciones.*;
import dominio.persona.Persona;
import dominio.zona.Stand;
import dominio.zona.Zona;
import dominio.zona.ZonaRestringida;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import dominio.persistencia.Persistencia;

public class ControlAccesos {
    private List<Zona> zonas;
    private List<Persona> personas;

    /**
     * @param zonas    lista de zonas del festival
     * @param personas lista de personas registradas
     */
    public ControlAccesos(List<Zona> zonas, List<Persona> personas){
        this.zonas = zonas;
        this.personas = personas;
    }

    /**
     * Obtiene todos los stands (zonas restringidas de tipo Stand).
     *
     * @return lista de Stand
     */
    public List<Stand> getStands() {
        return zonas.stream()
                .filter(z -> z instanceof Stand)
                .map(z -> (Stand) z)
                .collect(Collectors.toList());
    }

    /**
     * @return lista de todas las zonas
     */
    public List<Zona> getZonas() {
        return zonas;
    }

    /**
     * @param zonas nueva lista de zonas
     */
    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    /**
     * @return lista de todas las personas
     */
    public List<Persona> getPersonas() {
        return personas;
    }

    /**
     *
     * @param personas nueva lista de personas
     */
    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }


    /**
     * Mueve una persona de una zona de origen a una zona destino,
     * registrando el acceso y actualizando la persistencia.
     *
     * @param p       persona a mover
     * @param origen  zona de la cual proviene la persona (puede ser null si es su primer ingreso)
     * @param destino zona a la que quiere ingresar
     * @throws NULLdestinoException         si el destino es null
     * @throws DestinoInvalidoException     si origen y destino son iguales
     * @throws AccesoNoAutorizadoException  si la persona no tiene permiso para entrar
     * @throws CapacidadAlcanzadaException  si la zona destino está llena
     */
    public void moverPersona(Persona p, Zona origen, Zona destino)
            throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, DestinoInvalidoException {
        if (destino == null) {
            throw new NULLdestinoException();
        }else if (origen != null && origen.equals(destino)) {
            throw new DestinoInvalidoException();
        } else if (!p.tieneAcceso(destino)) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            Persistencia.guardarPersonas(personas);
            throw new AccesoNoAutorizadoException(p, destino);
        } else if (!destino.hayEspacio()) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            Persistencia.guardarPersonas(personas);
            throw new CapacidadAlcanzadaException((ZonaRestringida) destino);
        } else {
            if (origen != null) {
                origen.eliminarPersona(p);
            }
            destino.agregarPersona(p);
            registrarAcceso(p, destino, Estado.AUTORIZADO);
            Persistencia.guardarZonas(zonas);
            Persistencia.guardarPersonas(personas);
        }
    }

    private void registrarAcceso(Persona p, Zona z, Estado estado) {
        LocalDateTime ahora = LocalDateTime.now();
        List<Acceso> accesos = p.getAccesos();
        if (!accesos.isEmpty()) {
            Acceso anterior = accesos.get(accesos.size() - 1);
            long mins = ChronoUnit.MINUTES.between(
                    anterior.getFecha(), ahora
            );
            anterior.setDuracion((int) mins);
        }
        Acceso nuevo = new Acceso(z, 0, estado);
        p.agregarAcceso(nuevo);
    }


    /**
     * Determina en qué zona se encuentra actualmente la persona.
     *
     * @param p persona a buscar
     * @return zona donde está la persona, o null si no está en ninguna
     */
    public Zona obtenerZonaActual(Persona p) {
        Iterator<Zona> it = zonas.iterator();
        Zona encontrada = null;
        while (it.hasNext() && encontrada == null) {
            Zona z = it.next();
            if (z.estaPersona(p)) {
                encontrada = z;
            }
        }
        return encontrada;
    }


    /**
     * Busca una persona por su ID.
     *
     * @param id identificador de la persona
     * @return objeto Persona encontrado
     * @throws RuntimeException si no existe ninguna persona con ese ID
     */
    public Persona obtenerPersona(int id) throws RuntimeException {
        Iterator<Persona> iter = personas.iterator();
        Persona encontrada = null;
        while (iter.hasNext() && encontrada == null) {
            Persona p = iter.next();
            if (p.getId() == id) {
                encontrada = p;
            }
        }
        if (encontrada == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + id);
        }
        return encontrada;
    }


    /**
     * Genera un texto con datos de la persona y su historial de accesos.
     *
     * @param p persona de la cual mostrar los datos
     * @return cadena formateada con la información
     */
    public String mostrarDatos(Persona p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.toString()).append("\n");
        sb.append("Accesos:\n");
        List<Acceso> accesos = p.getAccesos();
        if (accesos == null || accesos.isEmpty()) {
            sb.append("  (Sin accesos registrados)\n");
        } else {
            for (Acceso a : accesos) {
                sb.append(a.toString()).append("\n");
            }
        }
        return sb.toString();
    }

}
