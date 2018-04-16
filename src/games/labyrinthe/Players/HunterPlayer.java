package games.labyrinthe.Players;

import app.AppPlayer;
import games.labyrinthe.World;

public class HunterPlayer extends Player {

	public HunterPlayer(World w, AppPlayer aplayer) {
		super(w, aplayer);
		// TODO Auto-generated constructor stub
	}
	
	public void collideWithPlayer(Player p) {
		if(p instanceof VictimPlayer) {
			super.world.endGame(this);
		}
	}

}
