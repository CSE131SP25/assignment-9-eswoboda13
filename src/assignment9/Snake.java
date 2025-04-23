package assignment9;

import java.awt.Color;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private boolean shouldGrow = false;
	
	public Snake() {
		//FIXME - set up the segments instance variable
		//We are using a LinkedList<BodySegment> to represent our Snake, so this must be initialized
		//create a new BodySegment and add it to the list
		
		segments = new LinkedList<>();
		//Start at the center of the canvas
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * starting at the head from the LinkedList and update its position
	 * based on the current direction of travel
	 * updating its position by adding to the deltaX and deltaY values to its respective X and Y coordinates.
	 */
	public void move() {
		BodySegment head = segments.getFirst(); 
		double newX = head.getX() + deltaX;
		double newY = head.getY() + deltaY;	
		
		//Add new head in direction of movement
			segments.addFirst(new BodySegment (newX, newY, SEGMENT_SIZE));		
			segments.removeLast();
			}
				
	
	/**
	 * Draws the snake by drawing each segment
	 * iterate through the list and tell each BodySegmant to draw itself
	 */
	public void draw() {
		for (BodySegment segment : segments) {
			segment.draw();
		}
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double dx = head.getX() - f.getX();
		double dy = head.getY() - f.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		BodySegment tail = segments.getLast();
		
		if (distance < SEGMENT_SIZE + Food.FOOD_SIZE) {	
		segments.addFirst(new BodySegment (tail.getX(), tail.getY(), SEGMENT_SIZE));
		return true;
	}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		double x = head.getX();
		double y = head.getY();
		return x >= 0 && x <= 1 && y >= 0 && y <= 1;
	}
}
