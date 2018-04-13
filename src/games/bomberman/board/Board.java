package games.bomberman.board;

import games.bomberman.board.cases.Case;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.bomberman.board.cases.*;
public class Board {

	//taille case 50x50
	private float caseSize=50;
	private Case[][] cases;


	//Dans un permier temps, generation non dependante d'un niveau
	public Board() {
		cases = new Case[21][21];

		for(int i=0;i<21;i++) {
			for(int j=0;j<21;j++) {
				try {
					if(i%2==0) {
						cases[i][j]=new Ground(i,j);
					}else if(j%2==0) {
						cases[i][j]=new Ground(i,j);
					}else {
						cases[i][j]=new Wall(i,j);
					}
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 0: min x
	 * 1: max x
	 * 2: min y
	 * 3: max y
	 */
	public float[] getlimits() {
		float[] result= new float[4];
		result[0]=0;
		result[1]=cases.length*caseSize;
		result[2]=0;
		result[3]=cases[0].length*caseSize;
		return result;
	}
	
	public Case getCase(int i, int j) {
		return cases[i][j];
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		for(Case[] c:cases) {
			for(Case ca:c) {
				ca.render(container, game, context);
			}
		}
	}
}
