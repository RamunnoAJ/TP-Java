package cargador;

import dominio.acceso.*;
import dominio.persona.*;
import dominio.zona.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class CargadorXML {
    private final String ruta;

    public CargadorXML(String ruta) {
        this.ruta = ruta;
    }

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
                            new Backstage(codigo, descripcion, personasZona, Integer.parseInt(getText(elem, "capMax")));
                    case "escenario" ->
                            new Escenario(codigo, descripcion, personasZona, Integer.parseInt(getText(elem, "capMax")), new ArrayList<>());
                    case "stand" -> {
                        String ubicacion = getText(elem, "ubicacion");
                        String codZonaComun = getText(elem, "zonaComun");
                        ZonaComun zonaComun = (ZonaComun) zonasPorCodigo.get(codZonaComun); // se resuelve después
                        yield new Stand(codigo, descripcion, personasZona, Integer.parseInt(getText(elem, "capMax")), ubicacion, zonaComun, null, new ArrayList<>());
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
                int id = Integer.parseInt(getText(elem, "id"));
                String nombre = getText(elem, "nombre");

                List<Acceso> accesos = new ArrayList<>();

                Persona p = switch (tipo) {
                    case "asistente" -> new Asistente(id, nombre, accesos);
                    case "artista" -> {
                        String codEscenario = getText(elem, "escenario");
                        Escenario escenario = (Escenario) zonasPorCodigo.get(codEscenario);
                        yield new Artista(id, nombre, accesos, escenario);
                    }
                    case "comerciante" -> {
                        String codStand = getText(elem, "stand");
                        Stand stand = (Stand) zonasPorCodigo.get(codStand);
                        Comerciante c = new Comerciante(id, nombre, accesos, stand);
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
                int duracion = Integer.parseInt(getText(elem, "duracion"));
                Estado estado = Estado.valueOf(getText(elem, "estado"));

                Persona persona = personasPorId.get(idPersona);
                Zona zona = zonasPorCodigo.get(codZona);

                if (persona != null && zona != null) {
                    Acceso acceso = new Acceso(zona, duracion, estado);
                    persona.getAccesos().add(acceso);
                } else {
                    informe.agregar("Acceso inválido: persona " + idPersona + ", zona " + codZona);
                }
            }

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
