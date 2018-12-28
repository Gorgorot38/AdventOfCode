package aoc.aoc2018;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import aoc.Utils;

public class Day16 {

	
	public static List<List<String>> part1(final Path path) throws IOException {
		
		final List<List<List<Integer>>> samples = buildList(path);
		final List<List<String>> whatMethodList = new ArrayList<>();
		
		samples.forEach(triple -> {
			
			int number = 0;
			final List<String> methodName = new ArrayList<>();
			
			if (triple.get(2).equals(addi(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("addi;" + number);
			}
			if (triple.get(2).equals(addr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("addr;" + number);

			}
			if (triple.get(2).equals(bani(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("bani;" + number);

			}
			if (triple.get(2).equals(banr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("banr;" + number);

			}
			if (triple.get(2).equals(bori(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("bori;" + number);

			}
			if (triple.get(2).equals(borr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("borr;" + number);

			}
			if (triple.get(2).equals(eqir(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("eqir;" + number);

			}
			if (triple.get(2).equals(eqri(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("eqri;" + number);

			}
			if (triple.get(2).equals(eqrr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("eqrr;" + number);

			}
			if (triple.get(2).equals(gtir(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("gtir;" + number);

			}
			if (triple.get(2).equals(gtri(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("gtri;" + number);

			}
			if (triple.get(2).equals(gtrr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("gtrr;" + number);

			}
			if (triple.get(2).equals(muli(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("muli;" + number);

			}
			if (triple.get(2).equals(mulr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("mulr;" + number);

			}
			if (triple.get(2).equals(seti(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("seti;" + number);

			}
			if (triple.get(2).equals(setr(triple.get(0), triple.get(1)))) {
				number = triple.get(1).get(0);
				methodName.add("setr;" + number);

			}
			whatMethodList.add(methodName);
		});
		
		whatMethodList.forEach(lists -> {
			lists.forEach(word -> System.out.println(word));
		});
		
		return whatMethodList;
	}
	
	
	public static HashMap<Integer, String> findMap(final List<List<String>> allPossibles) {
		
		final HashMap<String, List<String>> finalMap = new HashMap<>();
		final HashMap<String, List<String>> tmpMap = new HashMap<>();
		final HashMap<Integer, String> reverseMap = new HashMap<>();

		boolean goOn = true;
		
		allPossibles.forEach(lists -> {
			lists.forEach(word -> {
				final String[] current = word.split(";");
				if(finalMap.containsKey(current[0])) {
					final List<String> currentList = finalMap.get(current[0]);
					if(!currentList.contains(current[1])) {
						currentList.add(current[1]);
					}
					finalMap.put(current[0], currentList);
				} else {
					final List<String> currentList = new ArrayList<>();
					currentList.add(current[1]);
					finalMap.put(current[0], currentList);
				}
			});
		});
		
		while(goOn) {
			goOn = false;
			
			finalMap.forEach((key, value) -> {
				tmpMap.put(key, value);
			});
			
			finalMap.forEach((key, value) -> {
				String sure = "";
				if (value.size() == 1) {
					sure = value.get(0);
					tmpMap.remove(key);
					for (final Entry<String, List<String>> e : tmpMap.entrySet()) {
						e.getValue().remove(sure);
					}
				}
			});
			
			tmpMap.forEach((key, value) -> {
				finalMap.put(key, value);
			});
			
			for (final Entry<String, List<String>> e : finalMap.entrySet()) {
				if(e.getValue().size() != 1) {
					goOn = true;
				}
			}
		}
		
		finalMap.forEach((method, num) -> {
			reverseMap.put(Integer.parseInt(num.get(0)), method);
		});
		
		return reverseMap;
	}
	
	public static void part2(final Path path) throws IOException {
		
		final List<List<String>> allPossibles = part1(path);
		final HashMap<Integer, String> finalMap = findMap(allPossibles);
		
		
		
		
	}
	
	public static List<List<List<Integer>>> buildList(final Path path) throws IOException{
		
		final List<List<List<Integer>>> finalList = new ArrayList<>();
		final String[] fileToArray = Utils.convertFileArray(path);
		
		for (int i = 0; i < fileToArray.length; i++) {
			
			final List<List<Integer>> tmpList = new ArrayList<>();
			
			if(fileToArray[i].startsWith("Before")) {
				String toInt = fileToArray[i].replace("Before: [", "");
				toInt = toInt.replace("]", "");
				toInt = toInt.replaceAll(", ", "");
				final String[] tmpArray = toInt.split("");
				final List<Integer> toAdd = new ArrayList<>();
				for (final String element : tmpArray) {
					toAdd.add(Integer.parseInt(element));
				}
				tmpList.add(toAdd);
			}
			i++;
			final String[] tmpArraybis = fileToArray[i].split(" ");
			final List<Integer> toAddbis = new ArrayList<>();
			for (final String element : tmpArraybis) {
				toAddbis.add(Integer.parseInt(element));
			}
			tmpList.add(toAddbis);

			i++;
			if(fileToArray[i].startsWith("After")) {
				String toInt = fileToArray[i].replace("After:  [", "");
				toInt = toInt.replace("]", "");
				toInt = toInt.replaceAll(", ", "");
				final String[] tmpArray = toInt.split("");
				final List<Integer> toAdd = new ArrayList<>();
				for (final String element : tmpArray) {
					toAdd.add(Integer.parseInt(element));
				}
				tmpList.add(toAdd);
			}

			i++;
			
			finalList.add(tmpList);
			
		}
	
		return finalList;
	}
	
	
	public static List<Integer> addr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		
		final int C = registers.get(values.get(1)) + registers.get(values.get(2));
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		
		return output;
		
	}
	
	public static List<Integer> addi(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		
		final int C = registers.get(values.get(1)) + values.get(2);
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		
		
		return output;
	}

	public static List<Integer> mulr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) * registers.get(values.get(2));
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> muli(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) * values.get(2);
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> banr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) & registers.get(values.get(2));
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> bani(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) & values.get(2);
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> borr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) | registers.get(values.get(2));
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> bori(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int C = registers.get(values.get(1)) | values.get(2);
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> setr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int A = registers.get(values.get(1));
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(A);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> seti(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		final int A = values.get(1);
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(A);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> gtir(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (values.get(1) > registers.get(values.get(2))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		
		
		return output;
		
	}
	public static List<Integer> gtri(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (values.get(2) < registers.get(values.get(1))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> gtrr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (registers.get(values.get(2)) < registers.get(values.get(1))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> eqir(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (values.get(1) == registers.get(values.get(2))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> eqri(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (values.get(2) == registers.get(values.get(1))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
		
	}
	public static List<Integer> eqrr(final List<Integer> registers, final List<Integer> values){
		
		final List<Integer> output = new ArrayList<>();
		int C = 0;
		if (registers.get(values.get(2)) == registers.get(values.get(1))) {
			C = 1;
		}
		
		for (int i = 0; i < registers.size(); i++) {
			
			if (values.get(3) == i) {
				output.add(C);
			} else {
				output.add(registers.get(i));
			}
		}		

		return output;
	}

}
