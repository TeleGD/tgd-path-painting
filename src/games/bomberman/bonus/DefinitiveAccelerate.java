package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class DefinitiveAccelerate extends Bonus{
	private boolean activated, deleted;
	
	private Sound sound;
	
	public DefinitiveAccelerate(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		this.deleted = false;

		try {
			sound = new Sound("musics/bonus/sncf.ogg");
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_defAccelerate.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		if (!isActivated()) {
			this.activated = true;

			player.setSpeed(player.getSpeed()*1.10f);
			
			this.deleted = true;
			
			sound.play(1, (float) 0.4);
		}
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public boolean isActivated() {
		return this.activated;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}