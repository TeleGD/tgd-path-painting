package games.labyrinthe.Labyrinth;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.labyrinthe.World;

public class Board {

	private Case board[][];
	private int rows;
	private int columns;
	private World w;
	
	public Board(World w, int rows, int columns) {
		this.w=w;
	}
	
	public boolean movePlayer(int posX, int posY){
		if(posX < rows && posY < columns && posX >= 0 && posY >= 0 /*&& board[posX][posY].getContains()==-1*/){
			//set new cell true
			// board[posX][posY].setContains(w.players.indexOf(p));
			//set old cell false
			// board[p.getX()][p.getY()].setContains(-1);
			
			return true;
		}
		return  false; 
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}
}
