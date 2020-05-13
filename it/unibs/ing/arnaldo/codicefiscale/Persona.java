package it.unibs.ing.arnaldo.codicefiscale;

import java.util.HashMap;
import java.util.regex.*;

public class Persona {
	
	private String nome, cognome, sesso, dataDiNascita, comuneDiNascita, codiceFiscale;
	private boolean codicePresente;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the dataDiNascita
	 */
	public String getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param dataDiNascita the dataDiNascita to set
	 */
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * @return the comuneDiNascita
	 */
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	/**
	 * @param comuneDiNascita the comuneDiNascita to set
	 */
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void generaCodiceFiscale() {
		StringBuffer buff = new StringBuffer();
		buff.append(generaCodiceCognome());
		buff.append(generaCodiceNome());
		buff.append(generaCodiceAnno());
		buff.append(generaCodiceMese());
		buff.append(generaCodiceGiorno());
		buff.append(generaCodiceComune());
		buff.append(generaCarattereControllo());
		codiceFiscale = buff.toString().toUpperCase();
		
	}

	private String generaCarattereControllo() {

		String codiceTemp = null;
		StringBuffer buff = new StringBuffer();
		buff.append(generaCodiceCognome());
		buff.append(generaCodiceNome());
		buff.append(generaCodiceAnno());
		buff.append(generaCodiceMese());
		buff.append(generaCodiceGiorno());
		buff.append(generaCodiceComune());
		codiceTemp = buff.toString();
		
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
		for (int i = 0; i < codiceTemp.length(); i++) {
			try {
				if (i % 2 == 0) { // lettera dispari
					somma += mappaCaratteriDispari.get(codiceTemp.charAt(i));
				} else {
					somma += mappaCaratteriPari.get(codiceTemp.charAt(i));
				}
			} catch (Exception e) {
				System.out.println(this.toString());
			}
		}
		
		int resto = somma % 26;
		
		return mappaCaratteriControllo.get(resto).toString();
	}

	private String generaCodiceComune() {
		
		for (int i = 0; i < LetturaCodiciComune.comuni.size(); i++) {
			if (comuneDiNascita.toUpperCase().contentEquals(LetturaCodiciComune.comuni.get(i).getNome().toUpperCase())) {
				return LetturaCodiciComune.comuni.get(i).getCodice().toUpperCase();
			}
		} return null;
	}

	private String generaCodiceGiorno() {
		
		if (generaCodiceMese() != "B") {
			Pattern giorno = Pattern.compile("-(0[1-9]|[12][0-9]|3[01])$");
			Matcher matcher = giorno.matcher(dataDiNascita);
			String codice = null;
			if (matcher.find()) codice = matcher.group().substring(1);
			if (sesso.toUpperCase() == "F") codice += 40;
			return codice;
		} else {
			Pattern giorno = Pattern.compile("-(0[1-9]|1[0-9]|2[0-9])$");
			Matcher matcher = giorno.matcher(dataDiNascita);
			String codice = null;
			if (matcher.find()) codice = matcher.group().substring(1);
			if (sesso.toUpperCase() == "F") codice += 40;
			return codice;
		}
		
	}

	private String generaCodiceMese() {
		
		Pattern mese = Pattern.compile("-(0[1-9]|1[0-2])-");
		Matcher matcher = mese.matcher(dataDiNascita);
		String numMese = null;
		if (matcher.find()) numMese = matcher.group().substring(1, 3);
		try {
			switch (numMese) {
			case "01": return "A";
			case "02": return "B";
			case "03": return "C";
			case "04": return "D";
			case "05": return "E";
			case "06": return "H";
			case "07": return "L";
			case "08": return "M";
			case "09": return "P";
			case "10": return "R";
			case "11": return "S";
			case "12": return "T";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return null;
	}

	private String generaCodiceAnno() {
		
		StringBuffer anno = new StringBuffer();
		for (int i = 0; i < dataDiNascita.length(); i++) {
			if (dataDiNascita.charAt(i) == '-') {
				for (int j = 2; j > 0; j--) anno.append(dataDiNascita.charAt(i-j));
				break;
			};
		} return anno.toString().toUpperCase();
	}

	private String generaCodiceCognome() {
		
		StringBuffer buff = new StringBuffer();
		Pattern consonante = Pattern.compile("[a-zA-Z&&[^aeiouAEIOU]]");
		Pattern vocale = Pattern.compile("[aeiouAEIOU]");
		for (int i = 0; i < cognome.length(); i++) {
			Matcher matcher = consonante.matcher(Character.toString(cognome.charAt(i)));
			if (matcher.matches() && buff.length() < 3) buff.append(cognome.charAt(i));
		}
		for (int i = 0; i < cognome.length(); i++) {
			Matcher matcher = vocale.matcher(Character.toString(cognome.charAt(i)));
			if (matcher.matches() && buff.length() < 3) buff.append(cognome.charAt(i));
		}
		while (buff.length() < 3) buff.append('X');
		
		return buff.toString().toUpperCase();	
	}

	private String generaCodiceNome() {

		StringBuffer buff = new StringBuffer();
		Pattern consonante = Pattern.compile("[a-zA-Z&&[^aeiouAEIOU]]");
		Pattern vocale = Pattern.compile("[aeiouAEIOU]");
		int contatore = 0;
		
		for (int i = 0; i < nome.length(); i++) {
			Matcher matcher = consonante.matcher(Character.toString(nome.charAt(i)));
			if (matcher.matches()) contatore++;
		}
		
		if (contatore < 4) {
			for (int i = 0; i < nome.length(); i++) {
				Matcher matcher = consonante.matcher(Character.toString(nome.charAt(i)));
				if (matcher.matches() && buff.length() < 3)
					buff.append(nome.charAt(i));
			}
			for (int i = 0; i < nome.length(); i++) {
				Matcher matcher = vocale.matcher(Character.toString(nome.charAt(i)));
				if (matcher.matches() && buff.length() < 3)
					buff.append(nome.charAt(i));
			}
			while (buff.length() < 3) buff.append('X');
		} else {
			int temp = 0;
			for (int i = 0; i < nome.length(); i++) {
				Matcher matcher = consonante.matcher(Character.toString(nome.charAt(i)));
				if (matcher.matches() && buff.length() < 3) {
					if (temp == 1) {
						temp++;
						continue;
					}
					buff.append(nome.charAt(i));
					temp++;
				}
			}
			for (int i = 0; i < nome.length(); i++) {
				Matcher matcher = vocale.matcher(Character.toString(nome.charAt(i)));
				if (matcher.matches() && buff.length() < 3)
					buff.append(nome.charAt(i));
			}
			while (buff.length() < 3) buff.append('X');
		}
		
		return buff.toString().toUpperCase();	
		
	}

	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", dataDiNascita="
				+ dataDiNascita + ", comuneDiNascita=" + comuneDiNascita + ", codiceFiscale=" + codiceFiscale + "]";
	}

	public boolean isCodicePresente() {
		return codicePresente;
	}

	public void setCodicePresente(boolean codicePresente) {
		this.codicePresente = codicePresente;
	}
	
	
	

}
