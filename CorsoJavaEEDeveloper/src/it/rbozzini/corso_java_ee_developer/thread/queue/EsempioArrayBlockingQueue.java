package it.rbozzini.corso_java_ee_developer.thread.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EsempioArrayBlockingQueue {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

		Thread prod = new Thread(new Producer(queue), "[prod]");
		Thread cons = new Thread(new Consumer(queue), "[cons]");

		prod.start();
		cons.start();
	}
}
