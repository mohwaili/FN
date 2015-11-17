package Controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import Models.PlayingField;
import Models.PlayingField.GameState;
import Views.PlayingFieldView;
import rx.functions.Action1;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	private Timer timer;
	private Action1<GameState> gameStateObserver;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;	
		this.timer = new Timer(1000 / 60, new PlayingFieldUpdater());
		//Observing for the game state
		observeForGameState();
		this.view.addMouseListener(new FieldMouseListener());
		//Subscribe to observables
		this.model.subscribeToScore(view.getScoreObserver());
		this.model.subscribeToLives(view.getLivesObserver());
		this.model.subscribeToGameObject(view.getGameObjectObserver());
		this.model.subscribeToGameState(gameStateObserver);
	}
	
	/**
	 * Subscribe to game state changes
	 */
	private void observeForGameState() {
		gameStateObserver = new Action1<PlayingField.GameState>() {
			@Override
			public void call(GameState gameState) {
				if (gameState == GameState.Playing) {
					timer.start();
				} else {
					timer.stop();
					view.showGameOver(model.getScore());
				}
			}
		};
	}
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Move the game object forward in one direction
			model.moveGameObject();
			view.getPlayingField().repaint();
			//Reset the game object when it's outside the field
			model.resetGameObjectWhenItsOutsideTheField();
			//Applying new slash to the play field
			if (model.isMouseDown()) {
				Point mousePosition = view.getPlayingField().getMousePosition();				
				model.applySlash(mousePosition);
			}
		}
	}
	
	private class FieldMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) { model.setMouseDown(true); }
		@Override
		public void mouseReleased(MouseEvent e) { model.setMouseDown(false); }
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

}


