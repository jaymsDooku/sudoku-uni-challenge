package io.jayms.sudoku.solver;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.jayms.sudoku.util.Vec2D;

@SuppressWarnings("unlikely-arg-type")
public class SudokuSolverCell {

	private Vec2D pos;
	private Set<AvailableNumber> available;
	
	public SudokuSolverCell(Vec2D pos, double basePheromone) {
		this.pos = pos;
		this.available = IntStream.range(0, 10)
							.boxed()
							.map(i -> new AvailableNumber(i, basePheromone))
							.collect(Collectors.toSet());
	}
	
	public Vec2D getPos() {
		return pos;
	}
	
	public void unavailable(int num) {
		available.remove(num);
	}
	
	public boolean isAvailable(int num) {
		return available.contains(num);
	}
	
	public int available() {
		return available.size();
	}
	
	// returns -1 if it is not solved and the last remaining number if it is solved.
	public int solved() {
		return (available() == 1) ? available.iterator().next().getValue() : -1;
	}
	
	public Set<AvailableNumber> getAvailable() {
		return available;
	}
	
}
