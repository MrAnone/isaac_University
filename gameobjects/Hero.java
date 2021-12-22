package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;
import resources.HeroInfos;
import resources.RoomInfos;
import libraries.Physics;
import gameobjects.Larmes;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int life;
	private int TimeLarmes ;
	private boolean Shoot ;


	public Hero(Vector2 position, Vector2 size, double speed, int life, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.life = life;
		this.TimeLarmes = HeroInfos.LARMES_FRAME;
		this.Shoot = false;
	}

	public void updateGameObject()
	{
		move();
	}

	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		if (libraries.Physics.ZonedeJeu(positionAfterMoving,getSize())){
			setPosition(positionAfterMoving);
			direction = new Vector2();
		}
		else {
			direction = new Vector2();
		}
	}

	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	public void shootUp()
	{
		Vector2 direction = new Vector2();
		direction.addY(1);
		boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		if (shoot) {
			setShoot(true);
		}
		
	}

	public void shootDown()
	{
		Vector2 direction = new Vector2();
		direction.addY(-1);
		boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		if (shoot) {
			setShoot(true);
		}
		
	}

	public void shootLeft()
	{
		Vector2 direction = new Vector2();
		direction.addX(-1);
		boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		if (shoot) {
			setShoot(true);
		}
	}

	public void shootRight()
	{
		Vector2 direction = new Vector2();
		direction.addX(1);
		boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		if (shoot) {
			setShoot(true);
		}
	}

	/*
	 * Moving from key inputs. Direction vector is later normalised.
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	public void goRightNext()
	{
		getDirection().addX(1);
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
	
	public int getLife()
	{
		return life;
	}

	public void setLife(int life)
	{
		this.life = life;
	}
	
	public int getTimeLarmes()
	{
		return TimeLarmes;
	}

	public void setTimeLarmes(int TimeLarmes)
	{
		this.TimeLarmes = TimeLarmes;
	}
	
	public boolean getShoot()
	{
		return Shoot;
	}

	public void setShoot(boolean Shoot)
	{
		this.Shoot = Shoot;
	}
}
