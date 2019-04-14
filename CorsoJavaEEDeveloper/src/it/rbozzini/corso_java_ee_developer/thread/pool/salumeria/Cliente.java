package it.rbozzini.corso_java_ee_developer.thread.pool.salumeria;

import java.util.concurrent.ThreadLocalRandom;

public class Cliente implements Runnable {

	private int numeroTicket;

	public Cliente(int numeroTicket) {
		super();
		this.numeroTicket = numeroTicket;
		System.out.println("E' arrivato un nuovo cliente e gli viene assegnato il numero " + numeroTicket);
	}

	@Override
	public void run() {
		richiediProdotti();
	}

	private void richiediProdotti() {
		System.out.println("Viene servito il cliente numero " + numeroTicket);

		int tempoImpiegatoPerAcquisto = ThreadLocalRandom.current().nextInt(5, 20 + 1) * 1000;

		try {
			Thread.sleep(tempoImpiegatoPerAcquisto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Il cliente che aveva il numero " + numeroTicket + " è stato servito in "
				+ tempoImpiegatoPerAcquisto / 1000 + " secondi.");
	}

}
