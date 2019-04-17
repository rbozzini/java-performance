package it.rbozzini.corso_java_ee_developer.lamba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();

		List<Utente> utenti = m.elencoUtenti();

		System.out.println("--- Stampo elenco ---");

		// Consumer
		Consumer<Utente> printCons = utente -> System.out.println(utente.getNome() + " " + utente.getCognome());
		utenti.forEach(printCons);

		System.out.println("\n\n--- Ricerca utenti con cognome Bozzini ---");

		// Predicate
		List<Utente> trovati = m.cercaUtenti(utenti, utente -> utente.getCognome().equals("Bozzini"));
		trovati.forEach(printCons);
	}

	public List<Utente> cercaUtenti(List<Utente> utenti, Predicate<Utente> p) {

		List<Utente> utentiTrovati = new ArrayList<Utente>();

		for (Utente utente : utenti) {
			if (p.test(utente)) {
				utentiTrovati.add(utente);
			}
		}

		return utentiTrovati;
	}
	
	private List<Utente> elencoUtenti() {
		List<Utente> utenti = new ArrayList<Utente>();
		
		utenti.add(new Utente("Valentina", "Brandolini", 9, "Bologna", "vale.brandolini@gmail.com", "test"));
		utenti.add(new Utente("Arianna", "Brandolini", 6, "Bologna", "ari.brandolini@gmail.com", "test"));
		utenti.add(new Utente("Amelia", "Paparini", 70, "Mantova", "titti.paparini@gmail.com", "test"));
		utenti.add(new Utente("Martina", "Bozzini", 39, "Venezia", "martina.bozzini@gmail.com", "test"));
		utenti.add(new Utente("Rosalba", "Buscherini", 74, "Filetto", "rosalba.buscherini@gmail.com", "test"));
		utenti.add(new Utente("Diego", "Romani", 40, "Verona", "diego.romani@gmail.com", "test"));
		utenti.add(new Utente("Riccardo", "Romani", 4, "Bologna", "riccardo.romani@gmail.com", "test"));
		utenti.add(new Utente("Rossella", "Bozzini", 45, "Verona", "rossellabozzini@gmail.com", "test"));
		utenti.add(new Utente("Denis", "Brandolini", 45, "Ravenna", "denis.brandolini@gmail.com", "test"));
		
		return utenti;
	}

}
