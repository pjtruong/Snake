package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

	public static Color blue = new Color(6724095);
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(blue);
		g.fillRect(0, 0, 600, 600);
		Snake snake = Snake.snake;
		g.setColor(Color.GREEN);
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y*Snake.SCALE, Snake.SCALE,Snake.SCALE);
		
		for(Point point:snake.snakeParts)
		{
			
			g.fillRect(point.x * Snake.SCALE, point.y*Snake.SCALE, Snake.SCALE,Snake.SCALE);
		}
		
		g.setColor(Color.RED);
		g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
		String string = "SCORE: " + snake.score + ", CHERRY: " + (snake.tailLength-5);
		g.setColor(Color.WHITE);
		g.drawString(string, 220, 20);
		if(snake.over)
		{
			g.drawString("GAME OVER", 250, 280);
			g.drawString("press space to play again.", 230, 300);
		}
		if(snake.pause)
		{
			g.drawString("PAUSED", 250, 280);
		}
	}
	
	
	
	

}
