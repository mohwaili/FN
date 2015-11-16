package Models;

import java.awt.Point;
import java.util.ArrayList;

import Models.GameObject.StartDirection;
import rx.functions.Action1;

public class PlayingField {
	
	private SlashTrailSection slashTrailSection;
	private ArrayList<GameObject> gameObjects;
	private Player player;
	private GameObject gameObject;
	
	public PlayingField(Player player) {
		this.gameObjects = new ArrayList<>();
		setPlayer(player);
		gameObject = new Fruit();
	}
	
	public SlashTrailSection getSlashTrailSection() {
		return slashTrailSection;
	}
	
	public void setSlashTrailSection(SlashTrailSection slashTrailSection) {
		this.slashTrailSection = slashTrailSection;
	}
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void addGameObject(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		int indexOfGameObject = gameObjects.indexOf(gameObject);
		gameObjects.remove(indexOfGameObject);
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void subscribeToScore(Action1<Integer> scoreObserver) {
		player.subscribeToScore(scoreObserver);
	}
	
	public void subscribeToLives(Action1<Integer> livesObserver) {
		player.subscribeToLives(livesObserver);
	}
	
	public void setScore(Integer score) {
		player.setScore(score);
	}
	
	public Integer getScore() {
		return player.getScore();
	}
	
	public void decrementLives() {
		player.decrementLives();
	}
	
	public Integer getLives() {
		return player.getLives();
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public boolean gameObjectIsOutsideTheField() {
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
	
	public void moveGameObject() {
		double currentGameObjectX = gameObject.getPosition().getX();
		double currentGameObjectY = gameObject.getPosition().getY();
		
		switch (gameObject.getStartDirection()) {
		case North:
			currentGameObjectY-= gameObject.getSpeed();
			break;
		case East:
			currentGameObjectX+= gameObject.getSpeed();
			break;
		case South:
			currentGameObjectY+= gameObject.getSpeed();
			break;
		case West:
			currentGameObjectX-= gameObject.getSpeed();
			break;
		default:
			break;
		}
		
		Point newGameObjectPosition = new Point((int)currentGameObjectX, (int)currentGameObjectY);
		gameObject.setPosition(newGameObjectPosition);
	}
	
	public boolean gameObjectCollidesWithMousePosition(Point mousePosition) {
		
		double mouseX = mousePosition.getX();
		double mouseY = mousePosition.getY();
		double gameObjectX = gameObject.getPosition().getX();
		double gameObjectY = gameObject.getPosition().getY();
		
		int gameObjectWidth = gameObject.getSize().getWidth();
		
		if (    (mouseX > gameObjectX && mouseX < gameObjectX + gameObjectWidth) && 
				(mouseY > gameObjectY && mouseY < gameObjectY + gameObjectWidth)) {
			return true;
		}
		
		return false;
	}
}
