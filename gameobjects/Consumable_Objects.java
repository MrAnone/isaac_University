package gameobjects;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Consumable_Objects {
	private Vector2 position;
	private Vector2 size;
	private double value;
	private String imagePath;

	public Consumable_Objects( double value, String imagePath) {
		this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
		this.value = value;
		this.imagePath = imagePath;
		this.position = randomPositionObjects();

	}
	// A second constructor for the shopRoom where position will be fixed by the room.
	public Consumable_Objects( double value, String imagePath,Vector2 position) {
		this.size = RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
		this.value = value;
		this.imagePath = imagePath;
		this.position = position;

	}

	public static Vector2 randomPositionObjects() {
		Random r = new Random();
		double randomValue = 1 * r.nextDouble();
		Vector2 res = new Vector2(randomValue, randomValue);
		return res;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}

	/*
	 * Getters and Setters
	 */
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
