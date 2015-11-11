import com.sun.javafx.event.EventQueue;

import Controllers.PlayingFieldController;
import Helpers.Size;
import Models.PlayingField;
import Views.PlayingFieldView;

public class FruitNinja {

	public static void main(String[] args) {
		
		PlayingField model = new PlayingField(new Size(600, 600));
		PlayingFieldView view = new PlayingFieldView();
		PlayingFieldController playingFieldController = new PlayingFieldController(model, view);
		
		view.setVisible(true);
		
	}

}
