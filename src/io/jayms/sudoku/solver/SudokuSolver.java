package io.jayms.sudoku.solver;

import java.util.HashSet;
import java.util.Set;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.util.Vec2D;

public class SudokuSolver {

	private Sudoku sudoku;
	
	public SudokuSolver(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	public boolean solve() {
		return solve(0, 0);
	}
	
	private boolean solve(int x, int y) {
		if (sudoku.isComplete()) {
			return true;
		}
		
		Vec2D cell = getNextCell(x, y);
		while (!sudoku.isEmptyCell(cell)) {
			cell = getNextCell(cell.getX(), cell.getY());
		}
		x = cell.getX();
		y = cell.getY();
		
		for (int i = 1; i < 10; i++) {
			if (isValidMove(x, y, i)) {
				sudoku.set(cell.getX(), cell.getY(), i);
				if (!solve(cell.getX(), cell.getY())) {
					sudoku.set(cell.getX(), cell.getY(), -1);
				} else {
					return true;
				}
			}
		}
		return false;
	}
	
	private Vec2D getNextCell(int x, int y) {
		if (x == Sudoku.GRID_DIMENSIONS - 1 && y == Sudoku.GRID_DIMENSIONS - 1) {
			return null;
		}
		if (y < Sudoku.GRID_DIMENSIONS - 1) {
			return new Vec2D(x, y + 1);
		} else {
			return new Vec2D(x + 1, 0);
		}
	}
	
	public boolean isValidMove(int x, int y, int num) {
		int[] row = sudoku.getRow(y);
		int[] column = sudoku.getColumn(x);
		int[] region = sudoku.getRegion(x, y);
		Set<Integer> rowSet = set(row);
		Set<Integer> columnSet = set(column);
		Set<Integer> regionSet = set(region);
		return !rowSet.contains(num) && !columnSet.contains(num) && !regionSet.contains(num);
	}
	
	private static Set<Integer> set(int[] arr) {
		Set<Integer> result = new HashSet<>();
		for (int i : arr) {
			result.add(i);
		}
		return result;
	}
	
	public Sudoku getSudoku() {
		return sudoku;
	}
	
}
