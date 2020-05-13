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
		
		ScritturaPersone.initializeWriter();
		
		ScritturaPersone.printFile();
		
	}

}
