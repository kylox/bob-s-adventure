package map;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import entity.Player;

public interface IMap {
		
		public int getTileWidth();
		public int getTileHeight();
		public int getLayerIndex(String name);
		public void render(GameContainer container, Graphics g, Player player);
		public Image getTileImage(int x, int y, int layerIndex);

}
