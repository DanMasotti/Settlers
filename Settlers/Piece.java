package Settlers;


import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/*
 * The super class of all the pieces, the basic
 * building block of getting points in this 
 * game
 */

public class Piece {
	
	private Shape _shape;
	private PieceType _type;
	
	public Piece(PieceType type) {
		_shape = new Circle();
		_type = type;
	}
	
	/*
	 * We call this when we tell the vertex that a player has just
	 * bought property so now there's gotta be a pentagon or a rectangle
	 * on you
	 */
	public void mutate(PieceType type, Shape newShape) {
		_type = type;
		_shape = newShape;
	}
	
	// Getter for the shape thats stored in the piece
	public Shape getShape() {
		return _shape;
	}
	
	// Getter for the type of piece thats here
	public PieceType getType() {
		return _type;
	}
}
