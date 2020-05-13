package it.unibs.ing.arnaldo.codicefiscale;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class LetturaCodiciFiscali {

	static String filename = "codiciFiscali.xml";

	static XMLStreamReader xmlr = null;
	
	static ArrayList<String> codici = new ArrayList<>();
	static ArrayList<String> codiciInvalidi = new ArrayList<>();
	static ArrayList<String> codiciValidi = new ArrayList<>();
	static ArrayList<String> codiciSpaiati = new ArrayList<>();
 	
	public static void initializeReader() {
		try {
		xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del reader:");
		 System.out.println(e.getMessage());
		}
	}
	
	public static void extractCodiciFiscali() {
		int id = 0;
		try {
			while (xmlr.hasNext()) { 
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 switch (xmlr.getLocalName()) {
						 case "codice": 
							 xmlr.next();
							 codici.add(id, xmlr.getText().toUpperCase());
							 id++;
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
	
	public static void calculateCodiciInvalidi() {
		
		Pattern codice = Pattern.compile("^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");
		
		for (int i = 0; i < codici.size(); i++) {
			Matcher matcher = codice.matcher(codici.get(i));
			if (!matcher.matches()) codiciInvalidi.add(codici.get(i));
			else {
				if (checkCarattereControllo(codici.get(i))) codiciValidi.add(codici.get(i));
				else codiciInvalidi.add(codici.get(i));
			}
		}
		
	}
	
	private static boolean checkCarattereControllo(String codice) {
		
		String subcode = codice.substring(0, codice.length()-1);
		char controlChar = codice.charAt(codice.length()-1);
		
		HashMap<Character, Integer> mappaCaratteriDispari = new HashMap<Character, Integer>();
		HashMap<Character, Integer> mappaCaratteriPari = new HashMap<Character, Integer>();
		HashMap<Integer, Character> mappaCaratteriControllo = new HashMap<Integer, Character>();
		
		mappaCaratteriDispari.put('0', 1);
		mappaCaratteriDispari.put('1', 0);
		mappaCaratteriDispari.put('2', 5);
		mappaCaratteriDispari.put('3', 7);
		mappaCaratteriDispari.put('4', 9);
		mappaCaratteriDispari.put('5', 13);
		mappaCaratteriDispari.put('6', 15);
		mappaCaratteriDispari.put('7', 17);
		mappaCaratteriDispari.put('8', 19);
		mappaCaratteriDispari.put('9', 21);
		mappaCaratteriDispari.put('A', 1);
		mappaCaratteriDispari.put('B', 0);
		mappaCaratteriDispari.put('C', 5);
		mappaCaratteriDispari.put('D', 7);
		mappaCaratteriDispari.put('E', 9);
		mappaCaratteriDispari.put('F', 13);
		mappaCaratteriDispari.put('G', 15);
		mappaCaratteriDispari.put('H', 17);
		mappaCaratteriDispari.put('I', 19);
		mappaCaratteriDispari.put('J', 21);
		mappaCaratteriDispari.put('K', 2);
		mappaCaratteriDispari.put('L', 4);
		mappaCaratteriDispari.put('M', 18);
		mappaCaratteriDispari.put('N', 20);
		mappaCaratteriDispari.put('O', 11);
		mappaCaratteriDispari.put('P', 3);
		mappaCaratteriDispari.put('Q', 6);
		mappaCaratteriDispari.put('R', 8);
		mappaCaratteriDispari.put('S', 12);
		mappaCaratteriDispari.put('T', 14);
		mappaCaratteriDispari.put('U', 16);
		mappaCaratteriDispari.put('V', 10);
		mappaCaratteriDispari.put('W', 22);
		mappaCaratteriDispari.put('X', 25);
		mappaCaratteriDispari.put('Y', 24);
		mappaCaratteriDispari.put('Z', 23);
		
		mappaCaratteriPari.put('0', 0);
		mappaCaratteriPari.put('1', 1);
		mappaCaratteriPari.put('2', 2);
		mappaCaratteriPari.put('3', 3);
		mappaCaratteriPari.put('4', 4);
		mappaCaratteriPari.put('5', 5);
		mappaCaratteriPari.put('6', 6);
		mappaCaratteriPari.put('7', 7);
		mappaCaratteriPari.put('8', 8);
		mappaCaratteriPari.put('9', 9);
		mappaCaratteriPari.put('A', 0);
		mappaCaratteriPari.put('B', 1);
		mappaCaratteriPari.put('C', 2);
		mappaCaratteriPari.put('D', 3);
		mappaCaratteriPari.put('E', 4);
		mappaCaratteriPari.put('F', 5);
		mappaCaratteriPari.put('G', 6);
		mappaCaratteriPari.put('H', 7);
		mappaCaratteriPari.put('I', 8);
		mappaCaratteriPari.put('J', 9);
		mappaCaratteriPari.put('K', 10);
		mappaCaratteriPari.put('L', 11);
		mappaCaratteriPari.put('M', 12);
		mappaCaratteriPari.put('N', 13);
		mappaCaratteriPari.put('O', 14);
		mappaCaratteriPari.put('P', 15);
		mappaCaratteriPari.put('Q', 16);
		mappaCaratteriPari.put('R', 17);
		mappaCaratteriPari.put('S', 18);
		mappaCaratteriPari.put('T', 19);
		mappaCaratteriPari.put('U', 20);
		mappaCaratteriPari.put('V', 21);
		mappaCaratteriPari.put('W', 22);
		mappaCaratteriPari.put('X', 23);
		mappaCaratteriPari.put('Y', 24);
		mappaCaratteriPari.put('Z', 25);
		
		mappaCaratteriControllo.put(0, 'A');
		mappaCaratteriControllo.put(1, 'B');
		mappaCaratteriControllo.put(2, 'C');
		mappaCaratteriControllo.put(3, 'D');
		mappaCaratteriControllo.put(4, 'E');
		mappaCaratteriControllo.put(5, 'F');
		mappaCaratteriControllo.put(6, 'G');
		mappaCaratteriControllo.put(7, 'H');
		mappaCaratteriControllo.put(8, 'I');
		mappaCaratteriControllo.put(9, 'J');
		mappaCaratteriControllo.put(10, 'K');
		mappaCaratteriControllo.put(11, 'L');
		mappaCaratteriControllo.put(12, 'M');
		mappaCaratteriControllo.put(13, 'N');
		mappaCaratteriControllo.put(14, 'O');
		mappaCaratteriControllo.put(15, 'P');
		mappaCaratteriControllo.put(16, 'Q');
		mappaCaratteriControllo.put(17, 'R');
		mappaCaratteriControllo.put(18, 'S');
		mappaCaratteriControllo.put(19, 'T');
		mappaCaratteriControllo.put(20, 'U');
		mappaCaratteriControllo.put(21, 'V');
		mappaCaratteriControllo.put(22, 'W');
		mappaCaratteriControllo.put(23, 'X');
		mappaCaratteriControllo.put(24, 'Y');
		mappaCaratteriControllo.put(25, 'Z');
	
		int somma = 0;
		for (int i = 0; i < subcode.length(); i++) {
			try {
				if (i % 2 == 0) { // lettera dispari
					somma += mappaCaratteriDispari.get(subcode.charAt(i));
				} else {
					somma += mappaCaratteriPari.get(subcode.charAt(i));
				}
			} catch (Exception e) {
				System.out.println();
			}
		}
		
		int resto = somma % 26;
		
		if (mappaCaratteriControllo.get(resto) == controlChar) return true;
		
		return false;
	}

	public static void calculateCodiciSpaiati() {
		
		boolean spaiato;
		for (int i = 0; i < codiciValidi.size(); i++) {
			spaiato = true;
			for (int j = 0; j < LetturaPersone.persone.size(); j++) {
				if (LetturaPersone.persone.get(j).getCodiceFiscale().contentEquals(codiciValidi.get(i))) spaiato = false;	
			}
			if (spaiato) codiciSpaiati.add(codiciValidi.get(i));
		}
	}
}
