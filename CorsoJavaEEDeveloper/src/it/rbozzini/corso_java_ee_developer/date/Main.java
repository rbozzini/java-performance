package it.rbozzini.corso_java_ee_developer.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.esCalendar();
	}

	private void esCalendar() {

		Calendar date1 = Calendar.getInstance();

		// Setto anno, mese e anno:
		date1.set(Calendar.YEAR, 2019);
		date1.set(Calendar.MONTH, 3);
		date1.set(Calendar.DATE, 20);

		System.out.println(date1.getTime());

		Calendar date2 = new GregorianCalendar();

		// Setto la data all'istante corrente:
		date2.setTime(new Date());
		// Stampo l'anno:
		System.out.println(date2.get(Calendar.YEAR));
	}

}
