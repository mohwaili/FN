package Models;

public class Player {

	private String name;
	private int score;
	
	public Player(String name) {
		setName(name);
		score = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		if (score >= 0) 
			this.score = score;
	}
	
}
