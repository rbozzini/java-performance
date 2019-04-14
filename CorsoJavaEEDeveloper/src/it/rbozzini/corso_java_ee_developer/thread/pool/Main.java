package it.rbozzini.corso_java_ee_developer.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {

		// Creo il thread pool:
		ExecutorService threadPool = Executors.newCachedThreadPool();

		// Metto in coda i thread:
		threadPool.execute(new GetSitePage("http://www.paolopreite.it"));
		threadPool.execute(new GetSitePage("https://www.google.it"));
		threadPool.execute(new GetSitePage("https://www.amazon.it"));

		// Spengo il thread pool
		threadPool.shutdown();
	}

}
