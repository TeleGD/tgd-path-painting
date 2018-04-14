package games.bomberman.board.cases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;

public class TP extends Case{

	private Case twin;
	private Sound sound;

	public TP(int i, int j, Case twin) throws SlickException {
		super(i, j, new Image("images/bomberman/try.png"), true);
		this.twin=twin;
		
		try {
			sound = new Sound("musics/bonus/tp.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAction(Player p) {
		super.getAction(p);
		if(p.isTPable()) {
			p.setTPable(false);			
			p.setIJ(twin.getI(),twin.getJ());
			
			sound.play(1, (float) 0.4);
		}
	}

}
