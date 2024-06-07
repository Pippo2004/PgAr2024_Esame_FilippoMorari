import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LettoreXML {

    public static List<Carta> leggiCarteDaXML(String nomeFile) {
        List<Carta> carte = new ArrayList<>();
        try {
            File fileXML = new File(nomeFile);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileXML);
            doc.getDocumentElement().normalize();
            NodeList listaCarte = doc.getElementsByTagName("carta");
            for (int i = 0; i < listaCarte.getLength(); i++) {
                Node nodoCarta = listaCarte.item(i);
                if (nodoCarta.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoCarta = (Element) nodoCarta;
                    String nome = elementoCarta.getElementsByTagName("nome").item(0).getTextContent();
                    String descrizione = elementoCarta.getElementsByTagName("descrizione").item(0).getTextContent();
                    int puntiFerita = Integer.parseInt(elementoCarta.getAttribute("pf"));
                    boolean equipaggiabile = Boolean.parseBoolean(elementoCarta.getAttribute("equipaggiabile"));
                    Carta carta = new Carta(nome, descrizione, puntiFerita, equipaggiabile);
                    carte.add(carta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carte;
    }
}
