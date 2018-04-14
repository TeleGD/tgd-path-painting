package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import games.bomberman.World;

public class WallCase extends Case {
	
	private boolean destructible;
	private static Image image;
	
	static {
		try {
			image = new Image(World.DIRECTORY_IMAGES+"/Ground.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public WallCase(int i, int j, int size, boolean destructible) throws SlickException {
		super(i, j, size, image);
		this.destructible = destructible;
	}

	public boolean isDestructible() {
		return destructible;
	}
	
	
}
