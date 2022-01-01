package gameWorld;

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
	private Boss boss;

	public Room_boss(Hero hero, String ID) {
		super(hero, ID);
		createListEntitiesOnRoom();
		this.boss = new Boss(new Vector2(0.15,0.8), MonstersInfos.BOSS_SIZE, MonstersInfos.BOSS_SPEED,
				MonstersInfos.BOSS_LIFE,
				new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0), ImagePaths.GAPER);
	}

	public void updateRoom() {

		collisionEntitiesAndHero();
		makeHeroPlay();
		makeLarmesPlay();
		collisionBoss_Isaac();
		collisionLarmes_Mur();
		collisionObjectAndHero();
		roomCleaned();
		boss.setSpiderOrFly(boss.initSpiderOrFly());
		if (boss.getSpiderOrFly() > 0.5) {
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

	public void drawRoom() {

		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);
		StdDraw.text(0.5, 0.5, "BOSS");
		DrawDoor();
		entitiesDraw();
		Larmes();
		objectsDraw();
		getHero().drawGameObject();
		boss.drawGameObject();
	}

	private void collisionBoss_Isaac() {

		if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
				boss.getPosition(), boss.getSize())) {
			getHero().setCurrentHealth(getHero().getCurrentHealth() - 0.5);
			boss.setDirection(boss.getDirection().reverse());
		}

	}

	private void makeBossPlayAsSpider() {
		boss.setMove(boss.getMove() - 1);
		if (boss.getStop()) {
			if (boss.getMove() == 0) {
				boss.setDirection(new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0));
				boss.setMove(20);
				boss.setStop(false);
			}
		} else {
			if (boss.getMove() == 0) {
				boss.setDirection(new Vector2());
				boss.setMove(5);
				boss.setStop(true);
			}
		}
		if (boss.getLife() != 0) {
			boss.updateGameObject();
		}
	}

	private void makeBossPlayAsFly() {
		if (boss.getLife() != 0) {
			boss.setDirection(new Vector2(getHero().getPosition().getX() - boss.getPosition().getX(),
					getHero().getPosition().getY() - boss.getPosition().getY()));
			boss.updateGameObject();
			makeTearsBossPlay();
		}
	}

	private void makeTearsBossPlay() {
		boolean shoot = true;
		if (shoot) {
			boss.setShoot(true);
		}
		makeTearsPlayBoss();
	}

	private void makeTearsPlayBoss() {
		if (boss.getTimeTears() == 0) {
			boss.setShoot(false);
			boss.setTimeTears(MonstersInfos.LARMES_FRAME);
		} else if (boss.isShoot()) {
			boss.setTimeTears(boss.getTimeTears() - 1);
			;
		}
		for (int i = 0; i < boss.Tears_Boss_Size(); i++) {
			boss.getTears_Boss(i).setScope(boss.getTears_Boss(i).getScope() - 1);
			if (boss.getTears_Boss(i).getScope() == 0) {
				boss.getTears_Boss(i);
			} else {
				boss.getTears_Boss(i).updateGameObject();
			}
		}
	}

	@Override
	public void roomCleaned() {
		if (boss.getLife() == 0) {
			ListObjects listFullObjects = new ListObjects();
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
			super.getListObjectOnFloor().add(listFullObjects.get(randomIndex));
			setRoomAlredayCleared(true);
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

}
