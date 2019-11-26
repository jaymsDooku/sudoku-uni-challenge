package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;

public class SetValueOption extends SudokuTextOption {

	public SetValueOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return TextOptionID.SET_VALUE_OPTION;
	}

	@Override
	public String text() {
		return "Set Sudoku value";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		int x = getInt("Enter x: ");
		int y = getInt("Enter y: ");
		int value = getInt("Enter value: ");
		sudoku.set(x, y, value);
		System.out.println(sudoku.display());
		return sudoku;
	}

}
