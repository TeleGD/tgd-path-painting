package games.bomberman;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.board.Board;

public class Bomb {

	private int x;
	private int y;
	private int i, j;
	private int portee;
	private int tpsRestant;
	private int numJoueur;
	private Image sprite;
	private boolean detruite = false;
	
	
	public Bomb(int numJoueur,int i,int j,int porteep,int tpsRestantp) {
		
		this.i = i;
		this.j = j;
		int[] XY = convertInXY(i,j);
		x = XY[0];
		y = XY[1];
		portee=porteep;
		tpsRestant=tpsRestantp;
		try {
			sprite = new Image("images/bomberman/bombe.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int[] convertInXY(int i, int j) {
		int sizeCase = (int) World.getBoard().getCaseSize();
		return new int[] {j * sizeCase, i * sizeCase};
	}
	
	public int getNumJoueur() {
		return numJoueur;
	}
	
	public int getTpsRestant() {
		return tpsRestant;
	}
	
	public boolean isDetruite() {
		return detruite;
	}

	public void update(int delta) {
		tpsRestant-=1*delta;
		if(tpsRestant<=0) {
			this.BombExplose();
		}
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(sprite, x, y);
	}
	
	public void BombExplose()
	{
		
		float[] tab=World.getBoard().getlimits();//renvoie limite basse et droite
 		float LR=tab[1];
 		float LB=tab[3];
		
		
		float xc=x;
		float yc=y;//curseurs pour se déplacer
		
		float Tcase=World.getBoard().getCase(0,0).getSize();//initialisation denom
		int cx,cy;//indices de cases
		//////////////////////////////////////
		int i=0;
		int ht=0;
		while(yc>0 && i<portee) {
			yc--;
			i++;
			ht++;
		}//ht est le nombre de cases que l'on peut monter 
		yc=y;
		/////////////////////////////////////
		i=0;
		int lft=0;
		while(xc>0 && i<portee) {
			xc--;
			i++;
			lft++;
		}//lft nb de cases dispos a gauche
		xc=x;
		/////////////////////////////////////
		i=0;
		int rgt=0;
		while(xc<LR && i<portee) {
			xc++;
			i++;
			rgt++;
		}//rgt nb de cases dispos a droite
		xc=x;
		/////////////////////////////////////
		i=0;
		int bs=0;
		while(yc<LB && i<portee) {
			yc++;
			i++;
			bs++;
		}//bs nb de cases dispos en dessous 
		yc=y;
		//////////////////////////////////////
		
		
		
		
		//parcours des cases pour destruction
		for(yc=y-ht;yc<y+bs;yc++) {//commence au coin sup gauche
			
			xc=x-lft;
			while(xc<x+rgt) {
				cx=(int) (xc/Tcase);
				cy=(int) (yc/Tcase);//traduire une position en num de case
				World.getBoard().destruct(cy,cx);
				xc++;
			}
		}
		//////////////////////////////////////
		//recherche de joueurs a frapper
		List<Player> pl= World.getPlayers();
		for(Player p:pl) {
			float xpl=p.getX();
			float ypl=p.getY();
			if(x-lft<=xpl && x+rgt>=xpl && y-ht<=ypl && y+bs>=ypl ) {
			
				p.takeDamage();
					
			}
		}
		///////////////////////////////////////
		// autre moyen : obtenir coords cellule bombe puis portee dans toutes directions
		
		
		detruite = true;
		
	}
	
	
	
}
