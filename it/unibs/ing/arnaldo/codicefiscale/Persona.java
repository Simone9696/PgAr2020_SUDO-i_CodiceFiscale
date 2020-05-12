package it.unibs.ing.arnaldo.codicefiscale;

import java.util.regex.*;

public class Persona {
	
	private String nome, cognome, sesso, dataDiNascita, comuneDiNascita, codiceFiscale;

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
		buff.append(generaCodiceNome());
		buff.append(generaCodiceCognome());
		buff.append(generaCodiceAnno());
		buff.append(generaCodiceMese());
		buff.append(generaCodiceGiorno());
		buff.append(generaCodiceComune());
		buff.append(generaCarattereControllo());
		codiceFiscale = buff.toString().toUpperCase();
		
	}

	private String generaCarattereControllo() {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaCodiceComune() {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaCodiceGiorno() {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaCodiceMese() {
		
		Pattern mese = Pattern.compile("-[0-9]{2}-");
		Matcher matcher = mese.matcher(dataDiNascita);
		String numMese = "13";
		if (matcher.find()) numMese = matcher.group().substring(1, 3);
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
		for (int i = 0; i < nome.length(); i++) {
			Matcher matcher = consonante.matcher(Character.toString(nome.charAt(i)));
			if (matcher.matches() && buff.length() < 3) buff.append(nome.charAt(i));
		}
		for (int i = 0; i < nome.length(); i++) {
			Matcher matcher = vocale.matcher(Character.toString(nome.charAt(i)));
			if (matcher.matches() && buff.length() < 3) buff.append(nome.charAt(i));
		}
		while (buff.length() < 3) buff.append('X');
		
		return buff.toString().toUpperCase();	
		
	}

	@Override
	public String toString() {
		return "Persona [nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", dataDiNascita="
				+ dataDiNascita + ", comuneDiNascita=" + comuneDiNascita + ", codiceFiscale=" + codiceFiscale + "]";
	}
	
	
	

}
