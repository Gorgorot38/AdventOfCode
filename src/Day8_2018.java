import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day8_2018 {
	
	List<Integer> input = new ArrayList<>();
	int total = 0;
	TreeNode root = new TreeNode();

	public void fileToList (final Path path) throws IOException{
		
	
		final String[] fileArray = Utils.convertFileArray(path);
		final String[] resultTmp = fileArray[0].split(" ");
		
		for (final String element : resultTmp) {
			this.input.add(Integer.parseInt(element));
		}
		
	}
	
	
	class TreeNode{
		
		List<TreeNode> children = new ArrayList<>();
		List<Integer> metaData = new ArrayList<>();
		
        int value() {
            if (this.children.size() == 0) {
                return this.metaData.stream()
                        .mapToInt(x -> x)
                        .sum();
            }
			int sum = 0;
			for (final Integer meta : this.metaData) {
			    if (meta <= this.children.size()) {
			        final TreeNode child = this.children.get(meta - 1);
			        if (child != null) {
			            sum += child.value();
			        }
			    }
			}
			return sum;
        }

	}
	
	public int buildTree(int index, final TreeNode current) {
		
		
		final int children = this.input.get(index++);
		final int metaData = this.input.get(index++);
		
        for (int i = 0; i < children; i++) {
            final TreeNode child = new TreeNode();
            current.children.add(child);
            index = buildTree(index, child);
        }
        for (int i = 0; i < metaData; i++) {
            current.metaData.add(this.input.get(index + i));
            this.total += this.input.get(index + i);
        }
        return index + metaData;

	}
	
	public int part1() throws IOException {
		
		fileToList(AdventOfCode.getDay8_2018());
		buildTree(0, this.root);
		return this.total;
	}
	
	public int part2() throws IOException {
		
		fileToList(AdventOfCode.getDay8_2018());
		buildTree(0, this.root);
		return this.root.value();
	}

	
}
