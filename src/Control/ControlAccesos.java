package Control;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Acceso.Acceso;
import Acceso.Estado;
import Excepciones.AccesoNoAutorizadoException;
import Excepciones.CapacidadAlcanzadaException;
import Excepciones.ZonaInvalidaException;
import Persona.Persona;
import Zona.Zona;

public class ControlAccesos {
    private DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter fmtHora  = DateTimeFormatter.ofPattern("HH:mm:ss");

    //para usar mas tarde
    public List<Acceso> consultarDatos(Persona p) {
        return p.getAccesos();
    }

    public void moverPersona(Persona p, Zona origen, Zona destino)
            throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, ZonaInvalidaException {
        if (destino == null || origen == null) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new ZonaInvalidaException("Alguna zona es nula");
        } else if (origen.equals(destino)) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new ZonaInvalidaException("Ya se encuentra en la zona");
        } else if (!p.tieneAcceso(destino)) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new AccesoNoAutorizadoException(
                    "Persona " + p.getNombre() + " no autorizada para zona " + destino.getCodigo());
        } else if (destino.getCapMax() > 0
                && destino.getPersonas().size() >= destino.getCapMax()) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new CapacidadAlcanzadaException(
                    "Zona " + destino.getCodigo() + " al máximo de su capacidad ("
                            + destino.getCapMax() + ")");
        } else {
            origen.getPersonas().remove(p);
            destino.getPersonas().add(p);
            registrarAcceso(p, destino, Estado.AUTORIZADO);
        }
    }

    private void registrarAcceso(Persona p, Zona z, Estado estado) {
        String fecha = LocalDate.now().format(fmtFecha);
        String hora  = LocalTime.now().format(fmtHora);
        Acceso a = new Acceso(z, fecha, hora, 0, estado);
        p.getAccesos().add(a);
    }

    public void mostrarDatos(Persona p) {
        System.out.println("Datos de la persona");
        System.out.println(p.toString());
        System.out.println("Historial de accesos de la persona");
        p.mostrarAccesos();
    }
}
