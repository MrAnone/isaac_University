package gameWorld;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import gameobjects.Hero;
import gameobjects.ListObjects;
import gameobjects.Objects;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public class Room_store extends Room {
	private HashMap<Objects,Integer> prixItems;
	private Objects objet1;
	private Objects objet2;
	private Objects objet3;

	public Room_store(Hero hero,String ID) {
		super(hero,ID);
		this.prixItems= new HashMap<>();
		this.objet1=null;
		this.objet2=null;
		this.objet3=null;
		CreateObjectsOnFloor();
	}
	
	@Override
	public void collisionObjectAndHero() {
		for (int i = 0; i < getListObjectOnFloor().size(); i++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
					getListObjectOnFloor().get(i).getPosition(), getListObjectOnFloor().get(i).getSize())) {
				if(getHero().getCurrentCoin()>=prixItems.get(getListObjectOnFloor().get(i))) {
					getHero().pickobject(getListObjectOnFloor().get(i));
					getHero().setCurrentCoin(getHero().getCurrentCoin()-prixItems.get(getListObjectOnFloor().get(i)));
					getListObjectOnFloor().remove(i);					
				}
			}
		}
		
	}

	@Override
	public void roomCleaned() {
		
	}
	/**
	 * Put 3 randoms items on the floor (except money, because it's useless to buy money with money) at precise position in order to be bought.
	 */
	public void CreateObjectsOnFloor() {
	
		ListObjects listFullObjects = new ListObjects();
		ArrayList<Objects> listPassivesObjects = new ArrayList<Objects>();
		
		/*
		 * Separate list for passives Items like BloodOfMartyr then remove them from the main list
		 */
		listPassivesObjects.add(listFullObjects.get(0));
		listFullObjects.removeListObjects(0);
		listPassivesObjects.add(listFullObjects.get(0));
		listFullObjects.removeListObjects(0);
		
		/*
		 * We remove money Items such as penny
		 */
		listFullObjects.removeListObjects(listFullObjects.size()-1);
		listFullObjects.removeListObjects(listFullObjects.size()-1);
		listFullObjects.removeListObjects(listFullObjects.size()-1);
		
		prixItems.put(listPassivesObjects.get(0),15);
		prixItems.put(listPassivesObjects.get(1),15);
		prixItems.put(listFullObjects.get(0),2);
		prixItems.put(listFullObjects.get(1),1);
		
		int randomNum1 = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());	
		objet1 = listFullObjects.get(randomNum1);
		listFullObjects.removeListObjects(randomNum1);
		/*
		 * Will always be a Passive Object as requested 
		 */
		int randomNum2 = ThreadLocalRandom.current().nextInt(0, listPassivesObjects.size());
		objet2 = listPassivesObjects.get(randomNum2);
		listPassivesObjects.remove(randomNum2);
		
		int randomNum3 = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
		objet3 = listFullObjects.get(randomNum3);
		listFullObjects.removeListObjects(randomNum3);
		
		Vector2 pos1 = new Vector2(0.25,0.5);
		objet1.setPosition(pos1);
		
		Vector2 pos2 = new Vector2(0.5,0.5);
		objet2.setPosition(pos2);
		
		Vector2 pos3 = new Vector2(0.75,0.5);
		objet3.setPosition(pos3);
		
		getListObjectOnFloor().add(objet1);
		getListObjectOnFloor().add(objet2);
		getListObjectOnFloor().add(objet3);
		
	}
	

	@Override
	public void updateRoom() {
		makeHeroPlay();
		collisionObjectAndHero();
	}
	private void objectsDraw() {
		for (int i = 0; i < super.getListObjectOnFloor().size(); i++) {
			super.getListObjectOnFloor().get(i).drawGameObject();
			StdDraw.text(super.getListObjectOnFloor().get(i).getPosition().getX(), 0.45, ""+prixItems.get(super.getListObjectOnFloor().get(i))+"");
		}
	}
	
	@Override
	public void drawRoom() {
		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);				
		objectsDraw();
		//Afficher();
		getHero().drawGameObject();
		DrawDoor();
	}

	

	

	

}
