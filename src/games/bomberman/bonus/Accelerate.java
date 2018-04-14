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

public class Accelerate extends Bonus{
	private float speed;
	private boolean activated, deleted;
	private Player player;
	private long initTime;
	
	private Sound sound;
	
	public Accelerate(int caseX, int caseY) {
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
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_accelerate.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		if (!isActivated()) {
			this.activated = true;

			this.speed = player.getSpeed();
			player.setSpeed(player.getSpeed()*1.25f);

			initTime = System.currentTimeMillis();
		
			this.player = player;

			sound.play(1, (float) 0.4);
		}
	}
	
	public void desactivate() {
		this.deleted = true;
		this.player.setSpeed(this.speed);
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public boolean isActivated() {
		return this.activated;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		if (activated && (System.currentTimeMillis() - initTime > 7000)) {
			this.desactivate();
		}
		
		super.update(container, game, delta);
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}
