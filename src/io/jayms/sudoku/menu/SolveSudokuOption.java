package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.SudokuIO;
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
		Sudoku solutionCopy = sudoku.copy();
		SudokuSolver solver = new SudokuSolver(solutionCopy);
		System.out.println("Before: ");
		System.out.println(sudoku.display());
		
		boolean solved = solver.solve();
		
		if (solved) {
			System.out.println("After: ");
			System.out.println(sudoku.display());
			
			System.out.println("Would you like to write solution to file? (Y/N)");
			String choice = getScanner().nextLine().toUpperCase();
			if (!choice.equals("Y")) {
				return sudoku;
			}
			
			System.out.println("Enter filename: ");
			String filename = getScanner().nextLine() + ".txt";
			if (SudokuIO.writeToFile(filename, solutionCopy)) {
				System.out.println("Written sudoku solution to: " + filename);
			} else {
				System.out.println("Failed to write sudoku.");
			}
		} else {
			System.out.println("This sudoku is not possible.");
		}
		
		return sudoku;
	}

}

