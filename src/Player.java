public class ExtraLife extends Powerup {
    private int health;
    private int speed;
    private String name;
    private ArrayList<Powerup> powerUps;
    
    public Player(int lane, int ypos, int hp, int s, String str) {
    	super(lane, ypos);
    	health = hp;
    	speed = s;
    	name = str;
    	powerUps = new ArrayList<Powerup>();
    }
    
    public void addPowerUp(Powerup p) {
    	powerUps.add(p);
    }
    
    public void removePowerUp(Powerup p) {
    	for (int i = 0; i < powerUps.size(); i++) {
    		if (powerUps.get(i).equals(p)) {
    			powerUps.remove(i);
    			break;
    		}
    	}
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
    
    public int getSpeed() {
    	return speed;
    }
    
    public void setSpeed(int hp) {
    	speed = hp;
    }
    
    
}
