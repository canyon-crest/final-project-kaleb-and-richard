public class Powerup extends Entity {
	private String name;
	private String description;
	
	public Powerup(String name, String description) {
		super((int)(Math.random()*3),0);
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
