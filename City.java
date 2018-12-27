package Indy;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/*
 * this is a super settlement that gets you double the points 
 */
public class City extends Piece{

	
	public City(double[] coords, Property property) {
		super(PieceType.CITY);
		Shape shape = this.make(coords, property);
		super.mutate(PieceType.CITY,  shape);
	}
	
	/*
	 * creates the fat pentagon for the board
	 */
	public Shape make(double[] coords, Property property) {
		double shift = 15;
		double v1X = coords[0]-2*shift;
		double v1Y = coords[1]+shift;
		double v2X = coords[0]-2*shift;
		double v2Y = coords[1]-shift;
		double v3X = coords[0];
		double v3Y = coords[1]-2*shift;
		double v4X = coords[0]+2*shift;
		double v4Y = coords[1]-shift;
		double v5X = coords[0]+2*shift;
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
	 * property  ---> color of the pentagon
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
	
	@Override
	public Shape getShape() {
		return super.getShape();
	}
}

