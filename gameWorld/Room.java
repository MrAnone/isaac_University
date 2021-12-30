package gameWorld;

import java.util.ArrayList;

import gameobjects.Hero;
import gameobjects.Door;
import gameobjects.Fly;
import libraries.Vector2;
import resources.ImagePaths;
import libraries.StdDraw;
import gameobjects.ListConsumables;
import gameobjects.ListPassivesObjects;

public abstract class Room
{
	private Hero hero;
	private ListConsumables listObjects;
	private ListPassivesObjects listPassives;
	private ArrayList<Door> Door_list;
	private String ID;
	
	public ArrayList<Door> getDoor_list() {
		return Door_list;
	}

	public void setDoor_list(ArrayList<Door> door_list) {
		Door_list = door_list;
	}

	public Room(Hero hero,String ID)
	{
		this.hero = hero;
		this.ID = ID;
		Door_list = new ArrayList <Door>();
		this.listObjects= new ListConsumables();
		this.listPassives= new ListPassivesObjects();
		CreateDoor();
	}
	
	public void Larmes() {
		for(int i=0;i<hero.tears_size();i++) {
			hero.tears_get(i).drawGameObject();
		}
	}
	

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		
	}
	
	public void collisionLarmes_Mur() {
		for(int i=0;i<hero.tears_size();i++) {
			Vector2 normalizedDirection = new Vector2(hero.tears_get(i).getDirection());
			normalizedDirection.euclidianNormalize(hero.tears_get(i).getSpeed());
			Vector2 positionAfterMoving = hero.tears_get(i).getPosition().addVector(normalizedDirection);
			if (!libraries.Physics.ZonedeJeu(positionAfterMoving,hero.tears_get(i).getSize())){
				hero.tears_remove(i);
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
		for(int i=0;i<hero.tears_size();i++) {
			hero.tears_get(i).setScope(hero.tears_get(i).getScope()-1);
			if (hero.tears_get(i).getScope()==0) {
				hero.tears_remove(i);
			}
			else {
				hero.tears_get(i).updateGameObject();
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
	public void drawObject() {
		for(int i=0;i<listObjects.size();i++) {
			listObjects.get(i).drawGameObject();
		}
		for(int i=0;i<listPassives.size();i++) {
			listPassives.get(i).drawGameObject();
		}
	}
	public void CreateDoor() {
		Door d1 = new Door (new Vector2(0.071,0.5),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,90.0);
		Door d2 = new Door (new Vector2(0.5,0.929),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,0.0);
		Door d3 = new Door (new Vector2(0.929,0.5),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,-90.0);
		Door d4 = new Door (new Vector2(0.5,0.071),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,180.0);
		Door_list.add(d1);
		Door_list.add(d2);
		Door_list.add(d3);
		Door_list.add(d4);
	}
	public void DrawDoor() {
		for(int i =0;i<Door_list.size();i++) {
			Door_list.get(i).drawGameObject();
		}
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
}
