package Settlers;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * This is my AI player, it discounts the edges, runs a traversal
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
	 * 1) Look at all the edges, discount them about some factors
	 * 2) Run kruskal on the discounted graph
	 */
	@Override
	public ArrayList<Edge> makeMove(Graph graph, int iteration) {
		// Create a value associated with each position
		Iterator<Edge> edges = graph.edges();
		while (edges.hasNext()) {
			Edge edge = edges.next();
			Vertex v1 = edge.getFromVertex();
			Vertex v2 = edge.getToVertex();
			// We favor free spots
			if (edge.getProperty().equals(Property.FREE)) {
				// We dislike being close to opposing settlements
				if (v1.getProperty().equals(Property.FREE) && v2.getProperty().equals(Property.FREE)) {
					this.discount(edge);
				}
				// We favor areas close by
				if (v1.getProperty().equals(super.getProperty())) {
					this.discount(edge);
				}
				// Early in the game we want roads and settlements
				if (iteration < Constants.INFLECTION_ROUND) {
					if (v1.getResources().contains(Resource.BRICK) || v1.getResources().contains(Resource.LUMBER)) {
						this.discount(edge);
					}
				}
				// Towards the end of the game we want cities
				if (iteration >= Constants.INFLECTION_ROUND) {
					if (v1.getResources().contains(Resource.ORE) || v1.getResources().contains(Resource.GRAIN)) {
						this.discount(edge);
					}
				}
				// We favor hexes with high probability rolls
				if (v1.getRolls().contains(5) || v1.getRolls().contains(6) || v1.getRolls().contains(8)) {
					this.discount(edge);
				}
			}
		}
		KruskalAlgo kruskal = new KruskalAlgo(graph);
		return kruskal.getMinSpanForest(graph);
	}
	
	/*
	 * Helper for halving the edge weight
	 */
	private void discount(Edge edge) {
		edge.setElement(edge.getElement()*Constants.DISCOUNT);
	}
}

	
	