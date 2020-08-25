package Settlers;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * This class opens a new window so that the human user can chose what they'd like to build
 * 
 */
public class BuildBox {
	
	private PieceType _answer;
	
	public BuildBox(String title, String message, Player p) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // Block other window
		window.setTitle(title);
		window.setMinWidth(Constants.DIMENSIONS*.5);
		Label label = new Label();
		label.setText(message);
		Label list = new Label();
		list.setText("You have: ");
		Button closeButton = new Button("Close window");
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		VBox smallBox = this.makeSubBox(p, window);
		smallBox.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(label, smallBox, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	/*
	 * Checks if the user has enough resources to build X and then
	 * presents their option
	 */
	public VBox makeSubBox(Player p, Stage window) {
		int woodNum = 0;
		int sheepNum = 0;
		int brickNum = 0;
		int hayNum = 0;
		int oreNum = 0;
		ArrayList<Resource> basket = p.getBasket();
		// Count up the basket
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
		VBox vbox = new VBox();
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);
	    Label labels[] = {
	    					new Label("Brick: " + Integer.toString(brickNum)), 
	    					new Label("Lumber: " + Integer.toString(woodNum)),
	    					new Label("Wool: " + Integer.toString(sheepNum)),
	    					new Label("Grain: " + Integer.toString(hayNum)),
	    					new Label("Ore: " + Integer.toString(oreNum))
	    					};
	    for (int i=0; i<5; i++) {
	        VBox.setMargin(labels[i], new Insets(0, 0, 0, 8));
	        vbox.getChildren().add(labels[i]);
	    }
	    Button road = new Button("Road");
	    Button house = new Button("Settlement");
	    Button city = new Button("City");
	    // Adds this button if the user has enough stuff
	    if (brickNum >=1 && woodNum >= 1) {
	    		if (hayNum >=1 && sheepNum >=1) {
	    			house.setOnAction(e -> {_answer = PieceType.SETTLEMENT; window.close();}) ;
	    			vbox.getChildren().add(house);
	    		}
	    		road.setOnAction(e -> {_answer = PieceType.ROAD; window.close();});
	    		vbox.getChildren().add(road);
	    }
	    if (hayNum >= 2 && oreNum >= 3) {
	    		city.setOnAction(e -> {_answer = PieceType.CITY; window.close();} );
	    		vbox.getChildren().add(city);
	    }
	    return vbox;
	}
	
	/*
	 * Retrieves the user's selection
	 */
	public PieceType getAnswer() {
		return _answer;
	}
}
