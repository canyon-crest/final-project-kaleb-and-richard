import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(149, 0, 2, Game.HEIGHT);
		g.fillRect(299, 0, 2, Game.HEIGHT);
		g.fillRect(449, 0, 2, Game.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString("SCORE: ", 10, 25);
	}
}
