package gameobjects;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public abstract class Objects {
	private Vector2 size;
	private Vector2 position;
	private String imagePath;
	private int id;
	
	
	public Objects(int id, Vector2 position,String imagePath) {
		this.size=RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
		this.position=position;
		this.imagePath=imagePath;
		this.id=id;
	}
	
	public Objects(int id, String imagePath) {
		this.size=RoomInfos.TILE_SIZE.scalarMultiplication(0.3);
		this.position=randomPositionObjects();
		this.imagePath=imagePath;
		this.id=id;
	}
	
	
	/**
	 * generate a random value used for the object's position
	 * @return a vector used as the object's position
	 */
	public static Vector2 randomPositionObjects() {
		Random r = new Random();
		double randomValue1 =  r.nextDouble();
		double randomValue2 =  r.nextDouble();
		Vector2 res = new Vector2(randomValue1, randomValue2);
		return res;
	}
	
	public  void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	


	public Vector2 getSize() {
		return size;
	}


	public void setSize(Vector2 size) {
		this.size = size;
	}


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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
