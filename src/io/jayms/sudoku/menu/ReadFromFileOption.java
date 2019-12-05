package io.jayms.sudoku.menu;

import java.io.FileNotFoundException;
import java.util.Scanner;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.SudokuIO;
import io.jayms.sudoku.util.SudokuException;

public class ReadFromFileOption extends SudokuTextOption {

	public ReadFromFileOption(Scanner in) {
		super(in);
	}

	@Override
	public int id() {
		return TextOptionID.READ_FILE_OPTION;
	}

	@Override
	public String text() {
		return "Read Sudoku from file";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		System.out.println("Enter filename: ");
		String filename = getScanner().nextLine() + ".txt";
		try {
			sudoku = SudokuIO.readSudokuFromFile(filename, 27);
			System.out.println("Loaded sudoku: " + sudoku.display());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SudokuException e) {
			System.out.println("Failed to load sudoku: " + e.getMessage());
		}
		return sudoku;
	}

}
