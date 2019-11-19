package io.jayms.sudoku.menu;

import io.jayms.sudoku.Sudoku;

public interface SudokuMenuOption {

	String text();
	
	Sudoku process(Sudoku sudoku);
	
}
