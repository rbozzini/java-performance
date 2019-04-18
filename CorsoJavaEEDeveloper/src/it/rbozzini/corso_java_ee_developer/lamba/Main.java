package it.rbozzini.corso_java_ee_developer.lamba;

import java.util.List;
import java.util.function.Consumer;

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
	}

}
