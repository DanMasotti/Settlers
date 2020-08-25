package Settlers;


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/*
 * This is the road class, these guys go on edges and
 * are the only way to get from one settlement to the 
 * next
 */
public class Road extends Piece{


	public Road(double[] coords, Property property) {
		super(PieceType.ROAD);
		Shape shape = this.make(coords,property);
		super.mutate(PieceType.ROAD, shape);
	}
	
	/*
	 * Tells piece to become a rectangle
	 */
	private Shape make(double[] coords, Property property) {
		Shape shape = new Line(coords[0],coords[1],coords[2],coords[3]);
		shape = this.setColor(shape, property);
		shape.setStrokeWidth(5);
		return shape;
	}
	
	/*
	 * Paint the shape
	 */
	public Shape setColor(Shape shape, Property property) {
		switch (property) {
			case BLUE:
				shape.setStroke(Color.NAVY);
				break;
			case RED:
				shape.setStroke(Color.RED);
				break;
			case WHITE:
				shape.setStroke(Color.WHITE);
				break;
			case ORANGE:
				shape.setStroke(Color.ORANGE);
				break;
			default: break;
			}
		return shape;
		}

	/*
	 * (non-Javadoc)
	 * @see settlers.Piece#getShape()
	 */
	@Override
	public Shape getShape() {
		return super.getShape();
	}
}
