package Models;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameMusic {
	private AudioInputStream audioStream;
	private Clip audioClip;
	
	public GameMusic(String filePath) {
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
		} 
		catch (UnsupportedAudioFileException | IOException e) { } 
		catch (LineUnavailableException e) { }
		
	}
	
	public void play() {
		audioClip.start();
	}
	
	public void stop() {
		audioClip.stop();
	}
}
