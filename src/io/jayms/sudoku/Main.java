package io.jayms.sudoku;

public class Main {

	public static void main(String[] args) {
		Sudoku sudoku = new SudokuBuilder()
				.set(0, 2, 9)
				.subGrid(1, new int[] { 1, -1, 3, -1, 4, -1, 5, 6, 7})
				.set(2, 0, 2)
				.set(2, 8, 3)
				.subGrid(3, new int[] { 9, -1, 4, -1, 4, 5, 7, -1, 8 })
				.subGrid(5, new int[] { 6, -1, 7, 8, 4, -1, 5, -1, 2 })
				.set(6, 0, 8)
				.set(6, 8, 6)
				.subGrid(7, new int[] { 4, 1, 2, -1, 3, -1, 9, -1, 5 })
				.set(8, 2, 5)
				.set(8, 6, 4)
				.build();
		/*Sudoku sudoku = new SudokuBuilder().build();
		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				sudoku.set(x, y, 1);
			}
		}*/
		
		SudokuTextInterface textInterface = new SudokuTextInterface(sudoku);
		textInterface.start();
	}
	
}
