package Models;

import rx.Observer;
import rx.subjects.PublishSubject;

public class Player {

	private Integer scoreValue;
	public PublishSubject<String> name;
	private PublishSubject<Integer> score;

	public Player(String name) {
		this.name = PublishSubject.create();
		this.score = PublishSubject.create();
		setName(name);
		setScore(0);
		scoreValue = 0;
	}
	
	public void subscribeToName(Observer<? super String> observer) {
		this.name.subscribe(observer);
	}
	
	public void subscribeToScore(Observer<? super Integer> observer) {
		this.score.subscribe(observer);
	}
	
	public void setName(String name) {
		this.name.onNext(name);
	}
	
	public Integer getScore() {
		return scoreValue;
	}
	
	public void setScore(Integer score) {
		if (score >= 0)  {
			this.score.onNext(score);
			this.scoreValue = score;
		}
			
	}
	
}
