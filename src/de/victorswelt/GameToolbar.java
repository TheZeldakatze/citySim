package de.victorswelt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameToolbar extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	CitySimGame game;
	
	JButton inspect, build, bulldoze, water;
	
	BuildWindow buildWindow;
	
	public GameToolbar(CitySimGame csg) {
		game = csg;
		
		buildWindow = new BuildWindow(game);
		game.add(buildWindow);
		buildWindow.setVisible(false);
		
		inspect  = new JButton(GameIcon.inspect);
		build    = new JButton(GameIcon.build);
		bulldoze = new JButton(GameIcon.bulldoze);
		water    = new JButton(GameIcon.water);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(inspect);
		add(build);
		add(bulldoze);
		add(water);
		
		inspect.setActionCommand("INSP");
		build.setActionCommand("BUILD");
		bulldoze.setActionCommand("BULLDOZE");
		water.setActionCommand("WATER");
		
		inspect.addActionListener(this);
		build.addActionListener(this);
		bulldoze.addActionListener(this);
		water.addActionListener(this);
		
		// I don't know why but when recreating the toolbar the size is not properly set and we have to do it manually or else the layout creates an empty bar
		setSize(getPreferredSize());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		if(source == inspect.getActionCommand()) {
			game.setTool(new SelectorTool(game, game.tileWorld));
		} else
		if(source == build.getActionCommand()) {
			buildWindow.setVisible(true);
		} else
		if(source == bulldoze.getActionCommand()) {
			
		} else
		if(source == water.getActionCommand()) {
			
		}
	}
}
