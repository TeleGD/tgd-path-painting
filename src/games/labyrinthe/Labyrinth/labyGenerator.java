package games.labyrinthe.Labyrinth;

import java.util.ArrayList;
import java.util.Random;


import org.newdawn.slick.SlickException;

import games.labyrinthe.Labyrinth.Case;

public class labyGenerator {
	 private ArrayList<ArrayList<Case>> lab; 
	 private int n;
	 private int m;

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
		 this.lab = new ArrayList<ArrayList<Case>>();
		 
	 }
	 
	 public int getNbCol() {
		 return this.m;
	 }
	 public int getNbLig() {
		 return this.n;
	 }
	 
	 
	 public void creerLabMur() throws SlickException{
		 for (int i=0;i<this.n;i++){
		 	ArrayList<Case> lig =  new ArrayList<Case>();
		 	for (int j=0;i<this.m;j++){
		 		WallCase c = new WallCase(i,j,50,50,false);
		 		lig.add(c); 
		 	}
		 	this.lab.add(lig);
		 }
	 }
	 
		 
	public void genLab() throws SlickException{
		ArrayList<Case> liste = new ArrayList<Case>();
		Case d = new FreeCase(r.nextInt(this.n/2),r.nextInt(this.m/2),50,50);
		liste.add(d);
		int choix ;
		int l = liste.size();
		Case pos;
		while (!liste.isEmpty()){
			choix = r.nextInt(l);
			pos=liste.get(choix);
	        if (pos.getI()!=0 && this.lab.get(pos.getI()-2).get(pos.getJ()) instanceof WallCase){
	        	this.lab.get(pos.getI()-2).add(pos.getJ(),new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()-2).remove(pos.getJ());
	        	this.lab.get(pos.getI()-1).add(pos.getJ(),new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()-1).remove(pos.getJ());
	        	pos = this.lab.get(pos.getI()-2).get(pos.getJ());
	        	liste.add(pos);
	        }
	        if (pos.getI()!=(n-1) && this.lab.get(pos.getI()+2).get(pos.getJ()) instanceof WallCase){
	        	this.lab.get(pos.getI()+2).add(pos.getJ(),new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()+2).remove(pos.getJ());
	        	this.lab.get(pos.getI()+1).add(pos.getJ(),new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()+1).remove(pos.getJ());
	        	pos = this.lab.get(pos.getI()+2).get(pos.getJ());
	        	liste.add(pos);
	        }
	        if (pos.getJ()!=0 && this.lab.get(pos.getI()).get(pos.getJ()-2) instanceof WallCase){
	        	this.lab.get(pos.getI()).add(pos.getJ()-2,new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()).remove(pos.getJ()-2);
	        	this.lab.get(pos.getI()).add(pos.getJ()-1,new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()).remove(pos.getJ()-1);
	        	pos = this.lab.get(pos.getI()).get(pos.getJ()-2);
	        	liste.add(pos);
	        }
	        if (pos.getJ()!=(m-1) && this.lab.get(pos.getI()).get(pos.getJ()+2) instanceof WallCase){
	        	this.lab.get(pos.getI()).add(pos.getJ()+2,new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()).remove(pos.getJ()+2);
	        	this.lab.get(pos.getI()).add(pos.getJ()+1,new FreeCase(pos.getI(),pos.getJ(),50,50));
	        	this.lab.get(pos.getI()).remove(pos.getJ()+1);
	        	pos = this.lab.get(pos.getI()).get(pos.getJ()+2);
	        	liste.add(pos);
	        }
		}
	        for (int i=0;i<this.n;i++){
	        	for (int j=0;i<this.m;j++){
	        		int p = r.nextInt(100);
	        			if (p<=7) {
	        				this.lab.get(i).add(j,new FreeCase(i,j,50,50));
	        				this.lab.get(i).remove(j+1);
	        	}
	        }
		}
	}
}
		