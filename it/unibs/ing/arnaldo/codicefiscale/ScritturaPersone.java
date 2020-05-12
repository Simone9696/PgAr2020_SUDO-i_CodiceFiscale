package it.unibs.ing.arnaldo.codicefiscale;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class ScritturaPersone {

	 private static final String PERSONE_NUMERO = "persone numero";
	static String version="1.0";
	 static String encoding="UTF-8";
	 static String filename = "Persone.xml";
	 static XMLOutputFactory xmlof = null;
	 static XMLStreamWriter xmlw = null;
	private static String nome;
	private static String cognome;
	private static String sesso;
	private static String comune_nascita;
	private static String data_nascita;
	private static String codice_fiscale;
	 
	 
	 public static void initializeWriter() {
		
		 try {
		xmlof = XMLOutputFactory.newInstance();
		xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename),encoding);
		xmlw.writeStartDocument(encoding, version);
		} catch (Exception e) { 
		System.out.println("Errore nell'inizializzazione del writer:");
		System.out.println(e.getMessage());
		}
	     }
	 
	
		 public static void printFile() {
			 String[] autori = {"Simone Maccobatti", "Alessandro Formigoni", "", "Francesca Gambi"}; 
			 int numeroPersone = LetturaPersone.persone.size();
			 
			 try { 
			 xmlw.writeStartElement("esercizio codice fiscale "); 
			 xmlw.writeComment("LISTA AUTORI"); 
			 for (int i = 0; i < autori.length; i++) {
			 xmlw.writeStartElement("autore"); 
			 xmlw.writeAttribute("id", Integer.toString(i)); 
			 xmlw.writeCharacters(autori[i]); 
			 xmlw.writeEndElement(); // chiusura di </autore>
			 xmlw.writeStartElement("output");//radice
             xmlw.writeComment("LISTA PERSONE DA FILE INPUT"); // scrittura di un commento
             xmlw.writeStartElement("persona");
             xmlw.writeAttribute("numero persone",Integer.toString(numeroPersone));
             for (Persona persona : LetturaPersone.persone) {
			 xmlw.writeAttribute("id",Integer.toString(i) );
			 xmlw.writeAttribute("nome",persona.getNome());
			 xmlw.writeAttribute("cognome",persona.getCognome());
			 xmlw.writeAttribute("sesso",persona.getSesso());
			 xmlw.writeAttribute("comune_nascita" ,persona.getComuneDiNascita());
			 xmlw.writeAttribute("data_nascita",persona.getDataDiNascita());
			 if(persona.isCodicePresente()) {
		     xmlw.writeAttribute("codice_fiscale",persona.getCodiceFiscale());}
			 else {xmlw.writeCharacters("ASSENTE");	 
			 xmlw.writeEndElement();
			 xmlw.writeStartElement("codici");
			 

			 
			 
			 
			 xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
			 xmlw.writeEndDocument(); // scrittura della fine del documento
			 xmlw.flush(); // svuota il buffer e procede alla scrittura
			 xmlw.close(); // chiusura del documento e delle risorse impiegate
			 } 
			 catch (Exception e) { // se c’è un errore viene eseguita questa parte
			 System.out.println("Errore nella scrittura");
			 }
			 
             }
		
	
		 
		 
		 
//		try {
//			  while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
//				 switch (xmlr.getEventType()) { // switch sul tipo di evento
//					 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
//						 System.out.println("Start Read Doc " + filename); break;
//					 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
//						 System.out.println("Tag " + xmlr.getLocalName());
//						 for (int i = 0; i < xmlr.getAttributeCount(); i++)
//						 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
//						 break;
//					 case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso
//						 System.out.println("END-Tag " + xmlr.getLocalName()); break;
//					 case XMLStreamConstants.COMMENT:
//						 System.out.println("// commento " + xmlr.getText()); break; // commento: ne stampa il contenuto
//					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
//						 if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
//						 System.out.println("-> " + xmlr.getText());
//						 break;
//				 }
//				 xmlr.next();
//				}
//		} catch (XMLStreamException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
