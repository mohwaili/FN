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
	
	/**
	 * Initializing new game music
	 * @param filePath: the path for the sound file
	 */
	public GameMusic(String filePath) {
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
		} 
		catch (UnsupportedAudioFileException | IOException e) { } 
		catch (LineUnavailableException e) { }
		
	}
	
	/**
	 * Start playing the sound
	 */
	public void play() {
		audioClip.start();
	}
	
	/**
	 * Stop the sound
	 */
	public void stop() {
		audioClip.stop();
	}
}
