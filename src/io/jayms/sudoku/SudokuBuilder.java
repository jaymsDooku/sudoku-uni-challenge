package io.jayms.sudoku;

public class SudokuBuilder {
	
	public static int[] subGrid() {
		int[] subGrid = new int[9];
		for (int i = 0; i < subGrid.length; i++) {
			subGrid[i] = -1;
		}
		return subGrid;
	}

	private int clues;
	private int[][] grid;
	
	public SudokuBuilder() {
		this.grid = new int[9][9];
		for (int i = 0; i < grid.length; i++) {
			this.grid[i] = subGrid();
		}
	}
	
	public SudokuBuilder subGrid(int i, int[] subGrid) {
		this.grid[i] = subGrid;
		return this;
	}
	
	public SudokuBuilder set(int i, int j, int val) {
		int[] subGrid = this.grid[i];
		if (subGrid == null) {
			subGrid = subGrid();
			grid[i] = subGrid;
		}
		subGrid[j] = val;
		return this;
	}
	
	public Sudoku build() {
		return new Sudoku(clues, grid);
	}
	
}
