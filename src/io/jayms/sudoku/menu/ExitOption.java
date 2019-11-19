package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;

public class ExitOption extends SudokuTextOption {

	public ExitOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return 4;
	}

	@Override
	public String text() {
		return "Exit";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		System.out.println("Goodbye World.");
		System.exit(1);
		return null;
	}

}
