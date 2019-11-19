package io.jayms.sudoku;

import java.util.InputMismatchException;
import java.util.Scanner;

import io.jayms.sudoku.menu.DisplayOption;
import io.jayms.sudoku.menu.ExitOption;
import io.jayms.sudoku.menu.ReadFromFileOption;
import io.jayms.sudoku.menu.SudokuTextMenu;
import io.jayms.sudoku.menu.SudokuTextOption;
import io.jayms.sudoku.menu.WriteToFileOption;

public class SudokuTextInterface {

	private Sudoku sudoku;
	private Scanner in;
	private SudokuTextMenu menu;
	
	public SudokuTextInterface(Sudoku sudoku) {
		this.sudoku = sudoku;
		this.in = new Scanner(System.in);
		this.menu = new SudokuTextMenu();
		
		menu.registerOption(new DisplayOption(in));
		menu.registerOption(new WriteToFileOption(in));
		menu.registerOption(new ReadFromFileOption(in));
		menu.registerOption(new ExitOption(in));
	}
	
	public void start() {
		while (true) {
			System.out.println("Options:");
			System.out.println("1. Display Sudoku");
			System.out.println("2. Write Sudoku to File");
			System.out.println("3. Read Sudoku from File");
			System.out.println("4. Exit");
			try {
				int option = in.nextInt();
				in.nextLine();
				SudokuTextOption textOption = menu.getOption(option);
				if (textOption == null) {
					System.out.println("Invalid option.");
					continue;
				}
				sudoku = textOption.process(sudoku);
			} catch (InputMismatchException e) {
				System.out.println("Invalid option.");
			}
		}
	}
}
