package games.labyrinthe.Players;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;
import games.bomberman.board.Board;
import games.labyrinthe.World;

public abstract class Player {

	
	protected World world;
	protected Board bd;
	
	protected Image spriteUp;
	protected Image spriteDown;
	protected Image spriteLeft;
	protected Image spriteRight;
	
	
	protected int posX;
	protected int posY;
	protected int x;
	protected int y;
	
	protected int speed;
	protected int direction;// 1 right 2 up 3 left 4 down 
	protected boolean moveLeft;
	protected boolean moveRight;
	protected boolean moveDown;
	protected boolean moveUp;
	
	protected int controlerID;
	
	
	public Player(World w,AppPlayer aplayer) {
		controlerID=aplayer.getControllerID();
		world=w;
		bd=w.board;
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		//if(direction==)
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.move(container);
	}
	
	public void move(GameContainer container) {
		AppInput input = (AppInput) container.getInput();
		moveLeft = input.isControlPressed(AppInput.BUTTON_LEFT,controlerID);
		moveRight = input.isControlPressed(AppInput.BUTTON_RIGHT,controlerID);
		moveUp = input.isControlPressed(AppInput.BUTTON_UP,controlerID);
		moveDown = input.isControlPressed(AppInput.BUTTON_DOWN,controlerID);
		
		if(moveLeft) {
			if(bd.movePlayer(posX-1,posY)) {
				posX--;
			}
		}
		if(moveRight) {
			if(bd.movePlayer(posX+1,posY)) {
				posX++;
			}
		}
		
	}
	
}
