package example2;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;

public class Sound {

	public static int RIPETI = 0;
	public static int NON_RIPETI = 1;
	private int i;
	private Clip clip;
	private String clipName;

	/**
	 * Memorizza l'URL del file audio
	 */
	public Sound(String s) {
		clipName = s;
	}

	/**
	 * Metodo che blocca l'esecuzione della musica.
	 */
	public void stop() {
		clip.close();
	}

	/**
	 * Metodo che avvia l'esecuzione della musica.
	 */
	public void start(int i) {
		this.i = i;
		startMusica(clipName);
	}

	/**
	 * Metodo a cui interno sono inizializzati i parametri e istanziati gli
	 * oggetti necessari per ottenere lo stream audio e che fa partire il file
	 * audio.
	 *
	 */
	public void startMusica(String fileName) {
		File file = new File(fileName);
		try {
			AudioFileFormat audiofileformat = AudioSystem.getAudioFileFormat(file);
			AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(file);
			AudioFormat audioformat = audiofileformat.getFormat();
			// javax.sound.sampled.DataLine.Info info = new
			// javax.sound.sampled.DataLine.Info(class$javax$sound$sampled$Clip
			// != null ? class$javax$sound$sampled$Clip :
			// (class$javax$sound$sampled$Clip =
			// class$("javax.sound.sampled.Clip")),
			// audioinputstream.getFormat(),
			// (int)audioinputstream.getFrameLength() *
			// audioformat.getFrameSize());
			DataLine.Info info = new DataLine.Info(Clip.class, audioinputstream
					.getFormat(), (int) audioinputstream.getFrameLength()
					* audioformat.getFrameSize());

			clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioinputstream);
			if (i == RIPETI) { // Se � una musica di sottofondo
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				clip.loop(0);
			}
		} catch (Exception exception) {
			JOptionPane.showMessageDialog(null,
					"File audio non trovato o formato non corretto", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		Sound sound = new Sound("files/song1.wav");
		sound.start(RIPETI);
	}

}
