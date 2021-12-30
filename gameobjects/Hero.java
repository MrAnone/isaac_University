package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.HeroInfos;

import java.util.ArrayList;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int TimeLarmes ;
	private boolean Shoot ;
	private ArrayList<Tears> tears_list;
	private int  currentCoin;
	private double  currentHealth;
	private final int coin_maxValue=99;
	private double health_maxValue;
	private double dmg;


	public Hero(Vector2 position, Vector2 size, double speed, int life, String imagePath)
	{
		this.position = position;
		this.size = size;
		this.speed = speed;
		this.imagePath = imagePath;
		this.direction = new Vector2();
		this.TimeLarmes = HeroInfos.LARMES_FRAME;
		this.Shoot = false;
		tears_list = new ArrayList <Tears>();
		this.dmg=3.5;
		this.health_maxValue=6;
		this.currentHealth=5;
		this.currentCoin=0;
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
	
	private boolean createTearsHero(Vector2 direction) {
		if (!Shoot) {
			Tears shoot = new Tears(position,direction, HeroInfos.LARMES_SIZE, HeroInfos.LARMES_SPEED, HeroInfos.LARMES_SCOPE,ImagePaths.TEAR);
			tears_list.add(shoot);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void shootUp()
	{
		Vector2 direction = new Vector2();
		direction.addY(1);
		//boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		boolean shoot = createTearsHero(direction);
		if (shoot) {
			setShoot(true);
		}
		
	}

	public void shootDown()
	{
		Vector2 direction = new Vector2();
		direction.addY(-1);
		//boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		boolean shoot = createTearsHero(direction);
		if (shoot) {
			setShoot(true);
		}
		
	}

	public void shootLeft()
	{
		Vector2 direction = new Vector2();
		direction.addX(-1);
		//boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		boolean shoot = createTearsHero(direction);
		if (shoot) {
			setShoot(true);
		}
	}

	public void shootRight()
	{
		Vector2 direction = new Vector2();
		direction.addX(1);
		//boolean shoot = Larmes.createLarmesHero(getPosition(),direction,getShoot());
		boolean shoot = createTearsHero(direction);
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
	
	public Tears tears_get(int i)
	{
		return tears_list.get(i);
	}
	
	public void tears_remove(int i)
	{
		tears_list.remove(i);
	}
	
	public int tears_size()
	{
		return tears_list.size();
	}
	
	public double getCoin_maxValue() {
		return coin_maxValue;
	}

	public double getHealth_maxValue() {
		return health_maxValue;
	}

	public void setHealth_maxValue(double health_maxValue) {
		this.health_maxValue = health_maxValue;
	}

	public double getDmg() {
		return dmg;
	}

	public void setDmg(double dmg) {
		this.dmg = dmg;
	}

	public int getCurrentCoin() {
		return currentCoin;
	}

	public void setCurrentCoin(int currentCoin) {
		this.currentCoin = currentCoin;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;
	}

	
	
}
