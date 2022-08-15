package example3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import example3.model.Player;

public class Controller implements ActionListener, ChangeListener {

	private Player player;

	public Controller(Player player) {
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if( command.equals("play") ) {
			player.play();
		} else if (command.equals("pause")) {
			player.pause();
		} else if (command.equals("stop")) {
			player.stop();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider slider = (JSlider) e.getSource();
		int value = slider.getValue();
		System.out.println(value);
		player.setVolume(value);
	}

}
