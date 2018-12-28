package aoc.aoc2018;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aoc.Utils;

public class Day1_2 {


	public static int countFrequency(final int[] frequencies) {

		int count = 0;

		for (final int frequencie : frequencies) {
			count = count + frequencie;
		}

		return count;

	}

	public static int computeChecksum(final String[] codeList) {

		int checksum = 0;
		int sumOf2 = 0;
		int sumOf3 = 0;

		final HashMap<Integer, Integer> checksumMap = new HashMap<>();
		final HashMap<Character, Integer> codeMap = new HashMap<>();

		for (final String element : codeList) {
			final List<Character> tempList = Utils.stringToList(element);

			codeMap.clear();
			checksumMap.clear();

			checksumMap.put(2, 0);
			checksumMap.put(3, 0);

			tempList.forEach(letter -> {
				if (codeMap.containsKey(letter)) {
					codeMap.put(letter, codeMap.get(letter) + 1);
				} else {
					codeMap.put(letter, 1);
				}
			});

			codeMap.forEach((key, value) -> {
				if (value == 2 || value == 3) {
					checksumMap.put(value, checksumMap.get(value) + 1);
				}
			});

			if (checksumMap.get(2) > 0) {
				sumOf2++;
			}

			if (checksumMap.get(3) > 0) {
				sumOf3++;
			}

		}

		checksum = sumOf2 * sumOf3;

		return checksum;

	}

	public static int remFreq(final int[] frequencies) {

		int value = 0;
		final HashMap<Integer, Integer> map = new HashMap<>();
		map.put(value, 0);
		boolean test = true;
		int i = 0;

		while (test) {
			for (i = 0; i < frequencies.length; i++) {
				value = value + frequencies[i];

				if (map.containsKey(value)) {
					test = false;
					break;
				}
				map.put(value, 0);
			}
		}
		return value;
	}

	public static List<Character> findDiff(final String[] ids) {

		final List<Character> match = new ArrayList<>();
		int count = 0;
		boolean test = true;

		for (int i = 0; i < ids.length && test; i++) {

			final List<Character> tempList1 = Utils.stringToList(ids[i]);

			for (int j = 0; j < ids.length && test; j++) {

				final List<Character> tempList2 = Utils.stringToList(ids[j]);

				if (!tempList1.equals(tempList2)) {

					for (int k = 0; k < tempList1.size(); k++) {

						if (tempList1.get(k) != tempList2.get(k)) {
							count++;
						} else {
							match.add(tempList1.get(k));
						}
					}
					if (count > 1) {
						match.clear();
						count = 0;
					} else {
						test = false;
						count = 0;
					}
				}
			}
		}
		return match;
	}


}
