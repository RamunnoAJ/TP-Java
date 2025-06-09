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
import Zona.ZonaRestringida;

public class ControlAccesos {
    private DateTimeFormatter fmtFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter fmtHora  = DateTimeFormatter.ofPattern("HH:mm:ss");

    //para usar mas tarde
    public List<Acceso> consultarDatos(Persona p) {
        return p.getAccesos();
    }

    public void moverPersona(Persona p, Zona origen, Zona destino)
            throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, ZonaInvalidaException {
        if (destino == null || origen == null || origen.equals(destino)) {
            throw new ZonaInvalidaException();
        } else if (!p.tieneAcceso(destino)) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new AccesoNoAutorizadoException(p, destino);
        } else if (destino.getCapMax() > 0
                && destino.getPersonas().size() >= destino.getCapMax()) {
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new CapacidadAlcanzadaException((ZonaRestringida) destino);
        } else {
            origen.eliminarPersona(p);
            destino.agregarPersona(p);
            registrarAcceso(p, destino, Estado.AUTORIZADO);
        }
    }

    private void registrarAcceso(Persona p, Zona z, Estado estado) {
        String fecha = LocalDate.now().format(fmtFecha);
        String hora  = LocalTime.now().format(fmtHora);
        Acceso a = new Acceso(z, 0, estado);
        p.agregarAcceso(a);
    }

    public void mostrarDatos(Persona p) { // Esto se reemplaza, no se deberían de hacer los println en la capa del dominio
        System.out.println("Datos de la persona");
        System.out.println(p.toString());
        System.out.println("Historial de accesos de la persona");
        p.mostrarAccesos();
    }
}
