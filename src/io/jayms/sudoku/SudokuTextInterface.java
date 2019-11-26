package io.jayms.sudoku;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import io.jayms.sudoku.menu.DisplayOption;
import io.jayms.sudoku.menu.ExitOption;
import io.jayms.sudoku.menu.ReadFromFileOption;
import io.jayms.sudoku.menu.SetValueOption;
import io.jayms.sudoku.menu.SolveSudokuOption;
import io.jayms.sudoku.menu.SudokuTextMenu;
import io.jayms.sudoku.menu.SudokuTextOption;
import io.jayms.sudoku.menu.ValidateSudokuOption;
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
		menu.registerOption(new SetValueOption(in));
		menu.registerOption(new WriteToFileOption(in));
		menu.registerOption(new ReadFromFileOption(in));
		menu.registerOption(new ValidateSudokuOption(in));
		menu.registerOption(new SolveSudokuOption(in));
		menu.registerOption(new ExitOption(in));
	}
	
	public void start() {
		while (true) {
			System.out.println("Options:");
			List<SudokuTextOption> options = menu.getOrderedOptions();
			for (SudokuTextOption option : options) {
				System.out.println(option.id() + ". " + option.text());
			}
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
