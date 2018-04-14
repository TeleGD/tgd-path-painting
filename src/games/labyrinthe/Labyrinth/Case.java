package games.labyrinthe.Labyrinth;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Case {

		protected int posX;
		protected int posY;
		protected int i;
		protected int j;
		
		private int size;
		
		protected boolean passable;
		protected Image img;
		
		public Case(int i,int j, int size, Image img) {
			this.i=i;
			this.j=j;
			this.img=img;
			this.size = size;
		}


		public void render (GameContainer container, StateBasedGame game, Graphics context) {
			context.drawImage(img, j*size, i*size);
		}
		
		public int getSize() {
			return size;
		}


		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}

		public int getPosX() {
			return posX;
		}
		
		public int getPosY() {
			return posY;
		}
		
	
}