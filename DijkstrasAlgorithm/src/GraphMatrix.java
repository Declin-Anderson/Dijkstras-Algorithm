import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class GraphMatrix {
	
	// instance variable to the array of all vertices in the graph.
	private Vertex[] vertices;
	
	// A 2D array of edge weights from Vertex to Vertex. The indices in the array match the indices of the Vertex in vertices.
	private int[][] edgeWeights;
	
	// assign parameters to instance variables.
	public GraphMatrix(Vertex[] vertices, int[][] edgeWeights) {
		this.vertices = vertices;
		this.edgeWeights = edgeWeights;
	}
	
	// returns the index of the Vertex object vertex stored in vertices (loop through to find it). If vertex is not in vertices, it will return -
	public int indexOfVertex(Vertex vertex) {
		for(int i = 0; i < vertices.length; i++) {
			if(vertices[i] == vertex) {
				return i;
			}
		}
		return -1;
	}
	
	// returns the vertices that have an edge from vertex
	public ArrayList<Vertex> getNeighborsOfVertex(Vertex vertex) {
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
		int index = indexOfVertex(vertex);
		for(int i = 0; i < edgeWeights[index].length; i++) {
			if(vertices[i].getDistance() > 0) {
				neighbors.add(vertices[i]);
			}
		}
		return neighbors;
	}
	
	/*
	 * Algorithm: Dijkstra(int sourceIndex):
	 * 	Vertex source = vertices[sourceIndex]
	 * 	create PriorityQueue<Vertex> Q
	 * 	create a HashMap<Vertex,Vertex> predecessor
	 * 	for each vertex v in vertices
	 * 		dist[v] <- INFINITY //v.setDistance(Integer.MAX_INT)
	 * 		if v is source
	 * 			set dist[source] <- 0
	 * 		add v to Q
	 * 	//end for
	 * 	while Q is not empty do
	 * 		u <- remove u from Q //vertex in Q with min distance
	 * 		uIndex <-index of u in vertices
	 * 		for each neighbor v of u do
	 * 			if v still in Q and edge from u to v
	 * 				alt <- dist[u] + length(u, v)
	 * 				if alt < dist[v]:
	 * 					remove v from Q
	 * 					dist[v] <- alt
	 * 					add v to Q
	 * 					put(v, u) in predecessor //u predecessor to v
	 * 	print out distance to each vertex from the source vertex
	 * 	print out path from source to each vertex
	 * 	for each Vertex in vertices named current
	 * 		String path = “”;
	 * 		if(not sourceIndex)
	 * 			path = “ -> “+current
	 * 			while(predecessor contains key (current) )
	 * 				if( predecessor of current is source
	 * 					path = predecessor get current +path
	 * 				else
	 * 					path = “ -> “+predecessor get current + path
	 * 				current = predecessor get current
	 * 			//end while
	 * 		//end if
	 * 		print path
	 * 	//end for
	 */
	public void Dijkstra(int sourceIndex) {
		// Vertex source = vertices[sourceIndex]
		Vertex source = vertices[sourceIndex];
		// create PriorityQueue<Vertex> Q
		PriorityQueue<Vertex> Q = new PriorityQueue<Vertex>();
		// create a HashMap<Vertex,Vertex> predecessor
		HashMap<Vertex, Vertex> predecessor = new HashMap<Vertex, Vertex>();
		// for each vertex v in vertices
		for (Vertex v : vertices) {
			// dist[v] <- INFINITY //v.setDistance(Integer.MAX_INT)
			v.setDistance(Integer.MAX_VALUE);
			// if v is source
			if (v.equals(source)) {
				// set dist[source] <- 0
				source.setDistance(0);
			}
			// add v to Q
			Q.add(v);
		}
		// while Q is not empty do
		while (Q.isEmpty() == false) {
			// * u <- remove u from Q //vertex in Q with min distance
			Vertex u = Q.remove();

			// * uIndex <-index of u in vertices
			int uIndex = this.indexOfVertex(u);
			// * for each neighbor v of u do
			for (Vertex v : this.getNeighborsOfVertex(u)) {
				// * if v still in Q and edge from u to v
				if (Q.contains(v) && edgeWeights[this.indexOfVertex(u)][this.indexOfVertex(v)] > 0) {

					// * alt <- dist[u] + length(u, v)
					int alt = u.getDistance() + edgeWeights[this.indexOfVertex(u)][this.indexOfVertex(v)];
					// * if alt < dist[v]:
					if (alt < v.getDistance()) {
						// * remove v from Q
						Q.remove(v);
						// * dist[v] <- alt
						v.setDistance(alt);
						// * add v to Q
						Q.add(v);
						// * put(v, u) in predecessor //u predecessor to v
						predecessor.put(v, u);

					}
				}
			}
		}
		// * print out distance to each vertex from the source vertex
		System.out.print("Distances from Vertex A");
		for (Vertex v : vertices) {
			System.out.print("\n" + v.getName());
			if (!v.equals(source)) {
				System.out.print(", " + v.getDistance());
			}
		}
		//// * print out path from source to each vertex
		System.out.print("\n\nShortest paths from Vertex A");
		// * for each Vertex in vertices named current
		for (Vertex current : vertices) {

			// * String path = “”;
			String path = "";
			// * if(not sourceIndex)
			if (!current.equals(source)) {
				// * path = “ -> “+current
				path = " -> " + current.getName() + ", " + current.getDistance();
				// * while(predecessor contains key (current) )
				while (predecessor.containsKey(current)) {

					// * if( predecessor of current is source
					if (predecessor.get(current).equals(source)) {
						// * path = predecessor get current +path
						path = predecessor.get(current).getName() + path;
						// * else
					} else {
						// * path = “ -> “+predecessor get current + path
						path = " -> " + predecessor.get(current).getName() + ", " +predecessor.get(current).getDistance() + path;
					}
						// * current = predecessor get current
						current = predecessor.get(current);
					// * //end while
				}
				// * //end if
			}
			// * print path
			System.out.println(path);
			// * //end for
		}
	}
	
	// Print out vertex and its edges to other vertices with the weight of the edge
	public String toString() {
		String s = "Adjacency matrix for graph:\n";
		for(int fromVertex = 0; fromVertex < edgeWeights.length; fromVertex++) {
			s+=vertices[fromVertex];
			for(int toVertex = 0; toVertex < edgeWeights[fromVertex].length; toVertex++) {
				if(edgeWeights[fromVertex][toVertex]>0)
					s+= " ->"+vertices[toVertex]+", "+edgeWeights[fromVertex][toVertex];
			}
			s+="\n";
		}
		return s;
	}
}
