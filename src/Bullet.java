import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Entity {
	private BufferedImage image;
	public Bullet(Game game, int speed) {
		super(game, game.getPlayer().getLane(), game.getPlayer().getY(), game.getPlayer().getSpeed() + speed); // initiates the bullet where the player is
    	try {
			image = ImageIO.read(new File("images/bullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() // calls update function while also checking if bullet has hit an enemy car
	{
		super.update();
		for (Entity i : getGame().getEntities()) {
			if (isTouching(i) && i instanceof Enemy) {
				((Enemy)i).die();
			}
		}
	}
	
	public void draw(Graphics g)
	{
    	g.drawImage(image, 150 * getLane() + 75 - 45, getY() - 45, 100, 100, null);
	}
}