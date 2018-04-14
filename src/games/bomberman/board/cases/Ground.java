package games.bomberman.board.cases;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ground extends Case{

	public Ground(int i, int j) throws SlickException {
		super(i, j, new Image("images/bomberman/Ground.png"), true);
	}

}
