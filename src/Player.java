import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Entity {
	private BufferedImage image;
    private int health;
    private String name;
    private ArrayList<Powerup> powerUps;
    
    /*
     * @param lane: lane where player starts
     * @param ypos: position where the player plays from
     * @param hp: the amount of hp the player starts with
     * @param s: the speed the player starts at
     * @param str: player name
     */
    public Player(Game game, int lane, int ypos, int hp, int s, String str) {
    	super(game, lane, ypos, s, 1, 10000);
    	try {
			image = ImageIO.read(new File("images/player_car.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	health = hp;
    	name = str;
    	powerUps = new ArrayList<Powerup>();
    }
    
    public void addPowerUp(Powerup p) { // this method is called to add a powerup to the player every time player picks up a powerup
    	if (!(p.getName().equals("Gun"))) {
    		powerUps.add(p);
    	}
    }
    
    public void removePowerUp(Powerup p) { // once powerup effect has been applied the powerup is removed from the player
    	for (int i = 0; i < powerUps.size(); i++) {
    		if (powerUps.get(i).equals(p)) {
    			powerUps.remove(i);
    			break;
    		}
    	}
    }
    
    //@return returns all powerups currently in affect in an arraylist
    public ArrayList<Powerup> getPowerups()
    {
    	return powerUps;
    }
    
    public void addHealth(int hp) {
    	health += hp;
    }
    
    public void removeHealth(int hp) {
    	health -= hp;
    }
    
    public int getHealth() {
    	return health;
    }
    
    public void setHealth(int hp) {
    	health = hp;
    }
    
    public void applyPowerUps() { // loops thru powerUps and applies the powerups that are not in effect, and then removes ones that are in effect already
    	for (int i = powerUps.size()-1; i>=0; i--) {
    		powerUps.get(i).apply(this);
    		if (powerUps.get(i).isPickedUp()) {
    			this.removePowerUp(powerUps.get(i));
    		}
    	}
    }
    
    
    @Override
    public void update()
    {
    	accelerate();
    	applyPowerUps();
    }
    public void draw(Graphics g)
    {
//    	g.setColor(Color.BLUE);
//    	g.fillRect(150 * getLane() + 50, getY() - 50, 50, 100);
    	g.drawImage(image, 150 * getLane() + 75 - 78, getY() - 75, 150, 150, null);
    }
}
