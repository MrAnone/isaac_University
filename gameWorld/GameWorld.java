package gameWorld;

import gameobjects.Hero;
import gameobjects.Fly;
import libraries.StdDraw;
import resources.Controls;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class GameWorld
{
	private Room_classic currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		//Fly mouche = new Fly(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED,ImagePaths.FLY);
		currentRoom = new Room_classic(hero);
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShoot();
	}

	public boolean gameOver()
	{
		return false;
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
