package example2;

import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class AudioClip {

	public static void main(String[] args) throws IOException {
		File file = new File("files/menu2.wav");
		InputStream inputStream = new FileInputStream(file);
		// Create an AudioStream object from the input stream.
		AudioStream audioStream = null;
		try {
			audioStream = new AudioStream(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Create audio stream as discussed previously. Create AudioData source.
		AudioData audioData = audioStream.getData();
		// Create ContinuousAudioDataStream.
		ContinuousAudioDataStream continuousAudioDataStream = new ContinuousAudioDataStream(audioData);
		// Use the static class member "player" from class AudioPlayer to play
		// clip.
		AudioPlayer.player.start(audioStream);
	}

}
