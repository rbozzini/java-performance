package it.rossella.corsofederico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

//		System.out.println("----- Ciclo for -----");
//
//		for (int i = 1; i <= 10; i++) {
//			System.out.println("[for] Stampo " + i);
//		}
//
//		System.out.println("\n\n----- Ciclo while -----");
//
//		int j = 1;
//
//		while (j <= 10) {
//			System.out.println("[while] Stampo " + j);
//			j++;
//		}
//
		System.out.println("\n\n----- Persona -----");
		Persona federico = new Persona("Federico", 19);
//		federico.saluta();
		Persona valentina = new Persona("Valentina", 9);
//		valentina.saluta(federico);

		List<Persona> listaPersone = new ArrayList<Persona>();
		listaPersone.add(federico);
		listaPersone.add(new Persona("Eleonora", 15));
		listaPersone.add(new Persona("Francesca", 12));
		listaPersone.add(valentina);
		listaPersone.add(new Persona("Arianna", 6));

//		for (Persona persona : listaPersone) {
//			persona.saluta();
//			System.out.println("...io ho " + persona.getAge() + " anni.");
//		}

//		int randomNum = -1;
//		Persona pers = null;
//		do {
//			randomNum = ThreadLocalRandom.current().nextInt(0, 4 + 1);
//
//			pers = listaPersone.get(randomNum);
//			pers.saluta();
//		} while (pers.getAge() > 7);

		File personeTxt = new File("persone.txt");
		if (!personeTxt.exists()) {
			FileOutputStream fo = null;
			try {
				personeTxt.createNewFile();
				fo = new FileOutputStream(personeTxt);

				for (Persona persona : listaPersone) {
					String riga = "Ciao sono " + persona.getName() + " e ho " + persona.getAge() + " anni.\n";
					fo.write(riga.getBytes());
				}

			} catch (IOException e) {
				e.printStackTrace();
				return;
			} finally {
				try {
					fo.close();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}

	}
}
