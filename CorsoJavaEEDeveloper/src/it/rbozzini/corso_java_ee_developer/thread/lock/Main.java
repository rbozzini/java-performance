package it.rbozzini.corso_java_ee_developer.thread.lock;

public class Main {
	public static void main(String[] args) {
		ReentrantLockEsempio counter = new ReentrantLockEsempio();

		Contatore c1 = new Contatore(counter, 20, 1000);
		Contatore c2 = new Contatore(counter, 20, 1000);

		c1.start();
		c2.start();
	}

}
