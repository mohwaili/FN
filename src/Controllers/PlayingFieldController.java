package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.Attributes.Name;

import javax.swing.Timer;

import Models.Player;
import Models.PlayingField;
import Views.PlayingFieldView;
import rx.Observer;

public class PlayingFieldController {

	private PlayingField model;
	private PlayingFieldView view;
	private Player player;
	
	Timer timer;
	
	public PlayingFieldController(PlayingField model, PlayingFieldView view) {
		this.model = model;
		this.view = view;
		this.player = new Player("mohammed");

		this.player.subscribeToName(new Observer<String>() {
			@Override
			public void onNext(String name) {
				System.out.println(name);
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.view.setSize(this.model.getSize());
		
		this.timer = new Timer(100, new PlayingFieldUpdater(this.view));
		this.timer.start();
	}
	
}

class PlayingFieldUpdater implements ActionListener {

	private PlayingFieldView view;
	
	public PlayingFieldUpdater(PlayingFieldView view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: do something to the view and repaint it
	}
	
}
