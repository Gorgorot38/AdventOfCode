import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Day3_2018 {

	public static String formatString(final String something) {

		String formatedString = something;

		formatedString = formatedString.replace(" ", "");
		formatedString = formatedString.replace("#", "");
		formatedString = formatedString.replace("@", " ");
		formatedString = formatedString.replace(",", " ");
		formatedString = formatedString.replace(":", " ");
		formatedString = formatedString.replace("x", " ");


		return formatedString;
	}

	
	public static int countOverlap(final Path fabricInfos) throws IOException {

		int count = 0;

		final String[] firstArray = Utils.convertFileArray(fabricInfos);

		final String[][] fabricArea = buildFormatedArray(firstArray);
		
		final String[][] overlaping = buildFabricArray(fabricArea);
		
		
		for (int i = 0; i < overlaping.length; i++){
			for(int j = 0; j < overlaping[i].length; j++){
				if(overlaping[i][j] == "X"){
					count++;
				}
			}
		}
		
		return count;
	}
	
	public static String findOverlap(final Path fabricInfos) throws IOException{
		
		String id = "";
		boolean test = true;

		final String[] firstArray = Utils.convertFileArray(fabricInfos);

		final String[][] fabricArea = buildFormatedArray(firstArray);
		
		final String[][] overlaping = buildFabricArray(fabricArea);
		
		
		for (int i = 0; i < fabricArea.length; i++){
			
			final int abs = Integer.parseInt(fabricArea[i][2]);
			final int ord = Integer.parseInt(fabricArea[i][1]);
			final int wide = Integer.parseInt(fabricArea[i][3]);
			final int tall = Integer.parseInt(fabricArea[i][4]);
			final String currentId = fabricArea[i][0];
			boolean currentTest = false;

			
			for (int j = abs; j < abs + tall; j++){
				for(int k = ord; k < ord + wide; k++){
					if(overlaping[j][k] != currentId){
						test = false;
						currentTest = true;
						break;
					}
				}
				if (currentTest){
					break;
				}
			}
			
			id = currentId;
			
			if(!currentTest){
				break;
			}
		}
		
		return id;
	}
	
	public static String[][] buildFormatedArray(final String[] input){
		
		for (int i = 0; i < input.length; i++) {
			input[i] = formatString(input[i]);
		}
		final String[][] fabricArea = new String[input.length][input[0].split(" ").length];

		for (int j = 0; j < input.length; j++) {
			final String[] lineArray = input[j].split(" ");
			for (int k = 0; k < lineArray.length; k++) {
				fabricArea[j][k] = lineArray[k];
			}
		}

		
		return fabricArea;
	}
	
	public static String[][] buildFabricArray(final String[][] fabricArea) {
		final String[][] overlaping = new String[1500][1500];

		for (int i = 0; i < fabricArea.length; i++) {

			final int abs = Integer.parseInt(fabricArea[i][2]);
			final int ord = Integer.parseInt(fabricArea[i][1]);

			if (overlaping[abs][ord] == null) {
				overlaping[abs][ord] = fabricArea[i][0];

			} else {
				overlaping[abs][ord] = "X";
			}

			for (int tall = 0; tall < Integer.parseInt(fabricArea[i][4]); tall++) {
				for (int wide = 0; wide < Integer.parseInt(fabricArea[i][3]); wide++) {

					if (overlaping[abs + tall][ord + wide] == null || overlaping[abs + tall][ord + wide] == fabricArea[i][0]) {
						overlaping[abs + tall][ord + wide] = fabricArea[i][0];
					} else {
						overlaping[abs + tall][ord + wide] = "X";
					}
				}
			}
		}

		return overlaping;
	}
}
