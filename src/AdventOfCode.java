import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;

public class AdventOfCode {

	private static Path day4_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/guard.txt");
	private static Path day5_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/chemical.txt");
	private static Path day8_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/nodes.txt");
	private static Path day9_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/marbles.txt");
	private static Path day10_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/message.txt");
	private static Path day12_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/pots.txt");
	private static Path day13_2018 = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/carts.txt");
	private static Path day13_2018_test = Paths.get("C:/dev/workspace/AdventOfCode/AdventOfCode/files/cartsTest.txt");
	



	public static void main(final String[] args) throws IOException, ParseException {

		final Day14_2018 fromage = new Day14_2018();
		
		fromage.part2();		

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
