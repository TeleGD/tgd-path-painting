package games.bomberman.board.cases;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Case {

	protected boolean passable;
	private Image img;
	private int i,j;
	private float size=50;
	
	public Case(int i,int j,Image img,boolean passable) {
		this.i=i;
		this.j=j;
		this.img=img;
		this.passable=passable;
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(img, j*50, i*50);
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
	}
	
	
	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	
	
}
