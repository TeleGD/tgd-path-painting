package games.labyrinthe;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppGame;
import app.AppWorld;
import games.labyrinthe.Labyrinth.*;
import games.labyrinthe.Players.*;

public class World extends AppWorld {
	
	private final int id;
	
	public final static String GAME_FOLDER_NAME="labyrinthe";
	public final static String DIRECTORY_SOUNDS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_MUSICS="musics"+File.separator+GAME_FOLDER_NAME+File.separator;
	public final static String DIRECTORY_IMAGES="images"+File.separator+GAME_FOLDER_NAME+File.separator;
	
	public Board board;
	public ArrayList<Player> players;

	private int height;
	private int width;
	
	public World(int id) {
		this.id=id;
	}
	
	public void play(GameContainer container, StateBasedGame game) {
		AppGame appGame = (AppGame) game;
		int n = appGame.appPlayers.size ();
		height = container.getHeight();
		width = container.getWidth();
		board = new Board(this,100,100);
		players = new ArrayList<Player>();
		players.add(new VictimPlayer(this, appGame.appPlayers.get(0)));
		for (int i = 1; i < n; i++) {
			players.add(new HunterPlayer(this, appGame.appPlayers.get(i)));
		}
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) {
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		board.render(container, game, context);
		for (Player p : players) {
			p.render(container, game, context);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		board.update(container, game, delta);
		for (Player p : players) {
			p.update(container, game, delta);
		}
	}

	@Override
	public int getID() {
		return this.id;
	}

	public void endGame() {
		// Met fin au jeu
		
	}

	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}

}
