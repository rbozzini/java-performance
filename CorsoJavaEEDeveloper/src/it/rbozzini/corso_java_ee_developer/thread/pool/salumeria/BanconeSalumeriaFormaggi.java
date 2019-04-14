package it.rbozzini.corso_java_ee_developer.thread.pool.salumeria;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BanconeSalumeriaFormaggi {

	private static final int CLIENTI_DA_SERVIRE = 30;
	private static final int DIPENDENTI_AL_BANCONE = 3;

	private BlockingQueue<Runnable> codaBancone = new ArrayBlockingQueue<Runnable>(CLIENTI_DA_SERVIRE, true);
	private ExecutorService dipendentiDisponibili = Executors.newFixedThreadPool(DIPENDENTI_AL_BANCONE);

	public static void main(String[] args) {
		System.out.println("Clienti da servire: " + CLIENTI_DA_SERVIRE);

		BanconeSalumeriaFormaggi bancone = new BanconeSalumeriaFormaggi();
		bancone.arrivoClientiAlBancone();
		bancone.servizioClienti();

	}

	private void arrivoClientiAlBancone() {
		for (int i = 1; i <= CLIENTI_DA_SERVIRE; i++) {
			try {
				codaBancone.put(new Cliente(i));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void servizioClienti() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						dipendentiDisponibili.execute(codaBancone.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();
	}

}
