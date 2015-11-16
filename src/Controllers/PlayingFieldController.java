package Controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import Models.PlayingField;
import Models.SlashTrailSection;
import Models.PlayingField.GameState;
import Views.PlayingFieldView;
import rx.functions.Action1;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	private boolean isMouseDown;
	private Timer timer;
	private Action1<GameState> gameStateObserver;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;	
		this.timer = new Timer(1000 / 60, new PlayingFieldUpdater());
		
		gameStateObserver = new Action1<PlayingField.GameState>() {
			@Override
			public void call(GameState gameState) {
				if (gameState == GameState.Playing) {
					timer.start();
				} else {
					timer.stop();
				}
			}
		};
		
		this.isMouseDown = false;
		this.view.addMouseListener(new FieldMouseListener());
//		this.view.setGameObject(this.model.getGameObject());
		this.model.subscribeToScore(view.getScoreObserver());
		this.model.subscribeToLives(view.getLivesObserver());
		this.model.subscribeToGameObject(view.getGameObjectObserver());
		this.model.subscribeToGameState(gameStateObserver);
		
//		this.timer.start();

	}
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.moveGameObject();
			view.getPlayingField().repaint();
			
			if (model.gameObjectIsOutsideTheField()) 
				model.addNewGameObjectToField();

			if (isMouseDown) {
				if (model.getSlashTrailSection() == null) {
					model.setSlashTrailSection(new SlashTrailSection());
				}
				Point mousePosition = view.getPlayingField().getMousePosition();				
				model.applySlash(mousePosition);
			}
		}
	}
	
	private class FieldMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) { isMouseDown = true; }
		@Override
		public void mouseReleased(MouseEvent e) {
			isMouseDown = false;
			model.setSlashTrailSection(null);
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

}


