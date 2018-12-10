import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class Day6_2018 {

	public static HashMap<String, String> getCoordinatesMap(final Path path) throws IOException{
		
		final HashMap<String, String> coordinatesMap = new HashMap<>();
		final String[] coordArray = Utils.convertFileArray(path);
		char first = 'A';
		char second = 'A';
		
		for (final String element : coordArray) {
			coordinatesMap.put("" + first + second, element);
			if (second == 'Z') {
				first = 'B';
				second = '@';
			}
			second++;
		}
		
		return coordinatesMap;
	}
	
}
