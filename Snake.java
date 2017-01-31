package snake;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
	
	
	public JFrame jframe;
	public RenderPanel renderPanel;
	public Timer timer = new Timer(20, this);
	public static Snake snake;
	
	public ArrayList<Point> snakeParts = new ArrayList<Point>();
	public int ticks = 0, direction = DOWN, tailLength;
	public Point head, cherry;
	public static final int UP=0, DOWN=1, LEFT=2, RIGHT=3, SCALE=10;
	public Random random;
	public Dimension dim;
	public int score;
	public boolean over = false, pause = false;
	
	public Snake(){
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake");
		jframe.setVisible(true);
		jframe.setSize(600, 600);
		jframe.setLocation(dim.width/2-jframe.getWidth()/2, dim.height/2-jframe.getHeight()/2);
		jframe.add(renderPanel = new RenderPanel());
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startGame();
	}//constructor for object snake
	
	
	public void startGame()
	{
		over = false;
		score = 0;
		tailLength = 5;
		direction = DOWN;
		head = new Point(0,0);
		random = new Random();
		snakeParts.clear();
		cherry = new Point(random.nextInt(57), random.nextInt(59));
		timer.start();
	}
	
	
	public boolean noTailAt(int x, int y)
	{
		for(Point point : snakeParts)
		{
			if(point.equals(new Point(x,y)))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		renderPanel.repaint();
		ticks++;
		
		if(ticks%3 == 0 && head != null && over != true && pause != true){
			snakeParts.add(new Point(head.x,head.y));
			if(direction == UP)
				if(head.y-1 >= 0 && noTailAt(head.x,head.y-1))
					head = new Point(head.x,head.y-1);
				else
					over = true;
			if(direction == DOWN)
				if(head.y+1 < 58 && noTailAt(head.x,head.y+1))
					head = new Point(head.x,head.y+1);
				else
					over = true;
			if(direction == LEFT)
				if(head.x-1 >= 0 && noTailAt(head.x-1,head.y))
					head = new Point(head.x-1,head.y);
				else
					over = true;
			if(direction == RIGHT)
				if(head.x+1 < 60 && noTailAt(head.x+1,head.y))
					head = new Point(head.x+1,head.y);
				else
					over = true;
			if(snakeParts.size() > tailLength)
			{
				snakeParts.remove(0);
			}
			if(cherry != null){
				if(head.equals(cherry))
				{
					score += 100;
					tailLength++;
					cherry.setLocation(new Point(random.nextInt(57), random.nextInt(59)));
				} 
				
			}
			
			
			
		}
	
	}
	

	public static void main(String[] args) {
		
		snake = new Snake();
		
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i == KeyEvent.VK_A && direction != RIGHT)
			direction = LEFT;
		if(i == KeyEvent.VK_D && direction != LEFT)
			direction = RIGHT;
		if(i == KeyEvent.VK_W && direction != DOWN)
			direction = UP;
		if(i == KeyEvent.VK_S && direction != UP)
			direction = DOWN;
		if(i == KeyEvent.VK_SPACE && over)
		{
			startGame();
		}
		else if(i== KeyEvent.VK_SPACE)
		{
			pause = !pause;
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
