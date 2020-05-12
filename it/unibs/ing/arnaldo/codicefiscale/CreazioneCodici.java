package it.unibs.ing.arnaldo.codicefiscale;

public class CreazioneCodici {
	
	public static void generaCodici() {
		for (int i = 0; i < LetturaCodici.persone.size(); i++) {
			LetturaCodici.persone.get(i).generaCodiceFiscale();
		}
	}
}
