package gameobjects;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import libraries.Vector2;
import resources.MonstersInfos;

public class Boss extends Monsters {
	private double SpiderOrFly;
	private int move;
	private boolean stop;
	private ArrayList<Tears> Tears_Boss;
	private int TimeTears ;
	private boolean Shoot ;

	public Boss(Vector2 position, Vector2 size, double speed, int life, Vector2 direction, String imagePath) {
		super(position, size, speed, life, direction, imagePath);
		this.SpiderOrFly = initSpiderOrFly();
		this.move = 20;
		this.stop = false;
		this.Tears_Boss = new ArrayList <Tears>();
		this.TimeTears = MonstersInfos.LARMES_FRAME;
		this.Shoot = false;
	}
	
	public double initSpiderOrFly() {
		 return ThreadLocalRandom.current().nextDouble(1.1);
	}

	public double getSpiderOrFly() {
		return SpiderOrFly;
	}

	public void setSpiderOrFly(double spiderOrFly) {
		SpiderOrFly = spiderOrFly;
	}

	public void setMove(int move) {
		this.move = move;
	}

	public int getMove() {
		return this.move;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean getStop() {
		return this.stop;
	}

	public Tears getTears_Boss(int i) {
		return Tears_Boss.get(i);
	}
	
	public void Tears_Boss_remove(int i)
	{
		Tears_Boss.remove(i);
	}
	
	public void Tears_Boss_add(Tears tears)
	{
		Tears_Boss.add(tears);
	}
	public int Tears_Boss_Size()
	{
		return Tears_Boss.size();
	}

	public void setTears_Boss(ArrayList<Tears> tears_Boss) {
		Tears_Boss = tears_Boss;
	}

	public int getTimeTears() {
		return TimeTears;
	}

	public void setTimeTears(int timeTears) {
		TimeTears = timeTears;
	}

	public boolean isShoot() {
		return Shoot;
	}

	public void setShoot(boolean shoot) {
		Shoot = shoot;
	}

}
