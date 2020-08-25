package Settlers;

import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import java.util.ArrayList;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;

/*
 * This is the hex class.  hexes don't hold any useful data but are important for
 * the graphical representation of the games board.  its a wrapper class for polygon
 */
public class Hex {

	private Polygon _hex;
	private Resource _type;
	private int _number;
	private Pane _pane;
	private ArrayList<Double> _vertices;

	public Hex(
			Pane pane,
			double vx1, double vy1,
			double vx2, double vy2,
			double vx3, double vy3,
			double vx4, double vy4,
			double vx5, double vy5,
			double vx6, double vy6,
			Resource type,
			int number
			) {
		_hex = new Polygon(
				vx1, vy1,
				vx2, vy2,
				vx3, vy3,
				vx4, vy4,
				vx5, vy5,
				vx6, vy6
				);
		_type = type;
		_number = number;
		this.logVertices(vx1, vy1, vx2, vy2, vx3, vy3, vx4, vy4, vx5, vy5, vx6, vy6);
		_pane = pane;
		InnerShadow shadow = new InnerShadow();
        shadow.setOffsetX(-3f);
        shadow.setOffsetY(-3f);
		_hex.setFill(this.getColor());
		_hex.setStroke(Color.BLACK);
		_hex.setStrokeWidth(1);
		_hex.setEffect(shadow);
        int shift = 25;
		_pane.getChildren().addAll(_hex,this.setText(((vx1+vx2+vx3+vx4+vx5+vx6)/6)-shift,(vy1+vy2+vy3+vy4+vy5+vy6)/6));
	}

	/*
	 * Labels the hex with the corresponding dice roll
	 */
	public Text setText(double xLoc, double yLoc) {
		if (_type.equals(Resource.DESERT)) {
			return new Text("Desert");
		}
		else {
			Text text = new Text(_type + " : " + _number);
			text.setX(xLoc);
			text.setY(yLoc);
			text.setFill(Color.BLACK);
			return text;
		}
	}

	/*
	 * Capture the vertices from the creation and keeps it in the instance variable
	 */
	public void logVertices(
			double vx1, double vy1,
			double vx2, double vy2,
			double vx3, double vy3,
			double vx4, double vy4,
			double vx5, double vy5,
			double vx6, double vy6) {
		_vertices = new ArrayList<Double>(12);
		_vertices.add(vx1);
		_vertices.add(vy1);
		_vertices.add(vx2);
		_vertices.add(vy2);
		_vertices.add(vx3);
		_vertices.add(vy3);
		_vertices.add(vx4);
		_vertices.add(vy4);
		_vertices.add(vx5);
		_vertices.add(vy5);
		_vertices.add(vx6);
		_vertices.add(vy6);
	}

	/*
	 * Which vertices did I use to make this Hex
	 */
	public ArrayList<Double> getVertices() {
		return _vertices;
	}

	/*
	 * Paints the hex
	 */
	public Paint getColor() {
		switch (this.getType()) {
		case BRICK:
			return Color.DARKRED;
		case LUMBER:
			return Color.DARKGREEN;
		case ORE:
			return Color.DARKGRAY;
		case WOOL:
			return Color.YELLOWGREEN;
		case GRAIN:
			return Color.WHEAT;
		case DESERT:
			return Color.GOLD;
		default: return null;
		}
	}

	/*
	 * The resource given to the player
	 */
	public Resource getType() {
		return _type;
	}

	// Getter for the underlying shape
	public Polygon getHex() {
		return _hex;
	}
}
