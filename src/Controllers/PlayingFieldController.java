package Controllers;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import Helpers.Helper;
import Helpers.Size;
import Models.Bomb;
import Models.Fruit;
import Models.GameMusic;
import Models.GameObject;
import Models.PlayingField;
import Models.SlashTrailSection;
import Views.PlayingFieldView;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	private boolean isMouseDown;
	private Timer timer;
	private GameMusic gameMusic;
	private GameMusic slashEffect;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;
		gameMusic = new GameMusic("assets/game_music.wav");
		gameMusic.play();
		this.isMouseDown = false;
		this.view.addMouseListener(new FieldMouseListener());
		this.view.setGameObject(this.model.getGameObject());
		this.model.subscribeToScore(this.view.getScoreObserver());
		this.model.subscribeToLives(this.view.getLivesObserver());
		this.timer = new Timer(1000 / 60, new PlayingFieldUpdater());
		this.timer.start();
	}
	
	private void playSlashEffect() {
		slashEffect = new GameMusic("assets/slash.wav");
		slashEffect.play();
	}
	
	//TODO: make this observable
	private void addNewGameObjectToField() {
		int randomIndex = Helper.generateRandomNumber(1, 5);
		GameObject newGameObject = (randomIndex == 5) ? new Bomb(new Size(50, 50)) : new Fruit();
		model.setGameObject(newGameObject);
		view.setGameObject(newGameObject);
	}

	private void applySlash(Point mousePosition, SlashTrailSection slashTrailSection) {
		if (mousePosition != null) {
			if (slashTrailSection.getBeginPosition() == null) 
				slashTrailSection.setBeginPosition(mousePosition);
			
			slashTrailSection.setEndPosition(mousePosition);
			
			if (model.getGameObject().collidesWithMousePosition(mousePosition)) {
				if (model.slashIsValid()) {
					playSlashEffect();
					if (model.getGameObject() instanceof Fruit) 
						model.setScore(model.getScore() + ((Fruit)model.getGameObject()).getPoints());
						
					else {
						model.decrementLives();
						if (model.getLives() == 0) {
							timer.stop();
							gameMusic.stop();
						}
					}
					addNewGameObjectToField();
				}
			}
		}
	}
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.moveGameObject();
			view.getPlayingField().repaint();
			
			if (model.gameObjectIsOutsideTheField()) 
				addNewGameObjectToField();

			if (isMouseDown) {
				if (model.getSlashTrailSection() == null) {
					model.setSlashTrailSection(new SlashTrailSection());
				}
				SlashTrailSection currentSlashTrailSection = model.getSlashTrailSection();
				Point mousePosition = view.getPlayingField().getMousePosition();				
				applySlash(mousePosition, currentSlashTrailSection);
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


