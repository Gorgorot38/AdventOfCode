import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day7_2018 {

	
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
				final List<String> tempList = Arrays.asList(element[0]);
				stepsMap.put(element[1], tempList);
			}
		}
		
		return stepsMap;
		
	}
}
