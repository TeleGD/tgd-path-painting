package games.bomberman;

import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class LesTrucsQuiVontSAfficherACoteDuPlateau {
	
	private List<Player> players;
	private int hauteur;
	private int longueur;
	private int hauteurPlateau;
	
	public LesTrucsQuiVontSAfficherACoteDuPlateau(World w, int hauteurPlateau) {
		this.players = World.getPlayers();
		this.hauteurPlateau=hauteurPlateau;
		this.hauteur = w.getHeight()-hauteurPlateau;
		this.longueur = w.getWidth();
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		for (int i = 0 ; i<players.size() ; i++) {
			context.setColor(Color.white);
			context.drawLine((i*longueur-1)/(players.size()), hauteurPlateau, (i*longueur-1)/(players.size()), hauteurPlateau+hauteur);
		}
	}

}
