package Models;

import java.util.ArrayList;

import Helpers.Position;
import Helpers.Size;
import rx.Observer;

public class PlayingField {
	
	private SlashTrailSection slashTrailSection;
	private ArrayList<GameObject> gameObjects;
	private Player player;
	private Fruit fruit;
	
	public PlayingField(Player player) {
		this.gameObjects = new ArrayList<>();
		setPlayer(player);
		fruit = new Fruit(new Position(20, 20), new Size(20, 20));
	}
	
	public SlashTrailSection geSlashTrailSection() {
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
	
	public void subscribeToScore(Observer<? super Integer> observer) {
		player.subscribeToScore(observer);
	}
	
	public void setScore(Integer score) {
		player.setScore(score);
	}
	
	public Integer getScore() {
		return player.getScore();
	}
	
//	public Player getPlayer() {
//		return player;
//	}
	
	public Fruit getFruit() {
		return fruit;
	}
	
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}
}
