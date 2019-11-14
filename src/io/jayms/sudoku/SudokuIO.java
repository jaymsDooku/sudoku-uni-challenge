package io.jayms.sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SudokuIO {

	public static boolean writeToFile(String file, Sudoku sudoku) {
		return writeToFile(new File(file), sudoku);
	}
	
	public static boolean writeToFile(File file, Sudoku sudoku) {
		try {
			String sudokuDisplay = sudoku.display();
			Scanner in = new Scanner(sudokuDisplay);
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				writer.println(line);
			}
			
			writer.flush();
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
