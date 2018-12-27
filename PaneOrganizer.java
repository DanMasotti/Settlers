package Indy;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	//helps sets up the graphical stuff
	private void setupRoot() {
		_root = new BorderPane();
		_root.setStyle("-fx-background-color: blue");
		_root.setMinSize(Constants.DIMENSIONS,Constants.DIMENSIONS);
	}

	//This is a helper method to set up the buttons
	private void setupButtons() {
		_buttonPane = new HBox();
		_buttonPane.setMinHeight(Constants.DIMENSIONS*.05);
		_root.setTop(_buttonPane);
		Button b1 = new Button(" Quit ");
		Button b2 = new Button(" Trade ");
		Button b3 = new Button(" Build ");
		Button b4 = new Button( " Settle ");
		_buttonPane.getChildren().addAll(b1,b2,b3,b4);
		_buttonPane.setSpacing(30);
		_buttonPane.setStyle("-fx-background-color: white");
		_buttonPane.setFocusTraversable(false);
		b1.setOnAction(new QuitHandler());
		b2.setOnAction(new TradeHandler());
		b3.setOnAction(new BuildHandler());
		b4.setOnAction(new SettleHandler());
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

	//tells the game that the user wants to trade with someone
	private class TradeHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			// TODO Auto-generated method stub
            if (!_game.isOver()){
                _game.setStrategy(Strategy.TRADE);
    			_game.moveHelper();
            }
		}

	}

	//tells the game that the human user wants to build something
	private class BuildHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			// TODO Auto-generated method stub
            if (!_game.isOver()){
                _game.setStrategy(Strategy.BUILD);
    			_game.moveHelper();
            }
		}
	}

	//tells the game the human user ended their turn and the game moves forward
	private class SettleHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			// TODO Auto-generated method stub
            if (!_game.isOver()){
                _game.setStrategy(Strategy.SETTLE);
    			_game.moveHelper();
            }
		}

	}

}
