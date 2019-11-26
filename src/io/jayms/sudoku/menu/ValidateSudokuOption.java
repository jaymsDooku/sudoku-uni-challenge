package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.SudokuValidator;

public class ValidateSudokuOption extends SudokuTextOption {

	public ValidateSudokuOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return TextOptionID.VALIDATE_OPTION;
	}

	@Override
	public String text() {
		return "Validate the sudoku.";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		SudokuValidator validator = new SudokuValidator(sudoku);
		String outcome = validator.validate() ? "passed" : "failed";
		System.out.println("Sudoku " + outcome + " validation.");
		return sudoku;
	}

}
