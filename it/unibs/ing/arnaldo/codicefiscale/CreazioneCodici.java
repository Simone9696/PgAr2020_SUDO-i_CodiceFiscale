package it.unibs.ing.arnaldo.codicefiscale;

public class CreazioneCodici {
	
	public static void generaCodici() {
		for (int i = 0; i < LetturaPersone.persone.size(); i++) {
			LetturaPersone.persone.get(i).generaCodiceFiscale();
		}
	}
}
