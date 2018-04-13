package games.bomberman.board;

import games.bomberman.board.cases.Case;

import java.util.ArrayList;

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
	public Board(int imax,int jmax) {
		cases = new Case[imax][jmax];
		//j sur x et i sur y
		try {

			//bords safes
			cases[0][0]=new Ground(0,0);
			cases[0][1]=new Ground(0,1);
			cases[1][0]=new Ground(1,0);

			cases[0][jmax-1]=new Ground(0,jmax-1);
			cases[1][jmax-1]=new Ground(1,jmax-1);
			cases[0][jmax-2]=new Ground(0,jmax-2);

			cases[imax-1][0]=new Ground(imax-1,0);
			cases[imax-1][1]=new Ground(imax-1,1);
			cases[imax-2][0]=new Ground(imax-2,0);

			cases[imax-1][jmax-1]=new Ground(imax-1,jmax-1);
			cases[imax-2][jmax-1]=new Ground(imax-2,jmax-1);
			cases[imax-1][jmax-2]=new Ground(imax-1,jmax-2);

			for (int i=0;i<imax;i++) {
				for(int j=0;j<jmax;j++) {
					if(i%2==1 && j%2==1 && cases[i][j]==null) {
						cases[i][j]= new Wall(i,j);
					}else {
						if(cases[i][j]==null) {
							if(Math.random()>0.7)
								cases[i][j]=new Ground(i,j);
							else
								cases[i][j]=new DestructibleWall(i,j);
						}
					}
				}
			}
			/*if(i%2==0) {
						cases[i][j]=new Ground(i,j);
					}else if(j%2==0) {
						cases[i][j]=new Ground(i,j);
					}else {
						cases[i][j]=new Wall(i,j);
					}*/
		} catch (SlickException e) {
			e.printStackTrace();
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
	public void destruct(int i,int j) {
		try {
			if(cases[i][j] instanceof DestructibleWall)
				cases[i][j]=new Ground(i,j);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Case> getAllCases(){
		ArrayList<Case> result=new ArrayList<Case>();
		for (int i=0;i<cases.length;i++) {
			for(int j=0;j<cases.length;j++) {
				result.add(cases[i][j]);
			}
		}
		return result;
	}
}
