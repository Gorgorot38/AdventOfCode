import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day12_2018 {

	private int steps = 365;
	private final int plantSize = 1000;
	private final String[][] grid = new String[this.steps+1][this.plantSize];
	private final HashMap<String, List<List<String>>> potsPattern = new HashMap<>();
	
	public void constructInitialState(final Path path) throws IOException {
		
		int indexParsed = 0;
		final String[] inputFile = Utils.convertFileArray(path);
		final String[][] fileParsed = new String[inputFile.length][116];
		final List<List<String>> hashtags = new ArrayList<>();
		final List<List<String>> dots = new ArrayList<>();

		
		for (final String element : inputFile) {
			fileParsed[indexParsed] = element.split("");
			indexParsed++;
		}

		for (int i = 15; i < fileParsed[0].length; i++) {
			this.grid[0][i+200-15] = fileParsed[0][i];
		}
		
		for (int i = 0; i < this.grid[0].length; i ++) {
			
			if(!"#".equalsIgnoreCase(this.grid[0][i]) && !".".equalsIgnoreCase(this.grid[0][i])) {
				this.grid[0][i] = ".";
			}
		}
		
		for (int i = 2 ; i < fileParsed.length; i++) {
			final List<String> tmpList = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
				tmpList.add(fileParsed[i][j]);
			}
			if(fileParsed[i][9].equals("#")) {
				hashtags.add(tmpList);
			} else {
				dots.add(tmpList);
			}
		}
		
		this.potsPattern.put("#", hashtags);
		this.potsPattern.put(".", dots);
		
	}
	
	public void constructGrid(final Path path) throws IOException {

		constructInitialState(path);

		for (int i = 0; i < this.grid.length - 1; i++) {
			for (int j = 0; j < this.grid[i].length; j++) {
				final List<String> currentPattern = new ArrayList<>();
				switch (j) {
				case 0:
					currentPattern.add(this.grid[i][98]);
					currentPattern.add(this.grid[i][99]);
					currentPattern.add(this.grid[i][j]);
					currentPattern.add(this.grid[i][j + 1]);
					currentPattern.add(this.grid[i][j + 2]);
					break;
				case 1:
					currentPattern.add(this.grid[i][99]);
					currentPattern.add(this.grid[i][j - 1]);
					currentPattern.add(this.grid[i][j]);
					currentPattern.add(this.grid[i][j + 1]);
					currentPattern.add(this.grid[i][j + 2]);
					break;
				case 998:
					currentPattern.add(this.grid[i][j - 2]);
					currentPattern.add(this.grid[i][j - 1]);
					currentPattern.add(this.grid[i][j]);
					currentPattern.add(this.grid[i][j + 1]);
					currentPattern.add(this.grid[i][0]);
					break;
				case 999:
					currentPattern.add(this.grid[i][j - 2]);
					currentPattern.add(this.grid[i][j - 1]);
					currentPattern.add(this.grid[i][j]);
					currentPattern.add(this.grid[i][0]);
					currentPattern.add(this.grid[i][1]);
					break;

				default:
					currentPattern.add(this.grid[i][j - 2]);
					currentPattern.add(this.grid[i][j - 1]);
					currentPattern.add(this.grid[i][j]);
					currentPattern.add(this.grid[i][j + 1]);
					currentPattern.add(this.grid[i][j + 2]);
				}
				

				if (this.potsPattern.get("#").contains(currentPattern)) {
					this.grid[i + 1][j] = "#";
				} else if (this.potsPattern.get(".").contains(currentPattern)) {
					this.grid[i + 1][j] = ".";
				}
			}
		}
	}
	
	public int findIndexStart(final String[][] finaLgrid) {
		int index = 0;
		for (int i = 0; i < finaLgrid[0].length; i++) {
			if (finaLgrid[0][i].equals("#")) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public int countPots(final Path path) throws IOException {

		constructGrid(path);
		final int index = findIndexStart(this.grid);
		int result = 0;

		final StringBuilder builder = new StringBuilder();
		for (final String[] element : this.grid) {
			for (int j = 0; j < element.length; j++)// for each column
			{
				builder.append(element[j] + "");// append to the output string
				if (j < this.grid.length - 1) {
					builder.append("");// then add comma (if you don't like commas you can use spaces)
				}
			}
			builder.append("\n");// append new line at the end of the row
		}
		final BufferedWriter writer = new BufferedWriter(
		                new FileWriter("C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/potsTest.txt"));
		writer.write(builder.toString());// save the string representation of the board
		writer.close();

		for (int i = 0; i < this.grid[20].length; i++) {
			if (this.grid[this.steps][i].equalsIgnoreCase("#")) {
				result = result + i - index;

			}

		}

		return result;

	}
	
	public void part1(final Path path) throws IOException {
		
		setSteps(20);
		final int pots = countPots(path);
		System.out.println(pots);
		
	}
	
	public long predictFuture(final Path path) throws IOException {
		
		long future = 0;
		setSteps(365);
		
		future = 50000000000L * countPots(path) / 365;
		
		return future;
		
	}
	
	public void part2(final Path path) throws IOException {
		
		final long future = predictFuture(path);
		System.out.println(future);
		
	}

	
	/**
	 * @return the steps
	 */
	public int getSteps() {
		return this.steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(final int steps) {
		this.steps = steps;
	}

	/**
	 * @return the plantSize
	 */
	public int getPlantSize() {
		return this.plantSize;
	}
	/**
	 * @return the grid
	 */
	public String[][] getGrid() {
		return this.grid;
	}

	/**
	 * @return the potsPattern
	 */
	public HashMap<String, List<List<String>>> getPotsPattern() {
		return this.potsPattern;
	}
	
}
