package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import org.w3c.dom.css.Counter;

import Helpers.Position;
import Models.PlayingField;
import Views.PlayingFieldView;
import rx.Observer;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	
	private int currentFruitX;
	private int currentFruitY;
	
	private Position newFruitPosition;
	
	Timer timer;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;
		
		this.view.getPlayingField().setFruit(this.model.getFruit());
		this.model.subscribeToScore(new ScoreObserver());
		
		this.currentFruitX = this.model.getFruit().getPosition().getX();
		this.currentFruitY = this.model.getFruit().getPosition().getY();
		
		this.timer = new Timer(15, new PlayingFieldUpdater());
		this.timer.start();
	}
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model.setScore(model.getScore() + 1);
			
			currentFruitX = model.getFruit().getPosition().getX();
			currentFruitY = model.getFruit().getPosition().getY();
			
			newFruitPosition = new Position(currentFruitX+=model.getFruit().getSpeed(), currentFruitY);
			model.getFruit().setPosition(newFruitPosition);
			System.out.println(newFruitPosition.getX());
			view.getPlayingField().repaint();
		}
		
	}
	
	//Observers
	
	private class ScoreObserver implements Observer<Integer> {
		
		@Override
		public void onNext(Integer score) {
//			System.out.println(score);
//			view.setScore(score);
//			counter++;
			
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


