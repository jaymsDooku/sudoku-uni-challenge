package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.SudokuValidator;
import io.jayms.sudoku.solver.SudokuSolver;

public class SolveSudokuOption extends SudokuTextOption {

	public SolveSudokuOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return TextOptionID.SOLVE_OPTION;
	}

	@Override
	public String text() {
		return "Solve the sudoku.";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		SudokuSolver solver = new SudokuSolver(sudoku);
		System.out.println("Before: ");
		System.out.println(sudoku.display());
		
		solver.solve();
		
		System.out.println("After: ");
		System.out.println(sudoku.display());
		
		SudokuValidator validator = new SudokuValidator(sudoku);
		String outcome = validator.validate() ? "passed" : "failed";
		System.out.println("Sudoku " + outcome + " validation.");
		return sudoku;
	}

}

