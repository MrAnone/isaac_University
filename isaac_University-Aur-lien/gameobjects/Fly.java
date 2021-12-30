package gameobjects;

import java.util.ArrayList;

import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;
public class Fly extends Monsters{
	
	public ArrayList<Larmes> larmesFly_list;
	private int TimeLarmes ;
	private boolean Shoot ;
	
	public Fly(Vector2 position, Vector2 size, double speed, int life, Vector2 direction, String imagePath) {
		super(position, size, speed, life, direction, ImagePaths.FLY);
		larmesFly_list = new ArrayList <Larmes>();
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
}
