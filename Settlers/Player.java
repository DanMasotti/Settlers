package Settlers;

import java.util.ArrayList;
/*
 * Superclass of human and bot players
 */
public class Player {

	private ArrayList<Resource> _basket;
	private int _victoryPoints;
	private Property _property;
	
	public Player(Property property) {
		_basket = new ArrayList<Resource>();
		_property = property;
		_victoryPoints = 0;
	}
	
	/*
	 * Distributes resources to player
	 */
	public void collect(Resource resource, int i) {
		for (int j = 0; j < i; j++) {
			_basket.add(resource);
		}
	}
	
	/*
	 * Removes resources from player
	 */
	public void removeResource(Resource resource, int i) {
		for (int j = 0; j < i; j++) {
			_basket.remove(resource);
		}
	}
	
	// Getter for victory points
	public int getPoints() {
		return _victoryPoints;
	}
	
	/*
	 * Called when the player purchases new property
	 * and gets rewarded
	 */
	public void addPoints(int points) {
		_victoryPoints = _victoryPoints + points;
	}
	
	// Gets the player's basket of resources
	public ArrayList<Resource> getBasket(){
		return _basket;
	}

	// Getter for the player's identity
	public Property getProperty() {
		return _property;
	}

	/*
	 * This is a method that's implemented by the bot player
	 * but is useless for anyone else
	 */
	public ArrayList<Edge> makeMove(Graph graph, int integer) {
		return null;
	}
	
	/*
	 * Not a robot by default... the bot player overrides this to
	 * say yes
	 */
	public boolean isBot() {
		return false;
	}
}
