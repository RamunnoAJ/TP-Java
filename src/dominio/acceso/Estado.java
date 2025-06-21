package dominio.acceso;

/**
 * Representa los posibles estados de un acceso a zona.
 */
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


