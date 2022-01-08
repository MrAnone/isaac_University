package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Door {
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private boolean status;
	private double degres;
	private String ID;
	
	public Door(Vector2 position, Vector2 size, boolean status, String imagePath, double degres, String ID)
	{
		this.position = position;
		this.size = size;
		this.imagePath = imagePath;
		this.status = status;
		this.degres = degres;
		this.ID=ID;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				getDegres());
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

	public boolean getStatus()
	{
		return status;
	}

	public void setStatus(boolean status)
	{
		if (status) {
			imagePath = ImagePaths.OPENED_DOOR;
		}
		else {
			imagePath = ImagePaths.CLOSED_DOOR;
		}
		this.status = status;
	}
	
	public double getDegres()
	{
		return degres;
	}

	public void setDegres(double degres)
	{
		this.degres = degres;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
