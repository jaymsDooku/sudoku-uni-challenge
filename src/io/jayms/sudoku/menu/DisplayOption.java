package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;

public class DisplayOption extends SudokuTextOption {

	public DisplayOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return 1;
	}

	@Override
	public String text() {
		return "Display Sudoku";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		System.out.println(sudoku.display());
		return sudoku;
	}

}
