package Indy;


import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/*
 * this is the road class, these guys go on edges and 
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
	 * tells piece to become a rectangle
	 */
	private Shape make(double[] coords, Property property) {
		// TODO Auto-generated method stub
		Shape shape = new Line(coords[0],coords[1],coords[2],coords[3]);
		shape = this.setColor(shape, property);
		shape.setStrokeWidth(5);
		return shape;
	}
	
	/*
	 * paint me
	 */
	public Shape setColor(Shape shape, Property property) {
		// TODO Auto-generated method stub
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
