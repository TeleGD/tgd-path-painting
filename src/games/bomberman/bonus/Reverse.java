package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Reverse extends Bonus{
	private boolean activated, deleted;
	private Player player;
	private int duration;
	
	private Sound sound;
	
	public Reverse(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		this.deleted = false;
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_reverse.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sound = new Sound("musics/bonus/interf.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		if(!activated) {
			this.activated = true;
		
			player.setReversed(-player.getReversed());
		
			this.player = player;
			duration = 7;
			
			sound.play(1, (float) 0.4);
		}
	}
	
	public void desactivate() {
		this.player.setReversed(1);
		this.deleted = true;
	}
	
	public boolean isActivated() {
		return this.activated;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		duration-=delta;
		if (activated && (duration<=0)) {
			activated=false;
			this.desactivate();
		}
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}
