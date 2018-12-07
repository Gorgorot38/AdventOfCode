import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day4_2018 {

	
	public static int findGuardsMin(final Path guardInfo) throws IOException {
		
		final int count = 0;
		
		final String[] firstGuardsArray = Utils.convertFileArray(guardInfo);
		
		return count;
		
	}
	
	public static String formatString(final String stringToFormat) {

		String formatedString = stringToFormat;

		formatedString = formatedString.replace("[", "");
		formatedString = formatedString.replace("wakes up", "w");
		formatedString = formatedString.replace("falls asleep", "f");
		formatedString = formatedString.replace("Guard #", "");
		formatedString = formatedString.replace(" begins shift", "");
		formatedString = formatedString.replace("] ", "]");

		return formatedString;
	}
	
	public static LinkedHashMap<Date, String> formatArray(final String[] input) throws ParseException {

		final HashMap<Date, String> unsortedMap = new HashMap<>();

		for (int i = 0; i < input.length; i++) {
			input[i] = formatString(input[i]);
		}

		final String[][] formatedArray = new String[input.length][2];

		for (int j = 0; j < input.length; j++) {
			final String[] lineArray = input[j].split("]");
			for (int k = 0; k < lineArray.length; k++) {
				formatedArray[j][k] = lineArray[k];
			}
		}

		for (final String[] element : formatedArray) {
			final SimpleDateFormat stringDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			final Date date = stringDate.parse(element[0]);
			unsortedMap.put(date, element[1]);
		}

		final LinkedHashMap<Date, String> sortedMap = unsortedMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
		                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
		                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		return sortedMap;

	}
	
	public static LinkedHashMap<Date, String> getOneGuardMap(final LinkedHashMap<Date, String> allShifts){
		
		final LinkedHashMap<Date, String> oneGuardMap = new LinkedHashMap<>();
		String id = "";
		final Entry<String, Long> theOne = findMostSleepy(allShifts);
		
		for (final Map.Entry<Date, String> e : allShifts.entrySet()) {
			if (!e.getValue().equals("f") && !e.getValue().equals("w")) {
				id = e.getValue();
			}
			
			if (id.equals(theOne.getKey())) {
				oneGuardMap.put(e.getKey(), e.getValue());
			}
		}
		
		return oneGuardMap;
		
	}
	
	
	public static LinkedHashMap<Date, String> getMinMap(final LinkedHashMap<Date, String> oneGuardMap) {

		final LinkedHashMap<Date, String> minMap = new LinkedHashMap<>();

		oneGuardMap.forEach((key, value) -> {
			minMap.put(key, value);
		});
		
		oneGuardMap.forEach((key, value) -> {
			if (!value.equals("w") && !value.equals("f")) {
				minMap.remove(key);
			}
		});
		return minMap;
	}
	
	@SuppressWarnings("deprecation")
	public static Entry<Integer, Integer> fillMinMap(final LinkedHashMap<Date, String> minMap) {

		final LinkedHashMap<Integer, Integer> allMinMap = new LinkedHashMap<>();
		int fall = 0;
		int wake = 0;

		for (int i = 0; i < 60; i++) {
			allMinMap.put(i, 0);
		}
		
		
		for (final Entry<Date, String> e : minMap.entrySet()) {
			if (e.getValue().equals("f")) {
				fall = e.getKey().getMinutes();
			}

			if (e.getValue().equals("w")) {
				wake = e.getKey().getMinutes();

				for (int j = fall; j < wake; j++) {
					allMinMap.put(j, allMinMap.get(j) + 1);
				}
			}
		}

		Map.Entry<Integer, Integer> maxEntry = null;

		for (final Entry<Integer, Integer> entry : allMinMap.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

	
		return maxEntry;

	}
		
	
	public static Entry<String, Long> findMostSleepy(final LinkedHashMap<Date, String> schedule) {

		String id = "";
		final NavigableMap<Date, String> tempMap = new TreeMap<>();
		final HashMap<String, Long> mapOfTime = new HashMap<>();

		schedule.forEach((timestamp, value) -> {
			tempMap.put(timestamp, value);
		});

		for (final Map.Entry<Date, String> e : tempMap.entrySet()) {
			final Map.Entry<Date, String> next = tempMap.higherEntry(e.getKey()); // next
			if (!e.getValue().equals("f") && !e.getValue().equals("w")) {
				id = e.getValue();
			}

			if (!e.getValue().equals("f") && !e.getValue().equals("w") && !mapOfTime.containsKey(e.getValue())) {
				mapOfTime.put(e.getValue(), (long) 0);
			}

			if (e.getValue().equals("f")) {
				final long minute = (next.getKey().getTime() - e.getKey().getTime()) / (60 * 1000);
				mapOfTime.put(id, minute + mapOfTime.get(id));
			}
		}

		Map.Entry<String, Long> maxEntry = null;

		for (final Map.Entry<String, Long> entry : mapOfTime.entrySet()) {
			if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
				maxEntry = entry;
			}
		}

		return maxEntry;

	}
	
}
