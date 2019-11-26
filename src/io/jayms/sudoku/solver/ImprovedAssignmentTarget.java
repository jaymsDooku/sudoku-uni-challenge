package io.jayms.sudoku.solver;

import java.util.function.BiFunction;

import io.jayms.sudoku.util.Vec2D;

public enum ImprovedAssignmentTarget {

	ROW((solver, pos) -> {
		return solver.getRow(pos.getY());
	}),
	COLUMN((solver, pos) -> {
		return solver.getColumn(pos.getX());
	}),
	REGION((solver, pos) -> {
		return solver.getRegion(pos.getX(), pos.getY());
	});
	
	private BiFunction<SudokuSolver, Vec2D, SudokuSolverCell[]> cellProvider;
	
	private ImprovedAssignmentTarget(BiFunction<SudokuSolver, Vec2D, SudokuSolverCell[]> cellProvider) {
		this.cellProvider = cellProvider;
	}
	
	public BiFunction<SudokuSolver, Vec2D, SudokuSolverCell[]> getCellProvider() {
		return cellProvider;
	}
	
}
