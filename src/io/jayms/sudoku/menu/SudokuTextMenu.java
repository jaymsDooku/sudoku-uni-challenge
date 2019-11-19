package io.jayms.sudoku.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuTextMenu {

	private Map<Integer, SudokuTextOption> options = new HashMap<>();
	
	public SudokuTextMenu() {
	}
	
	public void registerOption(SudokuTextOption option) {
		options.put(option.id(), option);
	}
	
	public SudokuTextOption getOption(int id) {
		return options.get(id);
	}
	
	public List<SudokuTextOption> getOrderedOptions() {
		List<SudokuTextOption> result = new ArrayList<>(options.values());
		Collections.sort(result);
		return result;
	}
	
}
