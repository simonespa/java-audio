package example3.view;

import javax.swing.JFrame;

import example3.model.Player;

public class AudioPlayer extends JFrame {

	private PlayerPanel panel;

	public AudioPlayer(Player player) {
		super("Player");
		panel = new PlayerPanel(player);
		setContentPane(panel);
		setSize(500, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public int getValue() {
		return panel.getValue();
	}

}
