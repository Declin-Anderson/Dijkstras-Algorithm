
public class Vertex implements Comparable<Vertex>{
	// constant color values for BFS Algorithm
	public static final int WHITE = 1;
	public static final int GREEN = 2;
	public static final int BLACK = 3;

	// instance variable
	private String name;
	private int color;
	private boolean visited; // used for DFS Algorithm
	private int distance;
	/**
	 * Vertex constructor to initialize instance variable
	 * @param name
	 */
	public Vertex(String name) {
		this.name = name;
		color = 0;
		visited = false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int compareTo(Vertex o) {
		int value = 0;
		if(distance<o.getDistance())
			value = -1;
		else if(distance>o.getDistance())
			value = 1;
		return value;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
