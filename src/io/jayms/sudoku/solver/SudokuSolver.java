package io.jayms.sudoku.solver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import io.jayms.sudoku.Sudoku;
import io.jayms.sudoku.util.Vec2D;

public class SudokuSolver {

	private Sudoku sudoku;
	private Deque<SudokuAssignment> assignmentHistory;
	
	public SudokuSolver(Sudoku sudoku) {
		this.sudoku = sudoku;
		this.assignmentHistory = new ArrayDeque<>();
	}
	
	public void solve() {
		for (int x = 0; x < Sudoku.GRID_DIMENSIONS; x++) {
			for (int y = 0; y < Sudoku.GRID_DIMENSIONS; y++) {
				if (!sudoku.isEmptyCell(x, y)) continue;
				boolean foundMove = false;
				for (int i = 1; i < 10; i++) {
					if (isValidMove(x, y, i)) {
						System.out.println("Trying " + i + " x=" + x + ", y=" + y + ", assignments=" + assignmentHistory.size());
						set(x, y, i);
						foundMove = true;
					}
				}
				if (!foundMove) {
					System.out.println("undoing " + x + " " + y);
					SudokuAssignment assignment = undo();
					x = assignment.getPos().getX();
					y = assignment.getPos().getY();
				}
			}
		}
	}
	
	private void set(int x, int y, int num) {
		sudoku.set(x, y, num);
		assignmentHistory.push(new SudokuAssignment(new Vec2D(x, y), num));
	}
	
	private SudokuAssignment undo() {
		SudokuAssignment assignment = assignmentHistory.pop();
		int x = assignment.getPos().getX();
		int y = assignment.getPos().getY();
		sudoku.set(x, y, -1);
		return assignment;
	}
	
	public boolean isValidMove(int x, int y, int num) {
		int[] row = sudoku.getRow(y);
		int[] column = sudoku.getColumn(x);
		int[] region = sudoku.getRegion(x, y);
		Set<Integer> rowSet = set(row);
		Set<Integer> columnSet = set(column);
		Set<Integer> regionSet = set(region);
		return !rowSet.contains(num) && !columnSet.contains(num) && !regionSet.contains(num);
	}
	
	private static Set<Integer> set(int[] arr) {
		Set<Integer> result = new HashSet<>();
		for (int i : arr) {
			result.add(i);
		}
		return result;
	}
	
	public Sudoku getSudoku() {
		return sudoku;
	}
	
}
