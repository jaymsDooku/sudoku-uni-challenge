package io.jayms.sudoku.menu;

import java.util.Scanner;

public abstract class SudokuTextOption implements SudokuMenuOption, Comparable<SudokuTextOption> {
	
	private Scanner in;
	
	protected SudokuTextOption(Scanner in) {
		this.in = in;
	}
	
	protected Scanner getScanner() {
		return in;
	}
	
	public abstract int id();
	
	@Override
	public int compareTo(SudokuTextOption o) {
		if (o.id() == id()) {
			return 0;
		} else if (o.id() > id()) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
