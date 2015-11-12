

import Controllers.PlayingFieldController;
import Helpers.Size;
import Models.PlayingField;
import Views.PlayingFieldView;

public class FruitNinja {

	public static void main(String[] args) {
		
		PlayingField model = new PlayingField();
		PlayingFieldView view = new PlayingFieldView(new Size(600, 600));
		PlayingFieldController playingFieldController = new PlayingFieldController(model, view);
		
		view.setVisible(true);
		
	}

}
