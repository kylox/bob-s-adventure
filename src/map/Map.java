package map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import entity.Player;

public class Map extends TiledMap implements IMap {

	public Map(String source) throws SlickException {
		super(source);
	}

	public void init(GameContainer container) {

	}

	public void keyPressed(int key, char c) {
	}

	public void keyReleased(int key, char c) {

	}

	public void render(GameContainer container, Graphics g, Player player) {

		this.render(0, 0, 0);
		this.render(0, 0, 1);
		this.render(0, 0, 2);
		this.render(0, 0, 3);
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(player.getX() - 16, player.getY() - 8, 32, 16);
		this.render(0, 0, 6);
		player.render(container, g);
		this.render(0, 0, 5);
		this.render(0, 0, 7);
	}

	public void update(GameContainer container, int delta) {

	}

	@Override
	public int getLayerIndex(String name) {
		return super.getLayerIndex(name);
	}
	
	public Image getTileImage(int x, int y, int layerIndex) {
		return super.getTileImage(x, y, layerIndex);
	}

}
