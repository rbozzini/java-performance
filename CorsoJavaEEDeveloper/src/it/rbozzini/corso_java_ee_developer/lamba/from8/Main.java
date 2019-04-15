package it.rbozzini.corso_java_ee_developer.lamba.from8;

import it.rbozzini.corso_java_ee_developer.lamba.before8.FormaGeometrica;

public class Main {

	public static void main(String[] args) {
		FormaGeometrica Rettangolo = (a, b) -> a * b;
		Rettangolo.calcolaArea(3, 4);
	}

}
