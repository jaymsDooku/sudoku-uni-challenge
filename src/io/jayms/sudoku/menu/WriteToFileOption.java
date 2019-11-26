package io.jayms.sudoku.menu;

import java.util.Scanner;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.SudokuIO;

public class WriteToFileOption extends SudokuTextOption {

	public WriteToFileOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return TextOptionID.WRITE_FILE_OPTION;
	}

	@Override
	public String text() {
		return "Write Sudoku To File";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		System.out.println("Enter filename: ");
		String filename = getScanner().nextLine() + ".txt";
		if (SudokuIO.writeToFile(filename, sudoku)) {
			System.out.println("Written sudoku display to: " + filename);
		} else {
			System.out.println("Failed to write sudoku.");
		}
		return sudoku;
	}

}
