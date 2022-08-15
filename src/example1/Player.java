package example1;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread {

	private File file;
	private AudioFormat format;
	private AudioInputStream stream;
	private Clip audioClip;
	private boolean loop;
	private boolean running;

	public Player(String fileName) {
		file = new File(fileName);
		loop = false;
		running = false;
		try {
			stream = AudioSystem.getAudioInputStream(file);
			if( stream != null ) {
				format = stream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format,
						(int) (stream.getFrameLength() * format.getFrameSize()));
				audioClip = (Clip) AudioSystem.getLine(info);
				audioClip.open(stream);
			}
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		running = true;
		if( loop )
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		else
			audioClip.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while( running );
	}

	public void restart() {
		audioClip.start();
	}

	public void pause() {
		audioClip.stop();
	}

	public void close() {
		running = false;
	}

	public void setLoopMode(boolean loop) {
		this.loop = loop;
	}

	@Override
	protected void finalize() throws Throwable {
		if( audioClip != null && audioClip.isOpen() )
			audioClip.close();
	}

}
