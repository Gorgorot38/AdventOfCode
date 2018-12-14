import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Day11_2018 {

	
	private final int puzzleInput = 5468;
	private final int possibleSize = 300;
	
	private final Integer[][] puzzleGrid = new Integer[301][301];

	
	public void buildGrid() {
		
		for (int y = 1; y < this.puzzleGrid.length; y++) {
			for (int x = 1; x < this.puzzleGrid[y].length; x++) {

				final int rackId = x + 1 + 10;
				final int beginPowerLevel = rackId * y;
				final int increasePowerLevel = beginPowerLevel + this.puzzleInput;
				int setPowerLevel = increasePowerLevel * rackId;
				setPowerLevel = setPowerLevel / 100;
				final int powerLevel = setPowerLevel % 10;

				this.puzzleGrid[y][x] = powerLevel - 5;

			}
		}
	}
	
	public List<Integer> findSquare() throws IOException{
		
		final HashMap<List<Integer>, Integer> squaresMap = new HashMap<>();
		List<Integer> maxSquareCoord = new ArrayList<>();
		final HashMap<List<Integer>, Integer> squaresMapTmp = new HashMap<>();
		int max = 0;
		buildGrid();
		
		for (int size = 1; size <= this.possibleSize; size ++) {
			for (int y = 1; y < this.puzzleGrid.length - size - 1; y++) {
				for (int x = 1; x < this.puzzleGrid[y].length - size - 1; x++) {

					final List<Integer> coord = new ArrayList<>();
					coord.add(x+1);
					coord.add(y);
					coord.add(size);
					
					int squareValue = 0;

					for (int length = 0; length <= size - 1; length++) {
						for (int height = 0; height <=  size - 1; height++) {
							squareValue += this.puzzleGrid[y + length][x + height];
						}
					}
					//System.out.println(coord);
					//System.out.println(squareValue);

					squaresMap.put(coord, squareValue);
					squaresMapTmp.put(coord, squareValue);
				}
			}
			int maxTmp = -2000;
			for (final Entry<List<Integer>, Integer> e : squaresMapTmp.entrySet()) {
				if (e.getValue() >= max) {
					maxTmp = e.getValue();
				}
			}
			squaresMapTmp.clear();
			if(maxTmp < 0) {
				break;
			}
		}
		
		
		for (final Entry<List<Integer>, Integer> e : squaresMap.entrySet()) {
			if (e.getValue() >= max) {
				maxSquareCoord.clear();
				max = e.getValue();
				maxSquareCoord = e.getKey();
			}
		}


		
		return maxSquareCoord;
		
	}
	
	/**
	 * @return the puzzleInput
	 */
	public int getPuzzleInput() {
		return this.puzzleInput;
	}

	/**
	 * @return the puzzleGrid
	 */
	public Integer[][] getPuzzleGrid() {
		return this.puzzleGrid;
	}
	
}
