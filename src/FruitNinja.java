

import Controllers.PlayingFieldController;
import Helpers.Size;
import Models.Player;
import Models.PlayingField;
import Views.PlayingFieldView;

public class FruitNinja {

	public static void main(String[] args) {
		
		PlayingField model = new PlayingField(new Player("mohammed"));
		PlayingFieldView view = new PlayingFieldView(new Size(500,500));
		PlayingFieldController playingFieldController = new PlayingFieldController(model, view);
		
		view.setVisible(true);
		
	}

}
