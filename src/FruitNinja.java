import Controllers.PlayingFieldController;
import Helpers.Size;
import Models.Player;
import Models.PlayingField;
import Views.PlayingFieldView;

public class FruitNinja {

	public static void main(String[] args) {
		PlayingField model = new PlayingField(new Player(), new Size(500, 500));
		PlayingFieldView view = new PlayingFieldView(model.getSize());
		PlayingFieldController playingFieldController = new PlayingFieldController(model, view);
		view.setVisible(true);
	}

}
