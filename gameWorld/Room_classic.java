package gameWorld;

import java.util.ArrayList;
import resources.MonstersInfos;

import gameobjects.Fly;
import gameobjects.Spider;
import gameobjects.Hero;
import gameobjects.Tears;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import libraries.StdDraw;
import libraries.Vector2;

public class Room_classic extends Room{
	
	private Hero hero;
	private ArrayList<Fly> Fly_list;
	private ArrayList<Spider> Spider_list;
	
	public Room_classic(Hero hero,String ID) {
		super(hero, ID);
		this.hero = hero;
		Fly_list = new ArrayList <Fly>();
		Spider_list = new ArrayList <Spider>();
		CreateSpider();
		CreateFly();
	}
	private void CreateSpider() {
		for(int i=0 ; i<1;i++) {
			//Vector2 position = new Vector2 (Math.random()*((1.0-(resources.MonstersInfos.FLY_SIZE.getX()))-(0.0+(resources.MonstersInfos.FLY_SIZE.getX()))),Math.random()*((1.0-(resources.MonstersInfos.FLY_SIZE.getY()))-(0.0+(resources.MonstersInfos.FLY_SIZE.getY())))-(resources.MonstersInfos.FLY_SIZE.getY()/2));
			Vector2 position = new Vector2 (0.75,0.75);
			Vector2 direction = new Vector2 (Math.random()*(2.0-0.0)-1.0,Math.random()*(2.0-0.0)-1.0);
			Spider arraigné = new Spider(position, MonstersInfos.SPIDER_SIZE, MonstersInfos.SPIDER_SPEED, MonstersInfos.SPIDER_LIFE,direction, ImagePaths.SPIDER);
			Spider_list.add(arraigné);
		}
	}
	
	private void CreateFly() {
		//Vector2 position = new Vector2 (Math.random()*((1.0-(resources.MonstersInfos.FLY_SIZE.getX())/2)-(0.0+(resources.MonstersInfos.FLY_SIZE.getX())/2)),Math.random()*((1.0-(resources.MonstersInfos.FLY_SIZE.getY()))-(0.0+(resources.MonstersInfos.FLY_SIZE.getY())))-(resources.MonstersInfos.FLY_SIZE.getY()/2));
		Vector2 position = new Vector2 (0.75,0.75);
		Vector2 direction = new Vector2 (hero.getPosition().getX()-position.getX(),hero.getPosition().getY()-position.getY());
		Fly mouche = new Fly(position, MonstersInfos.FLY_SIZE, MonstersInfos.FLY_SPEED, MonstersInfos.FLY_LIFE, direction, ImagePaths.FLY);
		Fly_list.add(mouche);
	}
	
	private void SpiderDraw() {
		for(int i=0;i<Spider_list.size();i++) {
			Spider_list.get(i).drawGameObject();
		}
	}
	
	private void FlyDraw() {
		for(int i=0;i<Fly_list.size();i++) {
			Fly_list.get(i).drawGameObject();
			for(int j=0;j<Fly_list.get(i).Tears_Fly_size();j++) {
				Fly_list.get(i).Tears_Fly_get(j).drawGameObject();
			}
		}
	}
	@Override
	public void updateRoom()
	{
		
		makeSpiderPlay();
		makeFlyPlay();
		makeHeroPlay();
		makeLarmesPlay();
		collisionLarmes_Mur();
		collisionLarmes_Spiders();
		collisionLarmes_Fly();
		collisionLarmesFly_Isaac();
		collisionSpider_Isaac();
		collisionFly_Isaac();
		collisionLarmesFly_Mur();		
	}
	private void collisionLarmesFly_Mur() {
		for(int j=0;j<Fly_list.size();j++) {
			for(int i=0;i<Fly_list.get(j).Tears_Fly_size();i++) {
				Vector2 normalizedDirection = new Vector2(Fly_list.get(j).Tears_Fly_get(i).getDirection());
				normalizedDirection.euclidianNormalize(Fly_list.get(j).Tears_Fly_get(i).getSpeed());
				Vector2 positionAfterMoving = Fly_list.get(j).Tears_Fly_get(i).getPosition().addVector(normalizedDirection);
				if (!libraries.Physics.ZonedeJeu(positionAfterMoving,Fly_list.get(j).Tears_Fly_get(i).getSize())){
					Fly_list.get(j).Tears_Fly_remove(i);
				}
		}
		}
		
		
	}
	
	
	private void collisionLarmes_Spiders() {
		for(int i=0;i<hero.tears_size();i++) {
			for(int j=0;j<Spider_list.size();j++) {
				if (libraries.Physics.rectangleCollision(hero.tears_get(i).getPosition(),hero.tears_get(i).getSize(),Spider_list.get(j).getPosition(),Spider_list.get(j).getSize())) {
					Spider_list.get(j).setLife(Spider_list.get(j).getLife()-1);
					hero.tears_remove(i);
				}
			}
		}
	}
	private void collisionLarmes_Fly() {
		for(int i=0;i<hero.tears_size();i++) {
			for(int j=0;j<Fly_list.size();j++) {
				if (libraries.Physics.rectangleCollision(hero.tears_get(i).getPosition(),hero.tears_get(i).getSize(),Fly_list.get(j).getPosition(),Fly_list.get(j).getSize())) {
					Fly_list.get(j).setLife(Fly_list.get(j).getLife()-1);
					hero.tears_remove(i);
				}
			}
		}
	}
	
	private void collisionLarmesFly_Isaac() {
		for(int i=0;i<Fly_list.size();i++) {
			for(int j=0;j<Fly_list.get(i).Tears_Fly_size();j++) {
				if (libraries.Physics.rectangleCollision(hero.getPosition(),hero.getSize(),Fly_list.get(i).Tears_Fly_get(j).getPosition(),Fly_list.get(i).Tears_Fly_get(j).getSize())) {
					hero.setCurrentHealth(hero.getCurrentHealth()-1);
					Fly_list.get(i).Tears_Fly_remove(j);
				}
			}
		}
		
	}
	
	private void collisionFly_Isaac() {
		for(int j=0;j<Fly_list.size();j++) {
			if (libraries.Physics.rectangleCollision(hero.getPosition(),hero.getSize(),Fly_list.get(j).getPosition(),Fly_list.get(j).getSize())) {
				hero.setCurrentHealth(hero.getCurrentHealth()-1);
				Fly_list.get(j).setDirection(Fly_list.get(j).getDirection().reverse());
			}
		}
	}
	
	private void collisionSpider_Isaac() {
		for(int j=0;j<Spider_list.size();j++) {
			if (libraries.Physics.rectangleCollision(hero.getPosition(),hero.getSize(),Spider_list.get(j).getPosition(),Spider_list.get(j).getSize())) {
				hero.setCurrentHealth(hero.getCurrentHealth()-1);
				Spider_list.get(j).setDirection(Spider_list.get(j).getDirection().reverse());
			}
		}
	}
	private void makeSpiderPlay()
	{
		for(int i=0;i<Spider_list.size();i++) {
			Spider_list.get(i).setMove(Spider_list.get(i).getMove()-1);
			if (Spider_list.get(i).getStop()) {
				if (Spider_list.get(i).getMove()==0) {
					Spider_list.get(i).setDirection(new Vector2 (Math.random()*(2.0-0.0)-1.0,Math.random()*(2.0-0.0)-1.0));
					Spider_list.get(i).setMove(20);
					Spider_list.get(i).setStop(false);
				}
			}
			else {
				if (Spider_list.get(i).getMove()==0) {
					Spider_list.get(i).setDirection(new Vector2 ());
					Spider_list.get(i).setMove(5);
					Spider_list.get(i).setStop(true);
				}
			}
			if (Spider_list.get(i).getLife()==0) {
				Spider_list.remove(i);
			}
			else {
				Spider_list.get(i).updateGameObject();
			}
		}
	}
	
	private void makeFlyPlay()
	{
		for(int i=0;i<Fly_list.size();i++) {
			if (Fly_list.get(i).getLife()==0) {
				Fly_list.remove(i);
			}
			else {
				Fly_list.get(i).setDirection(new Vector2 (hero.getPosition().getX()-Fly_list.get(i).getPosition().getX(),hero.getPosition().getY()-Fly_list.get(i).getPosition().getY()));
				Fly_list.get(i).updateGameObject();
				makeLarmesFlyPlay(Fly_list.get(i));
			}
		}
	}
	
	private void makeLarmesFlyPlay(Fly mouche) {
		boolean shoot = createLarmesFly(mouche);
		if (shoot) {
			mouche.setShoot(true);
		}
		makeLarmesPlayFly(mouche);
	}
	private boolean createLarmesFly(Fly mouche) {
		if (mouche.getShoot() == false) {
			Tears shoot = new Tears(mouche.getPosition(),mouche.getDirection(), HeroInfos.LARMES_SIZE, MonstersInfos.LARMES_SPEED, MonstersInfos.LARMES_SCOPE,ImagePaths.TEAR_FLY);
			mouche.Tears_Fly_add(shoot);
			return true;
		}
		else {
			return false;
		}
	}
	private void makeLarmesPlayFly(Fly mouche)
	{
		if(mouche.getTimeLarmes()==0) {
			mouche.setShoot(false);
			mouche.setTimeLarmes(MonstersInfos.LARMES_FRAME);
		}
		else if (mouche.getShoot()) {
			mouche.setTimeLarmes(mouche.getTimeLarmes()-1);;
		}
		for(int i=0;i<mouche.Tears_Fly_size();i++) {
			mouche.Tears_Fly_get(i).setScope(mouche.Tears_Fly_get(i).getScope()-1);
			if (mouche.Tears_Fly_get(i).getScope()==0) {
				mouche.Tears_Fly_remove(i);
			}
			else {
				mouche.Tears_Fly_get(i).updateGameObject();
			}
		}
	}
	@Override
	public void  drawRoom () {
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		SpiderDraw();
		FlyDraw();
		Larmes();
		drawObject();
		DrawDoor();
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
