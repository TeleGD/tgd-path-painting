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
	private labyGenerator lab;
	
	public Board(World w, int rows, int columns) {
		this.w=w;
		this.lab = (new labyGenerator(rows,columns));
		board=lab.getLab();
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
		for (int i=0; i<this.lab.getNbLig();i++){
			for (int j=0; j<this.lab.getNbCol();j++){
				this.board[i][j].render(container, game, context);
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}
}
