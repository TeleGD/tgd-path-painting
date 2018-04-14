package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Range extends Bonus{
	
	private boolean activated, deleted;
	private int rand;
	//private Sound sound;
	
	public Range(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		this.deleted = false;
		rand = (int)(Math.random()*2);
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_range"+rand+".png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*try {
			sound = new Sound("musics/bonus/.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void activate(Player player) {
		if (!activated) {
			this.activated = true;
			
			if (rand == 0) {
				player.addRange(1);
			} else {
				player.addRange(-1);
			}
			
			
			this.deleted = true;

			//sound.play(1, (float) 0.4);
		}
	}
	
	public boolean isDeleted() {
		return this.deleted;
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