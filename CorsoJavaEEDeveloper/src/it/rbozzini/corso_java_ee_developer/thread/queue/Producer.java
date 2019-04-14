package it.rbozzini.corso_java_ee_developer.thread.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

	private BlockingQueue<String> queue;

	public Producer(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		int i = 0;

		while (true) {
			String elem = "Elemento numero " + i;

			boolean aggiunto = queue.offer(elem);

			System.out
					.println(Thread.currentThread().getName() + " L'elemento " + i + " è stato aggiunto? " + aggiunto);
			i++;

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
