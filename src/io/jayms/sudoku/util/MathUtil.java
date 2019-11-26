package io.jayms.sudoku.util;

public final class MathUtil {

	public static int roundDown(int n, int m) {
	    return n >= 0 ? (n / m) * m : ((n - m + 1) / m) * m;
	}
	
}
