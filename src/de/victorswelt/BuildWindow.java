package de.victorswelt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

public class BuildWindow extends JInternalFrame {
	private CitySimGame game;
	
	public BuildWindow(CitySimGame csg) {
		super("Build", true, true, true, true);
		game = csg;
		
		setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		add(new BuildingButton(game, "Farm", BuildingBlueprintType.FARM, Farm.class));
		add(new BuildingButton(game, "Small House", BuildingBlueprintType.SMALL_HOUSE, SmallHouse.class));
		pack();
	}
	
	// a class for 
	private class BuildingButton extends JButton implements ActionListener {
		private CitySimGame game;
		private BuildingBlueprintType blueprint;
		Class<? extends Building> buildingClass;
		
		public BuildingButton(CitySimGame csg, String name, BuildingBlueprintType blueprint, Class<? extends Building> buildingClass) {
			super(name);
			game = csg;
			this.blueprint = blueprint;
			this.buildingClass = buildingClass;
			
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			game.setTool(new BuildingTool(game, game.tileWorld, blueprint, buildingClass));
		}
	}
}
