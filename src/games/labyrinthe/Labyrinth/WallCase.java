package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;

public class WallCase extends Case {
	
	private boolean destructible;

	public WallCase(int i, int j,int height, int width, boolean destructible, Image img) {
		super(i, j, height, width, img);
		this.destructible = destructible;
	}

	public boolean isDestructible() {
		return destructible;
	}
	
	
}
