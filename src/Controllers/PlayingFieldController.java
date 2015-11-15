package Controllers;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import Helpers.Helper;
import Models.Fruit;
import Models.GameObject;
import Models.PlayingField;
import Models.SlashTrailSection;
import Models.GameObject.StartDirection;
import Views.PlayingFieldView;
import rx.Observer;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	
	private boolean isMouseDown;
	
	private double currentGameObjectX;
	private double currentGameObjectY;
	
	private Point newGameObjectPosition;
	
	private Timer timer;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;
		
		this.isMouseDown = false;
		this.view.addMouseListener(new MyMouseListener());
		
		this.view.getPlayingField().setGameObject(this.model.getGameObject());
		this.model.subscribeToScore(new ScoreObserver());
		
		this.currentGameObjectX = this.model.getGameObject().getPosition().getX();
		this.currentGameObjectY = this.model.getGameObject().getPosition().getY();
		
		this.timer = new Timer(15, new PlayingFieldUpdater());
		this.timer.start();
	}
	
	private boolean gameObjectIsOutsideTheField(GameObject gameObject) {
		double posX = gameObject.getPosition().getX();
		double posY = gameObject.getPosition().getY();

		if (gameObject.getStartDirection() == StartDirection.North) {
			if (posY < -80) 
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.East) {
			if (posX > 580) 
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.South) {
			if (posY > 520)
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.West) {
			if (posX < -80)
				return true;
		}
		
		return false;
	}
	
	private void addNewGameObjectToField() {
		Fruit newFruit = new Fruit();
		model.setGameObject(newFruit);
		view.getPlayingField().setGameObject(newFruit);
		System.out.println("New fruit");
	}
	
	//ActionListeners
	
	private class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			isMouseDown = true;
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			isMouseDown = false;
			model.setSlashTrailSection(null);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private boolean gameObjectCollidesWithMousePosition(Point gameObjectPosition, Point mousePosition) {
		
		double mouseX = mousePosition.getX();
		double mouseY = mousePosition.getY();
		
		double gameObjectX = gameObjectPosition.getX();
		double gameObjectY = gameObjectPosition.getY();
		
		int gameObjectWidth = model.getGameObject().getSize().getWidth();
		
		if (    (mouseX > gameObjectX && mouseX < gameObjectX + gameObjectWidth) && 
				(mouseY > gameObjectY && mouseY < gameObjectY + gameObjectWidth)) {
			return true;
		}
		
		return false;
	}
	
	private class PlayingFieldUpdater implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			currentGameObjectX = model.getGameObject().getPosition().getX();
			currentGameObjectY = model.getGameObject().getPosition().getY();
			
			switch (model.getGameObject().getStartDirection()) {
			case North:
				currentGameObjectY-= model.getGameObject().getSpeed();
				break;
			case East:
				currentGameObjectX+= model.getGameObject().getSpeed();
				break;
			case South:
				currentGameObjectY+= model.getGameObject().getSpeed();
				break;
			case West:
				currentGameObjectX-= model.getGameObject().getSpeed();
				break;
			default:
				break;
			}
			
			newGameObjectPosition = new Point((int)currentGameObjectX, (int)currentGameObjectY);
			model.getGameObject().setPosition(newGameObjectPosition);
			view.getPlayingField().repaint();
			
			if (gameObjectIsOutsideTheField(model.getGameObject())) {
				addNewGameObjectToField();
			}
			
			if (isMouseDown) {
				if (model.getSlashTrailSection() == null) {
					model.setSlashTrailSection(new SlashTrailSection());
				}
				
				SlashTrailSection currentSlashTrailSection = model.getSlashTrailSection();
				
				Point mousePosition = view.getPlayingField().getMousePosition();
				
				if (mousePosition != null) {
					if (!currentSlashTrailSection.beginPositionIsSet) {
						currentSlashTrailSection.setBeginPosition(mousePosition);
						currentSlashTrailSection.beginPositionIsSet = true;
					}
					
					currentSlashTrailSection.setEndPosition(mousePosition);
					
					if (gameObjectCollidesWithMousePosition(model.getGameObject().getPosition(), mousePosition)) {
						if ((currentSlashTrailSection.getBeginPosition().getX() != currentSlashTrailSection.getEndPosition().getX()) ||
							(currentSlashTrailSection.getBeginPosition().getY() != currentSlashTrailSection.getEndPosition().getY())) {
							
							if (model.getGameObject() instanceof Fruit) {
								model.setScore(model.getScore() + ((Fruit)model.getGameObject()).getPoints());
							}
							
							addNewGameObjectToField();
						}
					}
				}
			}
		}
		
	}
	
	//Observers
	
	private class ScoreObserver implements Observer<Integer> {
		
		@Override
		public void onNext(Integer score) {
			view.setScore(score);
			
		}

		@Override
		public void onCompleted() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onError(Throwable arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}


