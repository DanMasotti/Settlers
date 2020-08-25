package Settlers;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

   /*
    * This is the main data structure of this project
    * implemented using an adjacency matrix, and is parameterized 
    * by vertices and edges
    */
public class Graph {

    // The underlying data structure of my graph: the adjacency matrix
    private Edge[][] _adjMatrix;
    

    // Sets to store the vertices and edges of my graph
    private Set<Vertex> _vertices;
    private Set<Edge> _edges;
    
    // Structure for recycling vertex numbers
    private Integer _currVert;
    
    
    public Graph() {
        _adjMatrix = this.makeEmptyEdgeArray();
        _currVert = 0;
        _vertices = new HashSet<Vertex>();
        _edges = new HashSet<Edge>(); 
    }
    
    // Returns the set of vertices
    public Iterator<Vertex> vertices() {
        return _vertices.iterator();
    }

    // Returns the set of edges
    public Iterator<Edge> edges() {
        return _edges.iterator();
    }
	// Inserts a vertex
    public Vertex insertVertex(double[] coordinates) {
    		Vertex newVert = new Vertex(coordinates);
    		newVert.setVertexNumber(_currVert);
    		_vertices.add(newVert);
    		_currVert = _currVert + 1;
        return newVert;
    }

 	/*
	 * Purpose: inserts a new edge
	 * Input: two vertices and an element to store in the edge
	 * Output: a new edge
	 */

    public Edge insertEdge(Vertex v1, Vertex v2, Integer edgeElement) {
    		Edge newEdge = new Edge(v1, v2, edgeElement);
        	newEdge.setFromVertex(v1);
        	newEdge.setToVertex(v2);
        	// This is an undirected graph so it goes in both spots
        	_adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] = newEdge;
        	_adjMatrix[v2.getVertexNumber()][v1.getVertexNumber()]= newEdge;
        	_edges.add(newEdge);
        return newEdge;
    	}
    	
	/*
	 * Purpose: returns the edge that connects the vertices
	 * Input: two vertices
	 * Output: the edge 
	 */
    public Edge connectingEdge(Vertex v1, Vertex v2){
    		return _adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()];
    	}
    
	/*
	 * Purpose: gets the edges that are connected to a vertex
	 * Input: a vertex
	 * Output: the incident edges to that vertex
	 */
    public Iterator<Edge> incidentEdges(Vertex vert) {
    		Set<Edge> incEdges = new HashSet<Edge>();
    		int vertIndex = vert.getVertexNumber();    	
    		for (int row = 0; row<Constants.MAX_VERTICES; row++){
    			if (_adjMatrix[row][vertIndex] != null){
    				incEdges.add(_adjMatrix[row][vertIndex]);
    			}
    		}
    		for (int col = 0; col<Constants.MAX_VERTICES; col++){
    			if (_adjMatrix[vertIndex][col] != null){
    				incEdges.add(_adjMatrix[vertIndex][col]);
    				}
    			}
    		return incEdges.iterator();
    		}
    	
   	/*
   	 * Purpose: gets the vertex that is across an edge connected to a vertex
     * Input: a vertex and an edge
     * Output: the vertex at the other end
   	 */  	
   public Vertex opposite(Vertex vert, Edge edge){

   		Vertex opp = new Vertex(new double[] {0,0});
       	if (edge.getFromVertex()==vert){
      		opp = edge.getToVertex();
        	}
        	else{
       		opp = edge.getFromVertex();
        	}
       	if (!this.areAdjacent(vert,opp)){
        		System.out.println("this edge is not even connected to this vertex");
       	}
        	else{
        	return opp;
       	}
			return opp;
    	}
    
	/*
	 * Purpose: returns the vertices that are connected by this edge
	 * Input: an edge
	 * Output: a list of two vertices containing the end vertices
	 */
    public ArrayList<Vertex> endVertices(Edge e){
    		ArrayList<Vertex> list = new ArrayList<Vertex>();
        	list.add(e.getFromVertex());
        	list.add(e.getToVertex());
        return list;
    	}
    	
    /*
	 * Purpose: checks if the edges are next to each other or not
	 * Input: two vertices 
	 * Output: a boolean indicating if they are adjacent or not
	 */
    public boolean areAdjacent(Vertex v1, Vertex v2){
    		Edge entry = _adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()];
    		return isValid(entry);
    	}
    
    /*
	 * Clears everything
	 */
    public void clear() {
    	_edges.clear();
    	_vertices.clear();
    	_adjMatrix = this.makeEmptyEdgeArray(); 	
    }

    
	/*
	 * Gives a new adjacency matrix
	 */
    private Edge[][] makeEmptyEdgeArray() {
        return new Edge[Constants.MAX_VERTICES][Constants.MAX_VERTICES];
    }
    
	/*
	 * Purpose: checks if any object given is null before proceeding with whatever task
	 * Input: any object; vertex, edge, or entry in this case
	 * Output: a boolean corresponding to whether a input is valid or not
	 */
    private boolean isValid(Object e){
    	if (e == null){
    		return false;
    		}
    	else{
    		return true;
    		}
    }
}
