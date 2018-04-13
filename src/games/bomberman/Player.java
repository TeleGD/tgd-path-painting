package games.bomberman;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppPlayer;
import app.AppInput;

public class Player {

	private Color fillColor;
	private Color strokeColor;
	private int controllerID;
	private String name;
	
	//Position du joueur :
	private float x = 50;	// Abcisse réelle du joueur
	private float y = 50;	// Ordonnée réelle du joueur
	private int i;		// Position du joueur dans la matrice
	private int j;
	
	private int height = 50;
	private int width = 50;

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
		
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(fillColor);
		context.fillRect(x, y, height, width);
	}

	public int getControllerID () {
		return this.controllerID;
	}

	public String getName () {
		return this.name;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

}
