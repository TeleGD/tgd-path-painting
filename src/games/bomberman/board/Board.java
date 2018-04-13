package games.bomberman.board;

import games.bomberman.board.cases.Case;

public class Board {

	//taille case 50x50
	private float caseSize=50;
	private Case[][] cases;
	
	
	//Dans un permier temps, generation non dependante d'un niveau
	public Board() {
		
		
		
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
}
