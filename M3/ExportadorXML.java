package proyectoFinal;

import java.sql.*;
import java.io.FileWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class ExportadorXML {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ExportadorXML <battle_id>"); 
            return;
        }

        int battleId = Integer.parseInt(args[0]);

        String urlDatos = "jdbc:mysql://spacewars.chwyik8wac7w.eu-north-1.rds.amazonaws.com:3306/mydb";
        String usuario = "admin";
        String passwd = "Jefecolorado123!";

        String query = "SELECT * FROM battle_log WHERE num_battle = ? ORDER BY num_line;";

        try (
            Connection conn = DriverManager.getConnection(urlDatos, usuario, passwd);
            PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setInt(1, battleId);
            ResultSet rs = pstmt.executeQuery();

            String xml = ResultSetParserUtil.convertToXML(rs);

            // Rutas ajustadas según estructura del usuario con nombre relativo obligatorio
            String relativeBasePath = "../html/battles/";
            String xmlFilename = relativeBasePath + "battle" + battleId + ".xml";
            String htmlFilename = relativeBasePath + "battle" + battleId + ".html";
            String xslFile = relativeBasePath + "battle" + battleId + ".xsl";

            FileWriter writer = new FileWriter(xmlFilename);
            writer.write("<?xml version='1.0' encoding='UTF-8'?>\n");
            writer.write(xml);
            writer.close();

            // Transformar a HTML con rutas relativas
            transform(xslFile, xmlFilename, htmlFilename);

            System.out.println("Generado: " + htmlFilename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void transform(String xslFile, String xmlFile, String htmlFile) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        StreamSource xsl = new StreamSource(new File(xslFile));
        StreamSource xml = new StreamSource(new File(xmlFile));
        StreamResult html = new StreamResult(new File(htmlFile));
        Transformer transformer = tf.newTransformer(xsl);
        transformer.transform(xml, html);
    }
}