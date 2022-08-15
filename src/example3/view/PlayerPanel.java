package example3.view;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import example3.model.Player;

import example3.controller.Controller;

public class PlayerPanel extends JPanel {

	private JSlider volume;
	private BoundedRangeModel model;
	private JButton play;
	private JButton pause;
	private JButton stop;
	private Controller controller;

	public PlayerPanel(Player player) {
		super(true);
		controller = new Controller(player);
		model = new DefaultBoundedRangeModel(100, 0, 0, 200);
		volume = new JSlider(model);
		volume.addChangeListener(controller);
		play = new JButton("Play");
		play.setActionCommand("play");
		play.addActionListener(controller);
		pause = new JButton("Pause");
		pause.setActionCommand("pause");
		pause.addActionListener(controller);
		stop = new JButton("Stop");
		stop.setActionCommand("stop");
		stop.addActionListener(controller);
		add(volume);
		add(play);
		add(pause);
		add(stop);
	}

	public int getValue() {
		return volume.getValue();
	}

}
