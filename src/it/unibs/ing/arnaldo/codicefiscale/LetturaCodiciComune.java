package it.unibs.ing.arnaldo.codicefiscale;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaCodiciComune {

	static String filename = "comuni.xml";

	static XMLStreamReader xmlr = null;
	
	static ArrayList<Comune> comuni = new ArrayList<>();
 	
	public static void initializeReader() {
		try {
		xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		}
	}
	
	public static void extractCodiceComune() {
		int id = 0;
		try {
			while (xmlr.hasNext()) { 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 switch (xmlr.getLocalName()) {
						 case "comune":  
							 comuni.add(id, new Comune());
							 id++;
							 break;
						 case "nome": 
							 xmlr.next();
							 comuni.get(id - 1).setNome(xmlr.getText());
							 break;
						 case "codice":
							 xmlr.next();
							 comuni.get(id - 1).setCodice((xmlr.getText()));
							 break;
						 }
						 break;
					 case XMLStreamConstants.END_ELEMENT:
						 break;
					 case XMLStreamConstants.COMMENT:
						 break; 
					 case XMLStreamConstants.CHARACTERS:
						 break;
				 }
				 xmlr.next();
				}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
