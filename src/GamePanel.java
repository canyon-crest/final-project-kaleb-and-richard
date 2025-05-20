import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private Font font;
	public GamePanel()
	{
		font = new Font("Arial", Font.PLAIN, 16);
	}
	
	public void update(Graphics g, Player player, ArrayList<Entity> entities)
	{
		repaint(g, player, entities);
	}
	public void repaint(Graphics g, Player player, ArrayList<Entity> entities)
	{
		paintComponent(g, player, entities);
	}
	public void paintComponent(Graphics g, Player player, ArrayList<Entity> entities)
	{
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(149, 0, 2, Game.HEIGHT);
		g.fillRect(299, 0, 2, Game.HEIGHT);
		
		for (Entity e : entities)
		{
			e.draw(g);
		}
		player.draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("SCORE: ", 10, 25);
	}
}
