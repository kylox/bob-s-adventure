package entity.test;

import map.IMap;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import entity.Player;



public class PlayerTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
			setThreadingPolicy(new Synchroniser());
		}
	};;
	private Player player = new Player();
	private IMap map = context.mock(IMap.class);
	private Animation[] animations;

	@BeforeClass
	public static void setUp() {

	}

	@Test
	public void testKeyUp() {
		//final Image tile = context.mock(Image.class);
		
		//testMoveMovingPlayerWithCollisionNoTile(Input.ANY_CONTROLLER);
//		testMoveMovingPlayerWithCollisionTile(Input.ANY_CONTROLLER, tile);
//		testMoveMovingPlayerWithCollisionTileAlpha(Input.ANY_CONTROLLER,tile);
//		testMoveMovingPlayerWithoutCollision(Input.ANY_CONTROLLER);
	}
	

	public void testMoveMovingPlayerWithoutCollision(int key) {
		player.keyPressed(key, 'a');
		System.out.println("performing test move without collision");
		context.checking(new Expectations() {
			{
				atLeast(1).of(map).getTileWidth();
				will(returnValue(32));
				atLeast(1).of(map).getTileHeight();
				will(returnValue(32));
				atLeast(1).of(map).getLayerIndex("collision");
				will(returnValue(5));
				atLeast(1).of(map).getTileImage(15, 12, 5);
			}
		});

		player.move(1, map);

	}

	@Test
	public void testMoveMovingPlayerWithCollisionNoTile() {
		player.keyPressed(Input.KEY_UP, 'a');
		System.out.println("performing test move with collision No Tile");
		context.checking(new Expectations() {
			{
				atLeast(1).of(map).getTileWidth();
				will(returnValue(32));
				atLeast(1).of(map).getTileHeight();
				will(returnValue(32));
				atLeast(1).of(map).getLayerIndex("collision");
				will(returnValue(5));
				atLeast(1).of(map).getTileImage(15, 12, 5);
				will(returnValue(null));
			}
		});

		player.move(1, map);

	}

	public void testMoveMovingPlayerWithCollisionTile(int key,final Image tile) {
		player.keyPressed(key, 'a');

		final Color color = new Color(255, 255, 255, -1);
		System.out.println("performing test move with collision Tile");
		context.checking(new Expectations() {
			{
				atLeast(1).of(map).getTileWidth();
				will(returnValue(32));
				atLeast(1).of(map).getTileHeight();
				will(returnValue(32));
				atLeast(1).of(map).getLayerIndex("collision");
				will(returnValue(5));
				atLeast(1).of(map).getTileImage(15, 12, 5);
				will(returnValue(tile));
				atLeast(1).of(tile).getColor(20, 15);
				will(returnValue(color));

			}
		});
		player.move(1, map);
	}

	public void testMoveMovingPlayerWithCollisionTileAlpha(int key, final Image tile) {
		player.keyPressed(key, 'a');
		final Color color = new Color(255, 255, 255, 1);
		System.out.println("performing test move with collision Tile");
		context.checking(new Expectations() {
			{
				atLeast(1).of(map).getTileWidth();
				will(returnValue(32));
				atLeast(1).of(map).getTileHeight();
				will(returnValue(32));
				atLeast(1).of(map).getLayerIndex("collision");
				will(returnValue(5));
				atLeast(1).of(map).getTileImage(15, 12, 5);
				will(returnValue(tile));
				atLeast(1).of(tile).getColor(20, 15);
				will(returnValue(color));

			}
		});
		player.move(1, map);
	}

	@Test
	public void testMoveStaticPlayer() {
		System.out.println("performing test static player");
		player.keyReleased(Input.KEY_UP, 'a');
		player.move(1, map);
	}

	@Test
	public void testKeyPressed() {
		player.keyPressed(Input.KEY_UP, 'a');
		player.keyPressed(Input.KEY_LEFT, 'a');
		player.keyPressed(Input.KEY_RIGHT, 'a');
		player.keyPressed(Input.KEY_DOWN, 'a');
		player.keyPressed(Input.ANY_CONTROLLER, 'a');
	}

	@Test
	public void testRenderNotMoving() throws SlickException {
		final GameContainer container = context.mock(GameContainer.class);
		final Graphics g = context.mock(Graphics.class);
		animations = new Animation[4];
		context.checking(new Expectations() {
			{
				atLeast(1).of(g).drawAnimation(animations[0],
						player.getX() - 32, player.getY() - 60);
			}
		});
		player.render(container, g);

	}

	@Test
	public void testRenderMoving() throws SlickException {
		final GameContainer container = context.mock(GameContainer.class);
		final Graphics g = context.mock(Graphics.class);
		animations = new Animation[4];
		player.keyPressed(Input.KEY_UP, 'a');
		context.checking(new Expectations() {
			{
				atLeast(1).of(g).drawAnimation(animations[3],
						player.getX() - 32, player.getY() - 60);
			}
		});
		player.render(container, g);
	}

}
