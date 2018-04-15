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
		 System.out.println(lab.toString());
		 
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
		 	Case[] lig =  new Case[n];
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
        for (int i=0;i<this.n;i++){
       	for (int j=0;j<this.m;j++){        		int p = r.nextInt(100);
    			if (p<=20) {
   				this.lab[i][j]=new FreeCase(i,j,laVariableQuiFaitPlaisirAAmos);	
	        	}
	        }
		}
	}
}
		