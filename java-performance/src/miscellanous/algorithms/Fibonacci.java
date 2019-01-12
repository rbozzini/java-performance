package miscellanous.algorithms;

/**
 * La classe contiene l'implementazione del calcolo del numero di Fibonacci.
 * 
 * @author rossellabozzini 11/01/2019
 *
 * 
 */
public class Fibonacci {
	
	public double fibonacci(int n) {
		
		if (n < 0) {
			throw new IllegalArgumentException("Argument must be > 0");
		}
		
		if (n == 0) {
	        return 0d;
		} 

		if (n == 1) {
	      return 1d;
	    } 
		
		double fib = fibonacci(n - 1) + fibonacci(n - 2);
		
		if (Double.isInfinite(fib)) {
			throw new ArithmeticException("Overflow");
		}
		
		return fib;
	}
	
}
