package aoc.aoc2018;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

import aoc.Utils;

public class Day6 {

	public static HashMap<String, Integer[]> getCoordinatesMap(final Path path) throws IOException{
		
		final HashMap<String, String> coordinatesMap = new HashMap<>();
		final HashMap<String, Integer[]> coordinatesMapReal = new HashMap<>();
		final String[] coordArray = Utils.convertFileArray(path);
		char first = 'A';
		char second = 'A';
		
		for (final String element : coordArray) {
			coordinatesMap.put("" + first + second, element);
			if (second == 'Z') {
				first = 'B';
				second = '@';
			}
			second++;
		}
		
		coordinatesMap.forEach((id, coord) -> {
			final String[] newCoord = coord.split(", ");
			final Integer[] intCoord = new Integer[2];
			intCoord[0] = Integer.parseInt(newCoord[0]);
			intCoord[1] = Integer.parseInt(newCoord[1]);

			coordinatesMapReal.put(id, intCoord);
		});
		
		return coordinatesMapReal;
	}
	
	public static String[][] buildBasicMatrix(final HashMap<String, Integer[]> coordinatesMapReal){
		
		int maxX = 0;
		int maxY = 0;
		
		for (final Entry<String, Integer[]> e : coordinatesMapReal.entrySet()) {
			if (e.getValue()[0] >= maxX) {
				maxX = e.getValue()[0];
			}
			if (e.getValue()[1] >= maxY) {
				maxY = e.getValue()[1];
			}
		}

		final String[][] firstMatrix = new String[maxX+2][maxY+2];
		
		for (final Entry<String, Integer[]> e : coordinatesMapReal.entrySet()) {
			if (e.getKey().equals(e.getKey().toUpperCase())) {
				firstMatrix[e.getValue()[0]][e.getValue()[1]] = e.getKey();
			}
		}
		
		return firstMatrix;
	}
	
	public static String[][] buildManMatrix(final Path path) throws IOException {

		final HashMap<String, Integer[]> coordMap = getCoordinatesMap(path);
		final String[][] fullMatrix = buildBasicMatrix(coordMap);
		final String toPut = "";
		final int manDist = 10000;


		for (int i = 0; i < fullMatrix.length; i++) {
			for (int j = 0; j < fullMatrix[i].length; j++) {

				if (fullMatrix[i][j] == null) {
					int allDist = 0;
					for (final Entry<String, Integer[]> e : coordMap.entrySet()) {
						final int currentManDist = Math.abs(e.getValue()[0] - i) + Math.abs(e.getValue()[1] - j);
						allDist = allDist + currentManDist;
					}
					if (allDist < manDist) {
						fullMatrix[i][j] = "#";
					}
				}
			}
		}

		final StringBuilder builder = new StringBuilder();
		for (final String[] element : fullMatrix) {
			for (final String element2 : element) {
				builder.append(element2 + "\t\t\t");// append to the output string
			}
			builder.append("\n");// append new line at the end of the row
		}
		final BufferedWriter writer = new BufferedWriter(
		                new FileWriter("C:/dev/workspace/AdventOfCode/AdventOfCode/files/output.txt"));
		writer.write(builder.toString());// save the string representation of the board
		writer.close();

		return fullMatrix;

	}
	
	public static int countSafe(final Path path) throws IOException {
		
		int result = 0;
		
		final String[][] manMatrix = buildManMatrix(path);
		final HashMap<String, Integer[]> coordMap = getCoordinatesMap(path);
		
		for (final String[] element : manMatrix) {
			for (final String element2 : element) {
				if ("#".equalsIgnoreCase(element2)) {
					result++;
				}
			}
		}

		for (final Entry<String, Integer[]> e : coordMap.entrySet()) {
			if ("#".equals(manMatrix[e.getValue()[0] + 1][e.getValue()[1]])
			                && "#".equals(manMatrix[e.getValue()[0] - 1][e.getValue()[1]])
			                && "#".equals(manMatrix[e.getValue()[0]][e.getValue()[1] + 1])
			                && "#".equals(manMatrix[e.getValue()[0]][e.getValue()[1] - 1])) {
				result++;
			}
		}
		
		return result;
	}
	
	public static int countSurface(final Path path) throws IOException {
		
		int result = 0;
		final HashMap<String, Integer[]> coordMap = getCoordinatesMap(path);

		final String[][] manMatrix = buildManMatrix(path);
		
		for (final String[] element : manMatrix) {
			final int j = 0;
			final int k = element.length-1;
			
			if (coordMap.containsKey(element[j].toUpperCase()) && !element[j].equals(".")) {
				coordMap.remove(element[j].toUpperCase());
			}
			if (coordMap.containsKey(element[k].toUpperCase()) && !element[k].equals(".")) {
				coordMap.remove(element[k].toUpperCase());
			}
		}
		
		for (int j = 0; j < manMatrix[0].length; j ++) {
			final int i = 0;
			final int k = manMatrix.length-1;
			
			if (coordMap.containsKey(manMatrix[i][j].toUpperCase()) && !manMatrix[i][j].equals(".")) {
				coordMap.remove(manMatrix[i][j].toUpperCase());
			}
			if (coordMap.containsKey(manMatrix[k][j].toUpperCase()) && !manMatrix[k][j].equals(".")) {
				coordMap.remove(manMatrix[k][j].toUpperCase());
			}
		}
		
		for (final Entry<String, Integer[]> e : coordMap.entrySet()) {
			
			int currentResult = 0;
			
			for (final String[] element : manMatrix) {
				for (final String element2 : element) {
					if (element2.equalsIgnoreCase(e.getKey())) {
						currentResult++;
					}
				}
			}
			
			if (result <= currentResult) {
				result = currentResult;
			}
			
		}

		
		return result;
		
		
	}
	
}
