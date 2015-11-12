package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import Models.PlayingField;
import Views.PlayingFieldView;
import rx.Observer;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	
	Timer timer;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;
		
		this.model.subscribeToScore(new ScoreObserver());
		
		this.timer = new Timer(15, new PlayingFieldUpdater());
		this.timer.start();
	}
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model.setScore(model.getScore() + 1);
		}
		
	}
	
	//Observers
	
	private class ScoreObserver implements Observer<Integer> {

		@Override
		public void onNext(Integer score) {
			System.out.println(score);
			view.setScore(score);
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


