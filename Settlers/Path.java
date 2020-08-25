package Settlers;


import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/*
 * This is the placeholder piece in an
 * edge before I start adding roads
 */
public class Path extends Piece {
	
	public Path(double[] coords, Property property) {
		super(PieceType.PATH);
		Shape shape = this.make(coords, property);
		super.mutate(PieceType.ROAD, shape);
	}

	private Shape make(double[] coords, Property property) {
		Shape shape = new Line(coords[0],coords[1],coords[2],coords[3]);
		shape.setStrokeWidth(5);
		return shape;
	}

	@Override
	public Shape getShape() {
		return super.getShape();
	}
}
