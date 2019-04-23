package it.rbozzini.corso_java_ee_developer.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EsempioPatternMatcher {

	public static void main(String[] args) {

		Pattern p = Pattern.compile("\\d");
		String elenco = "1. Rossella, 2. Denis, 3. Valentina, 4. Arianna";
		String[] elencoArray = p.split(elenco);

		System.out.println("Split di un elenco usando il pattern '" + p.pattern() + "':");
		for (int i = 1; i < elencoArray.length; i++) {
			System.out.println(i + elencoArray[i].replaceAll(",", ""));
		}

		String email = "rossella.bozzini@gmail.com";
		boolean matches = Pattern.matches(".*@.*", email);
		System.out.println("\nIl testo '" + email + "' contiene '@'? " + matches);
		p = Pattern.compile("BOZZINI", Pattern.CASE_INSENSITIVE);
		System.out.println("\nSplit case insensitive sulla stringa 'ROSSELLA' utilizzando il flag " + p.flags());
		elencoArray = p.split(email);
		for (int i = 1; i <= elencoArray.length; i++) {
			System.out.println(i + " " + elencoArray[i - 1]);
		}

		String text = "<h1>Titolo 1</h1>" + "<p>Testo A</p>" + "</hr>" + "<p>Testo B</p>" + "<h2>Titolo 2</h2>"
				+ "<p>Testo C</p>";

		/*
		 * gruppo 0 - <p>([^<]+)</p> gruppo 1 - <p>([^<]+)</p> gruppo 2 - [^<]+
		 */
		String regex = "(<p>([^<]+)</p>)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		System.out.println("Gruppi presenti in " + regex + ": " + matcher.groupCount());
		System.out.println("");

		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println("-------------\n");
		}

		System.out.println("\nSECONDA ITERAZIONE");
		System.out.println("\nNon stampa nulla perchè ha consumato tutto il testo.");

		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println("-------------\n");
		}

		matcher.reset();
		System.out.println("\nDopo il reset può riprocessare il testo:");

		System.out.println("\nTERZA ITERAZIONE\n");

		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println("-------------\n");
		}

		System.out.println("\nCon il reset fornisco al matcher un nuovo testo:\n");
		matcher.reset(
				"<p>Lorem ipsum dolor sit amet</p><p>consectetur adipisci elit</p><p>sed do eiusmod tempor incidunt ut labore et dolore magna aliqua.</p>");
		while (matcher.find()) {
			System.out.println(matcher.group(2));
		}

		System.out.println("\n\n----- Validazione email -----\n");

		EsempioPatternMatcher epm = new EsempioPatternMatcher();

		System.out.println("rossellabozzini@gmail.com        -> " + epm.emailValidator("rossellabozzini@gmail.com"));
		System.out.println("rossella.bozzini@gmail.it        -> " + epm.emailValidator("rossella.bozzini@gmail.it"));
		System.out.println("rossella_bozzini@gmail.co.uk     -> " + epm.emailValidator("rossella_bozzini@gmail.co.uk"));
		System.out.println("rossella-bozzini@gmail.com       -> " + epm.emailValidator("rossellabozzini@gmail.com"));
		System.out.println("rossella-bozzini@gmail.c         -> " + epm.emailValidator("rossellabozzini@gmail.c"));
		System.out.println("rossella-bozzini@gmail.bologna   -> " + epm.emailValidator("rossellabozzini@gmail.com"));
		System.out.println("prova                            -> " + epm.emailValidator("prova"));
		System.out.println("@gmail.com                       -> " + epm.emailValidator("@gmail.com"));

		System.out.println("\n\n----- Validazione date -----\n");

		System.out.println("25/07/1973 -> " + epm.dateFormatValidator("25/07/1973"));
		System.out.println("25-07-1973 -> " + epm.dateFormatValidator("25-07-1973"));
		System.out.println("25.07.1973 -> " + epm.dateFormatValidator("25.07.1973"));
		System.out.println("25/07/3456 -> " + epm.dateFormatValidator("25/07/3456"));
		System.out.println("37/07/1973 -> " + epm.dateFormatValidator("37/07/1973"));
		System.out.println("25/25/1973 -> " + epm.dateFormatValidator("25/25/1973"));

		System.out.println("\n\n----- Validazione codice fiscale -----\n");

		System.out.println("BZZRSL73L65E349L -> " + epm.codiceFiscaleValidator("BZZRSL73L65E349L"));
		System.out.println("BRNDNS73M11D458X -> " + epm.codiceFiscaleValidator("BRNDNS73M11D458X"));
		System.out.println("BRNVNT09R46A944M -> " + epm.codiceFiscaleValidator("BRNVNT09R46A944M"));
		System.out.println("BRNRNN12E48A944Z -> " + epm.codiceFiscaleValidator("BRNRNN12E48A944Z"));
		System.out.println("brnrnn12e48a944z -> " + epm.codiceFiscaleValidator("BRNRNN12E48A944Z".toLowerCase()));
		// Una lettera in più nel nome cognome:
		System.out.println("BZZCRSL73L65E349L -> " + epm.codiceFiscaleValidator("BZZRSCL73L65E349L"));
		// Giorno di nascita = 83 (massimo 71):
		System.out.println("BZZRSL73L83E349L -> " + epm.codiceFiscaleValidator("BZZRSL73L83E349L"));
		// Mese di nascita non valido F:
		System.out.println("BRNRNN12F48A944Z -> " + epm.codiceFiscaleValidator("BRNRNN12F48A944Z"));

		epm.printParseCF("BZZRSL73L65E349L");
	}

	public boolean emailValidator(String email) {
		String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		return (matcher.matches() ? true : false);

	}

	public boolean dateFormatValidator(String date) {
		String regex = "^(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](19|20)\\d{2}$  ";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);

		return (matcher.matches() ? true : false);
	}

	public boolean codiceFiscaleValidator(String cf) {
		String regex = "^([a-zA-Z]{6})(\\d{2})([abcdehlmprstABCDEHLMPRST])([0-6][1-9]|7[01])([a-zA-Z]\\d{3})[a-zA-Z]$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cf);

		return (matcher.matches() ? true : false);
	}

	public void printParseCF(String cf) {
		String regex = "^([a-zA-Z]{6})(\\d{2})([abcdehlmprstABCDEHLMPRST])([0-6][1-9]|7[01])([a-zA-Z]\\d{3})([a-zA-Z])$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cf);
		matcher.find();

		System.out.println("\n\n----- Spiegazione della composizione del codice fiscale -----\n");

		System.out.println("\nAnalizziamo: " + cf);
		// BZZRSL 73 L 65 E349 L

		System.out.println("\n1 - 6 lettere che rappresentano nome e cognome:");
		System.out.println(matcher.group(1));

		System.out.println("\n2 - 2 numeri che rappresentano le ultime due cifre dell'anno di nascita:");
		System.out.println(matcher.group(2));

		System.out.println("\n3 - 1 lettera che rappresenta il mese di nascita:");
		System.out.println(matcher.group(3));

		System.out.println(
				"\n4 - 2 numeri che rappresentano il giorno di nascita (sommato a 40 nel caso di sesso femminile:");
		System.out.println(matcher.group(4));

		System.out.println("\n5 - 1 lettera e 3 cifre che rappresentano il codice del comune di nascita:");
		System.out.println(matcher.group(5));

		System.out.println("\n6 - 1 lettera di controllo:");
		System.out.println(matcher.group(6));
	}
}
