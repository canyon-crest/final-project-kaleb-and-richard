import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gun extends Powerup {
	private BufferedImage image;
	private int ammo;
	
	public Gun(Game game, int ammoAmt, String name, String description) {
		super(game, name, description);
    	try {
			image = ImageIO.read(new File("images/gun.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ammo = ammoAmt;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void apply(Player p) {
		if (super.pickedUp)
		{
			p.addAmmo(ammo);
		}
		super.pickedUp = false;
	}
	
	public void draw(Graphics g)
	{
    	g.drawImage(image, 150 * getLane() + 75 - 50, getY() - 50, 100, 100, null);
	}
}