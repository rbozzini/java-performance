package it.rbozzini.corso_java_ee_developer.lamba;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EsempiConsumer {

	public static void main(String args[]) {

		/* --- Esempio 1 --- */

		// Consumer to display a number
		Consumer<String> display = a -> System.out.println(a);

		// Implement display using accept()
		display.accept("--- Esempio 1 ---");
		display.accept("Stampo" + 10);

		/* --- Esempio 2 --- */
		display.accept("\n--- Esempio 2 ---");

		// Consumer to multiply 2 to every integer of a list
		Consumer<List<Integer>> modify = list -> {
			for (int i = 0; i < list.size(); i++)
				list.set(i, 2 * list.get(i));
		};

		// Consumer to display a list of numbers
		Consumer<List<Integer>> dispList = list -> list.stream().forEach(a -> System.out.print(a + " "));

		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		list.add(3);

		// Implement modify using accept()
		modify.accept(list);

		// Implement dispList using accept()
		dispList.accept(list);

		/* --- Esempio 3 --- */
		display.accept("\n\n--- Esempio 3 ---");

		// Consumer to multiply 2 to every integer of a list
		Consumer<List<Integer>> modify2 = list2 -> {
			for (int i = 0; i < list2.size(); i++)
				list2.set(i, 2 * list2.get(i));
		};

		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(1);
		list2.add(3);

		// using addThen()
		modify2.andThen(dispList).accept(list2);

		/* --- Esempio 4: metodo print che riceve un consumer --- */
		display.accept("\n\n--- Esempio 4 (stampo nome e cognome) ---");

		EsempiConsumer ec = new EsempiConsumer();
		List<Utente> utenti = UtentiUtils.elencoUtenti();

		Consumer<List<Utente>> printNomeCognome = elencoUtenti -> elencoUtenti.stream()
				.forEach(utente -> System.out.println(utente.getNome() + " " + utente.getCognome()));
		ec.print(utenti, printNomeCognome);

		display.accept("\n\n--- Esempio 4 (stampo nome, cognome, eta) ---");

		Consumer<List<Utente>> printNomeCognomeEta = elencoUtenti -> elencoUtenti.stream().forEach(
				utente -> System.out.println(utente.getNome() + " " + utente.getCognome() + " " + utente.getEta()));
		ec.print(utenti, printNomeCognomeEta);
	}

	private void print(List<Utente> utenti, Consumer<List<Utente>> printer) {
		printer.accept(utenti);
	}

}
