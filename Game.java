package Indy;


import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/*
 * here's my game class! It handles the bulk of the game logic: distributing resources,
 * rolling dice, changing players, interaction with the board, etc
 */

public class Game {

	private Pane _piecePane;
	private SideBar _sideBar;
	private Pane _root;
	private Graph _graph;
	private Player _currTurn;
	private ArrayList<Player> _playerList;
	private Strategy _strategy;
	private Label _label;
	private Player _blue;
	private Player _red;
	private Player _orange;
	private Player _white;
	private boolean _waitingOnHuman;
	private PieceType _answer;
	private int _iteration;
    private int _roll;


	public Game(Pane root) {
		_waitingOnHuman=true;
		_iteration = 0;
        //don't wanna distribute resources on first round
        _roll = 0;
		_root = root;
		_label = new Label("");
		this.setupPane();
		_root.setFocusTraversable(false);
		_graph = new Graph();
		new Board(_piecePane, _graph);
		_root.getChildren().addAll(_piecePane);
		this.startGame();
	}

	/*
	 * sets up some of the javafx interactions and mousehandler
	 */
	private void setupPane() {
		_piecePane = new Pane();
		_piecePane.addEventHandler(MouseEvent.ANY, new MouseHandler());
		_piecePane.setFocusTraversable(true);
		_piecePane.requestFocus();
	}

	/*
	 * gives the game the side bar that's instantiated in pane organizer
	 * I need constant access to this as it needs to update the players' scores
	 */
	public void setSideBar(SideBar sB) {
		_sideBar = sB;
	}

	/*
	 * this is like the ignition of the game, it sets up the players, initial distribution of
	 * resources, and starts the game cycle by calling taketurn
	 */
	public void startGame() {
		this.setStrategy(Strategy.BUILD);
		_playerList = new ArrayList<Player>(4);
		_blue = new HumanPlayer(Property.BLUE);
		_red = new HumanPlayer(Property.RED);
		_orange = new BotPlayer(Property.ORANGE);
		_white = new BotPlayer(Property.WHITE);
		_currTurn = _white;
		_playerList.add(_blue);
		_playerList.add(_orange);
		_playerList.add(_red);
		_playerList.add(_white);
		//in the beginning we want players to set up 2 settlements and 2 roads
		for (Player p : _playerList) {
			p.collect(Resource.LUMBER,4);
			p.collect(Resource.BRICK, 4);
			p.collect(Resource.GRAIN, 2);
			p.collect(Resource.WOOL, 2);
		}
		//arbitrarily set blue as player 1
		_currTurn = _white;
		this.takeTurn();
	}

	/*
	 * this method outlines the cycle of the game:
	 * 	1) count iteration (used for framing the AI)
	 * 	2) next player
	 * 	3) update current player -- is it a human
	 * `4) roll dice
	 * 	5) distribute resources to players
	 * 	6) humans and bots build or trade
	 * 	7) settle
	 * 	8) did someone win
	 * 	9) repeat
	 */
	private void takeTurn() {
		// TODO Auto-generated method stub
		_iteration = _iteration + 1;
		_currTurn = this.getNextPlayer();
		this.update();
		_waitingOnHuman = !this.isBot(_currTurn);
		int roll =(int) (Math.random()*6+1) +(int) (Math.random()*6+1);
        if (_iteration >= 5){
            _roll = roll;
        }
		//not fun if you're robbed in the first round
		if (roll == 7 && _iteration > 4) {
			this.robberHelper();
		}
		//distributing the resources
		Iterator<Vertex> verts = _graph.vertices();
		while(verts.hasNext()) {
			Vertex vert = verts.next();
			Property prop = vert.getProperty();
			if (!prop.equals(Property.FREE) || !prop.equals(Property.INVALID)) {
				ArrayList<Integer> rolls = vert.getRolls();
				for (int i : rolls) {
					if (i == roll) {
						ArrayList<Resource> res = vert.rollToResource(roll);
						for (Player p : _playerList) {
							if (p.getProperty().equals(prop)){
								for (Resource r : res) {
									p.collect(r, 1);
									if (vert.getPiece().getType().equals(PieceType.CITY)) {
										p.collect(r, 1);
									}
									_sideBar.update(_playerList);
									}
								}
							}
						}
					}
				}
			}
		//bots move
		if (!_waitingOnHuman) {
			ArrayList<Edge> mst = _currTurn.makeMove(_graph, _iteration);
			this.botHelper(mst);
			_strategy = Strategy.SETTLE;
			this.moveHelper();
		}
		//check if someone won
		if (this.isOver()) {
			return;
		}
	}

	/*
	 * helper method for the bot to interact with the GUI
	 */
	private void botHelper(ArrayList<Edge> mst) {
		// TODO Auto-generated method stub
		this.botMakeTrade();
		//early in the game the bot should prioritize building houses
		_strategy=Strategy.BUILD;
		if (_iteration < Constants.INFLECTION_ROUND) {
			for (Edge edge : mst) {
				Vertex v1 = edge.getFromVertex();
				Vertex v2 = edge.getToVertex();
				if (v1.getProperty().equals(Property.FREE)) {
					this.buildSettlement(v1);
				}
				if (v2.getProperty().equals(Property.FREE)) {
					this.buildSettlement(v2);
				}
				if (edge.getProperty().equals(Property.FREE)) {
					this.buildRoad(edge);
				}
				if (v1.getProperty().equals(_currTurn.getProperty())){
					this.buildCity(v1);
				}
				if (v2.getProperty().equals(_currTurn.getProperty())) {
					this.buildCity(v2);
				}
			}
		}
		//later in the game the bot should prioritize cities
		else {
			for (Edge edge : mst) {
				Vertex v1 = edge.getFromVertex();
				Vertex v2 = edge.getToVertex();
				if (v1.getProperty().equals(_currTurn.getProperty())){
					this.buildCity(v1);
				}
				if (v2.getProperty().equals(_currTurn.getProperty())) {
					this.buildCity(v2);
				}
				if (v1.getProperty().equals(Property.FREE)) {
					this.buildSettlement(v1);
				}
				if (v2.getProperty().equals(Property.FREE)) {
					this.buildSettlement(v2);
				}
				if (edge.getProperty().equals(Property.FREE)) {
					this.buildRoad(edge);
				}
			}
		}
	}

	/*
	 * helper method for the bot making trades
	 * follows similar structure to the trade box but is
	 * done internally
	 */
	private void botMakeTrade() {
		_strategy = Strategy.TRADE;
        int woodNum = 0;
		int brickNum = 0;
		int hayNum = 0;
		int oreNum = 0;
		ArrayList<Resource> basket = _currTurn.getBasket();
		for (Resource item : basket) {
			switch(item) {
			case BRICK:
				brickNum ++;
				break;
			case LUMBER:
				woodNum ++;
				break;
			case GRAIN:
				hayNum ++;
				break;
			case ORE:
				oreNum++;
				break;
			default: break;
			}
		}
		//early in the game, the bot should trade ore/hay for wood/brick
		if (_iteration < Constants.INFLECTION_ROUND) {
			if (woodNum == 0) {
				//trade with the bank for lumber
				if (oreNum>=4) {
					_currTurn.collect(Resource.LUMBER,1);
					_currTurn.removeResource(Resource.ORE, 4);
				}
				if (hayNum>=4) {
					_currTurn.collect(Resource.LUMBER, 1);
					_currTurn.removeResource(Resource.GRAIN, 4);
				}
			}
			if (brickNum==0) {
				if (oreNum>=4) {
					_currTurn.collect(Resource.BRICK,1);
					_currTurn.removeResource(Resource.ORE, 4);
				}
				if (hayNum>=4) {
					_currTurn.collect(Resource.BRICK, 1);
					_currTurn.removeResource(Resource.GRAIN, 4);
				}
			}
		}
		//later in the game, get ore and grain for cities
		else {
			if (oreNum == 0) {
				//trade with the bank for lumber
				if (brickNum>=4) {
					_currTurn.collect(Resource.ORE,1);
					_currTurn.removeResource(Resource.BRICK, 4);
				}
				if (woodNum>=4) {
					_currTurn.collect(Resource.ORE, 1);
					_currTurn.removeResource(Resource.LUMBER, 4);
				}
			}
			if (hayNum==0) {
				if (brickNum>=4) {
					_currTurn.collect(Resource.GRAIN,1);
					_currTurn.removeResource(Resource.BRICK, 4);
				}
				if (woodNum>=4) {
					_currTurn.collect(Resource.GRAIN, 1);
					_currTurn.removeResource(Resource.LUMBER, 4);
				}
			}
		}
		_sideBar.update(_playerList);
	}

	/*
	 * helper method to rob users with more than 7 resources
	 * note: in the original game, the current player gets to rob another player
	 * but I haven't implemented this, the robber just takes resources from users with
	 * 7 or more resources in their basket
	 */
	private void robberHelper() {
		// TODO Auto-generated method stub
		for (Player p : _playerList) {
			ArrayList<Resource> basket = p.getBasket();
			Collections.shuffle(basket);
			int size = basket.size();
			if (size>7) {
				int num = (int) (size/2);
				int i = 0;
				while (i < num) {
					basket.remove(i);
					i++;
				}
			}
		}
	}

	/*
	 * arbitrary method for getting the next player
	 * in this implementation I just started with player blue
	 */
	private Player getNextPlayer() {
		switch (_currTurn.getProperty()) {
		case BLUE:
			return _orange;
		case ORANGE:
			return _red;
		case RED:
			return _white;
		case WHITE:
			return _blue;
		default: return _blue;
		}
	}

	/*
	 * check if the player is a bot
	 */
	private boolean isBot(Player p) {
		if (p.isBot()){
			return true;
		}
		return false;
	}

	/*
	 * used by the side bar
	 */
	public ArrayList<Player> getPlayers(){
		return _playerList;
	}

	/*
	 * top left corner label that tells whos turn it is
	 */
	public void update() {
		_piecePane.getChildren().remove(_label);
		_label.setText("	It is " + _currTurn.getProperty() + "'s turn, the roll was: "+ _roll);
		_label.setTextFill(Color.WHITE);
		_piecePane.getChildren().add(_label);
	}

	/*
	 * takes in the strategy (for the user, its the buttons that send this data) and
	 * appropriately adjusts the game behavior
	 */
	public void moveHelper() {
		//button input
		switch (_strategy) {
		//new window for build mode that captures the human user data for what they'd like to build
		case BUILD:
			BuildBox buildbox = new BuildBox("Build Mode", "What would you like to build?", _currTurn);
			_answer = buildbox.getAnswer();
			break;
		//ends the current turn and returns to the cycle
		case SETTLE:
			this.takeTurn();
			break;
		/*
		 * this sets up the window for trading
		 * I didn't finish impementing trades between players but players can what they want from the bank
		 */
		case TRADE:
			TradeBox tradebox = new TradeBox("Trade Mode", "Make a deal",_currTurn,_playerList);
			//trading 4 of a kind with the bank
			if (tradebox.getNeighbor().equals(Property.BANK)) {
				if (tradebox.getMyQuant() >= 4) {
					_currTurn.removeResource(tradebox.getMyRes(), 4);
					_currTurn.collect(tradebox.getYourRes(), tradebox.getYourQuant());
				}
			}
			_sideBar.update(_playerList);
		default:
			break;
		}
	}

	//getter
	public Strategy getStrategy() {
		return _strategy;
	}

	//setter
	public void setStrategy(Strategy strat) {
		_strategy = strat;
	}

	/*
	 * checks if the player accrued a winning number of points and puts a
	 * big "GAME OVER" over the pane
	 */
	public boolean isOver() {
		for (Player p : _playerList) {
			int points = p.getPoints();
			if (points >= Constants.MAX_POINTS) {
				Label label = new Label(""
						+ "GAME OVER");
				label.setFont(Font.font ("Courier", 80));
				label.setTextFill(Color.RED);
				//makes the Game Over label look scary
				InnerShadow creepyEffect = new InnerShadow();
		        creepyEffect.setOffsetX(2.0f);
		        creepyEffect.setOffsetY(1.0f);
		        label.setEffect(creepyEffect);
				_piecePane.getChildren().add(label);
				return true;
			}
		}
		return false;
	}

	/*
	 * helper method for adding a settlement
	 */
	private void buildSettlement(Vertex vertex) {
		int woodNum = 0;
		int sheepNum = 0;
		int brickNum = 0;
		int hayNum = 0;
		ArrayList<Resource> basket = _currTurn.getBasket();
		for (Resource item : basket) {
			switch(item) {
			case BRICK:
				brickNum ++;
				break;
			case LUMBER:
				woodNum ++;
				break;
			case WOOL:
				sheepNum ++;
				break;
			case GRAIN:
				hayNum ++;
				break;
			default: break;
			}
		}
		if (brickNum >= 1 && woodNum >= 1 && sheepNum >= 1 && hayNum >= 1) {
			_currTurn.addPoints(Constants.SETTLEMENT_POINTS);
			//mutates the vertex into a settlement
			vertex.newProperty(_currTurn.getProperty(), PieceType.SETTLEMENT);
			_piecePane.getChildren().add(vertex.getPiece().getShape());
			//spend
			_currTurn.removeResource(Resource.BRICK, 1);
			_currTurn.removeResource(Resource.LUMBER, 1);
			_currTurn.removeResource(Resource.GRAIN, 1);
			_currTurn.removeResource(Resource.WOOL, 1);
			Iterator<Edge> incEdges = _graph.incidentEdges(vertex);
			//two houses can't be next to each other
			while (incEdges.hasNext()) {
				Edge edge = incEdges.next();
				Vertex opp = _graph.opposite(vertex, edge);
				opp.newProperty(Property.INVALID, PieceType.CAMP);
			}
		}
	}

	/*
	 * same thing for roads
	 */
	private void buildRoad(Edge edge) {
		int woodNum = 0;
		int brickNum = 0;
		ArrayList<Resource> basket = _currTurn.getBasket();
		for (Resource item : basket) {
			switch(item) {
			case BRICK:
				brickNum ++;
				break;
			case LUMBER:
				woodNum ++;
				break;
			default: break;
			}
		}
		//keep roads contiguous
		Vertex v1 =  edge.getFromVertex();
		Vertex v2 =  edge.getToVertex();
		Vertex farFrom = _graph.opposite(v1, edge);
		Vertex farTo = _graph.opposite(v2, edge);
		if (brickNum >= 1 && woodNum >= 1) {
			if (v1.getProperty().equals(_currTurn.getProperty())){
				this.roadHelper(edge);
				}
			else if (v2.getProperty().equals(_currTurn.getProperty())) {
				this.roadHelper(edge);
			}
			else if (farFrom.getProperty().equals(_currTurn.getProperty())) {
				this.roadHelper(edge);
			}
			else if (farTo.getProperty().equals(_currTurn.getProperty())) {
				this.roadHelper(edge);
			}
		}
	}

	/*
	 * need a bit more help with GUI and graph
	 */
	private void roadHelper(Edge edge) {
		edge.newProperty(_currTurn.getProperty());
		_currTurn.removeResource(Resource.BRICK, 1);
		_currTurn.removeResource(Resource.LUMBER, 1);
		edge.newProperty(_currTurn.getProperty());
		_piecePane.getChildren().add(edge.getPiece().getShape());
	}

	/*
	 * same thing but for building a city
	 */
	private void buildCity(Vertex vertex) {
		int hayNum = 0;
		int oreNum = 0;
		ArrayList<Resource> basket = _currTurn.getBasket();
		for (Resource item : basket) {
			switch(item) {
			case GRAIN:
				hayNum ++;
				break;
			case ORE:
				oreNum++;
				break;
			default: break;
			}
		}
		if (hayNum >=2 && oreNum >= 3) {
			_currTurn.addPoints(Constants.SETTLEMENT_POINTS);
			_currTurn.removeResource(Resource.ORE, 3);
			_currTurn.removeResource(Resource.GRAIN, 2);
			vertex.newProperty(_currTurn.getProperty(), PieceType.CITY);
			_piecePane.getChildren().add(vertex.getPiece().getShape());
		}
	}

	//my click handler class
	private class MouseHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent e) {
			// TODO Auto-generated method stub
			if (_waitingOnHuman) {
				if (e.getButton().equals(MouseButton.PRIMARY)) {
					double posX = e.getX();
					double posY = e.getY();
					e.consume();
					Iterator<Vertex> vertices = _graph.vertices();
					Iterator<Edge> edges = _graph.edges();
					switch (_answer) {
						case SETTLEMENT:
							//builds settlement
							while (vertices.hasNext()) {
								Vertex vertex = vertices.next();
								Shape shape = vertex.getPiece().getShape();
								if (shape.contains(posX, posY) && ((vertex.getProperty().equals(Property.FREE)))){
									Game.this.buildSettlement(vertex);
								}
							}
							break;
						case CITY:
							//builds city
							while (vertices.hasNext()) {
								Vertex vertex = vertices.next();
								Shape shape = vertex.getPiece().getShape();
								if (shape.contains(posX, posY) &&
										vertex.getProperty().equals(_currTurn.getProperty()) &&
											vertex.getPiece().getType().equals(PieceType.SETTLEMENT))	{
									Game.this.buildCity(vertex);

								}
							}
							break;
						case ROAD:
							//builds road
							while (edges.hasNext()) {
								Edge edge = edges.next();
								Shape shape = edge.getPiece().getShape();
								if (shape.contains(posX, posY) && edge.getProperty().equals(Property.FREE)){
									Game.this.buildRoad(edge);
								}
							}
							break;
						default: break;
					}
					_sideBar.update(_playerList);
				}
			}
		}
	}
}
