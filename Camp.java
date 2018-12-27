package Indy;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/*
 * this is the default piece that a vertex is instantiated with
 * like a placeholder that I'll tell to mutate later on
 */
public class Camp extends Piece{
	
	
	public Camp(double[] coords, Property property) {
		super(PieceType.CAMP);
		Shape shape = this.make(coords, property);
		super.mutate(PieceType.CAMP, shape);
	}

	public Shape make(double[] coords, Property property) {
		// TODO Auto-generated method stub
		Shape shape = new Circle();
		((Circle) shape).setRadius(Constants.VERTEX_SIZE);
		((Circle) shape).setCenterX(coords[0]);
		((Circle) shape).setCenterY(coords[1]);
		return shape;
	}
	
	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return super.getShape();
	}

}
