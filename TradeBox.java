package Indy;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * this opens up a new window for trading using comboboxes
 */
public class TradeBox {

	private Property _neighbor = Property.INVALID;
	private int _myQuant = 0;
	private Resource _myRes = Resource.DESERT;
	private Integer _yourQuant = 0;
	private Resource _yourRes = Resource.DESERT;


	public TradeBox(String title, String message, Player p, ArrayList<Player> neighbors) {
		Stage window = new Stage();
		//block other window
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(Constants.DIMENSIONS*.5);
		Label label = new Label();
		label.setText(message);
		Button closeButton = new Button("Close window");
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		ComboBox<Property> neighborBox = new ComboBox<Property>();
		//people you can trade with
        neighborBox.getItems().addAll(
        								Property.BANK
        								);
        //counts what you have
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
		int[] numRes = {brickNum,woodNum,sheepNum,hayNum,oreNum};
		Resource[] res = {Resource.BRICK, Resource.LUMBER, Resource.WOOL, Resource.GRAIN, Resource.ORE};
		//check what you actually have to barter
		ComboBox<Integer> quantityBox = new ComboBox<Integer>();
        ComboBox<Resource> myResComboBox = new ComboBox<Resource>();
        for (int i = 0; i < 5; i++) {
			if (numRes[i] >= 4) {
				quantityBox.getItems().add(numRes[i]);
				myResComboBox.getItems().add(res[i]);
			}
		}
        ComboBox<Integer> yourQuantBox = new ComboBox<Integer>();
        yourQuantBox.getItems().add(1);
		ComboBox<Resource> yourResComboBox = new ComboBox<Resource>();
		yourResComboBox.getItems().addAll(
										Resource.BRICK,
										Resource.LUMBER,
										Resource.WOOL,
										Resource.GRAIN,
										Resource.ORE
										);
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5,5,5,5));
        grid.add(neighborBox, 0, 0);
        grid.add(new Label(" , I'd like to trade "), 1, 0);
        grid.add(quantityBox, 2, 0);
        grid.add(myResComboBox, 3, 0);
        grid.add(new Label("for "), 4, 0);
        grid.add(yourQuantBox, 5, 0);
        grid.add(yourResComboBox, 6, 0);
        Button button = new Button("Make Deal");
        //this button captures the users selection
        button.setOnAction(		e -> {
        							_neighbor = neighborBox.getValue();
        							_myQuant = quantityBox.getValue();
        							_myRes = myResComboBox.getValue();
        							_yourQuant = yourQuantBox.getValue();
        							_yourRes = yourResComboBox.getValue();
        							window.close();}
        							);
        layout.getChildren().addAll(grid,button,closeButton);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

	/*
	 * getters for all the user's answers
	 * this how it looks: <player, I'd like to trade <this many> <of my resources> for <that many> <of yours>
	 */
	public Property getNeighbor() {
		return _neighbor;
	}

	public int getMyQuant() {
		return _myQuant;
	}

	public Resource getMyRes() {
		return _myRes;
	}

	public int getYourQuant() {
		return _yourQuant;
	}

	public Resource getYourRes() {
		return _yourRes;
	}
}
