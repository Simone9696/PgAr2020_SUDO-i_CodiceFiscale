package it.unibs.ing.arnaldo.codicefiscale;

public class CodiceMain {

	public static void main(String[] args) {

		LetturaCodici.initializeReader();
		
		LetturaCodici.extractPeople();
		
		CreazioneCodici.generaCodici();
		
		for (int i = 0; i < LetturaCodici.persone.size(); i++) {
			System.out.println(LetturaCodici.persone.get(i).toString() + "\n");
		}

	}

}
