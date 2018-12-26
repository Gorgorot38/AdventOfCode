import java.util.ArrayDeque;
import java.util.HashMap;

public class Day14_2018 {


	final int input = 509671;
	
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

	
	
	public void testCircle() {
		
		final Circle<String> circle = new Circle<>();
		circle.add("(3)*");
		circle.add("[7]");
		
		final HashMap<String, Integer> currentRecipes = new HashMap<>();

		//Find current recipes
		circle.forEach(recipe -> {
			if (recipe.startsWith("(")) {
				String tmpString = recipe.replace("(", "");
				tmpString = tmpString.replace(")", "");
				if(recipe.endsWith("*")) {
					tmpString.replace("*", "");
				}
				final int toInt = Integer.parseInt(tmpString);
				currentRecipes.put("(", toInt);
			}
			if (recipe.startsWith("[")) {
				String tmpString = recipe.replace("[", "");
				tmpString = tmpString.replace("]", "");
				if(recipe.endsWith("*")) {
					tmpString.replace("*", "");
				}
				final int toInt = Integer.parseInt(tmpString);
				currentRecipes.put("[", toInt);
			}
		});
		
		circle.rotate(3);
		
		
		circle.forEach(element -> System.out.println(element));

		
	}
	
	
	
}
