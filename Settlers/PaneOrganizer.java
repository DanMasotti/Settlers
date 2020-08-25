package Settlers;

import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;

/*
 * This is my PaneOrganizer class which sets up all the graphical elements for the game
 * It gives the Game a new pane to work with and adds its graphical elements to it. It
 * also makes the quit button
 */

public class PaneOrganizer {
	private BorderPane _root;
	private Game _game;
	private HBox _buttonPane;
	private Pane _gamePane;


	public PaneOrganizer(){
		this.setupRoot();
		_gamePane = new Pane();
		_game = new Game(_gamePane);
		Pane sideBarPane = new Pane();
		sideBarPane.setMinWidth(Constants.DIMENSIONS*.25);
		sideBarPane.setStyle("-fx-background-color: white");
		SideBar sideBar = new SideBar(sideBarPane, _game);
		_game.setSideBar(sideBar);
		_root.setCenter(_gamePane);
		_root.setRight(sideBarPane);
		_game.setSideBar(sideBar);
		this.setupButtons();
	}

	// Helps sets up the graphical stuff
	private void setupRoot() {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: blue");
		_root.setMinSize(Constants.DIMENSIONS,Constants.DIMENSIONS);
	}

	// Helper method to set up the buttons
	private void setupButtons() {
		_buttonPane = new HBox();
		_buttonPane.setMinHeight(Constants.DIMENSIONS*.05);
		_root.setTop(_buttonPane);
		Button quitButton = new Button(" Quit ");
		Button tradeButton = new Button(" Trade ");
		Button buildButton = new Button(" Build ");
		Button settleButton = new Button( " Settle ");
		_buttonPane.getChildren().addAll(quitButton,tradeButton,buildButton,settleButton);
		_buttonPane.setSpacing(30);
		_buttonPane.setStyle("-fx-background-color: white");
		_buttonPane.setFocusTraversable(false);
		quitButton.setOnAction(new QuitHandler());
		tradeButton.setOnAction(new TradeHandler());
		buildButton.setOnAction(new BuildHandler());
		settleButton.setOnAction(new SettleHandler());
	}
	public Pane getRoot() {
		return _root;
	}

	// When the quit button is pressed, you exit the game
	private class QuitHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			e.consume();
			System.exit(0);
		}
	}

	// Tells the game that the user wants to trade with someone
	private class TradeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
            if (!_game.isOver()){
                _game.setStrategy(Strategy.TRADE);
    			_game.moveHelper();
            }
		}
	}

	// Tells the game that the human user wants to build something
	private class BuildHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
            if (!_game.isOver()){
                _game.setStrategy(Strategy.BUILD);
    			_game.moveHelper();
            }
		}
	}

	// Tells the game the human user ended their turn and the game moves forward
	private class SettleHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
            if (!_game.isOver()){
                _game.setStrategy(Strategy.SETTLE);
    			_game.moveHelper();
            }
		}

	}

}
