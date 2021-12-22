package gameWorld;

import java.util.ArrayList;

import gameobjects.Fly;
import gameobjects.Spider;
import gameobjects.Hero;
import gameobjects.Larmes;
import libraries.Vector2;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.MonstersInfos;
import resources.RoomInfos;

public abstract class Room
{
	private Hero hero;
	public static ArrayList<Larmes> larmes_list;

	public Room(Hero hero)
	{
		this.hero = hero;
		larmes_list = new ArrayList <Larmes>();
	}
	
	public void Larmes() {
		for(int i=0;i<larmes_list.size();i++) {
			larmes_list.get(i).drawGameObject();
		}
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		
	}
	
	public void collisionLarmes_Mur() {
		for(int i=0;i<larmes_list.size();i++) {
			Vector2 normalizedDirection = new Vector2(larmes_list.get(i).getDirection());
			normalizedDirection.euclidianNormalize(larmes_list.get(i).getSpeed());
			Vector2 positionAfterMoving = larmes_list.get(i).getPosition().addVector(normalizedDirection);
			if (!libraries.Physics.ZonedeJeu(positionAfterMoving,larmes_list.get(i).getSize())){
				larmes_list.remove(i);
			}
		}
		
	}
		
	public void makeLarmesPlay()
	{
		if(hero.getTimeLarmes()==0) {
			hero.setShoot(false);
			hero.setTimeLarmes(resources.HeroInfos.LARMES_FRAME);
		}
		else if (hero.getShoot()) {
			hero.setTimeLarmes(hero.getTimeLarmes()-1);
		}
		for(int i=0;i<larmes_list.size();i++) {
			larmes_list.get(i).setScope(larmes_list.get(i).getScope()-1);
			if (larmes_list.get(i).getScope()==0) {
				larmes_list.remove(i);
			}
			else {
				larmes_list.get(i).updateGameObject();
			}
		}
	}
	
	public void makeHeroPlay()
	{
		hero.updateGameObject();
	}

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		
	}
}
