package aoc.aoc2018;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import aoc.Utils;

public class Day17 {

	String[][] waterFall = new String[1900][210];
	
	public void buildSandArray(final Path path) throws IOException {

		final String[] coord = Utils.convertFileArray(path);

		for (int i = 0; i < this.waterFall.length; i++) {
			for (int j = 0; j < this.waterFall[i].length; j++) {
				this.waterFall[i][j] = ".";
			}
		}

		for (final String element : coord) {

			if (element.startsWith("x=")) {

				String tmpJ = element.replace("x=", "");
				tmpJ = tmpJ.replace(", y", "");
				tmpJ = tmpJ.replace("..", "=");
				final String[] values = tmpJ.split("=");

				for (int i = Integer.parseInt(values[1]); i <= Integer.parseInt(values[2]); i++) {
					this.waterFall[i][Integer.parseInt(values[0]) - 400] = "#";
				}
			} else {
				String tmpJ = element.replace("y=", "");
				tmpJ = tmpJ.replace(", x", "");
				tmpJ = tmpJ.replace("..", "=");
				final String[] values = tmpJ.split("=");

				for (int i = Integer.parseInt(values[1]) - 400; i <= Integer.parseInt(values[2]) - 400; i++) {
					this.waterFall[Integer.parseInt(values[0])][i] = "#";
				}

			}
		}
		
		this.waterFall[0][100] = "+";
		this.waterFall[1][100] = "|";

		
//		final StringBuilder builder = new StringBuilder();
//		for (final String[] element : sandArray) {
//			for (int j = 0; j < element.length; j++)// for each column
//			{
//				builder.append(element[j] + "");// append to the output string
//				if (j < sandArray.length - 1) {
//					builder.append(" ");// then add comma (if you don't like commas you can use spaces)
//				}
//			}
//			builder.append("\n");// append new line at the end of the row
//		}
//		final BufferedWriter writer = new BufferedWriter(
//		                new FileWriter("C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/fromage.txt"));
//		writer.write(builder.toString());// save the string representation of the board
//		writer.close();

	}
	
	public void part1(final Path path, final int startY, final int startX) throws IOException {
		
		int tiles = 0;
		buildSandArray(path);
		falling(startY, startX);

		final StringBuilder builder = new StringBuilder();
		for (final String[] element : this.waterFall) {
			for (int k = 0; k < element.length; k++)// for each column
			{
				builder.append(element[k] + "");// append to the output string
				if (k < this.waterFall.length - 1) {
					builder.append(" ");// then add comma (if you don't like commas you can use spaces)
				}
			}
			builder.append("\n");// append new line at the end of the row
		}
		final BufferedWriter writer = new BufferedWriter(
		                new FileWriter("C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/fromage.txt"));
		writer.write(builder.toString());// save the string representation of the board
		writer.close();

		for (final String[] element : this.waterFall) {
			for (final String element2 : element) {
				
				if (element2 == "~") {
					tiles++;
				}
			}
		}
		System.out.println(tiles);
		
	}
	
	private void falling(final int startY, final int startX) throws IOException {

		final int over = 0;
		final boolean notOver = true;
		final int x = startX;
		final int y = startY;

		
		if (y == 1899) {
			return;
		}

		if (".".equals(this.waterFall[y + 1][x])) {
			this.waterFall[y + 1][x] = "|";
			falling(y+1, x);
		} else if ("#".equals(this.waterFall[y + 1][x])) {
			if (hasLeftWall(this.waterFall, y, x) && hasRightWall(this.waterFall, y, x)) {
				for (int i = findLeftWall(this.waterFall, y, x); i < findRightWall(this.waterFall, y, x); i++) {
					this.waterFall[y][i] = "~";
				}
				falling(y-1, x);
			} else if (!hasLeftWall(this.waterFall, y, x) && !hasRightWall(this.waterFall, y, x)) {
				int i = x;
				while(this.waterFall[y+1][i] != ".") {
					this.waterFall[y][i] = "|";
					i--;
				}
				this.waterFall[y][i] = "|";
				falling(y, i);
				i = x;
				while(this.waterFall[y+1][i] == "#") {
					this.waterFall[y][i] = "|";
					i++;
				}
				this.waterFall[y][i] = "|";
				falling(y, i);
			} else if (".".equals(this.waterFall[y + 1][x - 1])
			                && "~".equals(this.waterFall[y + 1][x + 1])) {
				this.waterFall[y][x - 1] = "|";
				falling(y, x - 1);
			} else if (".".equals(this.waterFall[y + 1][x + 1])
			                && "~".equals(this.waterFall[y + 1][x - 1])) {
				this.waterFall[y][x + 1] = "|";
				falling(y, x + 1);
			}
		} else if ("~".equals(this.waterFall[y + 1][x])) {
			if (hasLeftWall(this.waterFall, y, x) && hasRightWall(this.waterFall, y, x)) {
				for (int i = findLeftWall(this.waterFall, y, x); i < findRightWall(this.waterFall, y, x); i++) {
					this.waterFall[y][i] = "~";
				}
				falling(y-1, x);
			} else if (hasLeftWall(this.waterFall, y, x) && !hasRightWall(this.waterFall, y, x)) {
				for (int i = x; i >= findLeftWall(this.waterFall, y, x); i--) {
					this.waterFall[y][i] = "|";
				}
				int i = x;
				while(this.waterFall[y+1][i] != ".") {
					this.waterFall[y][i] = "|";
					i++;
				}
				if (this.waterFall[y+1][i-2] == "#") {
					this.waterFall[y][i] = "|";
					falling(y, i);
				}
				if (this.waterFall[y][x] != "~") {
					falling(y, i-1);
				}
			} else if (!hasLeftWall(this.waterFall, y, x) && hasRightWall(this.waterFall, y, x)) {
				for (int i = x; i < findRightWall(this.waterFall, y, x); i++) {
					this.waterFall[y][i] = "|";
				}
				int i = x;
				while(this.waterFall[y+1][i] != ".") {
					this.waterFall[y][i] = "|";
					i--;
				}
				if (this.waterFall[y+1][i+2] == "#") {
					this.waterFall[y][i] = "|";
					falling(y, i);
				}
				if (this.waterFall[y][x] != "~") {
					falling(y, i+1);
				}

			} else if (!hasLeftWall(this.waterFall, y, x) && !hasRightWall(this.waterFall, y, x)) {
				int i = x;
				while(this.waterFall[y+1][i] != ".") {
					this.waterFall[y][i] = "|";
					i--;
				}
				this.waterFall[y][i] = "|";
				falling(y, i);
				if (this.waterFall[y-1][x] != "~") {
					i = x;
					while(this.waterFall[y+1][i] != ".") {
						this.waterFall[y][i] = "|";
						i++;
					}
					this.waterFall[y][i] = "|";
					falling(y, i);
				}
			}
		}

//		final StringBuilder builder = new StringBuilder();
//		for (final String[] element : this.waterFall) {
//			for (int k = 0; k < element.length; k++)// for each column
//			{
//				builder.append(element[k] + "");// append to the output string
//				if (k < this.waterFall.length - 1) {
//					builder.append(" ");// then add comma (if you don't like commas you can use spaces)
//				}
//			}
//			builder.append("\n");// append new line at the end of the row
//		}
//		final BufferedWriter writer = new BufferedWriter(
//		                new FileWriter("C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/fromage.txt"));
//		writer.write(builder.toString());// save the string representation of the board
//		writer.close();
//
//		over++;
//		System.out.println(over);
		
		
	}

	public static int findLeftWall(final String[][] waterFall, final int i, final int j) {
		int tmpJ = j;
		while (!"#".equals(waterFall[i][tmpJ])) {
			tmpJ--;
		}
		return tmpJ+1;
	}
	
	public static boolean hasLeftWall(final String[][] waterFall, final int i, final int j) {
		int tmpJ = j;
		while(!"#".equals(waterFall[i][tmpJ])) {
			tmpJ--;
			if(".".equals(waterFall[i+1][tmpJ])) {
				return false;
			}
		}
		return true;
	}
	
	public static int findRightWall(final String[][] waterFall, final int i, final int j) {
		int tmpJ = j;
		while (!"#".equals(waterFall[i][tmpJ])) {
			tmpJ++;
		}
		return tmpJ;
	}

	public static boolean hasRightWall(final String[][] waterFall, final int i, final int j) {
		int tmpJ = j;
		while(!"#".equals(waterFall[i][tmpJ])) {
			tmpJ++;
			if(".".equals(waterFall[i+1][tmpJ])) {
				return false;
			}
		}
		return true;
	}

}
