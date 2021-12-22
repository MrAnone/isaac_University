package gameobjects;

import java.util.ArrayList;

import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;
import resources.MonstersInfos;
import resources.ImagePaths;
import gameWorld.Room;

public class Larmes {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int scope;
	
	public Larmes(Vector2 position,Vector2 direction, Vector2 size, double speed, int scope, String imagePath)
	{
		this.position = position;
		this.direction = direction;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.scope = scope;
	}
	
	public static boolean createLarmesHero(Vector2 position,Vector2 direction, boolean Shoot) {
		if (!Shoot) {
			Larmes shoot = new Larmes(position,direction, HeroInfos.LARMES_SIZE, HeroInfos.LARMES_SPEED, HeroInfos.LARMES_SCOPE,ImagePaths.TEAR);
			Room.larmes_list.add(shoot);
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean createLarmesFly(Vector2 position,Vector2 direction, ArrayList<Larmes> larmes_list, boolean Shoot) {
		if (Shoot == false) {
			Larmes shoot = new Larmes(position,direction, HeroInfos.LARMES_SIZE, MonstersInfos.LARMES_SPEED, MonstersInfos.LARMES_SCOPE,ImagePaths.TEAR);
			larmes_list.add(shoot);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void updateGameObject()
	{
		move();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(speed);
		return normalizedVector;
	}
	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public Vector2 getSize()
	{
		return size;
	}

	public void setSize(Vector2 size)
	{
		this.size = size;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public Vector2 getDirection()
	{
		return direction;
	}

	public void setDirection(Vector2 direction)
	{
		this.direction = direction;
	}
	
	public int getScope()
	{
		return scope;
	}

	public void setScope(int scope)
	{
		this.scope = scope;
	}
}
