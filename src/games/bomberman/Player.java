package games.bomberman;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import app.AppPlayer;
import games.bomberman.board.cases.TP;
import app.AppInput;

public class Player {

	private Color fillColor;
	private Color strokeColor;
	private int controllerID;
	private String name;
	
	//Position du joueur :
	private float x;	// Abcisse réelle du joueur
	private float y;	// Ordonnée réelle du joueur
	private float nextX, nextY;
	private int i;		// Position du joueur dans la matrice
	private int j;		// pillule bleue ou pillule rouge?
	
	private int height = 50;
	private int width = 50;
	private Animation animations[] = new Animation[8];
	
	private int life = 3;

	private boolean moveLeft,moveRight,moveUp,moveDown, dropTheBomb, move=false;
	private int direction;
	
	// Caractéristiques modifiables par bonus/malus :
	private float speed = 1;
	private int reversed = 1; // =1 : controles normaux, =-1 : controles inversés
	private int bombCapacity = 3; // Nombre de bombe posable en même temps
	private int dropCoolDown = 2000; // 2sec entre chaque bombe posée
	private int cooldownBomb = 0; // temps restant pour poser la prochaine bombe : est initialité à cooldownTime à chaque bombe posée
	private int clignotement;
	private boolean bombDropped =false;
	private boolean bouclier = false; 
	private boolean tpable; //pour la case de TP	
	private int range = 1; // Portée des bombes en cases
	private int invincibilite=1500;
	private boolean invincible;
	private Image imgBouclier;


	public Player (AppPlayer appPlayer) {
		// Trucs de Tristan :
		int colorID = appPlayer.getColorID ();
		int controllerID = appPlayer.getControllerID ();
		String name = appPlayer.getName ();
		this.fillColor = AppPlayer.FILL_COLORS [colorID];
		this.strokeColor = AppPlayer.STROKE_COLORS [colorID];
		this.controllerID = controllerID;
		this.name = name;
		// Fin des trucs de Tristan
		tpable = true;
		invincible=false;
		clignotement =200;
		try {
			imgBouclier = new Image ("images/bomberman/bouclier.png");
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Attribution des positions de départ en fonction du n° de joueur
		Integer[] size = World.getBoard().getDim();
		switch(controllerID) {
			case 0 : i = 0;
				j = 0;
				break;
			case 1 : i = size[0]-1;
				j = size[1] -1;
				break;
			case 2 : i = 0;
					j = size[1] -1;
					break;
			case 3 : i = size[0] -1;
					j = 0;
					break;
		}
		updateXY(); // initialisation de x et y selon les i et j de départ
		
		direction = 2;
		
		SpriteSheet perso;
		try {
			perso = new SpriteSheet("images/bomberman/personnage.png",50,50);
			animations[0] = loadAnimation(perso,0,1,0);
			animations[1] = loadAnimation(perso,0,1,1);
			animations[2] = loadAnimation(perso,0,1,2);
			animations[3] = loadAnimation(perso,0,1,3);
			animations[4] = loadAnimation(perso,1,9,0);
			animations[5] = loadAnimation(perso,1,9,1);
			animations[6] = loadAnimation(perso,1,9,2);
			animations[7] = loadAnimation(perso,1,9,3);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX;x<endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		AppInput input = (AppInput) container.getInput();
		moveLeft = input.isControlPressed(AppInput.BUTTON_LEFT,controllerID);
		moveRight = input.isControlPressed(AppInput.BUTTON_RIGHT,controllerID);
		moveUp = input.isControlPressed(AppInput.BUTTON_UP,controllerID);
		moveDown = input.isControlPressed(AppInput.BUTTON_DOWN,controllerID);
		dropTheBomb = input.isControlPressed(AppInput.BUTTON_A, controllerID);
		
		if (invincible) {
			invincibilite-=delta;
			if (invincibilite<0) {
				invincible=false;
				clignotement = 200;
				System.out.println("fin");
			}
			clignotement-=delta;
			if (clignotement<0) {
				clignotement=200;
				System.out.println("change");
			}
			
		}
		
		if (moveUp || moveDown || moveRight || moveLeft) {move=true;}
		else {move = false;}
		
		if (bombDropped) {
			cooldownBomb -= delta;
			if (cooldownBomb <= 0) {
				bombDropped = false;
			}
		}
		
		World.getBoard().getCase(i, j).getAction(this);
		mayDropBomb();
		callMove();
//		fluidMove(delta);
		updateXY();
		updateNextXY();
	}
	

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		//context.setColor(fillColor);
		//context.fillRect(x, y, height, width);
		if (!invincible) {
			context.drawAnimation(animations[direction + (move ? 4 : 0)], x, y);
		} else {
			if (clignotement < 100) {context.drawAnimation(animations[direction + (move ? 4 : 0)], x, y);}
		}
		if (bouclier) {
			context.drawImage(imgBouclier, x, y);
		}
	}
	
	public void callMove() throws SlickException{
		int deltaJ = 0, deltaI = 0;
		if(moveUp && !moveDown && !moveRight && ! moveLeft){ //haut
			deltaI = -1;
			direction = 0;
			moveUp = false;
		}
		if(moveDown && !moveUp && !moveRight && ! moveLeft){ //bas
			deltaI = 1;
			direction = 2;
			moveDown = false;
		}
		if(moveLeft && !moveRight && !moveUp && !moveDown){ //gauche
			deltaJ = -1;
			direction = 1;
			moveLeft = false;
		}
		if(moveRight && !moveLeft && !moveUp && !moveDown){ //droite
			deltaJ = 1;
			direction = 3;
			moveRight = false;
		}
		
		int newI = i + deltaI*reversed;
		int newJ = j + deltaJ*reversed;
		Integer[] size = World.getBoard().getDim();
		if (newI >= 0 && newI < size[0] && newJ >= 0 && newJ < size[1]) {
			if (World.getBoard().getCase(newI,newJ).isPassable()) {
				if(!tpable && !(World.getBoard().getCase(newI,newJ) instanceof TP))
					tpable = true;
				
				move(deltaI,deltaJ);
			}
		}	
	}
	
	public void move(int deltaI, int deltaJ) {
		i += deltaI * reversed;
		j += deltaJ * reversed;
	}
    
//    public void fluidMove(int delta) {
//    	switch(direction) {
//	    	case 0 : y = (y > nextY) ? y-delta*speed : nextY; break;
//	    	case 1 : x = (x > nextX) ? x-delta*speed : nextX; break;
//	    	case 2 : y = (y < nextY) ? y+delta*speed : nextY; break;
//	    	case 3 : x = (x < nextX) ? x+delta*speed : nextX; break;
//    	}
//    }
	
	public void updateXY() {
		int[] XY = convertInXY(i,j);
		x = XY[0];
		y = XY[1];
	}
    
	public void updateNextXY() {
		int[] XY = convertInXY(i,j);
		nextX = XY[0];
		nextY = XY[1];
	}
	
	public int[] convertInXY(int i, int j) {
		int sizeCase = (int) World.getBoard().getCaseSize();
		return new int[] {j * sizeCase, i * sizeCase};
	} 
	
	public void mayDropBomb() {
		if (dropTheBomb && !bombDropped) {
			dropBomb();
			dropTheBomb =false;
			cooldownBomb = dropCoolDown;
			bombDropped = true;
		}
	}
	
	public void dropBomb() {
		World.addBomb(controllerID, i, j, range, 3000);
	}
	
	public void addRange(int deltaRange) {
		range = Math.max(1, range + deltaRange ); // Car le minimum de portée est d'une case
	}

	public int getLife() {
		return life;
	}

	public void addLife(int deltaLife) {
		this.life += deltaLife;
	}
	
	public void takeDamage() {
		if (bouclier) {
			bouclier = false;
		} else {
			if (!invincible) {
				addLife(-1);
				invincible = true;
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getReversed() {
		return reversed;
	}

	public void setReversed(int reversed) {
		this.reversed = reversed;
	}

	public int getDropCoolDown() {
		return dropCoolDown;
	}

	public void setDropCoolDown(int dropCoolDown) {
		this.dropCoolDown = dropCoolDown;
	}

	public int getBombCapacity() {
		return bombCapacity;
	}

	public void setBombCapacity(int bombCapacity) {
		this.bombCapacity = bombCapacity;
	}

	public boolean isBouclier() {
		return bouclier;
	}

	public void setBouclier(boolean bouclier) {
		this.bouclier = bouclier;
	}

	public int getControllerID () {
		return this.controllerID;
	}

	public String getName () {
		return this.name;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setIJ(int i, int j) {
		this.i =i;
		this.j = j;
		updateXY();
		updateNextXY();
	}
	
	public boolean isTPable() {
		return this.tpable;
	}
	
	public void setTPable(boolean b) {
		this.tpable = b;
	}
}
