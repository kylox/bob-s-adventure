package entity;

import map.IMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface IEntity {
	public float getX();
	public float getY();
	public void render(GameContainer container, Graphics g) throws SlickException;
	void update(GameContainer container, int delta, IMap map);
	
}
