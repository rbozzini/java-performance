package it.rbozzini.corso_java_ee_developer.thread.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ExempioLinkedBlockingQueue {

	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

		Thread prod = new Thread(new Producer(queue), "[prod]");
		Thread cons = new Thread(new Consumer(queue), "[cons]");

		prod.start();
		cons.start();

	}

}
