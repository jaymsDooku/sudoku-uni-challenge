package io.jayms.sudoku.menu;

import java.io.FileNotFoundException;
import java.util.Arrays;
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
		return 3;
	}

	@Override
	public String text() {
		return "Write Sudoku To File";
	}

	@Override
	public Sudoku process(Sudoku sudoku) {
		System.out.println("Enter filename: ");
		String filename = getScanner().nextLine() + ".txt";
		try {
			int[][] grid = SudokuIO.readGridFromFile(filename);
			for (int i = 0; i < grid.length; i++) {
				System.out.println("arr " + (i+1) + ": " + Arrays.toString(grid[i]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SudokuException e) {
			e.printStackTrace();
		}
		return sudoku;
	}

}
