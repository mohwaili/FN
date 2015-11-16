package Models;

import java.awt.Point;
import java.util.ArrayList;
import Helpers.Helper;
import Helpers.Size;
import Models.GameObject.StartDirection;
import rx.functions.Action1;
import rx.subjects.ReplaySubject;

public class PlayingField {
	
	public enum GameState {
		Playing, GameOver
	}
	
	private SlashTrailSection slashTrailSection;
	private ArrayList<GameObject> gameObjects;
	private Player player;
	private GameObject gameObject;
	private ReplaySubject<GameObject> gameObjectObservable;
	private ReplaySubject<GameState> gameState; 
	private GameMusic gameMusic;
	private GameMusic slashEffect;

	public PlayingField(Player player) {
		this.gameObjects = new ArrayList<>();
		gameState = ReplaySubject.create();
		gameState.onNext(GameState.Playing);
		gameObjectObservable = ReplaySubject.create();
		setPlayer(player);
		gameObject = new Fruit();
		gameObjectObservable.onNext(gameObject);
		gameMusic = new GameMusic("assets/game_music.wav");
		gameMusic.play();
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
	
	public void decrementLives() {
		player.decrementLives();
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		gameObjectObservable.onNext(this.gameObject);
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
		
		int speed = gameObject.getSpeed();
		
		switch (gameObject.getStartDirection()) {
		case North:
			currentGameObjectY-= speed;
			break;
		case East:
			currentGameObjectX+= speed;
			break;
		case South:
			currentGameObjectY+= speed;
			break;
		case West:
			currentGameObjectX-= speed;
			break;
		default:
			break;
		}
		
		Point newGameObjectPosition = new Point((int)currentGameObjectX, (int)currentGameObjectY);
		gameObject.setPosition(newGameObjectPosition);
	}
	
	public boolean slashIsValid() {
		return (slashTrailSection.getBeginPosition().getX() != slashTrailSection.getEndPosition().getX()) ||
				(slashTrailSection.getBeginPosition().getY() != slashTrailSection.getEndPosition().getY());
	}
	
	private void playSlashEffect() {
		slashEffect = new GameMusic("assets/slash.wav");
		slashEffect.play();
	}
	
	public void addNewGameObjectToField() {
		int randomIndex = Helper.generateRandomNumber(1, 5);
		GameObject newGameObject = (randomIndex == 5) ? new Bomb(new Size(50, 50)) : new Fruit();
		setGameObject(newGameObject);
	}

	public void applySlash(Point mousePosition) {
		if (mousePosition != null) {
			if (slashTrailSection.getBeginPosition() == null) 
				slashTrailSection.setBeginPosition(mousePosition);
			
			slashTrailSection.setEndPosition(mousePosition);
			
			if (gameObject.collidesWithMousePosition(mousePosition)) {
				if (slashIsValid()) {
					playSlashEffect();
					if (gameObject instanceof Fruit) 
						setScore(player.getScore() + ((Fruit)gameObject).getPoints());
					else {
						decrementLives();
						if (player.getLives() == 0) {
							gameState.onNext(GameState.GameOver);
							gameMusic.stop();
						}
					}
					addNewGameObjectToField();
				}
			}
		}
	}
	
	public void subscribeToGameState(Action1<GameState> gameStateObserver) {
		gameState.subscribe(gameStateObserver);
	}
	
	public void subscribeToGameObject(Action1<GameObject> gameObjectObserver) {
		gameObjectObservable.subscribe(gameObjectObserver);
	}
}
