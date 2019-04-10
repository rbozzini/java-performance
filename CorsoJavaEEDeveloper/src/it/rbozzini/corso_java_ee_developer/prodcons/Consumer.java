package it.rbozzini.corso_java_ee_developer.prodcons;

import java.util.List;

public class Consumer implements Runnable {

	private final List<Integer> bufferCondiviso;

	public Consumer(List<Integer> bufferCondiviso) {
		this.bufferCondiviso = bufferCondiviso;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Il thread Consumer sta leggendo il buffer");
				consume();
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void consume() throws InterruptedException {
		// Il thread resta in stato di wait se il buffer è vuoto
		while (bufferCondiviso.isEmpty()) {
			synchronized ((bufferCondiviso)) {
				System.out.println(
						"Il buffer è vuoto, il thread Consumer resta in attesa... la dimensione del buffer adesso è: "
								+ bufferCondiviso.size());

				bufferCondiviso.wait();
			}
		}

		// Il buffer contiene elementi, quindi il thread può eliminarmìne uno e
		// notificarlo al Producer:
		synchronized ((bufferCondiviso)) {
			System.out.println("Il thread Consumer sta leggendo il buffer ed eliminando il seguente elemento: "
					+ bufferCondiviso.remove(0) + ". La dimensione del buffer adesso è: " + bufferCondiviso.size());

			bufferCondiviso.notifyAll();
		}

	}

}
