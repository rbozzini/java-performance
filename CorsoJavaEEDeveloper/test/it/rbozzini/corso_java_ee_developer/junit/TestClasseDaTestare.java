package it.rbozzini.corso_java_ee_developer.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
class TestClasseDaTestare {

	private int numeroTestato;
	private boolean risultatoAtteso;

	public TestClasseDaTestare(int numeroTestato, boolean risultatoAtteso) {
		super();
		this.numeroTestato = numeroTestato;
		this.risultatoAtteso = risultatoAtteso;
	}

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection dataSet() {
		return Arrays.asList(new Object[][] { { 1, false }, { 2, true }, { 3, false }, { 4, true }, { 5, false },
				{ 6, true }, { 7, false }, { 8, true }, { 9, false }, { 10, true } });

	}

	@Test
	void testNumeroPari() {
		ClasseDaTestare test = new ClasseDaTestare();

		System.out.println("Eseguo il test con il numero: " + numeroTestato);
		assertEquals("Il numero " + numeroTestato + " è dispari.", risultatoAtteso, test.numeroPari(numeroTestato));
	}

}
