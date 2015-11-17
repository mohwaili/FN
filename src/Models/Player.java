package Models;

import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class Player {

	private Integer scoreValue;
	private Integer livesValue;
	private PublishSubject<Integer> score;
	private PublishSubject<Integer> lives;

	/**
	 * Initialize new Player
	 * @param name: the player's name
	 */
	public Player() {
		this.score = PublishSubject.create();
		this.lives = PublishSubject.create();
		setScore(0);
		scoreValue = 0;
		livesValue = 3;
	}
	
	/**
	 * Subscribe to score changes
	 * @param scoreObserver: the score observer
	 */
	public void subscribeToScore(Action1<Integer> scoreObserver) {
		this.score.subscribe(scoreObserver);
	}
	
	/**
	 * Subscribe to lives changes
	 * @param livesObserver: the lives observer
	 */
	public void subscribeToLives(Action1<Integer> livesObserver) {
		this.lives.subscribe(livesObserver);
	}
	
	/**
	 * Get the current score
	 * @return Integer: the current score
	 */
	public Integer getScore() {
		return scoreValue;
	}
	
	/**
	 * Set the new score
	 * @param score: the new score
	 */
	public void setScore(Integer score) {
		if (score >= 0)  {
			this.score.onNext(score);
			this.scoreValue = score;
		}
	}
	
	/**
	 * Get the current amount of lives
	 * @return Integer: amount of lives left
	 */
	public Integer getLives() {
		return livesValue;
	}
	
	/**
	 * Decrements lives by 1
	 */
	public void decrementLives() {
		if (livesValue > 0) {
			livesValue--;
			lives.onNext(livesValue);
			
		}
	}
	
}
