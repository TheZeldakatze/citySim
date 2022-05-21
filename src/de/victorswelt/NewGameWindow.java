package de.victorswelt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class NewGameWindow extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	
	CitySim citySim;
	JSpinner  width, height;
	JButton newGame;
	
	public NewGameWindow(CitySim cs) {
		super("Start new Game");
		citySim = cs;
		
		SpinnerNumberModel spinnerNumberModel1 = new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1);
		SpinnerNumberModel spinnerNumberModel2 = new SpinnerNumberModel(100, 0, Integer.MAX_VALUE, 1);
		width = new JSpinner(spinnerNumberModel1);
		height = new JSpinner(spinnerNumberModel2);
		newGame = new JButton("Start Game!");
		
		// assemble everything
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		add(new JLabel("Width:"));
		add(width);
		add(new JLabel("Height:"));
		add(height);
		add(newGame);
		pack();
		
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int w = ((Integer) width.getValue()),
						h = ((Integer) height.getValue());
				citySim.setGame(new CitySimGame(citySim, w, h));
				//destroy();
			}
		});
	}
	
	public void destroy() {
		citySim.remove(this);
	}
}
