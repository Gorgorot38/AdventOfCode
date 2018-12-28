package aoc.aoc2018;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import aoc.Utils;

public class Day15 {

	DefaultDirectedGraph<String, DefaultEdge> field = new DefaultDirectedGraph<>(DefaultEdge.class);
	HashMap<String, Character> pointMap = new HashMap<>();
	
	
	public void constructGraph(final Path path) throws IOException {

		final char[][] filedChar = Utils.convertFileMatrix(path);

		for (int i = 0; i < filedChar.length; i++) {
			for (int j = 0; j < filedChar[i].length; j++) {

				final String id = "" + i + ";" + j;

				if (!this.pointMap.containsKey(id)) {
					this.pointMap.put(id, filedChar[i][j]);
					this.field.addVertex(id);
				}
				
				if (filedChar[i][j] != '#') {
					if (filedChar[i + 1][j] != '#') {
						final int x = i + 1;
						final String id1 = "" + x + ";" + j;
						if (!this.pointMap.containsKey(id1)) {
							this.pointMap.put(id1, filedChar[i + 1][j]);
							this.field.addVertex(id1);
						}

						this.field.addEdge(id, id1);
						
					}
					if (filedChar[i - 1][j] != '#') {
						final int x = i - 1;
						final String id1 = "" + x + ";" + j;
						if (!this.pointMap.containsKey(id1)) {
							this.pointMap.put(id1, filedChar[i - 1][j]);
							this.field.addVertex(id1);
						}

						this.field.addEdge(id, id1);
						
					}
					if (filedChar[i][j + 1] != '#') {
						final int x = j + 1;
						final String id1 = "" + i + ";" + x;
						if (!this.pointMap.containsKey(id1)) {
							this.pointMap.put(id1, filedChar[i][j + 1]);
							this.field.addVertex(id1);
						}

						this.field.addEdge(id, id1);

					}
					if (filedChar[i][j - 1] != '#') {
						final int x = j -1;
						final String id1 = "" + i + ";" + x;
						if (!this.pointMap.containsKey(id1)) {
							this.pointMap.put(id1, filedChar[i][j - 1]);
							this.field.addVertex(id1);
						}

						this.field.addEdge(id, id1);
					}
				}
			}
		}
	}
	
	public void moveOnePiece(final String id, final char type) {
		
		final HashMap<Integer, List<List<String>>> shortPathMap = new HashMap<>();
		int min = 100;
		
		this.pointMap.forEach((key, value) -> {
			
			if(!key.equalsIgnoreCase(id) && type != value && (value == 'G' || value == 'E')) {
				final DijkstraShortestPath<String, DefaultEdge> shortPaths = new DijkstraShortestPath<>(this.field);
				final List<String> shortestPath = shortPaths.getPath(id, key).getVertexList();
				if (!shortPathMap.containsKey(shortestPath.size())) {
					final List<List<String>> listOfShortPath = new ArrayList<>();
					listOfShortPath.add(shortestPath);
					shortPathMap.put(shortestPath.size(), listOfShortPath);
				} else {
					final List<List<String>> listOfShortPath = shortPathMap.get(shortestPath.size());
					listOfShortPath.add(shortestPath);
					shortPathMap.put(shortestPath.size(), listOfShortPath);
				}
			}
			
		});
		
		for (final Entry<Integer, List<List<String>>> e : shortPathMap.entrySet()) {
			if (e.getKey() <= min) {
				min = e.getKey();
			}
		}

		final List<List<String>> shortest = shortPathMap.get(min);
		
		System.out.println("");
	}
	
	public void part1(final Path path) throws IOException {
		constructGraph(path);
		moveOnePiece("10;3", 'G');
	}
}
