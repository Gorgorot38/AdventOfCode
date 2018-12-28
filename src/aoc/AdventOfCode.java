package aoc;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

import aoc.aoc2018.Day16;

public class AdventOfCode {

	private static Path day4_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/guard.txt");
	private static Path day5_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/chemical.txt");
	private static Path day8_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/nodes.txt");
	private static Path day9_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/marbles.txt");
	private static Path day10_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/message.txt");
	private static Path day12_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/pots.txt");
	private static Path day13_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/carts.txt");
	private static Path day13_2018_test = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/cartsTest.txt");
	private static Path day15_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/field.txt");
	private static Path day16_part1_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/day16_part1.txt");




	public static void main(final String[] args) throws IOException, ParseException {

		final Day16 fromage = new Day16();
		
		
		Day16.findMap(Day16.part1(day16_part1_2018));
		
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
