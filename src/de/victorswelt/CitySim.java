package de.victorswelt;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class CitySim extends JDesktopPane  {
	private static final long serialVersionUID = 1L;
	
	JFrame frame;
	CitySimGame game;
	
	public CitySim() {
		
		// create a frame for the game
		frame = new JFrame("CitySim");
		frame.setSize(640, 480);
		frame.add(this);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize the tile graphics
		try {
			TileGraphicType.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setGame(new CitySimGame(this, 10, 100));
		
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				game.setSize(getWidth(), getHeight());
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JInternalFrame jif = new NewGameWindow(this);
		add(jif);
		jif.setVisible(true);
		
		
	}
	
	public void setGame(CitySimGame newGame) {
		if(game != null) {
			game.setVisible(false);
			game.destroy();
			remove(game);
		}
		game = newGame;
		add(game);
		game.setSize(getWidth(), getHeight());
		frame.revalidate();
		frame.repaint();
	}
	
	public static void main(String args[]) {
		new CitySim();
	}
}
