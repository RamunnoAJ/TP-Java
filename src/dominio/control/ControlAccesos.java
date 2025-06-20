package dominio.control;


import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.excepciones.*;
import dominio.persona.Persona;
import dominio.zona.Zona;
import dominio.zona.ZonaRestringida;
import java.util.Iterator;
import java.util.List;
import dominio.persistencia.Persistencia;

public class ControlAccesos {
    private List<Zona> zonas;
    private List<Persona> personas;

    public ControlAccesos(List<Zona> zonas, List<Persona> personas){
        this.zonas = zonas;
        this.personas = personas;
    }

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
        Acceso a = new Acceso(z, 0, estado);
        p.agregarAcceso(a);
    }

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



    public String mostrarDatos(Persona p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.toString()).append("\n"); //Con esto se muestran los datos de la persona
        sb.append("Accesos:\n");
        List<Acceso> accesos = p.getAccesos();
        if (accesos == null || accesos.isEmpty()) {
            sb.append("  (Sin accesos registrados)\n");
        } else {
            for (Acceso a : accesos) { //Esto si para la lista de accesos de cada persona
                sb.append("  Zona: ")
                        .append(a.getZona().getCodigo())
                        .append("  – Fecha: ")
                        .append(a.getFecha())
                        .append("  – Duración: ")
                        .append(a.getCantMinutos()).append(" min")
                        .append("  – Estado: ")
                        .append(a.getEstado().name())
                        .append("\n");
            }
        }
        return sb.toString();
    }

}
