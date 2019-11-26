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
			in.close();
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
		int i = 3;
		int prevJ = 0;
		int j = 3;
		for (int l = 0; l < text.size(); l++) {
			String line = text.get(l);
			extractFromText(grid, line, new Vec2D(prevI, prevJ), new Vec2D(i, j));
			if ((l+1) % 3 == 0) {
				prevI = i;
				i += 3;
			}
			if (i >= 12) {
				prevI = 0;
				i = 3;
			}
			prevJ = j;
			j += 3;
			if (j >= 12) {
				prevJ = 0;
				j = 3;
			}
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
	
	public static Sudoku readSudokuFromFile(String file, int clues) throws FileNotFoundException, SudokuException {
		int[][] grid = readGridFromFile(file);
		return new Sudoku(clues, grid);
	}
	
	public static Sudoku readSudokuFromFile(File file, int clues) throws FileNotFoundException, SudokuException {
		int[][] grid = readGridFromFile(file);
		return new Sudoku(clues, grid);
	}
	
}
