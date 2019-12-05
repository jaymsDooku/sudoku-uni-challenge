package io.jayms.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

	private Sudoku sudoku;
	
	public SudokuValidator(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	// returns true if solved.
	public boolean validate() {
		boolean regions = validateRegions();
		boolean columns = validateColumns();
		boolean rows = validateRows();
		System.out.println("regions=" + regions + ", columns=" + columns + ", rows=" + rows);
		return regions & columns & rows;
	}
	
	public boolean validateRegions() {
		for (int i = 0; i < 9; i++) {
			int[] region = sudoku.getRegion(i);
			Set<Integer> regionSet = new HashSet<>();
			for (int j = 0; j < region.length; j++) {
				if (!regionSet.add(region[j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean validateColumns() {
		for (int x = 0; x < 9; x++) {
			Set<Integer> column = new HashSet<>();
			for (int y = 0; y < 9; y++) {
				int val = sudoku.get(x, y);
				if (!column.add(val)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean validateRows() {
		for (int y = 0; y < 9; y++) {
			Set<Integer> row = new HashSet<>();
			for (int x = 0; x < 9; x++) {
				int val = sudoku.get(x, y);
				if (!row.add(val)) {
					return false;
				}
			}
		}
		return true;
	}
	
}
