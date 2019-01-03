package aoc;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import aoc.aoc2018.Day17;

public class AdventOfCode {

	private static Path day4_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/waterfall.txt");
	private static Path day5_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/waterfall.txt");
	private static Path day8_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/waterfall.txt");
	private static Path day9_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/waterfall.txt");

	private static Path day17 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/waterfall.txt");






	public static void main(final String[] args) throws IOException, ParseException {

		final Day17 fromage = new Day17();
		
		
		fromage.part1(day17, 1, 100);
		
//		final List<Integer> lol = new ArrayList<>();
//		final List<Integer> lol2 = new ArrayList<>();
//
//		lol.add(3);
//		lol.add(2);
//		lol.add(1);
//		lol.add(1);
//		lol2.add(9);
//		lol2.add(2);
//		lol2.add(1);
//		lol2.add(2);
//
//		fromage.mulr(lol, lol2).forEach(res -> System.out.println(res));
//		System.out.println(5&7);

	}

	/**
	 * @return the day4_2018
	 */
	public static Path getDay4_2018() {
		return day4_2018;
	}

	/**
	 * @param day4_2018
	 *            the day4_2018 to set
	 */
	public static void setDay4_2018(final Path day4_2018) {
		AdventOfCode.day4_2018 = day4_2018;
	}

	/**
	 * @return the day5_2018
	 */
	public static Path getDay5_2018() {
		return day5_2018;
	}

	/**
	 * @param day4_2018
	 *            the day4_2018 to set
	 */
	public static void setDay5_2018(final Path day5_2018) {
		AdventOfCode.day5_2018 = day5_2018;
	}

	/**
	 * @return the day8_2018
	 */
	public static Path getDay8_2018() {
		return day8_2018;
	}

	/**
	 * @param day8_2018 the day8_2018 to set
	 */
	public static void setDay8_2018(final Path day8_2018) {
		AdventOfCode.day8_2018 = day8_2018;
	}

	/**
	 * @return the day9_2018
	 */
	public static Path getDay9_2018() {
		return day9_2018;
	}

	/**
	 * @param day9_2018 the day9_2018 to set
	 */
	public static void setDay9_2018(final Path day9_2018) {
		AdventOfCode.day9_2018 = day9_2018;
	}

}
