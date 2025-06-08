package Acceso;

public enum Estado {
    AUTORIZADO, DENEGADO;

    public String toString(){
        return (valueOf(AUTORIZADO))?"Autorizado":"Denegado";
    }
}


