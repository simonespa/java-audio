package example2;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import sun.audio.AudioPlayer;

public class Audio {

	public static void main(String[] args) throws InterruptedException {
		AudioClip audioClip = null;
		try {
			URL url = new File("files/menu2.wav").toURI().toURL();
			audioClip = Applet.newAudioClip(url);
		} catch (MalformedURLException e) {
			System.err.println(e);
		}
		audioClip.play();
	}

}
