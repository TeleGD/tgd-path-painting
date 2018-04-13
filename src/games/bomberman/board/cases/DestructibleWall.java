package games.bomberman.board.cases;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DestructibleWall extends Case{

	public DestructibleWall(int i, int j) throws SlickException {
		super(i, j, new Image("images/bomberman/Rocher.png"), false);
	}

}
