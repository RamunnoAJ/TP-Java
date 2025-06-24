package cargador;

import dominio.acceso.*;
import dominio.persona.*;
import dominio.zona.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.*;

/**
 * Carga la configuración del festival desde un archivo XML.
 */
public class CargadorXML {
    private final String ruta;

    /**
     * Crea un cargador que leerá el archivo XML en la ruta indicada.
     *
     * @param ruta la ruta al archivo XML de entrada
     */
    public CargadorXML(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Procesa el XML y genera un resultado con las zonas y personas cargadas.
     * Se registran en el informe de errores los problemas hallados (tags desconocidos,
     * accesos inválidos, etc.).
     *
     * @param informe el objeto donde se reportan errores de carga
     * @return objeto ResultadoCarga con las listas de zonas y personas
     */
    public ResultadoCarga cargar(InformeErrores informe) {
        Map<String, Zona> zonasPorCodigo = new HashMap<>();
        Map<Integer, Persona> personasPorId = new HashMap<>();
        List<Zona> zonas = new ArrayList<>();
        List<Persona> personas = new ArrayList<>();

        try {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File(ruta));
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();

            NodeList zonasList = root.getElementsByTagName("zonas").item(0).getChildNodes();
            for (int i = 0; i < zonasList.getLength(); i++) {
                if (zonasList.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
                Element elem = (Element) zonasList.item(i);
                String tipo = elem.getTagName();
                String codigo = getText(elem, "codigo");
                String descripcion = getText(elem, "descripcion");
                LinkedList<Persona> personasZona = new LinkedList<>();

                Zona zona = switch (tipo) {
                    case "patioDeComidas" -> new PatioDeComidas(codigo, descripcion, personasZona);
                    case "backstage" ->
                            new Backstage(codigo, descripcion, personasZona,
                                    Integer.parseInt(getText(elem, "capMax")));
                    case "escenario" ->
                            new Escenario(codigo, descripcion, personasZona,
                                    Integer.parseInt(getText(elem, "capMax")),
                                    new ArrayList<>());
                    case "stand" -> {
                        String ubicacion   = getText(elem, "ubicacion");
                        String codZonaComun= getText(elem, "zonaComun");
                        ZonaComun zc       = (ZonaComun) zonasPorCodigo.get(codZonaComun);
                        yield new Stand(codigo, descripcion, personasZona,
                                Integer.parseInt(getText(elem, "capMax")),
                                ubicacion, zc, null, new ArrayList<>());
                    }
                    default -> {
                        informe.agregar("Tipo de zona desconocido: " + tipo);
                        yield null;
                    }
                };

                if (zona != null) {
                    zonas.add(zona);
                    zonasPorCodigo.put(codigo, zona);
                }
            }

            NodeList personasList = root.getElementsByTagName("personas").item(0).getChildNodes();
            for (int i = 0; i < personasList.getLength(); i++) {
                if (personasList.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
                Element elem = (Element) personasList.item(i);
                String tipo = elem.getTagName();
                int id       = Integer.parseInt(getText(elem, "id"));
                String nombre= getText(elem, "nombre");
                Persona p = switch (tipo) {
                    case "staffOrganizador" -> new StaffOrganizador(id, nombre, new ArrayList<>());
                    case "asistente"        -> new Asistente(id, nombre, new ArrayList<>());
                    case "artista"          -> {
                        String codEsc = getText(elem, "escenario");
                        Escenario esc = (Escenario) zonasPorCodigo.get(codEsc);
                        yield new Artista(id, nombre, new ArrayList<>(), esc);
                    }
                    case "comerciante"      -> {
                        String codSt = getText(elem, "stand");
                        Stand stand = (Stand) zonasPorCodigo.get(codSt);
                        Comerciante c = new Comerciante(id, nombre, new ArrayList<>(), stand);
                        if (stand != null) {
                            if (stand.getResponsable() == null)
                                stand.setResponsable(c);
                            else
                                stand.getEmpleados().add(c);
                        }
                        yield c;
                    }
                    default -> {
                        informe.agregar("Tipo de persona desconocido: " + tipo);
                        yield null;
                    }
                };
                if (p != null) {
                    personas.add(p);
                    personasPorId.put(id, p);
                }
            }

            NodeList accesosList = root.getElementsByTagName("accesos").item(0).getChildNodes();
            for (int i = 0; i < accesosList.getLength(); i++) {
                if (accesosList.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
                Element elem = (Element) accesosList.item(i);

                int idPersona = Integer.parseInt(getText(elem, "persona"));
                String codZona = getText(elem, "zona");
                int duracion   = Integer.parseInt(getText(elem, "duracion"));
                Estado estado  = Estado.valueOf(getText(elem, "estado"));

                Persona persona = personasPorId.get(idPersona);
                Zona zona       = zonasPorCodigo.get(codZona);

                if (persona != null && zona != null) {
                    Acceso acceso = new Acceso(zona, duracion, estado);
                    persona.getAccesos().add(acceso);
                    zona.getPersonas().add(persona);  // ← ¡Clave para que la concurrencia suba!
                } else {
                    informe.agregar("Acceso inválido: persona " + idPersona + ", zona " + codZona);
                }
            }

            NodeList escenarios = root.getElementsByTagName("escenario");
            for (int i = 0; i < escenarios.getLength(); i++) {
                Element escElem = (Element) escenarios.item(i);
                String codEsc   = getText(escElem, "codigo");
                Escenario esc   = (Escenario) zonasPorCodigo.get(codEsc);

                NodeList eventosList = escElem.getElementsByTagName("evento");
                for (int j = 0; j < eventosList.getLength(); j++) {
                    if (eventosList.item(j).getNodeType() != Node.ELEMENT_NODE) continue;
                    Element evElem = (Element) eventosList.item(j);

                    String fecha  = getText(evElem, "fecha");
                    String hora   = getText(evElem, "hora");
                    int idArtista = Integer.parseInt(getText(evElem, "artista"));
                    Artista art   = (Artista) personasPorId.get(idArtista);

                    if (art != null) {
                        esc.getEventos().add(new Evento(fecha, hora, art));
                    } else {
                        informe.agregar(
                                "Evento inválido en escenario " + codEsc +
                                        ": artista " + idArtista + " no existe");
                    }
                }
            }

            //ordeno las zonas por codigo
            zonas.sort(Comparator.comparing(Zona::getCodigo));

        } catch (Exception e) {
            informe.agregar("Error procesando XML: " + e.getMessage());
        }

        return new ResultadoCarga(zonas, personas);
    }

    private String getText(Element e, String tag) {
        NodeList n = e.getElementsByTagName(tag);
        return (n.getLength() > 0) ? n.item(0).getTextContent().trim() : "";
    }
}
