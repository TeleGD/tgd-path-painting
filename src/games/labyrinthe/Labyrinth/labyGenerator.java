package games.labyrinthe.Labyrinth;

import java.util.ArrayList;
import java.util.Random;

import games.labyrinthe.Labyrinth.Case;

public class labyGenerator {
	 private Case[][] lab; 
	 private int n;
	 private int m;
//MAIS CEST QUOI CE BOULOT
	 //CEST DE LA MERDE PUTAIN!!!!
	 Random r = new Random();
	 
	 public labyGenerator (int n,int m){
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
		 	Case[] lig =  new Case[n];
		 	for (int j=0;i<this.m;j++){
		 		WallCase c = new WallCase(i*50,j*50,50,false);
		 		lig[j]=c; 
		 	}
		 	this.lab[i]=lig;
		 }
	 }
	 
		 
	public void genLab(){
		ArrayList<Case> liste = new ArrayList<Case>();
		Case d = new FreeCase(r.nextInt(this.n/2),r.nextInt(this.m/2),50);
		liste.add(d);
		int choix ;
		int l = liste.size();
		Case pos;
		while (!liste.isEmpty()){
			choix = r.nextInt(l);
			pos=liste.get(choix);
	        if (pos.getI()!=0 && this.lab[pos.getI()-2][pos.getJ()] instanceof WallCase){
	        	this.lab[pos.getI()-2][pos.getJ()]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	this.lab[pos.getI()-1][pos.getJ()]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()-2][pos.getJ()];
	        	liste.add(pos);
	        }
	        if (pos.getI()!=(n-1) && this.lab[pos.getI()+2][pos.getJ()] instanceof WallCase){
	        	this.lab[pos. getI()+2][pos.getJ()]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	this.lab[pos.getI()+1][pos.getJ()]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()+2][pos.getJ()];
	        	liste.add(pos);
	        }
	        if (pos.getJ()!=0 && this.lab[pos.getI()][pos.getJ()-2] instanceof WallCase){
	        	this.lab[pos.getI()][(pos.getJ()-2)]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	this.lab[pos.getI()][(pos.getJ()-1)]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);

	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()][(pos.getJ()-2)];
	        	liste.add(pos);
	        }
	        if (pos.getJ()!=(m-1) && this.lab[pos.getI()][(pos.getJ()+2)] instanceof WallCase){
	        	this.lab[pos.getI()][(pos.getJ()+2)]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	this.lab[pos.getI()][(pos.getJ()+1)]=new FreeCase(pos.getI()*50,pos.getJ()*50,50);
	        	liste.remove(pos);
	        	pos = this.lab[pos.getI()][(pos.getJ()+2)];
	        	liste.add(pos);
	        }
		}
	        for (int i=0;i<this.n;i++){
	        	for (int j=0;i<this.m;j++){
	        		int p = r.nextInt(100);
	        			if (p<=7) {
	        				this.lab[i][j]=new FreeCase(i*50,j*50,50);
	        				
	        	}
	        }
		}
	}
}
		