/**
 * @author Ariana Fairbanks
 */

package model_objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import control.AsteroidsControl;
import model_abstracts.Circle;
import model_abstracts.Point;
import model_enum.StarType;

public class Star extends Circle 
{
	private double speed;

	public Star(Point center, int radius, double speed)
	{
		super(center, radius);
		this.speed = speed;
	}

	public void paint(Graphics brush) 
	{
		Graphics2D g2d = (Graphics2D) brush;
		g2d.setColor(Color.white);
		if(ThreadLocalRandom.current().nextDouble() < .15)
		{
			g2d.setColor(Color.gray);
		}
		g2d.fillOval((int) center.x, (int) center.y, radius, radius);
	}

	public void move(double rotation) 
	{
		double radians = Math.toRadians(rotation);
		double mod = -3;
		
		if(Ship.forward) 
		{
			mod = -5;
		}
		
		center.x += speed * mod * Math.cos(radians);
		center.y += speed * mod * Math.sin(radians);


		if(center.x > AsteroidsControl.screenBoundaryRight) 
		{
			center.x -= AsteroidsControl.screenBoundaryRight;
		} 
		else if(center.x < AsteroidsControl.screenBoundaryLeft)
		{
			center.x += AsteroidsControl.screenBoundaryRight;
		}
		if(center.y > AsteroidsControl.screenHeight) 
		{
			center.y -= AsteroidsControl.screenHeight;
		} 
		else if(center.y < 0) 
		{
			center.y += AsteroidsControl.screenHeight;
		}
	}
	
	public static ArrayList<Star> createStars(ArrayList<Star> stars, int numberOfStars, StarType type) 
	{
		for(int i = 0; i < numberOfStars; ++i) 
		{
			Point center = new Point((ThreadLocalRandom.current().nextDouble() * (AsteroidsControl.screenBoundaryRight - AsteroidsControl.screenBoundaryLeft)) + AsteroidsControl.screenBoundaryLeft, ThreadLocalRandom.current().nextDouble() * AsteroidsControl.screenHeight);
			int radius = ThreadLocalRandom.current().nextInt(type.minRadius, type.maxRadius + 1);
			stars.add(new Star(center, radius, type.speed));
		}
		return stars;
	 }

	public static ArrayList<Star> createDefaultStars()
	{
		ArrayList<Star> stars;
		stars = Star.createStars(new ArrayList<Star>(), (AsteroidsControl.screenHeight / 6), StarType.FAST);
		stars = Star.createStars(stars, (AsteroidsControl.screenHeight / 12), StarType.STANDARD);
		stars = Star.createStars(stars, (AsteroidsControl.screenHeight / 24), StarType.SLOW);
		return stars;
	}
}
