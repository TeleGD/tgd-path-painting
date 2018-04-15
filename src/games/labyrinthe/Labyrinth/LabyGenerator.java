package games.labyrinthe.Labyrinth;

import java.util.ArrayList;
import java.util.Random;

import games.labyrinthe.Labyrinth.Case;

public class LabyGenerator {
	 private Case[][] lab; 
	 private int n;
	 private int m;
	 private Board board;
	 
	 Random r = new Random();
	 
	 public LabyGenerator (Board board,int n,int m){
		 this.board =board;
		 
		 if (n%2==0){
			 this.n = n;
		 }else{
			 this.n = n+1;
		 }
		 if (m%2==0){
			 this.m = m;
		 }else{
			 this.m = m+1;
		 }
		 this.lab = new Case[n][m];
		 creerLabMur();
		 genLab();
		 
	 }
	 
	 public int getNbCol() {
		 return this.m;
	 }
	 public int getNbLig() {
		 return this.n;
	 }
	 public Case[][] getLab() {
		 return this.lab;
	 }
	 
	 public void creerLabMur() {
		 for (int i=0;i<this.n;i++){
		 	Case[] lig =  new Case[m];
		 	for (int j=0;j<this.m;j++){
		 		WallCase c = new WallCase(i,j,board.getSize(),false);
		 		lig[j]=c; 
		 	}
		 	this.lab[i]=lig;
		 }
	 }
	 
		 
	public void genLab(){
		ArrayList<Case> liste = new ArrayList<Case>();
		int laVariableQuiFaitPlaisirAAmos = board.getSize();
		Case d = new FreeCase(r.nextInt(this.n/2)*2,r.nextInt(this.m/2)*2,board.getSize());
		liste.add(d);
		int choix ;
		int l = liste.size();
		Case pos;
		Case variableAAjouterPASPartout;
		while (!liste.isEmpty()){
			choix = r.nextInt(l);
			pos=liste.get(choix);
	        
			if ( pos.getI()!=0 && this.lab[pos.getI()-2][pos.getJ()] instanceof WallCase){	  
	        	variableAAjouterPASPartout = this.lab[pos.getI()-2][pos.getJ()];
	        	liste.add(variableAAjouterPASPartout);
	        } if (pos.getI()!=(n-2) && this.lab[pos.getI()+2][pos.getJ()] instanceof WallCase){
	        	variableAAjouterPASPartout = this.lab[pos.getI()+2][pos.getJ()];
	        	liste.add(variableAAjouterPASPartout);
	        } if (pos.getJ()!=0 && this.lab[pos.getI()][pos.getJ()-2] instanceof WallCase){
	        	variableAAjouterPASPartout = this.lab[pos.getI()][(pos.getJ()-2)];
	        	liste.add(variableAAjouterPASPartout);
	        } if (pos.getJ()!=(m-2) && this.lab[pos.getI()][(pos.getJ()+2)] instanceof WallCase){
	        	variableAAjouterPASPartout = this.lab[pos.getI()][(pos.getJ()+2)];
	        	liste.add(variableAAjouterPASPartout);
	        }
			
			if ( pos.getI()!=0 && this.lab[pos.getI()-2][pos.getJ()] instanceof WallCase){
	        	this.lab[pos.getI()-2][pos.getJ()]=new FreeCase(pos.getI()-2,pos.getJ(),laVariableQuiFaitPlaisirAAmos);
	        	this.lab[pos.getI()-1][pos.getJ()]=new FreeCase(pos.getI()-1,pos.getJ(),laVariableQuiFaitPlaisirAAmos);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()-2][pos.getJ()];
	        	liste.add(pos);
	        } else if (pos.getI()!=(n-2) && this.lab[pos.getI()+2][pos.getJ()] instanceof WallCase){
	        	this.lab[pos.getI()+2][pos.getJ()]=new FreeCase(pos.getI()+2,pos.getJ(),laVariableQuiFaitPlaisirAAmos);
	        	this.lab[pos.getI()+1][pos.getJ()]=new FreeCase(pos.getI()+1,pos.getJ(),laVariableQuiFaitPlaisirAAmos);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()+2][pos.getJ()];
	        	liste.add(pos);
	        } else if (pos.getJ()!=0 && this.lab[pos.getI()][pos.getJ()-2] instanceof WallCase){
	        	this.lab[pos.getI()][(pos.getJ()-2)]=new FreeCase(pos.getI(),pos.getJ()-2,laVariableQuiFaitPlaisirAAmos);
	        	this.lab[pos.getI()][(pos.getJ()-1)]=new FreeCase(pos.getI(),pos.getJ()-1,laVariableQuiFaitPlaisirAAmos);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()][(pos.getJ()-2)];
	        	liste.add(pos);
	        } else if (pos.getJ()!=(m-2) && this.lab[pos.getI()][(pos.getJ()+2)] instanceof WallCase){
	        	this.lab[pos.getI()][(pos.getJ()+2)]=new FreeCase(pos.getI(),pos.getJ()+2,laVariableQuiFaitPlaisirAAmos);
	        	this.lab[pos.getI()][(pos.getJ()+1)]=new FreeCase(pos.getI(),pos.getJ()+1,laVariableQuiFaitPlaisirAAmos);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()][(pos.getJ()+2)];
	        	liste.add(pos);
	        } else {
	        	liste.remove(pos);
	        }
		}
		for (int i=n/2;i<=n/2+1;i++){
			for (int j=m/2;j<=m/2+1;j++){
				this.lab[i][j]=new FreeCase(i,j,laVariableQuiFaitPlaisirAAmos);
			}
		}
		
        for (int i=0;i<this.n;i++){
        	for (int j=0;j<this.m;j++){        		
        		int p = r.nextInt(100);
    				if (p<=20) {
    					this.lab[i][j]=new FreeCase(i,j,laVariableQuiFaitPlaisirAAmos);	
	        	}
	        }
		}
        
        //on entoure la map de murs parce que c'est plus simple pour les collisions !!
        for (int i=0;i<this.n;i++) {
        	this.lab[i][0] = new WallCase(i,0,board.getSize(),false);
        	if(i!=0 && i!=this.n-1) {
        		this.lab[i][1] = new FreeCase(i,1,laVariableQuiFaitPlaisirAAmos);
        		this.lab[i][this.m-2] = new FreeCase(i,this.m-2,laVariableQuiFaitPlaisirAAmos);
        	}
        	this.lab[i][this.m-1] = new WallCase(i,this.m-1,board.getSize(),false);
        }
        for (int j=0;j<this.m;j++) {
        	this.lab[0][j] = new WallCase(0,j,board.getSize(),false);
        	if(j!=0 && j!=this.m-1) {
        		this.lab[1][j] = new FreeCase(1,j,laVariableQuiFaitPlaisirAAmos);
        		this.lab[this.n-2][j] = new FreeCase(this.m-2,j,laVariableQuiFaitPlaisirAAmos);
        	}
        	this.lab[this.n-1][j] = new WallCase(this.n-1,j,board.getSize(),false);
        }
        
	}
}
		