import java.io.IOException;
import java.nio.file.Path;

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

	
	public int countOverlap(final Path fabricInfos) throws IOException {

		final int count = 0;

		final String[] firstArray = Utils.convertFileArray(fabricInfos);

		final String[][] fabricArea = buildFormatedArray(firstArray);
		
		final String[][] overlaping = buildFabricArray(fabricArea, firstArray);
		
		
		
		return count;
	}
	
	public String[][] buildFormatedArray(final String[] input){
		
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
	
	public static String[][] buildFabricArray(final String[][] fabricArea, final String[] firstArray) {
		final String[][] overlaping = new String[5000][5000];

		for (int i = 0; i < firstArray.length; i++) {

			final int abs = Integer.parseInt(fabricArea[i][2]);
			final int ord = Integer.parseInt(fabricArea[i][1]);

			if (overlaping[abs][ord] == null) {
				overlaping[abs][ord] = fabricArea[i][0];

			} else {
				overlaping[abs][ord] = "X";
			}

			for (int tall = 0; tall < Integer.parseInt(fabricArea[i][4]); tall++) {
				for (int wide = 1; wide < Integer.parseInt(fabricArea[i][3]); wide++) {

					if (overlaping[abs + wide][ord + tall] == null) {
						overlaping[abs + wide][ord + tall] = fabricArea[i][0];
					} else {
						overlaping[abs + wide][ord + tall] = "X";
					}
				}
			}
		}

		return overlaping;
	}
}
