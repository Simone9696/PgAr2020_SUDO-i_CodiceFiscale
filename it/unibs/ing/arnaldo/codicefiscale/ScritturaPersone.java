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
	private static String invalidi_numero;
	 
	 
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
			 int codiciInvalidi =LetturaCodiciFiscali.codiciInvalidi.size();
			 int codiciSpaiati =LetturaCodiciFiscali.codiciSpaiati.size();
			 try { 
			 xmlw.writeStartElement("esercizio codice fiscale "); 
			 xmlw.writeComment("LISTA AUTORI"); 
			 for (int i = 0; i < autori.length; i++) {
			 xmlw.writeStartElement("autore"); 
			 xmlw.writeAttribute("id", Integer.toString(i)); 
			 xmlw.writeCharacters(autori[i]); 
			 xmlw.writeEndElement(); // chiusura di </autore>
			 xmlw.getPrefix("output");
             xmlw.writeComment("LISTA PERSONE DA FILE INPUT"); // scrittura di un commento
             xmlw.writeStartElement("persona");
             xmlw.writeAttribute("numero persone",Integer.toString(numeroPersone));
             for (Persona persona : LetturaPersone.persone) {
			 xmlw.writeAttribute("id",Integer.toString(i) );
			 xmlw.writeStartElement(nome);
			 xmlw.writeCharacters(persona.getNome());
			 xmlw.writeEndElement();
			 xmlw.writeStartElement(cognome);
			 xmlw.writeCharacters(persona.getCognome());
			 xmlw.writeEndElement();
			 xmlw.writeStartElement(sesso);
			 xmlw.writeCharacters(persona.getSesso());
			 xmlw.writeEndElement();
			 xmlw.writeStartElement(comune_nascita);
			 xmlw.writeCharacters(persona.getComuneDiNascita());
			 xmlw.writeEndElement();
			 xmlw.writeStartElement(data_nascita);
			 xmlw.writeCharacters(persona.getDataDiNascita());
			 xmlw.writeEndElement();
			 xmlw.writeEmptyElement(codice_fiscale);
			 if(persona.isCodicePresente()) {
		     xmlw.writeAttribute("codice_fiscale",persona.getCodiceFiscale());}
			 else {xmlw.writeCharacters("ASSENTE");	 
			 xmlw.writeEndElement();
			 xmlw.writeStartElement("codici");
			 xmlw.writeAttribute(invalidi_numero,Integer.toString(codiciInvalidi));
			 for (int j = 0; j < codiciInvalidi; j++) {
		     xmlw.writeStartElement("codice");
		     xmlw.writeCharacters(persona.getCodiceFiscale());
		     xmlw.writeEndElement();
		      }
			 xmlw.writeAttribute(invalidi_numero,Integer.toString(codiciSpaiati));
			 for (int k = 0; k < codiciSpaiati; k++) {
			     xmlw.writeStartElement("codice");
			     xmlw.writeCharacters(persona.getCodiceFiscale());
			     xmlw.writeEndElement();
                 }xmlw.writeEndElement(); // chiusura di </programmaArnaldo>
			 xmlw.writeEndDocument(); // scrittura della fine del documento
			 xmlw.flush(); // svuota il buffer e procede alla scrittura
			 xmlw.close(); // chiusura del documento e delle risorse impiegate
			 } 
			 catch (Exception e) { // se c’è un errore viene eseguita questa parte
			 System.out.println("Errore nella scrittura");
			 }
			 
             }
		