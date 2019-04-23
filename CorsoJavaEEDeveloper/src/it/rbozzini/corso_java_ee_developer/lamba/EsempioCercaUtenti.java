package it.rbozzini.corso_java_ee_developer.lamba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EsempioCercaUtenti {

	public static void main(String[] args) {
		// Consumer per la stampa
		Consumer<Utente> printCons = utente -> System.out.println(utente.getNome() + " " + utente.getCognome());

		EsempioCercaUtenti cu = new EsempioCercaUtenti();

		List<Utente> utenti = cu.elencoUtenti();

		System.out.println("--- Ricerca utenti con cognome Brandolini ---");
		List<Utente> trovati = cu.cecaUtenti(utenti, utente -> utente.getCognome().equals("Brandolini"));
		trovati.forEach(printCons);

		System.out.println("\n--- Ricerca utenti con più di 40 anni ---");
		trovati = cu.cecaUtenti(utenti, utente -> utente.getEta() > 40);
		trovati.forEach(printCons);
	}

	public List<Utente> cecaUtenti(List<Utente> utenti, Predicate<Utente> filtro) {
		List<Utente> trovati = new ArrayList<Utente>();

		for (Utente utente : utenti) {
			if (filtro.test(utente)) {
				trovati.add(utente);
			}
		}

		return trovati;
	}

	public Utente cecaUtente(List<Utente> utenti, Predicate<Utente> filtro) {
		for (Utente utente : utenti) {
			if (filtro.test(utente)) {
				return utente;
			}
		}

		return null;
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
