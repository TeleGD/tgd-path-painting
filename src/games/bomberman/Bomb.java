package games.bomberman;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.board.Board;
import games.bomberman.board.cases.Case;
import games.bomberman.board.cases.DestructibleWall;
import games.bomberman.board.cases.Wall;

public class Bomb {

	private int x;
	private int y;
	private int i, j;
	private int portee;
	private int tpsRestant;
	private int numJoueur;
	private Image sprite,bord,milieu,centre;
	private int arret[]= {0,0,0,0};
	private int tempsExplosion=700;
	private boolean detruite = false,explose=false;
	private Sound sound;
	
	
	public Bomb(int numJoueur,int i,int j,int porteep,int tpsRestantp) {
		
		this.i = i;
		this.j = j;
		int[] XY = convertInXY(i,j);
		x = XY[0];
		y = XY[1];
		portee=porteep;
		for (int k=0;k<4;k++) {arret[k]=portee;}
		tpsRestant=tpsRestantp;
		try {
			sprite = new Image("images/bomberman/bombe.png");
			bord = new Image("images/bomberman/fin_deflagration.png");
			milieu = new Image("images/bomberman/deflagration.png");
			centre = new Image("images/bomberman/bombe_explose.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sound = new Sound("musics/bomb/explo_forte.ogg");
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
		if (!explose) {
			tpsRestant-=delta;
			if(tpsRestant<=0) {
				this.BombExplose();
			}
		} else {
			tempsExplosion-=delta;
			if (tempsExplosion<=0) {
				detruite = true;
			}
		}
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		if (!explose) {
		context.drawImage(sprite, x, y);
		} else {
			
			context.drawImage(centre,x,y);
			bord.rotate(90);
			milieu.rotate(90);
			for (int k=1;k<arret[0];k++) {
				if (k==portee-1) {
					context.drawImage(bord, x, y-50*k);
				} else {
					context.drawImage(milieu, x, y-50*k);
				}
			}
			bord.rotate(90);
			milieu.rotate(90);
			for (int k=1;k<arret[1];k++) {
				if (k==portee-1) {
					context.drawImage(bord, x+50*k, y);
				} else {
					context.drawImage(milieu, x+50*k, y);
				}
			}
			bord.rotate(90);
			milieu.rotate(90);
			for (int k=1;k<arret[2];k++) {
				if (k==portee-1) {
					context.drawImage(bord, x, y+50*k);
				} else {
					context.drawImage(milieu, x, y+50*k);
				}
			}
			bord.rotate(90);
			milieu.rotate(90);
			for (int k=1;k<arret[3];k++) {
				if (k==portee-1) {
					context.drawImage(bord, x-50*k, y);
				} else {
					context.drawImage(milieu, x-50*k, y);
				}
			}
			
		}
	}
	
/*	public void BombExplose()
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
	*/
	
	public void BombExplose() {
		
		for (int dir=0;dir<4;dir++) {
			boolean stop = false;
			int d = 0;
			while (d<portee && !stop) {
				int l=i,c=j;
				switch (dir) {
				case 0 :
					l-=d;
					break;
				case 1 :
					c+=d;
					break;
				case 2 :
					l+=d;
					break;
				case 3 :
					c-=d;
				}
				//System.out.println(l+" "+c);
				if ( c<0 || l<0 || c>24 || l>12 ) {
					arret[dir] = d;
					stop = true;
				} else {
					Case ca = World.getBoard().getCase(l, c);
					if (ca instanceof DestructibleWall) {
						World.getBoard().destruct(l, c);
						arret[dir] = d+1;
						stop = true;
					}
					if (ca instanceof Wall) {
						arret[dir] = d;
						stop = true;
					}
					for (Player p : World.getPlayers()) {
						if (p.getI()==l && p.getJ()==c) {
							p.takeDamage();
							//System.out.println("prend damage");
						}
					}
				}
				d++;
			}
		}
//		for (int x : arret) {
//			System.out.println(x);
//		}
		sound.play(1, (float) 0.8);
		explose = true;

	}
	
	
}
