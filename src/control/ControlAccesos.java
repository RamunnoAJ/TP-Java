package control;

import acceso.Acceso;
import acceso.Estado;
import excepciones.AccesoNoAutorizadoException;
import excepciones.CapacidadAlcanzadaException;
import excepciones.ZonaInvalidaException;
import persona.Persona;
import zona.Zona;
import zona.ZonaRestringida;

public class ControlAccesos {

    public void moverPersona(Persona p, Zona origen, Zona destino)
            throws AccesoNoAutorizadoException, CapacidadAlcanzadaException, ZonaInvalidaException {
        if (destino == null || origen == null || origen.equals(destino)) {
            throw new ZonaInvalidaException();
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

    public void mostrarDatos(Persona p) { // Esto se reemplaza, no se deberían de hacer los println en la capa del dominio
        System.out.println("Datos de la persona");
        System.out.println(p);
        System.out.println("Historial de accesos de la persona");
        p.mostrarAccesos();
    }
}
