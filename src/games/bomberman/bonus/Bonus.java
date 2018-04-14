package games.bomberman.bonus;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.Player;

public abstract class Bonus {
	private int caseX, caseY;
	private Image sprite;
	
	public Bonus(int caseX, int caseY) {
		this.caseX = caseX;
		this.caseY = caseY;
	}
	
	public void setSprite(Image s) {
		this.sprite = s;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(sprite, caseX*50, caseY*50);
	}

	public abstract boolean isActivated();
	public abstract void activate(Player player) ;
	public abstract boolean isDeleted();
}