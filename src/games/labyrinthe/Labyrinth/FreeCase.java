package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FreeCase extends Case {
	
	private int playerId;
	private int bonusId;
	

	public FreeCase(int i, int j,int height, int width) throws SlickException {
		super(i, j, height, width, new Image("images/bomberman/Ground.png"));
	}


	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public int getBonusId() {
		return bonusId;
	}


	public void setBonusId(int bonusId) {
		this.bonusId = bonusId;
	}
	
	

}
