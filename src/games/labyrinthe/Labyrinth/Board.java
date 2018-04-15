package games.labyrinthe.Labyrinth;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import games.labyrinthe.World;
import games.labyrinthe.Players.Player;

public class Board {

	private Case board[][];
	private int rows;
	private int columns;
	private World w;
	private LabyGenerator lab;
	private int size;
	
	public Board(World w, int rows, int columns) {
		this.w=w;
		this.lab = (new LabyGenerator(rows,columns));
		board=lab.getLab();
		int height = w.getHeight();
		int width = w.getWidth();
		size = (columns/width>height/rows)?columns/width:height/rows;
	}
	
	public boolean movePlayer(int posX, int posY, Player p){
		if(posX < rows && posY < columns && posX >= 0 && posY >= 0 && board[posX][posY] instanceof FreeCase){
			if (board[posX][posY].getPlayerId()==-1) {
				//set new cell true
				board[posX][posY].setPlayerId(w.players.indexOf(p));
				//set old cell false
				board[p.getPosX()][p.getPosY()].setPlayerId(-1);
				return true;
			} else if (board[posX][posY].getPlayerId()==0) {
				w.endGame();
			}
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
	
	public int getSize() {
		return this.size;
	}
}
