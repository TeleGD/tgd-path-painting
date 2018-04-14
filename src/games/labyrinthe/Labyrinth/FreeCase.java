package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import games.labyrinthe.World;

public class FreeCase extends Case {
	
	private int playerId;
	private int bonusId;
	
	private static Image image;
	
	static {
		try {
			image = new Image(World.DIRECTORY_IMAGES+"/Ground.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public FreeCase(int i, int j,int size){
		super(i, j, size, image);
		
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
