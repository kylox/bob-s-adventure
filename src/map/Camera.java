package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import entity.Player;


public class Camera {
	private Player player;
	private float x;
	private float y;

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public Camera(Player player) {
		this.player = player;
		this.x = player.getX();
		this.y = player.getY();
	}

	public void init(GameContainer container) {

	}

	public void keyPressed(int key, char c) {
	}

	public void keyReleased(int key, char c) {

	}

	public void render(GameContainer container, Graphics g) {
		g.translate(container.getWidth() / 2 - (int) this.x,
				container.getHeight() / 2 - (int) this.y);

	}

	public void update(GameContainer container, int delta) {

		int w = container.getWidth() / 4;
		if (this.x >= 400) {
			if (player.getX() > this.x + w) {
				this.x = player.getX() - w;
			} else if (player.getX() < this.x - w) {
				this.x = player.getX() + w;
			}
		}
		if (this.y >= 300) {
			int h = container.getHeight() / 4;
			if (player.getY() > this.y + h) {
				this.y = player.getY() - h;
			} else if (player.getY() < this.y - h) {
				this.y = player.getY() + h;
			}
		}
		//MAP BOUND
		if (this.y < 300)
			this.y = 300;
		if (this.x < 400)
			this.x = 400;
	}
}
