import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Day5_2018 {

	public static NavigableMap<Integer, String> arrayToMap(final String[] chemical) {
		final NavigableMap<Integer, String> chemicalMap = new TreeMap<>();

		for (int i = 0; i < chemical.length; i++) {
			chemicalMap.put(i, chemical[i]);
		}

		return chemicalMap;
	}
	
	public static int findChemicalLength(final char alphabet) throws IOException {

		final String[] chemical = Utils.convertFileArray(AdventOfCode.getDay5_2018());
		final String[] formatChem = chemical[0].split("");
		final NavigableMap<Integer, String> chemicalMap = arrayToMap(formatChem);

		final NavigableMap<Integer, String> tmpMap = new TreeMap<>();
		
		chemicalMap.forEach((id, letter) -> {
			tmpMap.put(id, letter);
		});
		
		chemicalMap.forEach((id, letter) -> {
			if (letter.equalsIgnoreCase(Character.toString(alphabet))) {
				tmpMap.remove(id);
			}
		});

		chemicalMap.clear();
		tmpMap.forEach((id, letter) -> {
			chemicalMap.put(id, letter);
		});

		boolean notOver = true;
		while (notOver) {
			for (final Entry<Integer, String> e : chemicalMap.entrySet()) {
				final Map.Entry<Integer, String> next = chemicalMap.higherEntry(e.getKey());
				notOver = false;
				if (next != null && e.getValue().equals(e.getValue().toLowerCase())
				                && e.getValue().toUpperCase().equals(next.getValue())) {
					tmpMap.remove(e.getKey());
					tmpMap.remove(next.getKey());
					notOver = true;
					break;
				} else if (next != null && e.getValue().equals(e.getValue().toUpperCase())
				                && e.getValue().toLowerCase().equals(next.getValue())) {
					tmpMap.remove(e.getKey());
					tmpMap.remove(next.getKey());
					notOver = true;
					break;
				}
			}

			chemicalMap.clear();
			tmpMap.forEach((id, letter) -> {
				chemicalMap.put(id, letter);
			});
		}

		return tmpMap.size();
	}
	
	public static int findShortestPol() throws IOException {

		int shortest = 11894;

		for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {

			final int currentShort = findChemicalLength(alphabet);
			if (currentShort <= shortest) {
				shortest = currentShort;
			}
		}
		return shortest;
	}
	
}
