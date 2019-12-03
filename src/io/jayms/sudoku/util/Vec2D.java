package io.jayms.sudoku.util;

public class Vec2D {
	
	public static Vec2D fromCartesian(int x, int y) {
		return new Vec2D(getI(x, y), getJ(x, y));
	}
	
	public static int getI(int x, int y) {
		int i = MathUtil.roundDown(x, 3) / 3;
		
		if (y > 2) i += 3;
		if (y > 4) i += 3;
		
		return i;
	}
	
	public static int getJ(int x, int y) {
		int j = 0;
		
		int nx = x;
		while (nx > 2) {
			nx -= 3;
		}
		
		int ny = y;
		while (ny > 2) {
			ny -= 3;
		}
		
		j = nx + (ny + 3);
		
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
