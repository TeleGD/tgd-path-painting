package games.bomberman.board.cases;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends Case{

	public Wall(int i, int j) throws SlickException {
		super(i, j, new Image("images/bomberman/Wall.png"), false);
	}

	@Override
	public void destruct() {
	}

	
	
}
