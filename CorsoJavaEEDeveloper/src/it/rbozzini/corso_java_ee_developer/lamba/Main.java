package it.rbozzini.corso_java_ee_developer.lamba;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Main {

	public static void main(String[] args) {
		List<Utente> utenti = UtentiUtils.elencoUtenti();

		System.out.println("--- Stampo elenco ---");

		// Consumer
		Consumer<Utente> printCons = utente -> System.out.println(utente.getNome() + " " + utente.getCognome());
		utenti.forEach(printCons);

		System.out.println("\n\n--- Ricerca utenti con cognome Bozzini ---");

		// Predicate
		List<Utente> trovati = UtentiUtils.cercaUtenti(utenti, utente -> utente.getCognome().equals("Bozzini"));
		trovati.forEach(printCons);

		System.out.println("\n\n--- UnaryOperator ---");

		// Unary
		UnaryOperator<String> uop1 = str -> str.toLowerCase();
		UnaryOperator<Long> uop2 = val -> val * val;

		System.out.println(uop1.apply("Scrivo In Minuscolo"));
		System.out.println("2 * 2 = " + uop2.apply((long) 2));

		// BinaryOperator

		System.out.println("\n\n--- BinaryOperator ---");

		BinaryOperator<Integer> bop1 = (a1, a2) -> a1 * a2;
		System.out.println("3 * 4 = " + bop1.apply(3, 4));

		BinaryOperator<String> bop2 = (str1, str2) -> "Ciao " + str1 + " " + str2;
		System.out.println(bop2.apply("Rossella", "Bozzini"));
	}

}
