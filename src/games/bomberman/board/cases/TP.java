package games.bomberman.board.cases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;

public class TP extends Case{

	private Case twin;

	public TP(int i, int j, Case twin) throws SlickException {
		super(i, j, new Image("images/bomberman/try.png"), true);
		this.twin=twin;
	}

	public void getAction(Player p) {
		super.getAction(p);
		if(p.getOldI()!=twin.getI() && p.getOldJ()!=twin.getJ()) {
			p.setIJ(twin.getI(),twin.getJ());
		}
	}

}
