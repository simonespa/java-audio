package example3.model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player {

	private AudioFileFormat aff;
	private AudioInputStream ais;
	private AudioFormat af;
	private Clip clip;

	public Player(String fileName) {
		File file = new File(fileName);
		try {
			aff = AudioSystem.getAudioFileFormat(file);
			ais = AudioSystem.getAudioInputStream(file);
			af = ais.getFormat();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if( clip == null ) {
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			try {
				clip = (Clip) AudioSystem.getLine(info);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
		if (!clip.isOpen()) {
			try {
				clip.open(ais);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		clip.start();
	}

	public void pause() {
		if( clip != null && clip.isActive() ) {
			clip.stop();
		}
	}

	public void stop() {
		if( clip != null && clip.isOpen() ) {
			clip.close();
			clip = null;
		}
	}

	public void setVolume(int value) {
		if (clip != null) {
			if (clip.isControlSupported(FloatControl.Type.VOLUME)) {
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
				gainControl.setValue(value);
			}
		}
	}

}
