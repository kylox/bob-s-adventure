package game;

import map.Camera;
import map.IMap;
import map.Map;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entity.Player;

public class WindowGame extends BasicGame {

	GameContainer container;
	IMap map;
	Player player;
	Camera camera;
	public WindowGame() {
		super("Lesson 1 :: WindowGame");
	}

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new WindowGame(), 800, 600, false).start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		this.map = new Map("ressources/map/map02.tmx");
		this.player = new Player();
		this.player.init(container);
		this.camera = new Camera(player);
		//Music background = new Music("ressources/musics/MainTheme.ogg");
	    //background.loop();
	}

	@Override
	public void keyPressed(int key, char c) {
		this.player.keyPressed(key, c);
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
		this.player.keyReleased(key, c);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		this.camera.render(container, g);
		this.map.render(container, g, player);
		g.drawOval(camera.getX(), camera.getY(), 10, 10);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		player.update(container, delta,map);
		camera.update(container, delta);
	}
}