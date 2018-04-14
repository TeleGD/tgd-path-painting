package games.labyrinthe.Players;

import app.AppPlayer;
import games.labyrinthe.World;

public class VictimPlayer extends Player {
	
	private boolean invulnerable;
	private boolean immaterial;
	
	public VictimPlayer(World w,AppPlayer aplayer) {
		super(w,aplayer);
		invulnerable = false;
		immaterial = false;
	}

}
