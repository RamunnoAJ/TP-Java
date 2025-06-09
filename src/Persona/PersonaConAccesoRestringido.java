package Persona;

import Acceso.Acceso;

import java.util.List;
import Zona.Zona;

public abstract class PersonaConAccesoRestringido extends Persona {
    private List<Zona> zonasPermitidas;

    public PersonaConAccesoRestringido(int id, String nombre, List<Acceso> accesos, List<Zona> zon) {
        super(id, nombre, accesos);
        this.zonasPermitidas=zon;
    }

    public List<Zona> getZonasPermitidas() {
        return zonasPermitidas;
    }

    public void setZonasPermitidas(List<Zona> zonasPermitidas) {
        this.zonasPermitidas = zonasPermitidas;
    }

    //o hacer con iterador?
    public boolean estaPermitida(Zona zona) {
        return zonasPermitidas.contains(zona);
    }
}
