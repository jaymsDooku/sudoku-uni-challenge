package io.jayms.sudoku.util;

public class Vec2D {
	
	public static Vec2D fromCartesian(int x, int y) {
		return new Vec2D(getI(x, y), getJ(x, y));
	}
	
	public static int getI(int x, int y) {
		int i = 0;
		
		if (x > 2) i++;
		if (x > 5) i++;
		
		if (y > 2) i += 3;
		if (y > 5) i += 3;
		
		return i;
	}
	
	public static int getJ(int x, int y) {
		if (x > 5) x -= 3;
		if (x > 2) x -= 3;
		
		if (y > 5) y -= 3;
		if (y > 2) y -= 3;
		
		//System.out.println("x=" + x + ", y=" + y);
		int j = x + (y * 3);
		
		return j;
	}

	private int i;
	private int j;
	
	public Vec2D(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getX() {
		return i;
	}
	
	public int getY() {
		return j;
	}
	
}
