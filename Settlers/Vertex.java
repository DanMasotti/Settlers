package Settlers;

import java.util.ArrayList;
/*
 * Graph's are made up of vertices and edges this is the vertex class 
 * who holds the property of the owner and a piece so when the game 
 * tells the vertex to change its property (tells the piece to mutate into settlements or city
 */
public class Vertex {

	 private double[] _coordinates;
	 private ArrayList<Resource> _resources;
	 private ArrayList<Integer> _rolls;
	 private int _vertexId;
	 private Property _property;
	 private Piece _piece;
	public boolean _visited;
	 
	
	public Vertex(double[] coordinates) {
		_coordinates = coordinates;
		_resources = new ArrayList<Resource>();
		_rolls = new ArrayList<Integer>();
		_property = Property.FREE;
		_piece = new Camp(coordinates, _property);
	}
	
	/*
	 * Tells the piece to mutate into a settlement or city
	 */
	public void newProperty(Property property, PieceType type) {
		_property = property;
		switch (type) {
		case SETTLEMENT:
			_piece = new Settlement(_coordinates, _property);
			break;
		case CITY:
			_piece = new City(_coordinates, _property);
		default:
			break;
		}
	}
	
	// Getter for the property, I use this a lot
	public Property getProperty() {
		return _property;
	}

	/*
	 * Tells the vertex to hold this resource and the corresponding roll
	 */
	public void addResource(Resource res, int roll) {
		_resources.add(res);
		_rolls.add(roll);
	}
	
	// Getter for the currently held piece
	public Piece getPiece() {
		return _piece;
	}
	
	// Getter for resources associated with this vertex
	public ArrayList<Resource> getResources(){
		return _resources;
	}
	
	/*
	 * Because they're added the same time,rolls and resources have the same index
	 * so finds the roll and sends the associated resource
	 */
	public ArrayList<Resource> rollToResource(int roll){
		ArrayList<Resource> res = new ArrayList<Resource>();
		int count = 0;
		for (int num : _rolls) {
			if (num == roll) {
				res.add(_resources.get(count));
			}
			count ++;
		}
		return res;
	}
	
	// Getter for rolls
	public ArrayList<Integer> getRolls(){
		return _rolls;
	}
	
	// Getter for the vertex's internal representation of it
	public int getVertexNumber() {
		return _vertexId;
	}

	// Setter for the vertex's internal representation of it
	public void setVertexNumber(Integer num) {
		_vertexId = num;
	}

	// Vertices are parameterized by their coordinates
	public double[] element() {
		return _coordinates;
	}

	// When doing the traversal algo need to check if the vertex has been seen
	public void setVisited(boolean b) {
		_visited = b;
	}

	// Did i see this vertex already? for traversal
	public boolean isVisited() {
		return _visited;
	}

	
}
