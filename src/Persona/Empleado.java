package Persona;

import Acceso.Acceso;

import java.util.List;

public class Empleado extends Persona {
    public Empleado(int id, String nombre, List<Acceso> acceso) {
        super(id,nombre,acceso);
    }

    @Override
    public String toString() {
        return "Empleado. " + super.toString();
    }
}
//falta implementar tiene accceso