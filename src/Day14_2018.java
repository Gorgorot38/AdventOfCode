import java.util.ArrayList;

public class Day14_2018 {


	final int input = 509671;
	
	public void part1() {
		
		final ArrayList<String> recipes = new ArrayList<>();
		
		recipes.add("3");
		recipes.add("7");
		int sumOfRecipes = 0;
		String result = "";

		int elf1 = 0;
		int elf2 = 1;

		while (recipes.size() < this.input + 10) {
			sumOfRecipes = Integer.parseInt(recipes.get(elf1)) + Integer.parseInt(recipes.get(elf2));

			if (sumOfRecipes < 10) {
				recipes.add(Integer.toString(sumOfRecipes));
			} else {
				final String tmpString = Integer.toString(sumOfRecipes);
				final String digit1 = tmpString.substring(0, 1);
				final String digit2 = tmpString.substring(1);

				recipes.add(digit1);
				recipes.add(digit2);
			}

			elf1 = (elf1 + Integer.parseInt(recipes.get(elf1)) + 1) % recipes.size();
			elf2 = (elf2 + Integer.parseInt(recipes.get(elf2)) + 1) % recipes.size();
		}
		for (int i = this.input; i < this.input + 10; i++) {
			result = result + recipes.get(i);
		}
		
		System.out.println(result);
	}
	
	public void part2() {
		
		final ArrayList<String> recipes = new ArrayList<>();
		
		recipes.add("3");
		recipes.add("7");
		int sumOfRecipes = 0;
		String result = "";
		final String inputAsString = String.valueOf(this.input);

		int elf1 = 0;
		int elf2 = 1;

		while (!result.equalsIgnoreCase(inputAsString)) {
			sumOfRecipes = Integer.parseInt(recipes.get(elf1)) + Integer.parseInt(recipes.get(elf2));

			if (sumOfRecipes < 10) {
				recipes.add(Integer.toString(sumOfRecipes));
				if (recipes.size() > 20000000 && !result.equalsIgnoreCase(inputAsString)) {
					result = "";
					for (int i = recipes.size() - 6; i < recipes.size(); i++) {
						result = result + recipes.get(i);
					}
				}
			} else {
				final String tmpString = Integer.toString(sumOfRecipes);
				final String digit1 = tmpString.substring(0, 1);
				final String digit2 = tmpString.substring(1);

				recipes.add(digit1);
				if (recipes.size() > 20000000 && !result.equalsIgnoreCase(inputAsString)) {
					result = "";
					for (int i = recipes.size() - 6; i < recipes.size(); i++) {
						result = result + recipes.get(i);
					}
				}
				recipes.add(digit2);
				if (recipes.size() > 20000000 && !result.equalsIgnoreCase(inputAsString)) {
					result = "";
					for (int i = recipes.size() - 6; i < recipes.size(); i++) {
						result = result + recipes.get(i);
					}
				}
				if (result.equalsIgnoreCase(inputAsString)) {
					recipes.remove(recipes.size()-1);
				}
			}

			elf1 = (elf1 + Integer.parseInt(recipes.get(elf1)) + 1) % recipes.size();
			elf2 = (elf2 + Integer.parseInt(recipes.get(elf2)) + 1) % recipes.size();
			
		}
		
		
		System.out.println(result);
		System.out.println(recipes.size() - 6);
	}
	
}
