import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

public class Day6_2018 {

	public static HashMap<String, Integer[]> getCoordinatesMap(final Path path) throws IOException{
		
		final HashMap<String, String> coordinatesMap = new HashMap<>();
		final HashMap<String, Integer[]> coordinatesMapReal = new HashMap<>();
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
		
		coordinatesMap.forEach((id, coord) -> {
			final String[] newCoord = coord.split(", ");
			final Integer[] intCoord = new Integer[2];
			intCoord[0] = Integer.parseInt(newCoord[0]);
			intCoord[1] = Integer.parseInt(newCoord[1]);

			coordinatesMapReal.put(id, intCoord);
		});
		
		return coordinatesMapReal;
	}
	
	
}
