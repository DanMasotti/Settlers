package Indy;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/*
 * this is the sidebar class which is an additional
 *  graphical element that shows the current status of the
 *  game
 */
public class SideBar {
	
	VBox _vbox;
	VBox _bigBox;
	Label[] _options;
	Pane _pane;
	Game _game;
	ArrayList<Player> _playerList;
	
	public SideBar(Pane pane, Game game) {
		this.setGame(game);
		_pane = pane;
		Text title = new Text("  " + "Score Board");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		_bigBox = new VBox();
		_bigBox.getChildren().add(title);
		this.setupBigBox();
	}
	
	/*
	 * tells the sidebar what game we're playing
	 */
	public void setGame(Game game) {
		_game = game;
		_playerList = _game.getPlayers();
	}
	
	
	/*
	 * the main visual structure holding the player data
	 */
	public void setupBigBox() {
		for (Player p : _playerList) {
			_vbox = this.addVBox(p);
			_bigBox.getChildren().add(_vbox);
		}
		_pane.getChildren().add(_bigBox);
	}
	
	/*
	 * helper for making things look nice
	 */
	private VBox addVBox(Player p) {
	    VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    _options = this.start(p);
	    for (int i=0; i<8; i++) {
	        VBox.setMargin(_options[i], new Insets(0, 0, 0, 8));
	        vbox.getChildren().add(_options[i]);
	    }
	    return vbox;
	}
	
	/*
	 * for each player its gonna format a nice 
	 * read off of what they have and how much they have
	 */
	private Label[] start(Player p) {
		int woodNum = 0;
		int sheepNum = 0;
		int brickNum = 0;
		int hayNum = 0;
		int oreNum = 0;
		ArrayList<Resource> basket = p.getBasket();
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
			case ORE:
				oreNum++;
				break;
			default: break;	
			}
		}
		Label options[] = new Label[] {
			new Label(" "),
	    		new Label("Team: "+ p.getProperty().toString()),
	        new Label("Victory Points: " + p.getPoints()),
	        new Label("Brick: " + Integer.toString(brickNum)), 
			new Label("Lumber: " + Integer.toString(woodNum)),
			new Label("Wool: " + Integer.toString(sheepNum)),
			new Label("Grain: " + Integer.toString(hayNum)),
			new Label("Ore: " + Integer.toString(oreNum))
	       	};
		return options;
	}
	

	/*
	 * called through the game to keep the most updated numbers available
	 */
	public void update(ArrayList<Player> list) {
		_playerList = list;
		_pane.getChildren().clear();
		_bigBox.getChildren().clear();
		Text title = new Text("Score Board");
		title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		_bigBox.getChildren().add(title);
		this.setupBigBox();
	}
}
