package gameobjects;

import java.util.ArrayList;

import libraries.Vector2;
import resources.MonstersInfos;
public class Fly extends Monsters{
	
	private ArrayList<Tears> Tears_Fly;
	private int TimeLarmes ;
	private boolean Shoot ;
	
	public Fly(Vector2 position, Vector2 size, double speed, int life, Vector2 direction, String imagePath) {
		super(position, size, speed, life, direction, "Fly.png");
		Tears_Fly = new ArrayList <Tears>();
		this.TimeLarmes = MonstersInfos.LARMES_FRAME;
		this.Shoot = false;
		// TODO Auto-generated constructor stub		
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
	
	public Tears Tears_Fly_get(int i)
	{
		return Tears_Fly.get(i);
	}
	public void Tears_Fly_remove(int i)
	{
		Tears_Fly.remove(i);
	}
	public int Tears_Fly_size()
	{
		return Tears_Fly.size();
	}
	public void Tears_Fly_add(Tears tears)
	{
		Tears_Fly.add(tears);
	}
}
