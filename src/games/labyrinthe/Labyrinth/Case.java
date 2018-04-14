package games.labyrinthe.Labyrinth;

import org.newdawn.slick.Image;

public abstract class Case {

		protected int posX;
		protected int posY;
		protected int i;
		protected int j;
		
		protected boolean passable;
		protected Image img;
		protected float size=50;
		
		public Case(int i,int j,Image img) {
			this.i=i;
			this.j=j;
			this.img=img;
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