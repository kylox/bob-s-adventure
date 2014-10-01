package entity;

import map.IMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends Personnage implements IEntityFight {

	private float x = 500, y = 400;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public boolean getMoving() {
		return this.moving;
	}

	public Player() {

	}

	public void init(GameContainer container) throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet(
				("ressources/player/hero.png"), 64, 64);

		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX,
			int endX, int y) {
		Animation animation = new Animation();

		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}

		return animation;
	}

	public void render(GameContainer container, Graphics g) {
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32,
				y - 60);
	}

	public void keyPressed(int key, char c) {
		switch (key) {

		case Input.KEY_UP:
			this.direction = 0;
			this.moving = true;
			break;

		case Input.KEY_LEFT:
			this.direction = 1;
			this.moving = true;
			break;

		case Input.KEY_DOWN:
			this.direction = 2;
			this.moving = true;
			break;

		case Input.KEY_RIGHT:
			this.direction = 3;
			this.moving = true;
			break;
		}
	}

	public void keyReleased(int key, char c) {
		this.moving = false;
	}

	public void move(int delta, IMap map) {
		if (this.moving) {

			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);

			boolean collision = isCollision(futurX, futurY, map);

			if (collision) {
				this.moving = false;
			} else {
				this.x = futurX;
				this.y = futurY;
			}
		}
	}

	public void update(GameContainer container, int delta, IMap map) {
		this.move(delta, map);

	}

	private boolean isCollision(float x, float y, IMap map) {

		if (x < 32 || y < 32)
			return true;
		int tileW = map.getTileWidth();
		int tileH = map.getTileHeight();
		int logicLayer = map.getLayerIndex("collision");

		Image tile = map.getTileImage((int) x / tileW, (int) y / tileH,
				logicLayer);

		boolean collision = tile != null;
		if (collision) {
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			collision = color.getAlpha() > 0;
		}

		return collision;
	}

	private float getFuturX(int delta) {
		float futurX = this.x;
		switch (this.direction) {
		case 1:
			futurX = this.x - .1f * delta;
			break;
		case 3:
			futurX = this.x + .1f * delta;
			break;
		}
		return futurX;
	}

	private float getFuturY(int delta) {
		float futurY = this.y;
		switch (this.direction) {
		case 0:
			futurY = this.y - .1f * delta;
			break;
		case 2:
			futurY = this.y + .1f * delta;
			break;
		}
		return futurY;
	}

	public void attack() {
		// TODO Auto-generated method stub

	}
}
