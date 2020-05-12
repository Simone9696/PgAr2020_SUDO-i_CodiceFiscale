package it.unibs.ing.arnaldo.codicefiscale;

public class CodiceMain {

	public static void main(String[] args) {

		LetturaPersone.initializeReader(); 
		
		LetturaPersone.extractPeople();
		
		LetturaCodiciComune.initializeReader();
		
		LetturaCodiciComune.extractCodiceComune();
		
		CreazioneCodici.generaCodici();
		
		LetturaCodiciFiscali.initializeReader();
		
		LetturaCodiciFiscali.extractCodiciFiscali();
		
		LetturaCodiciFiscali.calculateCodiciInvalidi();
		
		LetturaCodiciFiscali.calculateCodiciSpaiati();
		
		LetturaPersone.calculateCodicePresente();
		
		for (int i = 0; i < LetturaCodiciFiscali.codiciInvalidi.size(); i++) {
			System.out.println(LetturaCodiciFiscali.codiciInvalidi.get(i) + "\n");
		}
		System.out.println(LetturaCodiciFiscali.codiciInvalidi.size() + "\n");
		System.out.println(LetturaCodiciFiscali.codiciValidi.size() + "\n");
		System.out.println(LetturaPersone.persone.size() + "\n");
	}

}
