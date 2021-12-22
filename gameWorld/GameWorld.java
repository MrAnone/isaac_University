package gameWorld;

import gameobjects.Fly;
import gameobjects.Spider;
import gameobjects.Hero;
import libraries.StdDraw;
import resources.Controls;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.MonstersInfos;
import resources.RoomInfos;

public class GameWorld
{
	private Room_classic currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Room_classic(hero);
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShoot();
	}

	public boolean gameOver()
	{
		if (hero.getLife()==0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
		
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}
	
	private void processKeysForShoot() {
		if (StdDraw.isKeyPressed(Controls.shootUp))
		{
			hero.shootUp();
		}
		if (StdDraw.isKeyPressed(Controls.shootDown))
		{
			hero.shootDown();
		}
		if (StdDraw.isKeyPressed(Controls.shootRight))
		{
			hero.shootRight();
		}
		if (StdDraw.isKeyPressed(Controls.shootLeft))
		{
			hero.shootLeft();
		}
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
	}
}
