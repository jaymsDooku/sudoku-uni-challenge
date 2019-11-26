package io.jayms.sudoku.solver;

import java.util.Objects;

public class AvailableNumber {

	private int value;
	private double pheromone;
	
	public AvailableNumber(int value, double pheromone) {
		this.value = value;
		this.pheromone = pheromone;
	}
	
	public int getValue() {
		return value;
	}
	
	public double getPheromone() {
		return pheromone;
	}
	
	public void increasePheromone(double dp) {
		pheromone += dp;
	}
	
	public void decreasePheromone(double dp) {
		pheromone -= dp;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AvailableNumber) { 
			return ((AvailableNumber) obj).getValue() == value;
		}
		
		if (obj instanceof Integer) {
			return ((Integer) obj) == value;
		}
		
		return false;
	}
	
}
