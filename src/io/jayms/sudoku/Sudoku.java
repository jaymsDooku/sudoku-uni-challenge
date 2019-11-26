package io.jayms.sudoku;

import java.util.Arrays;
import java.util.Random;

import io.jayms.sudoku.util.Vec2D;

public class Sudoku {

	private static final Random RNG = new Random();
	
	private int clues;
	private int generatedClues;
	private int[][] grid;
	
	public Sudoku(int clues) {
		this(clues, null);
	}
	
	public Sudoku(int clues, int[][] grid) {
		this.clues = clues;
		if (grid == null) {
			resetGrid();
		} else {
			this.grid = grid;
		}
	}
	
	public void generateClues() {
		if (generatedClues >= clues) {
			return;
		}
		
		for (int i = 0; i < grid.length; i++) {
			int[] subGrid = grid[i];
			for (int j = 0; j < subGrid.length; j++) {
				if (RNG.nextInt(33) % 3 == 0 && subGrid[j] == -1) {
					subGrid[j] = RNG.nextInt(10);
					generatedClues++;
					if (generatedClues >= clues) break;
				}
			}
			if (generatedClues >= clues) break;
		}
		
		if (generatedClues < clues) {
			generateClues();
		}
	}
	
	public void initializeGrid(int[][] grid) {
		this.grid = grid;
	}
	
	public void resetGrid() {
		grid = new int[9][9];
		generatedClues = 0;
		for (int i = 0; i < grid.length; i++) {
			int[] subGrid = grid[i];
			for (int j = 0; j < subGrid.length; j++) {
				subGrid[j] = -1;
			}
		}
	}
	
	public void set(int x, int y, int value) {
		Vec2D coords = Vec2D.fromCartesian(x, y);
		int i = coords.getI();
		int j = coords.getJ();
		
		System.out.println("i=" + i + ", j=" + j);
		grid[i][j] = value;
	}
	
	public int get(int x, int y) {
		Vec2D coords = Vec2D.fromCartesian(x, y);
		int i = coords.getI();
		int j = coords.getJ();
		
		return grid[i][j];
	}
	
	public int[] getRegion(int x, int y) {
		return grid[Vec2D.fromCartesian(x, y).getI()];
	}
	
	public int[] getRegion(int i) {
		return grid[i];
	}
	
	public int[] getColumn(int x) {
		int[] column = new int[9];
		for (int y = 0; y < 9; y++) {
			column[y] = get(x, y);
		}
		return column;
	}
	
	public int[] getRow(int y) {
		int[] row = new int[9];
		for (int x = 0; x < 9; x++) {
			row[x] = get(x, y);
		}
		return row;
	}
	
	public String display() {
		StringBuilder textDisplay = new StringBuilder();
		horizontalBar(textDisplay);
		newLine(textDisplay);
		
		int prevI;
		for (int l = 0; l < 3; l++) {
			prevI = 0;
			for (int i = 3; i <= 9; i += 3) {
				project(textDisplay, grid, new Vec2D(l * 3, prevI), new Vec2D((l+1) * 3, i));
				newLine(textDisplay);
				prevI = i;
			}
			
			horizontalBar(textDisplay);
			newLine(textDisplay);
		}
		
		return textDisplay.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < grid.length; i++) {
			sb.append("[");
			sb.append(Arrays.toString(grid[i]));
			sb.append("]");
			if (i < grid.length - 1) {
				sb.append(",\n ");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void project(StringBuilder sb, int[][] grid, Vec2D min, Vec2D max) {
		sb.append('|');
		for (int i = min.getI(); i < max.getI(); i++) {
			int[] subGrid = grid[i];
			for (int j = min.getJ(); j < max.getJ(); j++) {
				int val = subGrid[j];
				String append = " ";
				if (val != -1) {
					append = Integer.toString(val);
				}
				sb.append(append);
			}
			sb.append('|');
		}
	}
	
	public static void horizontalBar(StringBuilder sb) {
		for (int i = 0; i < 12; i++) {
			sb.append('-');
		}
	}
	
	public static void newLine(StringBuilder sb) {
		sb.append('\n');
	}
}
