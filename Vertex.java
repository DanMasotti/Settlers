package Indy;

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
	 * tells the piece to mutate into a settlement or city
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
	
	//getter for the property, I use this a lot
	public Property getProperty() {
		return _property;
	}

	/*
	 * tells the vertex to hold this resource and the corresponding roll
	 */
	public void addResource(Resource res, int roll) {
		_resources.add(res);
		_rolls.add(roll);
	}
	
	//getter for the currently held piece
	public Piece getPiece() {
		return _piece;
	}
	
	//getter for resources associated with this vertex
	public ArrayList<Resource> getResources(){
		return _resources;
	}
	
	/*
	 * because theyre added the same time,rolls and resources have the same index
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
	
	//getter for rolls
	public ArrayList<Integer> getRolls(){
		return _rolls;
	}
	
	//getter for the vertex's internal representation of it
	public int getVertexNumber() {
		// TODO Auto-generated method stub
		return _vertexId;
	}

	//setter for the vertex's internal representation of it 
	public void setVertexNumber(Integer num) {
		// TODO Auto-generated method stub
		_vertexId = num;
	}

	//vertices are parameterized by their coordinates
	public double[] element() {
		// TODO Auto-generated method stub
		return _coordinates;
	}

	//when doing the traversal algo need to check if the vertex has been seen
	public void setVisited(boolean b) {
		// TODO Auto-generated method stub
		_visited = b;
	}

	//did i see this vertex already? for traversal
	public boolean isVisited() {
		// TODO Auto-generated method stub
		return _visited;
	}

	
}
