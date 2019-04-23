package it.rbozzini.corso_java_ee_developer.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;

public class EsempioLocalDateTime {

	public static void main(String[] args) {
		EsempioLocalDateTime eld = new EsempioLocalDateTime();
		eld.esLocalDate();
		System.out.println();
		eld.esLocalTime();
		System.out.println();
		eld.esDurationPeriod();
	}

	private void esDurationPeriod() {
		LocalDate ldA = LocalDate.of(2019, Month.APRIL, 20);
		System.out
				.println("ldA = LocalDate.of(2019, Month.APRIL, 20) -> Ho impostato la data '" + ldA.toString() + "'");
		LocalDate ldB = LocalDate.from(ldA);
		System.out
				.println("ldB = LocalDate.from(ldA)                 -> Ho impostato la data '" + ldB.toString() + "'");
		System.out.println(
				"Duration.ofSeconds(10, 3)                 -> rappresenta una durata di 10 secondi e 3 nano secondi: "
						+ Duration.ofSeconds(10, 3).toString());
		System.out.println("Period.ofMonths(4)                        -> rappresenta un periodo di 4 mesi: "
				+ Period.ofMonths(4).toString());
		System.out.println(
				"Period.between(ldA, ldB)                  -> Istanza di Period che rappresenta l'intervallo tra ld1 e ld2: "
						+ Period.between(ldA, ldB).toString());

		LocalTime lt1 = LocalTime.of(9, 30);
		LocalTime lt2 = LocalTime.of(12, 30);

		System.out
				.println("lt1 = LocalTime.of(9, 30)                 -> Ho impostato l'orario '" + lt1.toString() + "'");
		System.out
				.println("lt2 = LocalTime.of(12, 30)                -> Ho impostato l'orario '" + lt2.toString() + "'");
		System.out.println(
				"Duration.between(lt1, lt2)                -> Istanza di Duration che rappresenta l'intervallo tra lt1 e lt2: "
						+ Duration.between(lt1, lt2).toString());
	}

	private void esLocalTime() {
		LocalTime lt = LocalTime.of(9, 30);
		System.out.println("lt = LocalTime.of(9, 30) -> Ho impostato l'orario '" + lt.toString() + "'");
		System.out.println("lt.getHour()             -> ora    = " + lt.getHour());
		System.out.println("lt.getMinute()           -> minuto = " + lt.getMinute());
		System.out.println("lt.withSecond(10)        -> imposto i secondi = " + lt.withSecond(10).toString());
		System.out.println("lt.plusSeconds(3)        -> aggiungo secondi = " + lt.plusSeconds(3).toString());
		System.out.println("lt.minusHours(3)         -> sottraggo 3 ore = " + lt.minusHours(3).toString());
		System.out.println("lt.plusHours(3)          -> aggiungo 3 ore = " + lt.plusHours(3).toString());
	}

	private void esLocalDate() {
		LocalDate ld = LocalDate.of(2019, 4, 20);
		System.out.println("ld = LocalDate.of(2019, 4, 20) -> Ho impostato la data '" + ld.toString() + "'");
		System.out.println("ld.isLeapYear())                -> ld è bisestile? " + ld.isLeapYear());
		System.out.println("ld.lengthOfMonth()              -> lunghezza del mese = " + ld.lengthOfMonth());
		System.out.println("ld.getDayOfWeek()               -> giorno della settimana = " + ld.getDayOfWeek());
		System.out.println(
				"ld.withYear(2017)               -> come ld ma con anno 2017 = " + ld.withYear(2017).toString());
		System.out
				.println("ld.plusMonths(3)                -> come ld ma con mese +3 = " + ld.plusMonths(3).toString());
		System.out.println(
				"ld.getDayOfWeek()               -> come ld ma con 5 gg in meno = " + ld.minusDays(5).toString());

	}

}
