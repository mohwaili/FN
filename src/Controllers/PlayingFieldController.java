package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import Helpers.Helper;
import Helpers.Position;
import Helpers.Size;
import Models.Fruit;
import Models.GameObject;
import Models.PlayingField;
import Models.GameObject.StartDirection;
import Views.PlayingFieldView;
import rx.Observer;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	
	private int currentFruitX;
	private int currentFruitY;
	
	private Position newFruitPosition;
	
	private Timer timer;
	
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
	
	private boolean gameObjectIsOutsideTheField(GameObject gameObject) {
		int posX = gameObject.getPosition().getX();
		int posY = gameObject.getPosition().getY();

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
	
	//ActionListeners
	
	private class PlayingFieldUpdater implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			model.setScore(model.getScore() + 1);
			
			currentFruitX = model.getFruit().getPosition().getX();
			currentFruitY = model.getFruit().getPosition().getY();
			
			switch (model.getFruit().getStartDirection()) {
			case North:
				currentFruitY-= model.getFruit().getSpeed();
				break;
			case East:
				currentFruitX+= model.getFruit().getSpeed();
				break;
			case South:
				currentFruitY+= model.getFruit().getSpeed();
				break;
			case West:
				currentFruitX-= model.getFruit().getSpeed();
				break;
			default:
				break;
			}
			
			newFruitPosition = new Position(currentFruitX, currentFruitY);
			model.getFruit().setPosition(newFruitPosition);
			view.getPlayingField().repaint();
			
			if (gameObjectIsOutsideTheField(model.getFruit())) {
				try {
					Thread.sleep(Helper.generateRandomNumber(500, 5000));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Fruit newFruit = new Fruit(new Size(20, 20));
				model.setFruit(newFruit);
				view.getPlayingField().setFruit(newFruit);
				System.out.println("New fruit");
				System.out.println("x: " + model.getFruit().getPosition().getX() + " y: " + model.getFruit().getPosition().getY());
				System.out.println("");
			}
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


