package performance;

import java.util.Random;

import miscellanous.algorithms.Fibonacci;

public class FibonacciMicrobenchmark extends Microbenchmark {
	
	//private static Logger log = new Logger("FibonacciMicrobenchmark");
	
	private volatile double l;
	private int nLoops;
	private int[] input;
	
	/**
	 * Costruisce un array di numeri casuali di cui verrà calcolato il numero di Fibonacci per il test.
	 * @param n Numero di loop di test
	 */
	private FibonacciMicrobenchmark(int n) {
		nLoops = n;
		input = new int[nLoops];
		Random r = new Random();
		for (int i = 0; i < nLoops; i++) {
			input[i] = r.nextInt(10);			
		}
	}
	
	protected void innerTest() {
		
		Fibonacci fib = new Fibonacci();
		
		for (int i = 0; i < nLoops; i++) {
			l = fib.fibonacci(input[i]);
		}
	}
	
	public static void main(String[] args) {
		
		FibonacciMicrobenchmark fmb = new FibonacciMicrobenchmark(10000000);
		
		fmb.test();
		
		System.out.println(fmb.getElapsedTime());
	}

}
