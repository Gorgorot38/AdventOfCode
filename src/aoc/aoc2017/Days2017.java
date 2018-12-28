package aoc.aoc2017;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Days2017 {

	int i = 2;
	int y = 0;

	public int compute(final String captcha) {

		stringToList(captcha).forEach(c -> {

			if (c - '0' == this.i) {
				this.y = this.y + c - '0';
			}
			this.i = c - '0';
		});

		return this.y;
	}

	public static List<Character> stringToList(final String captcha) {
		final List<Character> list = new ArrayList<>();
		for (final char c : captcha.toCharArray()) {
			list.add(c);
		}
		return list;
	}

	public static char[][] convertFileMatrix(final Path path) throws IOException {
		final char[][] array2d = Files.lines(path).map(String::toCharArray).toArray(char[][]::new);
		return array2d;
	}

	public static String[] convertFileArray(final Path path) throws IOException {
		final String[] passphrase = Files.lines(path).toArray(String[]::new);
		return passphrase;
	}

	public int countPass(final String[] passphrases) {
		int count = 0;

		for (final String passphrase2 : passphrases) {
			final String[] passphrase = passphrase2.split(" ");
			if (!isDuplicate(passphrase)) {
				count++;
			}
		}

		return count;
	}

	public int countCycles(final String[] cycles) {
		int count = 0;
		int index = 0;
		final int[] newCycles = convertStringArrToInt(cycles);
		boolean test = true;

		while (test) {

			final int value = newCycles[index];

			index = newCycles[index] + index;
			count++;

			if (newCycles[index] != 10000) {
				if (Math.abs(newCycles[index - value]) < 3) {
					newCycles[index - value] = newCycles[index - value] + 1;
				} else {
					if (newCycles[index - value] >= 0) {
						newCycles[index - value] = newCycles[index - value] - 1;
					} else {
						newCycles[index - value] = newCycles[index - value] + 1;

					}
				}
			}

			if (newCycles[index] == 10000) {
				test = false;
			}
		}

		return count;

	}

	public static int[] convertStringArrToInt(final String[] cycles) {

		final int[] cyclesInt = new int[cycles.length * 2];

		for (int i = 0; i < cycles.length; i++) {
			cyclesInt[i] = Integer.parseInt(cycles[i]);
		}

		for (int j = cycles.length; j < cyclesInt.length; j++) {
			cyclesInt[j] = 10000;
		}
		return cyclesInt;
	}

	public static int[][] convertStringMatToInt(final String[][] corruption) {

		final int[][] corrInt = new int[corruption.length][corruption[0].length];

		for (int i = 0; i < corruption.length; i++) {
			for (int j = 0; j < corruption[0].length; j++) {
				corrInt[i][j] = Integer.parseInt(corruption[i][j]);
			}

		}
		return corrInt;
	}

	public boolean isDuplicate(final String[] passphrase) {

		boolean dup = false;
		final HashMap<String, Boolean> map = new HashMap<>();

		for (this.i = 0; this.i < passphrase.length; this.i++) {
			final char tempArray[] = passphrase[this.i].toCharArray();

			Arrays.sort(tempArray);

			passphrase[this.i] = new String(tempArray);
		}

		for (final String word : passphrase) {
			if (map.containsKey(word)) {
				dup = true;
				break;
			}
			map.put(word, dup);
		}

		return dup;
	}

	public List<String> findWord(final char[][] matrix) {

		final List<String> word = new ArrayList<>();
		int a = 0;
		int o = 0;
		int count = 0;

		while (o >= 0) {

			if (matrix[a][o] == "|".charAt(0)) {
				count++;
				break;
			}
			o++;
		}

		while (matrix[a][o] == "|".charAt(0)) {
			a++;
		}

		while (true) {

			if (matrix[a][o + 1] == "-".charAt(0)) {
				o++;
				while (matrix[a][o] != "+".charAt(0)) {
					if (isAlpha(matrix[a][o])) {
						word.add(String.valueOf(matrix[a][o]));
						count++;
					}
					o++;
					count++;
				}
			} else if (matrix[a][o - 1] == "-".charAt(0)) {
				o--;
				while (matrix[a][o] != "+".charAt(0)) {
					if (matrix[a][o] == "M".charAt(0)) {
						word.add(String.valueOf(matrix[a][o]));
						count++;
						break;
					}
					if (isAlpha(matrix[a][o])) {
						word.add(String.valueOf(matrix[a][o]));
					}
					o--;
					count++;
				}
			}

			count++;

			if (matrix[a + 1][o] == "|".charAt(0)) {
				a++;
				while (matrix[a][o] != "+".charAt(0)) {
					if (isAlpha(matrix[a][o])) {
						word.add(String.valueOf(matrix[a][o]));
					}
					a++;
					count++;
				}
			} else if (matrix[a - 1][o] == "|".charAt(0)) {
				a--;
				while (matrix[a][o] != "+".charAt(0)) {
					if (isAlpha(matrix[a][o])) {
						word.add(String.valueOf(matrix[a][o]));
					}
					a--;
					count++;
				}
			}
			count++;

			if (matrix[a][o] == "M".charAt(0)) {
				break;
			}

		}

		// System.out.println(matrix[a][o]);
		System.out.println(count);

		return word;
	}

	public static boolean isAlpha(final char letter) {

		return letter == "A".charAt(0) || letter == "B".charAt(0) || letter == "C".charAt(0) || letter == "D".charAt(0)
		                || letter == "E".charAt(0) || letter == "F".charAt(0) || letter == "G".charAt(0)
		                || letter == "H".charAt(0) || letter == "J".charAt(0) || letter == "K".charAt(0)
		                || letter == "L".charAt(0) || letter == "M".charAt(0) || letter == "N".charAt(0)
		                || letter == "O".charAt(0) || letter == "P".charAt(0) || letter == "Q".charAt(0)
		                || letter == "R".charAt(0) || letter == "S".charAt(0) || letter == "T".charAt(0)
		                || letter == "U".charAt(0) || letter == "V".charAt(0) || letter == "W".charAt(0)
		                || letter == "X".charAt(0) || letter == "Y".charAt(0) || letter == "Z".charAt(0)
		                || letter == "I".charAt(0);
	}

}
