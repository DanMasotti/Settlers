package Settlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * This class is based on Kruskal's minimum spanning tree
 * algorithm. It finds the minimum spanning tree with respect to edge
 * weights for the bot 
 * Adapted from Dasgupta's Algorithms text book 
 */

public class KruskalAlgo {
	
	Decorator<Vertex,Vertex> _cloud;
	Decorator<Vertex,Integer> _rank;
	Decorator<Edge,Boolean> _isContained;
	Decorator<Vertex,Boolean> _isVisited;
	ArrayList<Edge> _mst;
	
	public KruskalAlgo(Graph g) {
		_mst = this.getMinSpanForest(g);
	}
	

	/*
	   * Purpose: to return the minimum spanning forest using the Kruskal's algo
	   * Input: an undirected graph
	   * Output: the minimum spanning forest
	   */
  public ArrayList<Edge> getMinSpanForest(Graph g) {
	  this.setupInstanceVars();
	  Iterator<Vertex> vertices = g.vertices();
	  Iterator<Edge> edges = g.edges();
	  while (vertices.hasNext()){
		  //initialize all the vertices in the graph before doing stuff
		  Vertex newV = vertices.next();
		  _isVisited.setDecoration(newV,false);
		  this.makeSet(newV);
	  }
	  while (edges.hasNext()){
		  Edge newE = edges.next();
		  _isContained.setDecoration(newE, false);
	  }
	  ArrayList<Edge> mst = new ArrayList<Edge>();
	  PriorityQueue<Edge> heap = this.makeHeap(g.edges());
	  while (!heap.isEmpty()){
		  //pick the lowest edge and add it if it doesn't create a cycle in the new graph
		  Edge edge = heap.remove();
		  List<Vertex> verts = g.endVertices(edge);
		  if (!this.find(verts.get(0)).equals(this.find(verts.get(1)))){
		  		mst.add(edge);
		  		_isContained.setDecoration(edge, true);
		  		this.union(verts.get(0), verts.get(1));	
		  }
	  }
	  return mst;
  }
  
  private void setupInstanceVars(){
	  //initializes the instance variables before the algorithm
	  _cloud = new Decorator<Vertex,Vertex>();
	  _rank = new Decorator<Vertex,Integer>();
	  _isContained = new Decorator<Edge,Boolean>();
	  _isVisited = new Decorator<Vertex,Boolean>(); 
  }
  
  private void makeSet(Vertex vertex){
	  /*
	   * Purpose: before the meat of the algo starts, I need to give every vertex its own
	   * cloud and set its rank
	   * Input: a vertex
	   * Output: nothing, but the decorators is changed
	   */
	  _cloud.setDecoration(vertex, vertex);
	  _rank.setDecoration(vertex, 0);
  	}
 
  /*
   * Purpose: path compression... when combining clouds, I want to do it such that the
   * lower ranked cloud joins the higher ranked one.  Uses recursion to find the root and set
   * the cloud to it
   * Input: a vertex 
   * Output: the root of the associated cloud
   */
  private Vertex find(Vertex vertex){
	  if (vertex != _cloud.getDecoration(vertex)){
		  _cloud.setDecoration(vertex, this.find(_cloud.getDecoration(vertex)));
	  }
	  return _cloud.getDecoration(vertex);
  }
  
  /*
   * Purpose: method for joining clouds in a graph
   * Input: the two end vertices of a popped edge
   * Output: nothing, but sets the clouds and rank when joining
   */
  private void union(Vertex x, Vertex y){
	  Vertex rootX = this.find(x);
	  Vertex rootY = this.find(y);
	  if (rootX == rootY){
		  // There's no reason to continue if they're in the same cloud
		  return;
	  }
	  if (_rank.getDecoration(rootX) > _rank.getDecoration(rootY)){
		  // Adding y to x's cloud
		  _cloud.setDecoration(rootY, rootX);
		  }
	  else{
		  // Adding x to y's cloud, and if they have the same rank, I increment
		  _cloud.setDecoration(rootX, rootY);
		  if (_rank.getDecoration(rootX) == _rank.getDecoration(rootY)){
			  _rank.setDecoration(rootY,(_rank.getDecoration(rootY)+1));
			  }
		  }
	  }

  /*
   * Purpose: constructs the heap for sorting the edges
   * Input: the edges in the graph
   * Output: a heap
   */
  private PriorityQueue<Edge> makeHeap(Iterator<Edge> edges){
	// The lambda thing is telling the pq to compare edges based on their elements (their values/weights)
  	PriorityQueue<Edge> heap = new PriorityQueue<Edge>((a,b) -> (int) (a.getElement() - b.getElement()));
  	while (edges.hasNext()){
  		Edge newE = edges.next();
  		heap.add(newE);
  		}
  	return heap;
  	}
}

