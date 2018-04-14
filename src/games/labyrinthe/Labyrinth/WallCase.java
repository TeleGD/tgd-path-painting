package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WallCase extends Case {
	
	private boolean destructible;

	public WallCase(int i, int j,int height, int width, boolean destructible) throws SlickException {
		super(i, j, height, width, new Image("images/bomberman/Wall.png"));
		this.destructible = destructible;
	}

	public boolean isDestructible() {
		return destructible;
	}
	
	
}
