package games.bomberman.bonus;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;
import games.bomberman.World;
import games.bomberman.board.cases.Case;
import games.bomberman.board.cases.Ground;

public class Teleport extends Bonus{
	private boolean activated, deleted;
	
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
	}
	
	public void activate(Player player) {
		this.activated = true;
		
		int i, j;
		
		do {
			i = (int)(Math.random()*World.getBoard().getDim()[0]);
			j = (int)(Math.random()*World.getBoard().getDim()[1]);
		} while(!World.getBoard().getCase(i, j).isPassable());
		
		player.setI(i);
		player.setJ(j);
		
		this.deleted = true;
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
