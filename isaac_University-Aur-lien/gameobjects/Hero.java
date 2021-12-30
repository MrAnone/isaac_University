package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.HeroInfos;

public class Hero
{
	private Vector2 position;
	private Vector2 size;
	private String imagePath;
	private double speed;
	private Vector2 direction;
	private int TimeLarmes ;
	private boolean Shoot ;
	private double  currentCoin;
	private double  currentHealth;
	private final int coinMaxValue=99;
	private double healthMaxValue;
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
		this.dmg=3.5;
		this.healthMaxValue=6;
		this.currentHealth=6;
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
	
	public void pickHeart() {
		currentHealth+=2;
		if(currentHealth>healthMaxValue) {
			currentHealth=healthMaxValue;
		}
	}
	
	public void pickHalfHeart() {
		currentHealth+=1;
		if(currentHealth>healthMaxValue) {
			currentHealth=healthMaxValue;
		}
	}
	
	public void pickCoin(Consumable_Objects object){
		currentCoin+=object.getValue();
	}
	/**
	 * This function up by one the damages the hero does, according to the item of the same name.
	 */
	public void BloodOfMartyr() {
		dmg+=+1;
	}
	public void hpUp() {
		healthMaxValue+=2;
		currentHealth=healthMaxValue;
	}
	public void pickobject(Objects object) {

			int id = object.getId();
			switch (id) {
			case 0:
				 BloodOfMartyr();
				break;
			case 1:
				hpUp();
			case 2:
				if(currentHealth<healthMaxValue) {
					pickHeart();
				}				
				break;
			case 3:
				if(currentHealth<healthMaxValue) {
					pickHalfHeart();
				}
				break;
			default:
				pickCoin((Consumable_Objects) object);
				
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

	public double getCurrentCoin() {
		return currentCoin;
	}

	public void setCurrentCoin(double d) {
		this.currentCoin = d;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;
	}

	public double getHealthMaxValue() {
		return healthMaxValue;
	}

	public void setHealthMaxValue(double health_maxValue) {
		this.healthMaxValue = health_maxValue;
	}

	public double getDmg() {
		return dmg;
	}

	public void setDmg(double dmg) {
		this.dmg = dmg;
	}

	public int getCoinMaxValue() {
		return coinMaxValue;
	}
}
