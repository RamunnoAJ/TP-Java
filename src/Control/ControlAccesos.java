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

    /*
     Devuelve la lista completa de accesos (autorizados y denegados)
     de la persona p. No tiene uso aun, para mas adelante para hacer los reportes
     */
    public List<Acceso> consultarDatos(Persona p) {
        return p.getAccesos();
    }
    
    public void moverPersona(Persona p, Zona origen, Zona destino) throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, ZonaInvalidaException {
        if (destino == null || origen == null) {
            // Zona destino inválida
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new ZonaInvalidaException("Zona destino u origen nula");
        } else if (!p.tieneAcceso(destino)) {// Chequeo permiso
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new AccesoNoAutorizadoException(
                    "Persona " + p.getNombre() + " no autorizada para zona " + destino.getCodigo());
        } else if (destino.getPersonas().size() >= destino.getCapacidadMaxima()) {// Chequeo capacidad
            registrarAcceso(p, destino, Estado.DENEGADO);
            throw new CapacidadAlcanzadaException(
                    "Zona " + destino.getCodigo() + " al máximo de su capacidad");

        }else{
            // Si llegamos acá es porque se puede mover a la persona
            // 1. Remuevo a la persona de la zona origen
            origen.getPersonas().remove(p);
            // 2. Agregar a la persona a la zona destino
            destino.getPersonas().add(p);
            // 3. Registrar acceso autorizado
            registrarAcceso(p, destino, Estado.AUTORIZADO);
        }
    }

    //La hice privada asi solo se puede acceder desde un metodo de esta clase y no de afuera, ya que solo se invoca si se pudo mover
    private void registrarAcceso(Persona p, Zona z, Estado estado) {
        String fecha = LocalDate.now().format(fmtFecha);
        String hora  = LocalTime.now().format(fmtHora);
        Acceso a = new Acceso(z, fecha, hora, 0, estado);
        p.getAccesos().add(a);
    }
}
