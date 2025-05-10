package proyectoFinal;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XsltTransformer {

	public static void main(String[] args) {
		String XSLFILE = "../";
		String INFILE = "../";
		String OUTFILE = "../m4/battles/";
		
		StreamSource xslcode = new StreamSource (new File(XSLFILE));
		StreamSource input = new StreamSource (new File(INFILE));
		Result output = new StreamResult(new File(OUTFILE));

		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer trans = tf.newTransformer(xslcode);
			trans.transform(input, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
