package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Accelerate extends Bonus{
	private float speed;
	private boolean activated;
	private Player player;
	private long initTime;
	
	public Accelerate(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_accelerate.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		this.speed = player.getSpeed();
		this.activated = true;
		
		player.setSpeed(player.getSpeed()*1.25f);
		
		initTime = System.currentTimeMillis();
		
		this.player = player;
	}
	
	public void desactivate() {
		this.player.setSpeed(this.speed);
		World.removeBonus(this);
	}
	
	public boolean isActivated() {
		return this.activated;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		if (activated && (System.currentTimeMillis() - initTime > 30000)) {
			this.desactivate();
		}
		
		super.update(container, game, delta);
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}
