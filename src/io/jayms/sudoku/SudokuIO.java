package io.jayms.sudoku;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.jayms.sudoku.util.SudokuException;
import io.jayms.sudoku.util.Vec2D;

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
	
	public static int[][] readGridFromFile(String file) throws FileNotFoundException, SudokuException {
		return readGridFromFile(new File(file));
	}
	
	public static int[][] readGridFromFile(File file) throws FileNotFoundException, SudokuException {
		List<String> text = new ArrayList<>();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNext()) {
			text.add(scanner.nextLine());
		}
		scanner.close();
		
		text = text.stream()
				.filter(s -> s.startsWith("|"))
				.collect(Collectors.toList());
		
		if (text.size() != 9) {
			throw new SudokuException("Invalid sudoku format.");
		}
		
		text = text.stream()
				.map(s -> s.replace("|", ""))
				.collect(Collectors.toList());
		
		int[][] grid = new int[9][9];
		int prevI = 0;
		for (int i = 0; i < text.size(); i++) {
			String line = text.get(i);
			//grid = extractFromText(grid, line, new Vec2D(0, 0), new Vec2D());
			
			//TODO: sort out whatever the hell is happening here
		}
		
		return grid;
	}
	
	private static void extractFromText(int[][] grid, String line, Vec2D min, Vec2D max) {
		int c = 0;
		for (int i = min.getI(); i < max.getI(); i++) {
			for (int j = min.getJ(); j < max.getJ(); j++) {
				char ch = line.charAt(c++);
				if (ch != ' ' && !Character.isDigit(ch)) {
					throw new SudokuException("Invalid sudoku format.");
				}
				int val = (ch == ' ') ? -1 : Character.getNumericValue(ch);
				grid[i][j] = val;
			}
		}
	}
	
	public static Sudoku readSudokuFromFile(String file) throws FileNotFoundException, SudokuException {
		return null;
	}
	
	public static Sudoku readSudokuFromFile(File file) throws FileNotFoundException, SudokuException {
		return null;
	}
	
}
