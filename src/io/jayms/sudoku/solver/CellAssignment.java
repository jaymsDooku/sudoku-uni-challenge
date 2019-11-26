package io.jayms.sudoku.solver;

import io.jayms.sudoku.util.Vec2D;

public class CellAssignment {

	private Vec2D pos;
	private int assigned;
	private CellAssignmentType type;
	
	public CellAssignment(Vec2D pos, int assigned, CellAssignmentType type) {
		this.pos = pos;
		this.assigned = assigned;
		this.type = type;
	}

	public Vec2D getPos() {
		return pos;
	}

	public int getAssigned() {
		return assigned;
	}

	public CellAssignmentType getType() {
		return type;
	}
	
}
