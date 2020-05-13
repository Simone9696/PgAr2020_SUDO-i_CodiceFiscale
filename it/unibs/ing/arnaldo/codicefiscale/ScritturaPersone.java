package it.unibs.ing.arnaldo.codicefiscale;

import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;

import javax.xml.stream.XMLStreamWriter;

public class ScritturaPersone {

	static String version = "1.0";
	static String encoding = "UTF-8";
	static String filename = "Persone.xml";
	static XMLOutputFactory xmlof = null;
	static XMLStreamWriter xmlw = null;

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
		
		String[] autori = {"Simone Maccobatti ", "Alessandro Formigoni ", "Francesca Gambi "}; 
		int numeroPersone = LetturaPersone.persone.size();
		int codiciInvalidi =LetturaCodiciFiscali.codiciInvalidi.size();
		int codiciSpaiati =LetturaCodiciFiscali.codiciSpaiati.size();
		try { 
			xmlw.writeComment("LISTA AUTORI: " + autori[0] + autori[1] + autori[2]);
			xmlw.writeStartElement("output"); 
			xmlw.writeStartElement("persone");
			xmlw.writeAttribute("numero", Integer.toString(numeroPersone)); 
			for (int i = 0; i < numeroPersone; i++) {
				xmlw.writeStartElement("persona"); 
				xmlw.writeAttribute("id", Integer.toString(i)); 
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(LetturaPersone.persone.get(i).getNome()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(LetturaPersone.persone.get(i).getCognome()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(LetturaPersone.persone.get(i).getSesso()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(LetturaPersone.persone.get(i).getComuneDiNascita()); 
				xmlw.writeEndElement();
				xmlw.writeStartElement("codice_fiscale");
				if (LetturaPersone.persone.get(i).isCodicePresente())
					xmlw.writeCharacters(LetturaPersone.persone.get(i).getCodiceFiscale());
				else 
					xmlw.writeCharacters("ASSENTE");
				xmlw.writeEndElement();
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.writeStartElement("codici");
			xmlw.writeStartElement("invalidi");
			xmlw.writeAttribute("numero", Integer.toString(codiciInvalidi));
			for (int i = 0; i < codiciInvalidi; i++) {
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(LetturaCodiciFiscali.codiciInvalidi.get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.writeStartElement("spaiati");
			xmlw.writeAttribute("numero", Integer.toString(codiciSpaiati));
			for (int i = 0; i < codiciSpaiati; i++) {
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(LetturaCodiciFiscali.codiciSpaiati.get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();
			xmlw.writeEndElement();
			xmlw.writeEndElement();
			xmlw.writeEndDocument();
			xmlw.flush(); 
			xmlw.close();
		} catch (Exception e) {
			System.out.println("Errore nella scrittura");
		}
	}
}