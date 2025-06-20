package dominio.control;


import dominio.acceso.Acceso;
import dominio.acceso.Estado;
import dominio.excepciones.AccesoNoAutorizadoException;
import dominio.excepciones.CapacidadAlcanzadaException;
import dominio.excepciones.ControlInvalidoException;
import dominio.excepciones.ZonaInvalidaException;
import dominio.persona.Persona;
import dominio.zona.Zona;
import dominio.zona.ZonaRestringida;
import java.util.Iterator;
import java.util.List;
import dominio.persistencia.Persistencia;

public class ControlAccesos {

    public void moverPersona(Persona p, Zona origen, Zona destino)
            throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, ZonaInvalidaException {
        if (destino == null || origen == null || origen.equals(destino)) {
            throw new ControlInvalidoException(origen,destino);
        } else if (!p.tieneAcceso(destino)) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new AccesoNoAutorizadoException(p, destino);
        } else if (!destino.hayEspacio()) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new CapacidadAlcanzadaException((ZonaRestringida) destino);
        } else {
            origen.eliminarPersona(p);
            destino.agregarPersona(p);
            registrarAcceso(p, destino, Estado.AUTORIZADO);
        }
    }

    private void registrarAcceso(Persona p, Zona z, Estado estado) {
        Acceso a = new Acceso(z, 0, estado);
        p.agregarAcceso(a);
    }

    public Zona obtenerZonaActual(Persona p) throws RuntimeException {
        List<Zona> zonas = Persistencia.cargarZonas();
        Iterator<Zona> it = zonas.iterator();
        Zona encontrada = null;
        while (it.hasNext() && encontrada == null) {
            Zona z = it.next();
            if (z.estaPersona(p)) {
                encontrada = z;
            }
        }
        if (encontrada != null) {
            return encontrada;
        } else {
            throw new RuntimeException(
                    "La persona con ID " + p.getId() + " no está en ninguna zona."
            );
        }
    }

    public Persona obtenerPersona(int id) throws RuntimeException {
        List<Persona> personas = Persistencia.cargarPersonas();
        Iterator<Persona> iter = personas.iterator();
        Persona encontrada = null;
        while (iter.hasNext() && encontrada == null) {
            Persona p = iter.next();
            if (p.getId() == id) {
                encontrada = p;
            }
        }
        if (encontrada == null) {
            throw new RuntimeException("Persona no encontrada con ID: " + id);//me quede dudando, tendremos que usar otras excepciones que las maneje la GUI
            //osea estara mal que los errores se muestren solo en la consola
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
