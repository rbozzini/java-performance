package miscellanous.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFibonacci {
	
	private static int[] fibonacci_values = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657};

	@Test
	void test() {
		Fibonacci fib = new Fibonacci();
		
		for (int i = 0; i < fibonacci_values.length; i++) {
			assertEquals(fibonacci_values[i], fib.fibonacci(i));
		}
		
	}

}
