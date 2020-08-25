package Settlers;

/*
 * Graph, the underlying data structure for the game is parameterized by vertices and edges... so here's my
 * edge class.  It potentially holds road pieces but also knows who
 * its attached to.  It also has an associated weight so that when the
 * bot looks at the graph it can discount for future moves
 */
public class Edge {
	
	private double _element;
	private Property _property;
	private Piece _piece;
	private double[] _coords;
	private Vertex _v1;
	private Vertex _v2;

	
	public Edge(Vertex v1, Vertex v2, double edgeElement){
		_v1 = v1;
		_v2 = v2;
		_element = edgeElement;
		_property = Property.FREE;
		_coords = new double[]{v1.element()[0],v1.element()[1],v2.element()[0], v2.element()[1]};
 		_piece = new Path(_coords, _property);
	}
	
	/*
	 * Puts a road on this edge
	 */
	public void newProperty(Property property) {
		_property = property;
		_piece = new Road(_coords, _property);
	}
	
	/*
	 * Returns the weight of the edge
	 */
	public double getElement() {
		return _element;
	}

	/*
	 * The graph is undirected but I usually think about going from
	 * to to when looping over the edges
	 */
	public Vertex getFromVertex() {
		return _v1;
	}

	/*
	 * Same as above but return the second of the pair
	 */
	public Vertex getToVertex() {
		return _v2;
	}

	// Setter for "to"
	public void setFromVertex(Vertex v1) {
		_v1 = v1;
	}

	// Setter for "from"
	public void setToVertex(Vertex v2) {
		_v2 = v2;
	}
	
	/*
	 * This is how to change the edge weights
	 */
	public void setElement(double val) {
		_element=val;
	}

	
	/*
	 * This is how the road shows up graphically
	 */
	public Piece getPiece() {
		return _piece;
	}

	/*
	 * Check if the edge is owned by another player or not
	 */
	public Property getProperty() {
		return _property;
	}
}
