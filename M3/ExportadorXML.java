package proyectoFinal;

import java.sql.*;
import java.io.FileWriter;
import java.nio.file.Paths;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.File;

public class ExportadorXML {

    public static void exportarBatalla(int battleId) {
    	String urlDatos = "jdbc:mysql://spacewars.chwyik8wac7w.eu-north-1.rds.amazonaws.com:3306/mydb?useSSL=false";
        String usuario = "admin";
        String passwd = "Jefecolorado123!";
        String query = "SELECT * FROM vista_reporte_batalla WHERE num_battle = ? ORDER BY num_line;";

        // Carpeta donde se guarda todo
        String relativeBasePath = Paths.get("html", "battles").toAbsolutePath().toString() + File.separator;
        String xmlFilename = relativeBasePath + "battle" + battleId + ".xml";
        String htmlFilename = relativeBasePath + "battle" + battleId + ".html";
        String xslFile = relativeBasePath + "battle" + battleId + ".xsl";

        try (
            Connection conn = DriverManager.getConnection(urlDatos, usuario, passwd);
            PreparedStatement pstmt = conn.prepareStatement(query))
        {
            pstmt.setInt(1, battleId);
            ResultSet rs = pstmt.executeQuery();

            String xml = ResultSetParserUtil.convertToXML(rs);

            // Crear carpeta si no existe
            File outputDir = new File(relativeBasePath);
            if (!outputDir.exists()) {
                boolean created = outputDir.mkdirs();
                if (!created) {
                    System.out.println("No se pudo crear el directorio: " + relativeBasePath);
                    return;
                }
            }

            // Guardar el XML
            FileWriter writer = new FileWriter(xmlFilename);
            writer.write("<?xml version='1.0' encoding='UTF-8'?>\n");
            writer.write(xml);
            writer.close();

            // Generar XSL si no existe
            File xsl = new File(xslFile);
            if (!xsl.exists()) {
                StringBuilder xslContent = new StringBuilder();
                xslContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                xslContent.append("<xsl:stylesheet version=\"1.0\"\n");
                xslContent.append("    xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n\n");

                xslContent.append("  <xsl:output method=\"html\" indent=\"yes\"/>\n\n");

                xslContent.append("  <xsl:template match=\"/battle_log\">\n");
                xslContent.append("    <html>\n");
                xslContent.append("      <head>\n");
                xslContent.append("        <title>Battle Report</title>\n");
                xslContent.append("        <style>\n");
                xslContent.append("          table { border-collapse: collapse; width: 100%; }\n");
                xslContent.append("          th, td { border: 1px solid black; padding: 5px; text-align: left; }\n");
                xslContent.append("          th { background-color: #f2f2f2; }\n");
                xslContent.append("        </style>\n");
                xslContent.append("      </head>\n");
                xslContent.append("      <body>\n");
                xslContent.append("        <h1>Battle Report</h1>\n");
                xslContent.append("        <table>\n");
                xslContent.append("          <tr>\n");
                xslContent.append("            <xsl:for-each select=\"log[1]/*\">\n");
                xslContent.append("              <th><xsl:value-of select=\"name()\"/></th>\n");
                xslContent.append("            </xsl:for-each>\n");
                xslContent.append("          </tr>\n");
                xslContent.append("          <xsl:for-each select=\"log\">\n");
                xslContent.append("            <tr>\n");
                xslContent.append("              <xsl:for-each select=\"*\">\n");
                xslContent.append("                <td><xsl:value-of select=\".\"/></td>\n");
                xslContent.append("              </xsl:for-each>\n");
                xslContent.append("            </tr>\n");
                xslContent.append("          </xsl:for-each>\n");
                xslContent.append("        </table>\n");
                xslContent.append("      </body>\n");
                xslContent.append("    </html>\n");
                xslContent.append("  </xsl:template>\n");

                xslContent.append("</xsl:stylesheet>\n");

                FileWriter xslWriter = new FileWriter(xslFile);
                xslWriter.write(xslContent.toString());
                xslWriter.close();
            }

            // Transformar a HTML
            transform(xslFile, xmlFilename, htmlFilename);

            System.out.println("Reporte de batalla exportado: " + htmlFilename);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para realizar la transformación
    private static void transform(String xslFile, String xmlFile, String htmlFile) throws Exception {
        TransformerFactory tf = TransformerFactory.newInstance();
        StreamSource xsl = new StreamSource(new File(xslFile));
        StreamSource xml = new StreamSource(new File(xmlFile));
        StreamResult html = new StreamResult(new File(htmlFile));
        Transformer transformer = tf.newTransformer(xsl);
        transformer.transform(xml, html);
    }
    
    public class ResultSetParserUtil {
        public static String convertToXML(ResultSet rs) throws Exception {
            StringBuilder xml = new StringBuilder();
            xml.append("<battle_log>\n");

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                xml.append("  <log>\n");
                for (int i = 1; i <= columnCount; i++) {
                    String column = meta.getColumnName(i);
                    String value = rs.getString(i);
                    if (value == null) value = "";
                    xml.append("    <").append(column).append(">")
                       .append(value)
                       .append("</").append(column).append(">\n");
                }
                xml.append("  </log>\n");
            }

            xml.append("</battle_log>");
            return xml.toString();
        }
    }

    public static void main(String[] args) {
        exportarBatalla(1); // prueba rápida con batalla 1
    }

}