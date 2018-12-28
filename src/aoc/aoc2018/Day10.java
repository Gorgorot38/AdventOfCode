package aoc.aoc2018;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aoc.Utils;

public class Day10 {
	

	public static Integer[][] fileToArray(final Path path) throws IOException {
		
		final String[] input = Utils.convertFileArray(path);
		final String[][] formatedInput = new String[input.length][4];
		final Integer[][] formatedInputInt = new Integer[input.length][4];

		
		for (int i = 0; i < input.length; i++) {
			formatedInput[i] = input[i].split(" ");
		}

		for (int i = 0; i < formatedInput.length ; i++) {
			for (int j = 0 ; j < formatedInput[i].length; j++) {
				
				formatedInputInt[i][j] = Integer.parseInt(formatedInput[i][j]);
			}
		}

		
		return formatedInputInt;
		
	}
	
	public static void move(final Integer[][] input) throws IOException {

		final HashMap<Boolean, List<Integer>> goodGrid = new HashMap<>();


		for (int loop = 0; loop < 60000; loop++) {
			
			final List<Integer> listTrue = new ArrayList<>();
			final List<Integer> listFalse = new ArrayList<>();

			goodGrid.put(true, listTrue);
			goodGrid.put(false, listFalse);

			
			for (int i = 0; i < input.length; i++) {
				

				input[i][0] = input[i][0] + input[i][2];
				input[i][1] = input[i][1] + input[i][3];

				if (input[i][0] < 250 && input[i][0] > -250) {
					listTrue.add(i);
					goodGrid.put(true, listTrue);
				} else {
					listFalse.add(i);
					goodGrid.put(false, listFalse);
				}
			}
			
			if (goodGrid.get(false).isEmpty()) {
				final String[][] grid = new String[500][500];
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[i].length; j++) {

						grid[i][j] = ".";
					}
				}
				for (int i = 0; i < input.length; i++) {
					grid[input[i][0] + 250][input[i][1] + 250] = "#";
				}

				
				
				final StringBuilder builder = new StringBuilder();
				for (final String[] element : grid) {
					for (int j = 0; j < element.length; j++)// for each column
					{
						builder.append(element[j] + "");// append to the output string
						if (j < grid.length - 1) {
							builder.append(" ");// then add comma (if you don't like commas you can use spaces)
						}
					}
					builder.append("\n");// append new line at the end of the row
				}
				final BufferedWriter writer = new BufferedWriter(new FileWriter(
				                "C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/potential" + loop + ".txt"));
				writer.write(builder.toString());// save the string representation of the board
				writer.close();

			}
			
			goodGrid.clear();
		}

	}
	
	
}
