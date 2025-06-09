package Acceso;

public enum Estado {
    AUTORIZADO {
        @Override
        public String toString() {
            return "Autorizado";
        }
    },
    DENEGADO {
        @Override
        public String toString() {
            return "Denegado";
        }
    }
}


