package aoc.aoc2018;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import aoc.Utils;

public class Day13 {

	int sizeX = 150;
	int sizeY = 150;
	
	class Cart{

		char direction = 'a';
		int turn = 0;
		
	}
	
	char[][] originalGrid = new char[this.sizeX][this.sizeY];
	
	public Cart[][] constructGrids(final Path path) throws IOException {
		
		final char[][] grid = Utils.convertFileMatrix(path);
		final Cart[][] gridConverted = new Cart[this.sizeX][this.sizeY];
		
		this.originalGrid = grid;
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				
				gridConverted[i][j] = new Cart();
				gridConverted[i][j].direction = grid[i][j];
				
				if(grid[i][j] == '<') {
					this.originalGrid[i][j] = '-';
				} else if( grid[i][j] == '>') {
					this.originalGrid[i][j] = '-';
				} else if (grid[i][j] == '^') {
					this.originalGrid[i][j] = '|';
				} else if (grid[i][j] == 'v') {
					this.originalGrid[i][j] = '|';
				} else {
					this.originalGrid[i][j] = grid[i][j];
				}
			}
		}
		return gridConverted;
	}
	
	public String findCrash(final Path path) throws IOException {

		String crash = "";
		int loops = 0;
		final Cart[][] grid = constructGrids(path);
		final Cart[][] tmpGrid = constructGrids(path);
		int sumdir = 0;

		while (sumdir != 1) {

			final HashMap<Character, Integer> dirCount = new HashMap<>();
			dirCount.put('^', 0);
			dirCount.put('v', 0);
			dirCount.put('>', 0);
			dirCount.put('<', 0);
			sumdir = 0;

			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {

					if (grid[i][j] == null) {
						grid[i][j] = new Cart();
						tmpGrid[i][j] = new Cart();
					}
					if (grid[i][j].direction == '<') {

						if (tmpGrid[i][j - 1].direction == '>' || tmpGrid[i][j - 1].direction == '<'
						                || tmpGrid[i][j - 1].direction == 'v' || tmpGrid[i][j - 1].direction == '^') {
							tmpGrid[i][j - 1].direction = 'X';
						} else if (tmpGrid[i][j - 1].direction == '-') {
							tmpGrid[i][j - 1].direction = '<';
							tmpGrid[i][j - 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j - 1].direction == '/') {
							tmpGrid[i][j - 1].direction = 'v';
							tmpGrid[i][j - 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j - 1].direction == '\\') {
							tmpGrid[i][j - 1].direction = '^';
							tmpGrid[i][j - 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j - 1].direction == '+') {
							tmpGrid[i][j - 1].direction = turnDir(grid[i][j].direction, grid[i][j].turn);
							tmpGrid[i][j - 1].turn = grid[i][j].turn + 1;
							tmpGrid[i][j].turn = 0;
						}
						tmpGrid[i][j].direction = this.originalGrid[i][j];
						if (tmpGrid[i][j - 1].direction == 'X') {
							tmpGrid[i][j - 1].direction = this.originalGrid[i][j - 1];
							grid[i][j - 1].direction = this.originalGrid[i][j - 1];

						}

					} else if (grid[i][j].direction == '>') {

						if (tmpGrid[i][j + 1].direction == '>' || tmpGrid[i][j + 1].direction == '<'
						                || tmpGrid[i][j + 1].direction == 'v' || tmpGrid[i][j + 1].direction == '^') {
							tmpGrid[i][j + 1].direction = 'X';
						} else if (tmpGrid[i][j + 1].direction == '-') {
							tmpGrid[i][j + 1].direction = '>';
							tmpGrid[i][j + 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j + 1].direction == '/') {
							tmpGrid[i][j + 1].direction = '^';
							tmpGrid[i][j + 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j + 1].direction == '\\') {
							tmpGrid[i][j + 1].direction = 'v';
							tmpGrid[i][j + 1].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i][j + 1].direction == '+') {
							tmpGrid[i][j + 1].direction = turnDir(grid[i][j].direction, grid[i][j].turn);
							tmpGrid[i][j + 1].turn = grid[i][j].turn + 1;
							tmpGrid[i][j].turn = 0;
						}
						tmpGrid[i][j].direction = this.originalGrid[i][j];
						if (tmpGrid[i][j + 1].direction == 'X') {
							tmpGrid[i][j + 1].direction = this.originalGrid[i][j + 1];
							grid[i][j + 1].direction = this.originalGrid[i][j + 1];

						}

					} else if (grid[i][j].direction == '^') {
						if (tmpGrid[i - 1][j].direction == '>' || tmpGrid[i - 1][j].direction == '<'
						                || tmpGrid[i - 1][j].direction == 'v' || tmpGrid[i - 1][j].direction == '^') {
							tmpGrid[i - 1][j].direction = 'X';
						} else if (tmpGrid[i - 1][j].direction == '|') {
							tmpGrid[i - 1][j].direction = '^';
							tmpGrid[i - 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i - 1][j].direction == '/') {
							tmpGrid[i - 1][j].direction = '>';
							tmpGrid[i - 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i - 1][j].direction == '\\') {
							tmpGrid[i - 1][j].direction = '<';
							tmpGrid[i - 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i - 1][j].direction == '+') {
							tmpGrid[i - 1][j].direction = turnDir(grid[i][j].direction, grid[i][j].turn);
							tmpGrid[i - 1][j].turn = grid[i][j].turn + 1;
							tmpGrid[i][j].turn = 0;
						}
						tmpGrid[i][j].direction = this.originalGrid[i][j];
						if (tmpGrid[i - 1][j].direction == 'X') {
							tmpGrid[i - 1][j].direction = this.originalGrid[i - 1][j];
							grid[i - 1][j].direction = this.originalGrid[i - 1][j];

						}

					} else if (grid[i][j].direction == 'v') {
						if (tmpGrid[i + 1][j].direction == '>' || tmpGrid[i + 1][j].direction == '<'
						                || tmpGrid[i + 1][j].direction == 'v' || tmpGrid[i + 1][j].direction == '^') {
							tmpGrid[i + 1][j].direction = 'X';
						} else if (tmpGrid[i + 1][j].direction == '|') {
							tmpGrid[i + 1][j].direction = 'v';
							tmpGrid[i + 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i + 1][j].direction == '/') {
							tmpGrid[i + 1][j].direction = '<';
							tmpGrid[i + 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i + 1][j].direction == '\\') {
							tmpGrid[i + 1][j].direction = '>';
							tmpGrid[i + 1][j].turn = grid[i][j].turn;
							tmpGrid[i][j].turn = 0;
						} else if (tmpGrid[i + 1][j].direction == '+') {
							tmpGrid[i + 1][j].direction = turnDir(grid[i][j].direction, grid[i][j].turn);
							tmpGrid[i + 1][j].turn = grid[i][j].turn + 1;
							tmpGrid[i][j].turn = 0;
						}
						tmpGrid[i][j].direction = this.originalGrid[i][j];
						if (tmpGrid[i + 1][j].direction == 'X') {
							tmpGrid[i + 1][j].direction = this.originalGrid[i + 1][j];
							grid[i + 1][j].direction = this.originalGrid[i + 1][j];

						}

					}
				}
			}
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					final char dir = tmpGrid[i][j].direction;
					final int turnTmp = tmpGrid[i][j].turn;

					if (dir == '^' || dir == 'v' || dir == '<' || dir == '>') {
						dirCount.put(dir, dirCount.get(dir) + 1);
						crash = j + "," + i;
						sumdir++;
					}
					grid[i][j].direction = dir;
					grid[i][j].turn = turnTmp;
				}
			}

			System.out.println("SOMME : " + sumdir + " TOUR : " + loops);
			
//			if (loops == 159 || loops == 160 || loops == 206 || loops == 117) {
//				 final StringBuilder builder = new StringBuilder();
//				 for (final Cart[] element : grid) {
//				 for (int j = 0; j < element.length; j++)// for each column
//				 {
//				 builder.append(element[j].direction + "");// append to the output string
//				 if (j < grid.length - 1) {
//				 builder.append("");// then add comma (if you don't like commas you can use spaces)
//				 }
//				 }
//				 builder.append("\n");// append new line at the end of the row
//				 }
//				 final BufferedWriter writer = new BufferedWriter(new FileWriter(
//				 "C:/dev/workspace/AdventOfCode/AdventOfCode/files/testMessage/" + loops + ".txt"));
//				 writer.write(builder.toString());// save the string representation of the board
//				 writer.close();
//
//			}
			

			loops++;

		}

		return crash;

	}
	
	public static char turnDir(final char direction, final int turn) {

		char newDirection = 'a';

		if (turn % 3 == 0) {

			if (direction == '<') {
				newDirection = 'v';
			} else if (direction == '>') {
				newDirection = '^';
			} else if (direction == '^') {
				newDirection = '<';
			} else if (direction == 'v') {
				newDirection = '>';
			}

		} else if (turn % 3 == 1) {

			newDirection = direction;

		} else if (turn % 3 == 2) {
			if (direction == '<') {
				newDirection = '^';
			} else if (direction == '>') {
				newDirection = 'v';
			} else if (direction == '^') {
				newDirection = '>';
			} else if (direction == 'v') {
				newDirection = '<';
			}

		}
		return newDirection;
	}
	
}
