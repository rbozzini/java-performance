package it.rbozzini.corso_java_ee_developer.thread.prodcons;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerTest {

	public static void main(String[] args) {
		List<Integer> bufferCondiviso = new LinkedList<Integer>();
		int size = 4;

		Thread prodThread = new Thread(new Producer(bufferCondiviso, size), "Producer");
		Thread consThread = new Thread(new Consumer(bufferCondiviso), "Consumer");

		prodThread.start();
		consThread.start();
	}
}
