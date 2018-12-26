import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map.Entry;

public class Day9_2018 {
	
	long players = 0;
	long marbles = 0;

	class Circle<E> extends ArrayDeque<E>{
		
		void rotate(final long n) {
			if (n == 0) {
				return;
			}
			if (n > 0) {
				for (long i = 0; i < n; i++) {
					final E e = this.removeLast();
					this.addFirst(e);
				}

			}
			if (n < 0) {
				for (long i = 0; i < Math.abs(n); i++) {
					final E e = this.removeFirst();
					this.addLast(e);
				}
			}
		}
	}
	
	public void initializeParam() throws IOException {
		final String[] input = Utils.convertFileArray(AdventOfCode.getDay9_2018());
		
		input[0] = input[0].replace(" players; last marble is worth", "");
		final String inputTmp = input[0].replace(" points", "");
		
		final Integer[] param = new Integer[2];
		String[] input2 = new String[2];

		input2 = inputTmp.split(" ");
		
		for (int i = 0; i < input2.length; i++) {
			param[i] = Integer.parseInt(input2[i]);
		}
		
		this.players = param[0];
		this.marbles = param[1] * 100;
	}
	
	public Long theGame() throws IOException {

		Long score = (long) 0;
		long currentPlayer = 1;
		final HashMap<Long, Long> scores = new HashMap<>();
		final Circle<Long> circle = new Circle<>();
		circle.addFirst((long) 0);
		
		initializeParam();

		for (long i = 1; i < this.marbles + 1; i++) {

			if (i % 23 == 0) {

				circle.rotate(8);

				if (scores.containsKey(currentPlayer)) {
					final long value = scores.get(currentPlayer);
					scores.put(currentPlayer, value + i + circle.pop());
				} else {
					scores.put(currentPlayer, i + circle.pop());
				}
				circle.rotate(-1);

			} else {
				circle.rotate(-1);
				circle.addLast(i);
			}

			if (i % this.players == 0) {
				currentPlayer = 0;
			}
			currentPlayer++;
		}

		for (final Entry<Long, Long> e : scores.entrySet()) {
			if (e.getValue() >= score) {
				score = e.getValue();
			}
		}

		return score;
	}
}
