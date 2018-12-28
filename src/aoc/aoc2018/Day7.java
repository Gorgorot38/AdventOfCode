package aoc.aoc2018;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import aoc.Utils;

import java.util.TreeMap;

public class Day7 {

	
	public static String[][] formatSteps(final Path path) throws IOException{
		
		final String[] input = Utils.convertFileArray(path);
		
		for (int i = 0; i < input.length; i++) {
			input[i] = input[i].replace(" must be finished before step ", "");
			input[i] = input[i].replace("Step ", "");
			input[i] = input[i].replace(" can begin.", "");
		}
		
		final String[][] formatedInput = new String [input.length][2];
		
		for (int i = 0; i < input.length; i++) {
			formatedInput[i] = input[i].split("");
		}
		
		return formatedInput;
		
	}
	
	public static HashMap<String, List<String>> getStepsMap(final Path path) throws IOException{
		
		final String[][] formatedInput = formatSteps(path);
		final HashMap<String, List<String>> stepsMap = new HashMap<>();
		
		for (final String[] element : formatedInput) {
			if (!stepsMap.containsKey(element[1])) {
				final List<String> tempList = new ArrayList<>();
				tempList.add(element[0]);
				stepsMap.put(element[1], tempList);
			}
			else {
				final List<String> tempList = stepsMap.get(element[1]);
				tempList.add(element[0]);
				stepsMap.put(element[1], tempList);
			}
		}
				
		return stepsMap;
		
	}
	
	public static List<String> findWord(final Path path) throws IOException{
		
		final HashMap<String, List<String>> stepsMap = getStepsMap(path);
		final List<String> word = new ArrayList<>();
		boolean isNotFinished = true;
		
		while(isNotFinished) {
			
			for (char letter = 'A'; letter <= 'Z'; letter++) {

				if (stepsMap.containsKey(Character.toString(letter))
				                && stepsMap.get(Character.toString(letter)).isEmpty()
				                && !word.contains(Character.toString(letter))) {
					word.add(Character.toString(letter));

					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(Character.toString(letter))) {
							e.getValue().remove(Character.toString(letter));
						}
					}

					break;

				} else if (!stepsMap.containsKey(Character.toString(letter))
				                && !word.contains(Character.toString(letter))) {
					word.add(Character.toString(letter));
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(Character.toString(letter))) {
							e.getValue().remove(Character.toString(letter));
						}
					}
					break;
				}

			}
			
			for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
				if(e.getValue().isEmpty() && word.size() == 26) {
					isNotFinished = false;
				} else {
					isNotFinished = true;
					break;
				}
			}

		}
		return word;
		
	}
	
	public static Map<String, Integer> getAvailableSteps(final HashMap<String, List<String>> stepsMap) {

		final Map<String, Integer> availSteps = new TreeMap<>();

		for (char letter = 'A'; letter <= 'Z'; letter++) {

			if (stepsMap.containsKey(Character.toString(letter))
			                && stepsMap.get(Character.toString(letter)).isEmpty()) {
				availSteps.put(Character.toString(letter), Character.getNumericValue(letter) - 9 + 60);
			} else if (!stepsMap.containsKey(Character.toString(letter))) {
				availSteps.put(Character.toString(letter), Character.getNumericValue(letter) - 9 + 60);
			}
		}
		return availSteps;
	}
	
	public static int findTime(final Path path) throws IOException {
		
		int time = 0;
		final HashMap<String, List<String>> stepsMap = getStepsMap(path);
		final List<String> word = new ArrayList<>();
		final List<String> seen = new ArrayList<>();
		boolean isNotFinished = true;
		int timeW1 = 1;
		int timeW2 = 1;
		int timeW3 = 1;
		int timeW4 = 1;
		int timeW5 = 1;
		boolean hasStarted1 = false;
		boolean hasStarted2 = false;
		boolean hasStarted3 = false;
		boolean hasStarted4 = false;
		boolean hasStarted5 = false;
		String stepW1 = "";
		String stepW2 = "";
		String stepW3 = "";
		String stepW4 = "";
		String stepW5 = "";


		while (isNotFinished) {
			
			final Map<String, Integer> availSteps = getAvailableSteps(stepsMap);
			final Map<String, Integer> tmpAvailSteps = new TreeMap<>();
			availSteps.forEach((key, value) -> tmpAvailSteps.put(key, value));
			
			if(!availSteps.isEmpty()) {
				for (final Entry<String, Integer> e : availSteps.entrySet()) {
					if (seen.contains(e.getKey())) {
						tmpAvailSteps.remove(e.getKey());
					}
				}
			}

			availSteps.clear();
			tmpAvailSteps.forEach((key, value) -> availSteps.put(key, value));
			
			if(timeW1 == 1) {
				if (hasStarted1) {
					word.add(stepW1);
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(stepW1)) {
							e.getValue().remove(stepW1);
						}
					}
				}
				if (!availSteps.isEmpty()) {
					for (char letter = 'A'; letter <= 'Z'; letter++) {
						if (availSteps.containsKey(Character.toString(letter))) {
							stepW1 = Character.toString(letter);
							timeW1 = availSteps.get(Character.toString(letter));
							availSteps.remove(Character.toString(letter));
							hasStarted1 = true;
							seen.add(stepW1);
							break;
						}
					}
				} else {
					timeW1 = 2;
					hasStarted1 = false;
				}
			}
			
			if(timeW2 == 1) {
				if (hasStarted2) {
					word.add(stepW2);
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(stepW2)) {
							e.getValue().remove(stepW2);
						}
					}
				}
				if (!availSteps.isEmpty()) {
					for (char letter = 'A'; letter <= 'Z'; letter++) {
						if (availSteps.containsKey(Character.toString(letter))) {
							stepW2 = Character.toString(letter);
							timeW2 = availSteps.get(Character.toString(letter));
							availSteps.remove(Character.toString(letter));
							hasStarted2 = true;
							seen.add(stepW2);
							break;
						}
					}
				} else {
					timeW2 = 2;
					hasStarted2 = false;
				}

			}
			
			if(timeW3 == 1) {
				if (hasStarted3) {
					word.add(stepW3);
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(stepW3)) {
							e.getValue().remove(stepW3);
						}
					}
				}
				if (!availSteps.isEmpty()) {
					for (char letter = 'A'; letter <= 'Z'; letter++) {
						if (availSteps.containsKey(Character.toString(letter))) {
							stepW3 = Character.toString(letter);
							timeW3 = availSteps.get(Character.toString(letter));
							availSteps.remove(Character.toString(letter));
							hasStarted3 = true;
							seen.add(stepW3);
							break;
						}
					}
				} else {
					timeW3 = 2;
					hasStarted3 = false;
				}
				
			}
			
			if(timeW4 == 1) {
				if (hasStarted4) {
					word.add(stepW4);
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(stepW4)) {
							e.getValue().remove(stepW4);
						}
					}
				}
				if (!availSteps.isEmpty()) {
					for (char letter = 'A'; letter <= 'Z'; letter++) {
						if (availSteps.containsKey(Character.toString(letter))) {
							stepW4 = Character.toString(letter);
							timeW4 = availSteps.get(Character.toString(letter));
							availSteps.remove(Character.toString(letter));
							hasStarted4 = true;
							seen.add(stepW4);
							break;
						}
					}
				} else {
					timeW4 = 2;
					hasStarted4 = false;
				}

				for (char letter = 'A'; letter <= 'Z'; letter++) {
					if (availSteps.containsKey(Character.toString(letter))) {
						stepW4 = Character.toString(letter);
						timeW4 = availSteps.get(Character.toString(letter));
						availSteps.remove(Character.toString(letter));
						hasStarted4 = true;
						break;
					}
				}
			}
			
			if(timeW5 == 1) {
				if (hasStarted5) {
					word.add(stepW5);
					for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
						if (e.getValue().contains(stepW5)) {
							e.getValue().remove(stepW5);
						}
					}
				}
				if (!availSteps.isEmpty()) {
					for (char letter = 'A'; letter <= 'Z'; letter++) {
						if (availSteps.containsKey(Character.toString(letter))) {
							stepW5 = Character.toString(letter);
							timeW5 = availSteps.get(Character.toString(letter));
							availSteps.remove(Character.toString(letter));
							hasStarted5 = true;
							seen.add(stepW5);
							break;
						}
					}
				} else {
					timeW5 = 2;
					hasStarted5 = false;
				}

			}

			time++;
			timeW1--;
			timeW2--;
			timeW3--;
			timeW4--;
			timeW5--;
			
			for (final Entry<String, List<String>> e : stepsMap.entrySet()) {
				if(e.getValue().isEmpty() && word.size() == 26) {
					isNotFinished = false;
				} else {
					isNotFinished = true;
					break;
				}
			}
		}
		
		return time;
	}
	
}
