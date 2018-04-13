package games.bomberman.board.cases;

import org.newdawn.slick.Image;

public class DestructibleWall extends Case{

	public DestructibleWall(int i, int j, Image img) {
		super(i, j, img, false);
	}

	@Override
	public void destruct() {
		this=new Ground(this.getI(),this.getJ());	
	}

}
