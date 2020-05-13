package it.unibs.ing.arnaldo.codicefiscale;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaPersone {
	
	static String filename = "inputPersone.xml";

	static XMLStreamReader xmlr = null;
	
	static ArrayList<Persona> persone = new ArrayList<>();
 	
	public static void initializeReader() {
		try {
		xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		}
	}

	public static void printFile() {
		try {
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				 switch (xmlr.getEventType()) { // switch sul tipo di evento
					 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
						 System.out.println("Start Read Doc " + filename); break;
					 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
						 System.out.println("Tag " + xmlr.getLocalName());
						 for (int i = 0; i < xmlr.getAttributeCount(); i++)
						 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						 break;
					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
						 System.out.println("END-Tag " + xmlr.getLocalName()); break;
					 case XMLStreamConstants.COMMENT:
						 System.out.println("// commento " + xmlr.getText()); break; // commento: ne stampa il contenuto
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
						 if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
						 System.out.println("-> " + xmlr.getText());
						 break;
				 }
				 xmlr.next();
				}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extractPeople() {
		int id = 0;
		try {
			while (xmlr.hasNext()) { 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 switch (xmlr.getLocalName()) {
						 case "persona": 
							 id = Integer.parseInt(xmlr.getAttributeValue(0)); 
							 persone.add(id, new Persona());
							 break;
						 case "nome": 
							 xmlr.next();
							 persone.get(id).setNome(xmlr.getText());
							 break;
						 case "cognome":
							 xmlr.next();
							 persone.get(id).setCognome(xmlr.getText());
							 break;
						 case "sesso":
							 xmlr.next();
							 persone.get(id).setSesso(xmlr.getText());
							 break;
						 case "comune_nascita":
							 xmlr.next();
							 persone.get(id).setComuneDiNascita(xmlr.getText());
							 break;
						 case "data_nascita":
							 xmlr.next();
							 persone.get(id).setDataDiNascita(xmlr.getText());
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
	
	public static void calculateCodicePresente() {
		boolean presente;
		for (int i = 0; i < persone.size(); i++) {
			presente = false;
			for (int j = 0; j < LetturaCodiciFiscali.codiciValidi.size(); j++) {
				if (LetturaCodiciFiscali.codiciValidi.get(j).contentEquals(persone.get(i).getCodiceFiscale())) {
					presente = true;
					break;
				}
			}
			persone.get(i).setCodicePresente(presente);
		}
	}
	
}