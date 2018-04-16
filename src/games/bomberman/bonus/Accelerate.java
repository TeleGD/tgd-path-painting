package games.bomberman.bonus;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Accelerate extends Bonus{
	private boolean activated, deleted;
	private Player player;
	private long initTime;
	
	private static Sound sound;
	private static Image sprite;
	
	static {
		try {
			sound = new Sound(World.DIRECTORY_SOUNDS+"bonus"+File.separator+"sncf.ogg");
			sprite = new Image(World.DIRECTORY_IMAGES+"bonus_accelerate.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Accelerate(int caseX, int caseY) {
		super(caseX, caseY);
		super.setSprite(sprite);
		this.activated = false;
		this.deleted = false;
	}
	
	public void activate(Player player) {
		if (!isActivated()) {
			this.activated = true;

			player.setSpeed(player.getSpeed()*10f);

			initTime = System.currentTimeMillis();
		
			this.player = player;

			sound.play(1, (float) 0.4);
		}
	}
	
	public void desactivate() {
		this.deleted = true;
		this.player.setSpeed(player.getSpeed()*0.1f);
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
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}
