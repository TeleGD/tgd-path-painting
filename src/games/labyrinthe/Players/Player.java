package games.labyrinthe.Players;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppInput;
import app.AppPlayer;
import games.labyrinthe.Labyrinth.*;
import games.labyrinthe.World;

public abstract class Player {

	
	protected World world;
	protected Board board;
	
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
	
	protected int controllerID;
	
	
	public Player(World w,AppPlayer appPlayer) {
		controllerID=appPlayer.getControllerID();
		world=w;
		this.board=w.board;
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		//if(direction==)
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.move(container);
	}
	
	public void move(GameContainer container) {
		if (controllerID == 0) {
			return;
		}
		AppInput input = (AppInput) container.getInput();
		moveLeft = input.isControlPressed(AppInput.BUTTON_LEFT,controllerID);
		moveRight = input.isControlPressed(AppInput.BUTTON_RIGHT,controllerID);
		moveUp = input.isControlPressed(AppInput.BUTTON_UP,controllerID);
		moveDown = input.isControlPressed(AppInput.BUTTON_DOWN,controllerID);
		
		if(moveLeft) {
			if(board.movePlayer(posX-1,posY)) {
				posX--;
			}
		}
		
		else {
			if(moveRight) {
				if(board.movePlayer(posX+1,posY)) {
					posX++;
				}
			}
			
			else {
				if(moveUp) {
					if(board.movePlayer(posX,posY+1)) {
						posY++;
					}
				}
				else {
					if(board.movePlayer(posX,posY-1)) {
						posY--;
					}
				}
			}
		}
		
		
	}
	
	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}
}
