package Settlers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/*
 * Is a piece that holds a pentagon
 */
public class Settlement extends Piece{
	
	
	public Settlement(double[] coords,Property property) {
		super(PieceType.SETTLEMENT);
		Shape shape = this.make(coords, property);
		super.mutate(PieceType.SETTLEMENT,  shape);
	}
	
	/*
	 * Makes the actual shape
	 */
	public Shape make(double[] coords, Property property){
		double shift = 15;
		double v1X = coords[0]-shift;
		double v1Y = coords[1]+shift;
		double v2X = coords[0]-shift;
		double v2Y = coords[1]-shift;
		double v3X = coords[0];
		double v3Y = coords[1]-2*shift;
		double v4X = coords[0]+shift;
		double v4Y = coords[1]-shift;
		double v5X = coords[0]+shift;
		double v5Y = coords[1] + shift;
		Shape shape = new Polygon(
							v1X,v1Y,
							v2X,v2Y,
							v3X,v3Y,
							v4X,v4Y,
							v5X,v5Y
							);
		shape = this.setColor(shape, property);
		return shape;
	}
	
	/*
	 * Property to color
	 */
	public Shape setColor(Shape shape, Property property) {
		switch (property) {
			case BLUE:
				shape.setFill(Color.NAVY);
				break;
			case RED:
				shape.setFill(Color.RED);
				break;
			case WHITE:
				shape.setFill(Color.WHITE);
				break;
			case ORANGE:
				shape.setFill(Color.ORANGE);
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

