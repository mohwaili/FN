package Models;

import java.util.ArrayList;
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
}
