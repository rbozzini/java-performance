package miscellanous.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sqrt {
	
	private static BigDecimal TWO = new BigDecimal(2);
	private static BigDecimal ERROR = new BigDecimal(0.0001);

	public static BigDecimal sqrtBabylonian(BigDecimal bd) {
		
	    BigDecimal initial = bd;
	    BigDecimal diff;
	    
	    do {
	        BigDecimal sDivX = bd.divide(initial, 8, RoundingMode.FLOOR);
	        BigDecimal sum = sDivX.add(initial);
	        BigDecimal div = sum.divide(TWO, 8, RoundingMode.FLOOR);
	        diff = div.subtract(initial).abs();
	        diff.setScale(8, RoundingMode.FLOOR);
	        initial = div;
	    } while (diff.compareTo(ERROR) > 0);
	    
	    return initial;
	}
	
	public static void main(String[] args) {
		
		BigDecimal sqrt = null;
		BigDecimal bd = null;
		
		bd = new BigDecimal(7);
		sqrt = Sqrt.sqrtBabylonian(bd);
		System.out.println("sqrt(" + bd + ") = " + sqrt);
	}
}
