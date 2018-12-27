package Indy;

/*
 * graphs are parameterized by vertices and edges... so here's my
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
	 * puts a road on this edge
	 */
	public void newProperty(Property property) {
		_property = property;
		_piece = new Road(_coords, _property);
	}
	
	/*
	 * returns the weight of the edge
	 */
	public double getElement() {
		return _element;
	}

	/*
	 * the graph is undirected but I usually think about going from
	 * to to when looping over the edges
	 */
	public Vertex getFromVertex() {
		return _v1;
	}

	/*
	 * same as above but return the second of the pair
	 */
	public Vertex getToVertex() {
		return _v2;
	}

	//setter for to
	public void setFromVertex(Vertex v1) {
		_v1 = v1;
	}

	//setter for from
	public void setToVertex(Vertex v2) {
		// TODO Auto-generated method stub
		_v2 = v2;
	}
	
	/*
	 * this is how I change the edge weights
	 */
	public void setElement(double val) {
		_element=val;
	}

	
	/*
	 * this is how the road shows up graphically 
	 */
	public Piece getPiece() {
		return _piece;
	}

	/*
	 * see if the edge is owned by another player or not
	 */
	public Property getProperty() {
		return _property;
	}
}
