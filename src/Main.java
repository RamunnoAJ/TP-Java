import cargador.CargadorXML;
import cargador.InformeErrores;
import cargador.ResultadoCarga;

public class Main{
    public static void main(String[] arg){
        CargadorXML cargador = new CargadorXML("src/festival.xml");
        InformeErrores errores = new InformeErrores();
        ResultadoCarga resultado = cargador.cargar(errores);

        errores.imprimir();

        System.out.println("Zonas cargadas:");
        resultado.getZonas().forEach(System.out::println);

        System.out.println("Personas cargadas:");
        resultado.getPersonas().forEach(System.out::println);

        System.out.println("Personas cargadas:");
        resultado.getPersonas().forEach(System.out::println);
    }
}