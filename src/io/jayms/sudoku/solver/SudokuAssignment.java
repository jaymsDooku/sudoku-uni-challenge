package io.jayms.sudoku.solver;

import io.jayms.sudoku.util.Vec2D;

public class SudokuAssignment {

	private Vec2D pos;
	private int value;
	
	public SudokuAssignment(Vec2D pos, int value) {
		this.pos = pos;
		this.value = value;
	}
	
	public Vec2D getPos() {
		return pos;
	}
	
	public int getValue() {
		return value;
	}
	
}
