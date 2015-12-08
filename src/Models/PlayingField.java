package Models;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Helpers.Helper;
import Helpers.Size;
import Models.GameObject.StartDirection;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class PlayingField {
	
	public enum GameState {
		Playing, GameOver
	}
	
	private SlashTrailSection slashTrailSection;
	private Player player;
	private GameObject gameObject;
	private BehaviorSubject<GameObject> gameObjectObservable;
	private BehaviorSubject<GameState> gameState; 
	private GameMusic gameMusic;
	private GameMusic slashEffect;
	private Size size;
	private boolean isMouseDown;
	private FieldMouseListener fieldMouseListener;

	/**
	 * Initialize new Playing field
	 * @param player : the player
	 * @param size : the playing field size
	 */
	public PlayingField(Player player, Size size) {
		this.isMouseDown = false;
		gameState = BehaviorSubject.create();
		gameState.onNext(GameState.Playing);
		gameObjectObservable = BehaviorSubject.create();
		this.size = size;
		this.player = player;
		gameObject = new Fruit();
		gameObjectObservable.onNext(gameObject);
		gameMusic = new GameMusic("assets/game_music.wav");
		gameMusic.play();
		fieldMouseListener = new FieldMouseListener();
		
	}
	
	public FieldMouseListener getFieldMouseListener() {
		return fieldMouseListener;
	}
	
	/**
	 * Set the game object 
	 * @param gameObject: the new game object
	 */
	private void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
		gameObjectObservable.onNext(this.gameObject);
	}
	
	/**
	 * Get the current player's score
	 * @return Integer: The score
	 */
	public Integer getScore() {
		return player.getScore();
	}
	
	/**
	 * Get the size of the current playing field
	 * @return Size: the field size
	 */
	public Size getSize() {
		return size;
	}
	
	/**
	 * Getting the mouse state
	 * @return True: if the mouse is down, False: if the mouse is not down
	 */
	public boolean isMouseDown() {
		return isMouseDown;
	}
	
	/**
	 * Changing the mouse state
	 * @param isMouseDown: the current mouse state
	 */
	public void setMouseDown(boolean isMouseDown) {
		this.isMouseDown = isMouseDown;
		if (!this.isMouseDown) {
			slashTrailSection = null;
		}
	}
	
	/**
	 * Reset the game object when it's outside the field
	 */
	public void resetGameObjectWhenItsOutsideTheField() {
		if (gameObjectIsOutsideTheField()) 
			addNewGameObjectToField();
	}
	
	/**
	 * Checking if the game object is outside the field
	 * @return True: if game object is outside the field, False: if not
	 */
	private boolean gameObjectIsOutsideTheField() {
		double posX = gameObject.getPosition().getX();
		double posY = gameObject.getPosition().getY();
		
		int margin = 80;

		if (gameObject.getStartDirection() == StartDirection.North) {
			if (posY < 0-margin) 
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.East) {
			if (posX > size.getWidth() + margin) 
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.South) {
			if (posY > size.getHeight() + margin)
				return true;
		} else if (gameObject.getStartDirection() == StartDirection.West) {
			if (posX < 0-margin)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Move the game object forward in one direction
	 */
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
	
	/**
	 * Check if the slash is valid
	 * @return True: the slash is valid, False: the slash is not valid
	 */
	private boolean slashIsValid() {
		return (slashTrailSection.getBeginPosition().getX() != slashTrailSection.getEndPosition().getX()) ||
				(slashTrailSection.getBeginPosition().getY() != slashTrailSection.getEndPosition().getY());
	}
	
	/**
	 * Play the slash sound effect
	 */
	private void playSlashEffect() {
		slashEffect = new GameMusic("assets/slash.wav");
		slashEffect.play();
	}
	
	/**
	 * Adding new object to the field
	 */
	private void addNewGameObjectToField() {
		int randomIndex = Helper.generateRandomNumber(1, 5);
		GameObject newGameObject = (randomIndex == 5) ? new Bomb(new Size(50, 50)) : new Fruit();
		setGameObject(newGameObject);
	}

	/**
	 * Applying the slash to the field
	 * @param mousePosition : The current mouse position
	 */
	public void applySlash(Point mousePosition) {
		if (mousePosition != null) {
			
			if (slashTrailSection == null) 
				slashTrailSection = new SlashTrailSection(mousePosition);
			
			slashTrailSection.setEndPosition(mousePosition);
			
			if (gameObject.collidesWithMousePosition(mousePosition)) {
				if (slashIsValid()) {
					playSlashEffect();
					this.slashTrailSection = null;
					if (gameObject instanceof Fruit) 
						player.setScore(player.getScore() + ((Fruit)gameObject).getPoints());
					else {
						player.decrementLives();
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
	
	/**
	 * Subscribing to score changes
	 * @param scoreObserver : the score observer
	 */
	public void subscribeToScore(Action1<Integer> scoreObserver) {
		player.subscribeToScore(scoreObserver);
	}
	
	/**
	 * Subscribing to lives changes
	 * @param livesObserver : the lives observer
	 */
	public void subscribeToLives(Action1<Integer> livesObserver) {
		player.subscribeToLives(livesObserver);
	}
	
	/**
	 * Subscribing to game state changes
	 * @param gameStateObserver : the game state observer
	 */
	public void subscribeToGameState(Action1<GameState> gameStateObserver) {
		gameState.subscribe(gameStateObserver);
	}
	
	/**
	 * Subscribing to game object
	 * @param gameObjectObserver : the game object observer
	 */
	public void subscribeToGameObject(Action1<GameObject> gameObjectObserver) {
		gameObjectObservable.subscribe(gameObjectObserver);
	}
	
	public class FieldMouseListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {}
		@Override
		public void mousePressed(MouseEvent e) { setMouseDown(true); }
		@Override
		public void mouseReleased(MouseEvent e) { setMouseDown(false); }
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
}
