package io.jayms.sudoku;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuTextInterface {

	private Sudoku sudoku;
	private Scanner in;
	
	public SudokuTextInterface(Sudoku sudoku) {
		this.sudoku = sudoku;
		this.in = new Scanner(System.in);
	}
	
	public void start() {
		while (true) {
			System.out.println("Options:");
			System.out.println("1. Display Sudoku");
			System.out.println("2. Write Sudoku to File");
			System.out.println("3. Exit");
			try {
				int option = in.nextInt();
				switch (option) {
					case 1:
						System.out.println(sudoku.display());
						break;
					case 2:
						System.out.println("Enter filename: ");
						String filename = in.nextLine();
						
						
						break;
					case 3:
						System.out.println("Goodbye.");
						System.exit(1);
						return;
					default:
						break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid option.");
			}
		}
	}
}
