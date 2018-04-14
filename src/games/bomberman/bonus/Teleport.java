package games.bomberman.bonus;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;
import games.bomberman.board.cases.Case;
import games.bomberman.board.cases.Ground;

public class Teleport extends Bonus{
	private boolean activated, deleted;
	private Sound sound;
	
	public Teleport(int caseX, int caseY) {
		super(caseX, caseY);
		this.activated = false;
		this.deleted = false;
		
		try {
			Image sprite = new Image(World.DIRECTORY_IMAGES+"bonus_teleport.png");
			super.setSprite(sprite);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sound = new Sound("musics/bonus/tp.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void activate(Player player) {
		if(!activated) {
			
			sound.play(1, (float) 0.4);
			
			this.activated = true;
		
			int i, j;
		
			do {
				i = (int)(Math.random()*World.getBoard().getDim()[0]);
				j = (int)(Math.random()*World.getBoard().getDim()[1]);
			} while(!World.getBoard().getCase(i, j).isPassable());
		
			player.setIJ(i,j);
		
			this.deleted = true;
		}
	}
	
	public boolean isActivated() {
		return this.activated;
	}
	
	public boolean isDeleted() {
		return this.deleted;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
	}	
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		super.render(container, game, context);
	}
}
