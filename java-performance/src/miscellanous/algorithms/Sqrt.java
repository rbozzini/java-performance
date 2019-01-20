package miscellanous.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sqrt {

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
	    } while (diff.compareTo(error) > 0);
	    
	    return initial;
	}
}
