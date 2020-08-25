package Settlers;

import javafx.scene.layout.Pane;

/*
 *	This is the board class which makes the game board GUI and connects it to the underlying data
 * structure
 * TODO: I modelled the board after the default Catan board and didn't worry about randomized boards
 */
public class Board {
	
	Graph _graph;
	double[][] _coordinates;

	public Board(Pane pane, Graph graph) {
		_graph = graph;
		//these numbers were experimentally found
		_coordinates = Constants.VERTEX_COORDINATES;
		for (int row = 0; row<114; row++) {
			for (int col = 0; col < 2; col++) {
				double newNum = _coordinates[row][col];
				_coordinates[row][col] = Constants.SCALE*newNum;
			}
		}
		this.makeBoard(pane);
	}

	/*
	 * Helper for the graphics and the data structure
	 */
	private void makeBoard(Pane pane) {
		this.makeGUI(pane);
		this.buildGraph(pane);
	}

	/*
	 * Builds the graph, done by modelling the default board as a
	 * graph
	 */
	private void buildGraph(Pane pane) {
		Vertex v0 = _graph.insertVertex(_coordinates[0]);
		Vertex v1 = _graph.insertVertex(_coordinates[1]);
		Vertex v2 = _graph.insertVertex(_coordinates[2]);
		Vertex v3 = _graph.insertVertex(_coordinates[3]);
		Vertex v4 = _graph.insertVertex(_coordinates[4]);
		Vertex v5 = _graph.insertVertex(_coordinates[5]);
		Vertex v6 = _graph.insertVertex(_coordinates[6]);
		Vertex v7 = _graph.insertVertex(_coordinates[9]);
		Vertex v8 = _graph.insertVertex(_coordinates[10]);
		Vertex v9 = _graph.insertVertex(_coordinates[11]);
		Vertex v10 = _graph.insertVertex(_coordinates[12]);
		Vertex v11 = _graph.insertVertex(_coordinates[15]);
		Vertex v12 = _graph.insertVertex(_coordinates[16]);
		Vertex v13 = _graph.insertVertex(_coordinates[17]);
		Vertex v14 = _graph.insertVertex(_coordinates[19]);
		Vertex v15 = _graph.insertVertex(_coordinates[20]);
		Vertex v16 = _graph.insertVertex(_coordinates[21]);
		Vertex v17 = _graph.insertVertex(_coordinates[22]);
		Vertex v18 = _graph.insertVertex(_coordinates[27]);
		Vertex v19 = _graph.insertVertex(_coordinates[28]);
		Vertex v20 = _graph.insertVertex(_coordinates[33]);
		Vertex v21 = _graph.insertVertex(_coordinates[34]);
		Vertex v22 = _graph.insertVertex(_coordinates[39]);
		Vertex v23 = _graph.insertVertex(_coordinates[40]);
		Vertex v24 = _graph.insertVertex(_coordinates[41]);
		Vertex v25 = _graph.insertVertex(_coordinates[43]);
		Vertex v26 = _graph.insertVertex(_coordinates[44]);
		Vertex v27 = _graph.insertVertex(_coordinates[45]);
		Vertex v28 = _graph.insertVertex(_coordinates[46]);
		Vertex v29 = _graph.insertVertex(_coordinates[51]);
		Vertex v30 = _graph.insertVertex(_coordinates[52]);
		Vertex v31 = _graph.insertVertex(_coordinates[57]);
		Vertex v32 = _graph.insertVertex(_coordinates[58]);
		Vertex v33 = _graph.insertVertex(_coordinates[63]);
		Vertex v34 = _graph.insertVertex(_coordinates[64]);
		Vertex v35 = _graph.insertVertex(_coordinates[69]);
		Vertex v36 = _graph.insertVertex(_coordinates[70]);
		Vertex v37 = _graph.insertVertex(_coordinates[71]);
		Vertex v38 = _graph.insertVertex(_coordinates[74]);
		Vertex v39 = _graph.insertVertex(_coordinates[75]);
		Vertex v40 = _graph.insertVertex(_coordinates[76]);
		Vertex v41 = _graph.insertVertex(_coordinates[81]);
		Vertex v42 = _graph.insertVertex(_coordinates[82]);
		Vertex v43 = _graph.insertVertex(_coordinates[87]);
		Vertex v44 = _graph.insertVertex(_coordinates[88]);
		Vertex v45 = _graph.insertVertex(_coordinates[93]);
		Vertex v46 = _graph.insertVertex(_coordinates[94]);
		Vertex v47 = _graph.insertVertex(_coordinates[98]);
		Vertex v48 = _graph.insertVertex(_coordinates[99]);
		Vertex v49 = _graph.insertVertex(_coordinates[100]);
		Vertex v50 = _graph.insertVertex(_coordinates[105]);
		Vertex v51 = _graph.insertVertex(_coordinates[106]);
		Vertex v52 = _graph.insertVertex(_coordinates[111]);
		Vertex v53 = _graph.insertVertex(_coordinates[112]);
		_graph.insertEdge(v0, v1, 100);
		_graph.insertEdge(v1, v2, 100);
		_graph.insertEdge(v2, v3, 100);
		_graph.insertEdge(v3, v4, 100);
		_graph.insertEdge(v4, v5, 100);
		_graph.insertEdge(v5, v0, 100);
		_graph.insertEdge(v5, v6, 100);
		_graph.insertEdge(v4, v7, 100);
		_graph.insertEdge(v7, v8, 100);
		_graph.insertEdge(v8, v9, 100);
		_graph.insertEdge(v9, v6, 100);
		_graph.insertEdge(v10, v9, 100);;
		_graph.insertEdge(v8, v11, 100);
		_graph.insertEdge(v11, v12, 100);
		_graph.insertEdge(v12, v13, 100);
		_graph.insertEdge(v13, v10, 100);
		_graph.insertEdge(v2, v14, 100);
		_graph.insertEdge(v14, v15, 100);
		_graph.insertEdge(v15, v16, 100);
		_graph.insertEdge(v16, v17, 100);
		_graph.insertEdge(v17, v3, 100);
		_graph.insertEdge(v17, v18, 100);
		_graph.insertEdge(v18, v19, 100);
		_graph.insertEdge(v19, v7, 100);
		_graph.insertEdge(v19, v20, 100);
		_graph.insertEdge(v20, v21, 100);
		_graph.insertEdge(v21, v11, 100);
		_graph.insertEdge(v21, v22, 100);
		_graph.insertEdge(v22, v23, 100);
		_graph.insertEdge(v23, v24, 100);
		_graph.insertEdge(v24, v12, 100);
		_graph.insertEdge(v15, v25, 100);
		_graph.insertEdge(v25, v26, 100);
		_graph.insertEdge(v26, v27, 100);
		_graph.insertEdge(v27, v28, 100);
		_graph.insertEdge(v28, v16, 100);
		_graph.insertEdge(v28, v29, 100);
		_graph.insertEdge(v29, v30, 100);
		_graph.insertEdge(v30, v18, 100);
		_graph.insertEdge(v30, v31, 100);
		_graph.insertEdge(v31, v32, 100);
		_graph.insertEdge(v32, v20, 100);
		_graph.insertEdge(v32, v33, 100);
		_graph.insertEdge(v33, v34, 100);
		_graph.insertEdge(v34, v22, 100);
		_graph.insertEdge(v34, v35, 100);
		_graph.insertEdge(v35, v36, 100);
		_graph.insertEdge(v36, v37, 100);
		_graph.insertEdge(v37, v23, 100);
		_graph.insertEdge(v27, v38, 100);
		_graph.insertEdge(v38, v39, 100);
		_graph.insertEdge(v39, v40, 100);
		_graph.insertEdge(v40, v29, 100);
		_graph.insertEdge(v40, v41, 100);
		_graph.insertEdge(v41, v42, 100);
		_graph.insertEdge(v42, v31, 100);
		_graph.insertEdge(v42, v43, 100);
		_graph.insertEdge(v43, v44, 100);
		_graph.insertEdge(v44, v33, 100);
		_graph.insertEdge(v44, v45, 100);
		_graph.insertEdge(v45, v46, 100);
		_graph.insertEdge(v46, v35, 100);
		_graph.insertEdge(v39, v47, 100);
		_graph.insertEdge(v47, v48, 100);
		_graph.insertEdge(v48, v49, 100);
		_graph.insertEdge(v49, v41, 100);
		_graph.insertEdge(v49, v50, 100);
		_graph.insertEdge(v50, v51, 100);
		_graph.insertEdge(v51, v43, 100);
		_graph.insertEdge(v51, v52, 100);
		_graph.insertEdge(v52, v53, 100);
		_graph.insertEdge(v53, v45, 100);
		
		v0.addResource(Resource.BRICK,5);		
		v1.addResource(Resource.BRICK,5);
		v2.addResource(Resource.BRICK,5);
		v2.addResource(Resource.LUMBER,8);
		v3.addResource(Resource.BRICK,5);
		v3.addResource(Resource.LUMBER,8);
		v3.addResource(Resource.ORE,3);
		v4.addResource(Resource.BRICK,5);
		v4.addResource(Resource.ORE,3);
		v4.addResource(Resource.GRAIN,6);
		v5.addResource(Resource.BRICK,5);
		v5.addResource(Resource.GRAIN,6);
		v6.addResource(Resource.GRAIN,6);
		v7.addResource(Resource.GRAIN,6);
		v7.addResource(Resource.ORE,3);
		v7.addResource(Resource.GRAIN,4);
		v8.addResource(Resource.GRAIN,6);
		v8.addResource(Resource.GRAIN,4);
		v8.addResource(Resource.WOOL,11);
		v9.addResource(Resource.GRAIN,6);
		v9.addResource(Resource.WOOL,11);
		v7.addResource(Resource.GRAIN,6);
		v10.addResource(Resource.WOOL,11);
		v11.addResource(Resource.WOOL,11);
		v11.addResource(Resource.GRAIN,4);
		v11.addResource(Resource.WOOL,5);
		v12.addResource(Resource.WOOL,11);
		v12.addResource(Resource.WOOL,5);
		v13.addResource(Resource.WOOL,11);
		v14.addResource(Resource.BRICK,5);
		v14.addResource(Resource.LUMBER,8);
		v15.addResource(Resource.LUMBER,8);
		v15.addResource(Resource.GRAIN,9);
		v16.addResource(Resource.LUMBER,8);
		v16.addResource(Resource.GRAIN,9);
		v16.addResource(Resource.LUMBER,11);
		v17.addResource(Resource.LUMBER,8);
		v17.addResource(Resource.LUMBER,11);
		v17.addResource(Resource.ORE,3);
		v18.addResource(Resource.ORE, 3);
		v18.addResource(Resource.LUMBER, 11);
		v19.addResource(Resource.ORE, 3);
		v19.addResource(Resource.GRAIN, 4);
		v20.addResource(Resource.GRAIN, 4);
		v20.addResource(Resource.LUMBER, 3);
		v21.addResource(Resource.GRAIN, 4);
		v21.addResource(Resource.LUMBER, 3);
		v21.addResource(Resource.WOOL, 5);
		v22.addResource(Resource.LUMBER, 3);
		v22.addResource(Resource.WOOL, 5);
		v22.addResource(Resource.ORE, 8);
		v23.addResource(Resource.WOOL, 5);
		v23.addResource(Resource.ORE, 8);
		v24.addResource(Resource.WOOL, 5);
		v25.addResource(Resource.GRAIN, 9);
		v26.addResource(Resource.GRAIN, 9);
		v27.addResource(Resource.GRAIN, 9);
		v27.addResource(Resource.GRAIN, 12);
		v28.addResource(Resource.GRAIN, 9);
		v28.addResource(Resource.GRAIN, 12);
		v29.addResource(Resource.GRAIN, 12);
		v29.addResource(Resource.LUMBER, 11);
		v29.addResource(Resource.BRICK, 6);
		v30.addResource(Resource.LUMBER, 11);
		v30.addResource(Resource.BRICK, 6);
		v31.addResource(Resource.BRICK, 6);
		v31.addResource(Resource.WOOL, 4);
		v32.addResource(Resource.LUMBER, 3);
		v32.addResource(Resource.WOOL, 4);
		v33.addResource(Resource.LUMBER, 3);
		v33.addResource(Resource.WOOL, 4);
		v33.addResource(Resource.BRICK, 10);
		v34.addResource(Resource.LUMBER, 3);
		v34.addResource(Resource.LUMBER, 10);
		v34.addResource(Resource.ORE, 8);
		v35.addResource(Resource.ORE, 8);
		v35.addResource(Resource.BRICK, 10);
		v36.addResource(Resource.ORE, 8);
		v37.addResource(Resource.ORE, 8);
		v38.addResource(Resource.GRAIN, 12);
		v39.addResource(Resource.GRAIN, 12);
		v39.addResource(Resource.ORE, 10);
		v40.addResource(Resource.GRAIN, 12);
		v40.addResource(Resource.ORE, 10);
		v40.addResource(Resource.BRICK, 6);
		v41.addResource(Resource.ORE, 10);
		v41.addResource(Resource.BRICK, 6);
		v41.addResource(Resource.WOOL, 2);
		v42.addResource(Resource.BRICK, 6);
		v42.addResource(Resource.WOOL, 2);
		v42.addResource(Resource.WOOL, 4);
		v43.addResource(Resource.WOOL, 2);
		v43.addResource(Resource.WOOL, 4);
		v44.addResource(Resource.WOOL, 4);
		v44.addResource(Resource.BRICK, 10);
		v45.addResource(Resource.BRICK, 10);
		v45.addResource(Resource.LUMBER, 9);
		v46.addResource(Resource.BRICK, 10);
		v47.addResource(Resource.ORE, 10);
		v48.addResource(Resource.ORE, 10);
		v49.addResource(Resource.ORE, 10);
		v49.addResource(Resource.WOOL, 2);
		v50.addResource(Resource.WOOL, 2);
		v51.addResource(Resource.WOOL, 2);
		v51.addResource(Resource.LUMBER, 9);
		v52.addResource(Resource.LUMBER, 9);
		v53.addResource(Resource.LUMBER, 9);
		
	}

	/*
	 * Graphical elements (colored Hex's)
	 */
	private void makeGUI(Pane pane) {
		new Hex(
				pane, 
				_coordinates[0][0],
				_coordinates[0][1],
				_coordinates[1][0],
				_coordinates[1][1],
				_coordinates[2][0],
				_coordinates[2][1],
				_coordinates[3][0],
				_coordinates[3][1],
				_coordinates[4][0],
				_coordinates[4][1],
				_coordinates[5][0],
				_coordinates[5][1],
				Resource.BRICK, 
				5);	
		new Hex(
				pane,
				_coordinates[6][0],
				_coordinates[6][1],
				_coordinates[7][0],
				_coordinates[7][1],
				_coordinates[8][0],
				_coordinates[8][1],
				_coordinates[9][0],
				_coordinates[9][1],
				_coordinates[10][0],
				_coordinates[10][1],
				_coordinates[11][0],
				_coordinates[11][1],
				Resource.GRAIN,
				6
				);
		new Hex(
				pane,
				_coordinates[12][0],
				_coordinates[12][1],
				_coordinates[13][0],
				_coordinates[13][1],
				_coordinates[14][0],
				_coordinates[14][1],
				_coordinates[15][0],
				_coordinates[15][1],
				_coordinates[16][0],
				_coordinates[16][1],
				_coordinates[17][0],
				_coordinates[17][1],
				Resource.WOOL,
				11
				);
		new Hex(
				pane,
				_coordinates[18][0],
				_coordinates[18][1],
				_coordinates[19][0],
				_coordinates[19][1],
				_coordinates[20][0],
				_coordinates[20][1],
				_coordinates[21][0],
				_coordinates[21][1],
				_coordinates[22][0],
				_coordinates[22][1],
				_coordinates[23][0],
				_coordinates[23][1],
				Resource.LUMBER,
				8
				);
		new Hex(
				pane,
				_coordinates[24][0],
				_coordinates[24][1],
				_coordinates[25][0],
				_coordinates[25][1],
				_coordinates[26][0],
				_coordinates[26][1],
				_coordinates[27][0],
				_coordinates[27][1],
				_coordinates[28][0],
				_coordinates[28][1],
				_coordinates[29][0],
				_coordinates[29][1],
				Resource.ORE,
				3
				);
		new Hex(
				pane,
				_coordinates[30][0],
				_coordinates[30][1],
				_coordinates[31][0],
				_coordinates[31][1],
				_coordinates[32][0],
				_coordinates[32][1],
				_coordinates[33][0],
				_coordinates[33][1],
				_coordinates[34][0],
				_coordinates[34][1],
				_coordinates[35][0],
				_coordinates[35][1],
				Resource.GRAIN,
				4
				);
		new Hex(
				pane,
				_coordinates[36][0],
				_coordinates[36][1],
				_coordinates[37][0],
				_coordinates[37][1],
				_coordinates[38][0],
				_coordinates[38][1],
				_coordinates[39][0],
				_coordinates[39][1],
				_coordinates[40][0],
				_coordinates[40][1],
				_coordinates[41][0],
				_coordinates[41][1],
				Resource.WOOL,
				5
				);
		new Hex(
				pane,
				_coordinates[42][0],
				_coordinates[42][1],
				_coordinates[43][0],
				_coordinates[43][1],
				_coordinates[44][0],
				_coordinates[44][1],
				_coordinates[45][0],
				_coordinates[45][1],
				_coordinates[46][0],
				_coordinates[46][1],
				_coordinates[47][0],
				_coordinates[47][1],
				Resource.GRAIN,
				9
				);
		new Hex(
				pane,
				_coordinates[48][0],
				_coordinates[48][1],
				_coordinates[49][0],
				_coordinates[49][1],
				_coordinates[50][0],
				_coordinates[50][1],
				_coordinates[51][0],
				_coordinates[51][1],
				_coordinates[52][0],
				_coordinates[52][1],
				_coordinates[53][0],
				_coordinates[53][1],
				Resource.LUMBER,
				11
				);
		new Hex(
				pane,
				_coordinates[54][0],
				_coordinates[54][1],
				_coordinates[55][0],
				_coordinates[55][1],
				_coordinates[56][0],
				_coordinates[56][1],
				_coordinates[57][0],
				_coordinates[57][1],
				_coordinates[58][0],
				_coordinates[58][1],
				_coordinates[59][0],
				_coordinates[59][1],
				Resource.DESERT,
				0
				);
		new Hex(
				pane,
				_coordinates[60][0],
				_coordinates[60][1],
				_coordinates[61][0],
				_coordinates[61][1],
				_coordinates[62][0],
				_coordinates[62][1],
				_coordinates[63][0],
				_coordinates[63][1],
				_coordinates[64][0],
				_coordinates[64][1],
				_coordinates[65][0],
				_coordinates[65][1],
				Resource.LUMBER,
				3
				);
		new Hex(
				pane,
				_coordinates[66][0],
				_coordinates[66][1],
				_coordinates[67][0],
				_coordinates[67][1],
				_coordinates[68][0],
				_coordinates[68][1],
				_coordinates[69][0],
				_coordinates[69][1],
				_coordinates[70][0],
				_coordinates[70][1],
				_coordinates[71][0],
				_coordinates[71][1],
				Resource.ORE,
				8
				);
		new Hex(
				pane,
				_coordinates[72][0],
				_coordinates[72][1],
				_coordinates[73][0],
				_coordinates[73][1],
				_coordinates[74][0],
				_coordinates[74][1],
				_coordinates[75][0],
				_coordinates[75][1],
				_coordinates[76][0],
				_coordinates[76][1],
				_coordinates[77][0],
				_coordinates[77][1],
				Resource.GRAIN,
				12
				);
		new Hex(
				pane,
				_coordinates[78][0],
				_coordinates[78][1],
				_coordinates[79][0],
				_coordinates[79][1],
				_coordinates[80][0],
				_coordinates[80][1],
				_coordinates[81][0],
				_coordinates[81][1],
				_coordinates[82][0],
				_coordinates[82][1],
				_coordinates[83][0],
				_coordinates[83][1],
				Resource.BRICK,
				6
				);
		new Hex(
				pane,
				_coordinates[84][0],
				_coordinates[84][1],
				_coordinates[85][0],
				_coordinates[85][1],
				_coordinates[86][0],
				_coordinates[86][1],
				_coordinates[87][0],
				_coordinates[87][1],
				_coordinates[88][0],
				_coordinates[88][1],
				_coordinates[89][0],
				_coordinates[89][1],
				Resource.WOOL,
				4
				);
		new Hex(
				pane,
				_coordinates[90][0],
				_coordinates[90][1],
				_coordinates[91][0],
				_coordinates[91][1],
				_coordinates[92][0],
				_coordinates[92][1],
				_coordinates[93][0],
				_coordinates[93][1],
				_coordinates[94][0],
				_coordinates[94][1],
				_coordinates[95][0],
				_coordinates[95][1],
				Resource.BRICK,
				10
				);
		new Hex(
				pane,
				_coordinates[96][0],
				_coordinates[96][1],
				_coordinates[97][0],
				_coordinates[97][1],
				_coordinates[98][0],
				_coordinates[98][1],
				_coordinates[99][0],
				_coordinates[99][1],
				_coordinates[100][0],
				_coordinates[100][1],
				_coordinates[101][0],
				_coordinates[101][1],
				Resource.ORE,
				10
				);
		new Hex(
				pane,
				_coordinates[102][0],
				_coordinates[102][1],
				_coordinates[103][0],
				_coordinates[103][1],
				_coordinates[104][0],
				_coordinates[104][1],
				_coordinates[105][0],
				_coordinates[105][1],
				_coordinates[106][0],
				_coordinates[106][1],
				_coordinates[107][0],
				_coordinates[107][1],
				Resource.WOOL,
				2
				);
		new Hex(
				pane,
				_coordinates[108][0],
				_coordinates[108][1],
				_coordinates[109][0],
				_coordinates[109][1],
				_coordinates[110][0],
				_coordinates[110][1],
				_coordinates[111][0],
				_coordinates[111][1],
				_coordinates[112][0],
				_coordinates[112][1],
				_coordinates[113][0],
				_coordinates[113][1],
				Resource.LUMBER,
				9
				);
	}
}

