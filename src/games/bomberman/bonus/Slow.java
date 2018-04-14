package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;

public class Slow extends Bonus{
	private boolean activated;
	private Player player;
	private long initTime;
	private Sound sound;
	
	public Slow(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_slow.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		try {
			sound = new Sound("musics/bonus/gary.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sound.play();
		for (Player p : World.getPlayers()) {
			if (!p.equals(player)) {
				p.setSpeed(p.getSpeed()*0.75f);
			}
		}
		
		initTime = System.currentTimeMillis();
		
		this.player = player;
	}
	
	public void desactivate() {
		for (Player p : World.getPlayers()) {
			if (!p.equals(player)) {
				p.setSpeed(1);
			}
		}
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
