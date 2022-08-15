package example3.model;

import example3.view.AudioPlayer;



public class Main {

	public static void main(String[] args) {
		Player player = new Player("files/song1.wav");
		AudioPlayer audioPlayer = new AudioPlayer(player);
	}

}
