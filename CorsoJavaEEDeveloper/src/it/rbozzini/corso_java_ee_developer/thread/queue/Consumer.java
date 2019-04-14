package it.rbozzini.corso_java_ee_developer.thread.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

	private BlockingQueue<String> queue;

	public Consumer(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			if (queue.remainingCapacity() > 0) {
				System.out.println(Thread.currentThread().getName() + " E' possibile aggiungere ancora "
						+ queue.remainingCapacity() + " su " + queue.size());
			} else if (queue.remainingCapacity() == 0) {
				String elementoRimosso = queue.remove();
				System.out
						.println(Thread.currentThread().getName() + " E' stato rimosso l'elemento " + elementoRimosso);
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
