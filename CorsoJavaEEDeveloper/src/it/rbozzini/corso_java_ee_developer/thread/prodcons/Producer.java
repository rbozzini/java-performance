package it.rbozzini.corso_java_ee_developer.thread.prodcons;

import java.util.List;

public class Producer implements Runnable {

	private final List<Integer> bufferCondiviso;
	private final int size;
	private int i = 1;

	public Producer(List<Integer> bufferCondiviso, int size) {
		this.bufferCondiviso = bufferCondiviso;
		this.size = size;
	}

	@Override
	public void run() {
		while (true) {
			try {
				produce();
				i++;
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void produce() throws InterruptedException {
		while (bufferCondiviso.size() == size) {
			synchronized ((bufferCondiviso)) {
				System.out.println(
						"Il buffer è pieno, il thread Producer resta in attesa... la dimensione del buffer adesso è: "
								+ bufferCondiviso.size());

				bufferCondiviso.wait();
			}
		}

		synchronized ((bufferCondiviso)) {
			bufferCondiviso.add(i);
			bufferCondiviso.notifyAll();
			System.out.println("Il thread Consumer ha aggiungto al buffer l'elemento: " + i
					+ ". La dimensione del buffer adesso è: " + bufferCondiviso.size());

		}
	}

}
