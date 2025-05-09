package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake snake;
	private Food food;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		
		//Construct Snake and Food
		snake = new Snake();
		food = new Food();
		
	}
	
	public void play() {
		while (snake.isInbounds() == true) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			
			if (dir != -1) {
				snake.changeDirection(dir);
			}		
			snake.move();
			
			if (snake.eatFood(food)) {
				food = new Food(); //generate new food
				
			}			
			updateDrawing();
		}
		while (snake.isInbounds() == false) {
		StdDraw.clear();
		
		StdDraw.setPenColor(0, 100, 255); 
		StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.05);
		StdDraw.setPenColor(0, 0, 0);
		StdDraw.text(0.5, 0.5, "Out of Bounds: Game Over!");
		
		StdDraw.pause(100);
		StdDraw.show();
		}
	}
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
	
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		
		StdDraw.pause(100);
		StdDraw.show();	
	
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
