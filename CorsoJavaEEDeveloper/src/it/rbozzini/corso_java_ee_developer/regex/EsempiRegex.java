package it.rbozzini.corso_java_ee_developer.regex;

public class EsempiRegex {

	public static void main(String[] args) {

		String testo = "Lorem ipsum dolor sit amet, consectetur adipisci elit, sed do eiusmod tempor incidunt ut labore et dolore magna aliqua. "
				+ "Ut enim ad minim veniam, quis nostrum exercitationem ullamco laboriosam, nisi ut aliquid ex ea commodi consequatur. "
				+ "Duis aute irure reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
				+ "Excepteur sint obcaecat cupiditat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
				+ "LLLLLLLLLLLLLLLolololrem" + "1974errrr122";

		// [...]
		System.out.println("\n[aeiou]");
		System.out.println("Le lettere a, e, i, o, u (case sensitive) sono sostituite dalla lettera 'H':");
		System.out.println(testo.replaceAll("[aeiou]", "H"));

		// [^...]
		System.out.println("\n[^aeiou]");
		System.out.println(
				"Tutte le lettere diverse da a, e, i, o, u (case sensitive) sono sostituite dalla lettera 'H':");
		System.out.println(testo.replaceAll("[^aeiou]", "H"));

		// [...-...]
		System.out.println("\n[a-e]");
		System.out.println(
				"Tutte le lettere nell'intervallo da a ad e (case sensitive) sono sostituite dalla lettera 'H':");
		System.out.println(testo.replaceAll("[a-e]", "H"));

		// [...&&[...]] Intersezione
		System.out.println("\n[A-Z&&[L]]");
		System.out.println(
				"Tutte le lettere nell'intervallo da A ad Z && la lettera L sono sostituite dalla lettera 'H' (quindi solo la lettera L):");
		System.out.println(testo.replaceAll("[A-Z&&[L]]", "H"));

		// .
		System.out.println("\n.");
		System.out.println("Tutti i caratteri sono sostituiti dalla lettera 'H':");
		System.out.println(testo.replaceAll(".", "H"));

		// [A-Z+[a-z]] unione
		System.out.println("\n[A-Z+[a-z]]");
		System.out.println("Unione di insiemi: tutte le lettere sono sostituite dalla lettera 'K':");
		System.out.println(testo.replaceAll("[A-Z+[a-z]]", "K"));

		// ^[A-Z] inizio testo
		System.out.println("\n^[A-Z]");
		System.out.println("Inizio testo: matcha il carattere nell'intervallo A-Z ad inizio testo:");
		System.out.println(testo.replaceAll("^[A-Z]", "K"));

		// [A-Z]$ fine testo
		System.out.println("\n[A-Z]$");
		System.out.println("Fine testo: matcha il carattere nell'intervallo A-Z a fine testo (non matcha nulla):");
		System.out.println(testo.replaceAll("[A-Z]$", "K"));

		// ...* Una o più occorrenze
		System.out.println("\nLo*");
		System.out.println("Matcha tutte le occorrenze di 'L' o 'L' + 'o' con 'o' in numero qualunque:");
		System.out.println(testo.replaceAll("Lo*", "K"));

		// a{n} n occorrenze
		System.out.println("\nL{2}");
		System.out.println("'LL' matcha con 'K' in numero qualunque:");
		System.out.println(testo.replaceAll("L{2}", "K"));

		// a{n,} almeno n occorrenze
		System.out.println("\nL{2,}");
		System.out.println("Ogni sequenza di 'L' composta da 2 o più elementi viene sostituita con 'K':");
		System.out.println(testo.replaceAll("L{2,}", "K"));

		// a{n,m} almeno n occorrenze ma meno di m
		System.out.println("\nL{2,4}");
		System.out.println("Ogni sequenza di 'L' composta da almeno 2 e massimo 4 elementi viene sostituita con 'K':");
		System.out.println(testo.replaceAll("L{2,4}", "K"));

		// \d (abbreviazione di [0-9]): tutti i numeri da 0 a 9
		System.out.println("\n\\\\d");
		System.out.println("Tutte le cifre da 0 a 9:");
		System.out.println(testo.replaceAll("\\d", "K"));

		// \D (abbreviazione di [^0-9]): tutti i caratteri diversi da 0 a 9
		System.out.println("\n\\D");
		System.out.println("Tutti i caratteri diversi dalle cifre:");
		System.out.println(testo.replaceAll("\\D", "K"));

		// \s carattere spazio
		System.out.println("\n\\s");
		System.out.println("Carattere spazio:");
		System.out.println(testo.replaceAll("\\s", "K"));

		// \S tutti i caratteri tranne lo spazio
		System.out.println("\n\\S");
		System.out.println("tutti i caratteri tranne lo spazio:");
		System.out.println(testo.replaceAll("\\S", "K"));

		// \w (abbreviazione di [a-zA-Z0-9] tutti i caratteri alfanumerici
		System.out.println("\n\\w");
		System.out.println("tutti i caratteri alfanumerici:");
		System.out.println(testo.replaceAll("\\w", "K"));

		// \W (abbreviazione di [^a-zA-Z0-9] tutti i caratteri non alfanumerici
		// (caratteri speciali e spazio)
		System.out.println("\n\\W");
		System.out.println("tutti i caratteri non alfanumerici (caratteri speciali e spazio):");
		System.out.println(testo.replaceAll("\\W", "K"));

	}
}
