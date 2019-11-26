package io.jayms.sudoku.solver;

import java.util.ArrayList;
import java.util.List;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.util.Vec2D;

public class SudokuSolver {

	private Sudoku sudoku;
	private SudokuSolverCell[][] cellData;
	private List<CellAssignment> assignments;
	private double basePheromone;
	
	private int basicIterations;
	private int improvedIterations;
	private int acoIterations;
	
	public SudokuSolver(Sudoku sudoku, double basePheromone) {
		this.sudoku = sudoku;
		this.cellData = new SudokuSolverCell[9][9];
		this.assignments = new ArrayList<>();
		this.basePheromone = basePheromone;
	}
	
	public void solve() {
		while (basicAssignment()) {
			basicIterations++;
		}
		
	}
	
	public int assignments() {
		return assignments.size();
	}
	
	public int getBasicIterations() {
		return basicIterations;
	}
	
	public int getImprovedIterations() {
		return improvedIterations;
	}
	
	public int findUnassignedCells() {
		int count = 0;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				int val = sudoku.get(x, y);
				if (val != -1) {
					getCellData(x, y);
					count++;
				}
			}
		}
		return count;
	}
	
	
	
	public boolean basicAssignment() {
		boolean madeAssignment = false;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				int val = sudoku.get(x, y);
				if (val != -1) continue;
				SudokuSolverCell cell = getCellData(x, y);
				unavailable(cell, sudoku.getColumn(x));
				unavailable(cell, sudoku.getRow(y));
				unavailable(cell, sudoku.getRegion(x, y));
				
				int solution = cell.solved();
				if (solution != -1) {
					sudoku.set(x, y, solution);
					assignments.add(new CellAssignment(new Vec2D(x, y), solution, CellAssignmentType.BASIC));
					madeAssignment = true;
				}
			}
		}
		return madeAssignment;
	}
	
	public boolean improvedAssignment(ImprovedAssignmentTarget target) {
		boolean improvedAssignment = false;
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				SudokuSolverCell cell = cellData[x][y];
				if (cell == null) continue;
				if (cell.available() != 1) continue;
				
				SudokuSolverCell[] checkCells = target.getCellProvider().apply(this, new Vec2D(x, y));
				for (AvailableNumber a : cell.getAvailable()) {
					boolean available = false;
					for (SudokuSolverCell checkingCell : checkCells) {
						if (checkingCell.isAvailable(a)) {
							break;
						}
					}
					
				}
			}
		}
		return improvedAssignment;
	}
	
	private void unavailable(SudokuSolverCell cell, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			cell.unavailable(arr[i]);
		}
	}
	
	public SudokuSolverCell getCellData(int x, int y) {
		SudokuSolverCell cell = cellData[x][y];
		if (cell == null) {
			cell = new SudokuSolverCell(new Vec2D(x, y));
			cellData[x][y] = cell;
		}
		return cell;
	}
	
	public SudokuSolverCell[] getColumn(int x) {
		SudokuSolverCell[] column = new SudokuSolverCell[9];
		for (int y = 0; y < column.length; y++) {
			column[y] = cellData[x][y];
		}
		return column;
	}
	
	public SudokuSolverCell[] getRow(int y) {
		SudokuSolverCell[] row = new SudokuSolverCell[9];
		for (int x = 0; x < row.length; x++) {
			row[x] = cellData[x][y];
		}
		return row;
	}
	
	public SudokuSolverCell[] getRegion(int x, int y) {
		SudokuSolverCell[] region = new SudokuSolverCell[9];
		int minI = x / 3;
		int minJ = y / 3;
		int maxI = minI + 3;
		int maxJ = minJ + 3;
		int r = 0;
		for (int i = minI; i < maxI; i++) {
			for (int j = minJ; j< maxJ; j++) {
				region[r++] = cellData[i][j]; 
			}
		}
		return region;
	}
	
}
