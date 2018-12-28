package aoc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static String[] convertFileArray(final Path path) throws IOException {
		final String[] passphrase = Files.lines(path).toArray(String[]::new);
		return passphrase;
	}

	public static int[] convertStringArrToInt(final String[] cycles) {

		final int[] cyclesInt = new int[cycles.length];

		for (int i = 0; i < cycles.length; i++) {
			cyclesInt[i] = Integer.parseInt(cycles[i]);
		}

		return cyclesInt;
	}

	public static List<Character> stringToList(final String captcha) {
		final List<Character> list = new ArrayList<>();
		for (final char c : captcha.toCharArray()) {
			list.add(c);
		}
		return list;
	}
	
	public static String getStringRepresentation(final List<Character> list) {
		final StringBuilder builder = new StringBuilder(list.size());
		for (final Character ch : list) {
			builder.append(ch);
		}
		return builder.toString();
	}

	public static char[][] convertFileMatrix(final Path path) throws IOException {
		final char[][] array2d = Files.lines(path).map(String::toCharArray).toArray(char[][]::new);
		return array2d;
	}

}
