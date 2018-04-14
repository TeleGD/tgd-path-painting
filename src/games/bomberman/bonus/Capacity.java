package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Capacity extends Bonus{
	
	private boolean activated, deleted;
	private Sound sound;
	
	public Capacity(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		this.deleted = false;
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_capacity.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		try {
			sound = new Sound("musics/bonus/tataa.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!isActivated()) sound.play(1, (float) 0.4);
		this.activated = true;
		
		player.setBombCapacity(player.getBombCapacity()+1);
		
		deleted = true;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}
	public boolean isActivated() {
		return this.activated;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}