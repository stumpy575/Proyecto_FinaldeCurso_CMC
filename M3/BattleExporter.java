package proyectoFinal;

import java.io.File;
import java.io.FileWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class BattleExporter {

    public static void exportBattleToHTML(Battle battle, int battleNumber) throws Exception {
        String basePath = "M4/html/battles";
        String xmlFile = basePath + "batalla" + battleNumber + ".xml";
        String htmlFile = basePath + "batalla" + battleNumber + ".html";
        String xslFile = basePath + "battleX.xsl"; // asegúrate de tener este XSL

        // Crear carpeta si no existe
        File dir = new File(basePath);
        if (!dir.exists()) dir.mkdirs();

        // Escribir XML
        try (FileWriter fw = new FileWriter(xmlFile)) {
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("<battle>\n");

            fw.write("  <summary><![CDATA[\n" + battle.getBattleStatistics() + "\n]]></summary>\n");
            fw.write("  <log><![CDATA[\n" + battle.getBattleDevelopmentLog() + "\n]]></log>\n");

            fw.write("</battle>");
        }

        // Transformar a HTML usando XSL
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(new StreamSource(new File(xslFile)));
        transformer.transform(new StreamSource(new File(xmlFile)), new StreamResult(new File(htmlFile)));

        System.out.println("HTML generado en: " + htmlFile);
    }
}