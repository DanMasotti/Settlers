package Indy;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * this is my AI player, I'll explain more but basically it discounts the edges, runs a traversal 
 * algo to get the minimum spanning forest which it returns which is used to pick the "best" choice
 */
public class BotPlayer extends Player{

	public BotPlayer(Property property) {
		super(property);
	}
	
	/*
	 * (non-Javadoc)
	 * @see settlers.Player#isBot()
	 */
	@Override
	public boolean isBot() {
		return true;
	}
	/*
	 * 1) look at all the edges, discount them about some factors
	 * 2) run kruskal on the discounted graph
	 */
	@Override
	public ArrayList<Edge> makeMove(Graph graph, int iteration) {
		//create some sort of value associated with each position
		Iterator<Edge> edges = graph.edges();
		while (edges.hasNext()) {
			Edge edge = edges.next();
			Vertex v1 = edge.getFromVertex();
			Vertex v2 = edge.getToVertex();
			//we like space
			if (edge.getProperty().equals(Property.FREE)) {
				//we hate neighbors
				if (v1.getProperty().equals(Property.FREE) && v2.getProperty().equals(Property.FREE)) {
					this.discount(edge);
				}
				//we like ourselves
				if (v1.getProperty().equals(super.getProperty())) {
					this.discount(edge);
				}
				//early in the game we want roads and settlements
				if (iteration < Constants.INFLECTION_ROUND) {
					if (v1.getResources().contains(Resource.BRICK) || v1.getResources().contains(Resource.LUMBER)) {
						this.discount(edge);
					}
				}
				//end of the game we want cities
				if (iteration >= Constants.INFLECTION_ROUND) {
					if (v1.getResources().contains(Resource.ORE) || v1.getResources().contains(Resource.GRAIN)) {
						this.discount(edge);
					}
				}
				//we like high rolls
				if (v1.getRolls().contains(5) || v1.getRolls().contains(6) || v1.getRolls().contains(8)) {
					this.discount(edge);
				}
			}
		}
		KruskalAlgo kruskal = new KruskalAlgo(graph);
		return kruskal.getMinSpanForest(graph);
	}
	
	/*
	 * helper for halving the edge weight
	 */
	private void discount(Edge edge) {
		edge.setElement(edge.getElement()*Constants.DISCOUNT);
	}
}

	
	