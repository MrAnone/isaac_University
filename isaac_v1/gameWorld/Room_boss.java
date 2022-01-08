package gameWorld;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import gameobjects.Boss;
import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.ListObjects;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;
import resources.RoomInfos;

public class Room_boss extends Room {
	private int tick;
	private ArrayList<Boss> bossList;

	public Room_boss(Hero hero, String ID) {
		super(hero, ID);
		createListEntitiesOnRoom();
		this.bossList= new ArrayList<Boss>();
		bossList.add(new Boss(new Vector2(0.15, 0.8), MonstersInfos.BOSS_SIZE, MonstersInfos.BOSS_SPEED,
				MonstersInfos.BOSS_LIFE,
				new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0), ImagePaths.GAPER));
		this.tick = 0;
	}

	public void updateRoom() {

		tick += 1;
		collisionEntitiesAndHero();
		makeHeroPlay();
		makeLarmesPlay();
		collisionTearsToBoss();
		collisionBoss_Isaac();
		collisionLarmes_Mur();
		collisionObjectAndHero();
		roomCleaned();
		randomSpiderOrFly();
		bossBehavior();
		removeBossIfDead();
		
	}

	public void drawRoom() {

		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);
		StdDraw.text(0.5, 0.5, "BOSS");
		DrawDoor();
		entitiesDraw();
		Larmes();
		objectsDraw();
		getHero().drawGameObject();
		for(int i=0;i<bossList.size();i++) {
			bossList.get(i).drawGameObject();
		}
		
	}
	
	private void randomSpiderOrFly() {
		for(int i=0;i<bossList.size();i++) {
			if (tick == 400) {
				bossList.get(i).setSpiderOrFly(bossList.get(i).initSpiderOrFly());
				tick = 0;
			}
		}
	}
	
	private void removeBossIfDead() {
		for(int i=0;i<bossList.size();i++) {
			if(bossList.get(i).getLife()==0) {
				bossList.remove(i);
			}
		}
	}
	private void bossBehavior() {
		for(int i=0;i<bossList.size();i++) {
			if (bossList.get(0).getSpiderOrFly()) {
				/*
				 * like a spider
				 */
				makeBossPlayAsSpider();
			} else {
				/*
				 * like a FLy
				 */
				makeBossPlayAsFly();
			}
		}
	}

	private void collisionBoss_Isaac() {
		for(int i=0;i<bossList.size();i++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(), bossList.get(i).getPosition(),
					bossList.get(i).getSize())) {
				if (getHero().isCanBeHurt()) {
					getHero().setCurrentHealth(getHero().getCurrentHealth() - 0.5);
				}
				bossList.get(i).setDirection(bossList.get(i).getDirection().reverse());
			}
		}

		

	}

	private void collisionTearsToBoss() {
		for (int i = 0; i < getHero().tears_size(); i++) {
			if (libraries.Physics.rectangleCollision(getHero().tears_get(i).getPosition(),
					getHero().tears_get(i).getSize(), bossList.get(0).getPosition(), bossList.get(0).getSize())) {
				bossList.get(0).setLife(bossList.get(0).getLife() - 1);
				getHero().tears_remove(i);
			}
		}
	}

	private void makeBossPlayAsSpider() {

		bossList.get(0).setMove(bossList.get(0).getMove() - 1);
		if (bossList.get(0).getStop()) {
			if (bossList.get(0).getMove() == 0) {
				bossList.get(0).setDirection(new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0));
				bossList.get(0).setMove(20);
				bossList.get(0).setStop(false);
			}
		} else {
			if (bossList.get(0).getMove() == 0) {
				bossList.get(0).setDirection(new Vector2());
				bossList.get(0).setMove(5);
				bossList.get(0).setStop(true);
			}
		}
		if (bossList.get(0).getLife() != 0) {
			bossList.get(0).updateGameObject();
			
		}

	}

	private void makeBossPlayAsFly() {

		bossList.get(0).setDirection(new Vector2(getHero().getPosition().getX() - bossList.get(0).getPosition().getX(),
				getHero().getPosition().getY() - bossList.get(0).getPosition().getY()));
		bossList.get(0).updateGameObject();

	}

	private void makeTearsBossPlay() {
		boolean shoot = true;
		if (shoot) {
			bossList.get(0).setShoot(true);
		}
		makeTearsPlayBoss();
	}

	private void makeTearsPlayBoss() {
		if (bossList.get(0).getTimeTears() == 0) {
			bossList.get(0).setShoot(false);
			bossList.get(0).setTimeTears(MonstersInfos.LARMES_FRAME);
		} else if (bossList.get(0).isShoot()) {
			bossList.get(0).setTimeTears(bossList.get(0).getTimeTears() - 1);
			;
		}
		for (int i = 0; i < bossList.get(0).Tears_Boss_Size(); i++) {
			bossList.get(0).getTears_Boss(i).setScope(bossList.get(0).getTears_Boss(i).getScope() - 1);
			if (bossList.get(0).getTears_Boss(i).getScope() == 0) {
				bossList.get(0).getTears_Boss(i);
			} else {
				bossList.get(0).getTears_Boss(i).updateGameObject();
			}
		}
	}

	@Override
	public void roomCleaned() {
		for(int i=0;i<bossList.size();i++) {
			if (bossList.get(i).getLife() == 0 && isRoomAlredayCleared() == false) {
				ListObjects listFullObjects = new ListObjects();
				int randomIndex = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
				super.getListObjectOnFloor().add(listFullObjects.get(randomIndex));
				setRoomAlredayCleared(true);
			}
		}
		
	}

	private void objectsDraw() {
		for (int i = 0; i < super.getListObjectOnFloor().size(); i++) {
			super.getListObjectOnFloor().get(i).drawGameObject();
		}
	}

	private void entitiesDraw() {
		for (int i = 0; i < super.getListEntitiesOnRoom().size(); i++) {
			super.getListEntitiesOnRoom().get(i).drawGameObject();
		}
	}

	@Override
	public void collisionObjectAndHero() {
		for (int i = 0; i < getListObjectOnFloor().size(); i++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
					getListObjectOnFloor().get(i).getPosition(), getListObjectOnFloor().get(i).getSize())) {
				getHero().pickobject(getListObjectOnFloor().get(i));
				getListObjectOnFloor().remove(i);

			}
		}

	}

	public ArrayList<Boss> getBossList() {
		return bossList;
	}

	public void setBossList(ArrayList<Boss> bossList) {
		this.bossList = bossList;
	}


}
