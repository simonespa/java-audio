package example2;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

public class ClipPlayer implements LineListener {

	private Clip clip;

	/*
	 * The clip will be played nLoopCount + 1 times.
	 */
	public ClipPlayer(File clipFile, int nLoopCount) {
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(clipFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (audioInputStream != null) {
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			try {
				clip = (Clip) AudioSystem.getLine(info);
				clip.addLineListener(this);
				clip.open(audioInputStream);
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		clip.start();
	}

	public void stop() {
		clip.stop();
	}

	public void close() {
		clip.close();
	}

	public void update(LineEvent event) {
		if (event.getType().equals(LineEvent.Type.STOP)) {
			//clip.close();
		} else if (event.getType().equals(LineEvent.Type.CLOSE)) {
			/*
			 * There is a bug in the jdk1.3/1.4. It prevents correct termination
			 * of the VM. So we have to exit ourselves.
			 */
			System.exit(0);
		}

	}

	public static void main(String[] args) throws InterruptedException {
		File clipFile = new File("files/menu2.wav");
		int nLoopCount = 0;
		ClipPlayer clip1 = new ClipPlayer(clipFile, nLoopCount);
		ClipPlayer clip2 = new ClipPlayer(clipFile, nLoopCount);
		clip1.start();
		Thread.sleep(2000);
		clip1.start();
		Thread.sleep(20000);
	}

}
