package Models;

import java.util.ArrayList;

import Helpers.Size;

public class PlayingField {
	
	private SlashTrailSection slashTrailSection;
	private ArrayList<GameObject> gameObjects;
	
	public PlayingField() {
		this.gameObjects = new ArrayList<>();
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
	
}
