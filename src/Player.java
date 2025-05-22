import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends Entity {
    private int health;
    private String name;
    private static ArrayList<Powerup> powerUps;
    
    public Player(Game game, int lane, int ypos, int hp, int s, String str) {
    	super(game, lane, ypos, s, 1, 10000);
    	health = hp;
    	name = str;
    	powerUps = new ArrayList<Powerup>();
    }
    
    public void addPowerUp(Powerup p) {
    	if (!(p.getName().equals("Gun"))) {
    		powerUps.add(p);
    	}
    }
    
    public void removePowerUp(Powerup p) {
    	for (int i = 0; i < powerUps.size(); i++) {
    		if (powerUps.get(i).equals(p)) {
    			powerUps.remove(i);
    			break;
    		}
    	}
    }
    
    public static ArrayList<Powerup> getPowerups()
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
    
    public void applyPowerUps() { // TODO: call this function every game update
    	for (Powerup i : powerUps) {
    		i.apply(this);
    		if (i.isPickedUp()) {
    			this.removePowerUp(i);
    		}
    	}
    }
    
    
    
    public void update()
    {
    	accelerate();
    }
    public void draw(Graphics g)
    {
    	g.setColor(Color.BLUE);
    	g.fillRect(150 * getLane() + 50, getY() - 50, 50, 100);
    }
}
