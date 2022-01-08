package gameloop;

import gameWorld.GameWorld;
import gameWorld.Room_boss;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Timer;
import resources.DisplaySettings;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;

public class Main
{
	public static void main(String[] args)
	{
		// Hero, world and display initialisation.
		Hero isaac = new Hero(RoomInfos.POSITION_CENTER_OF_ROOM, HeroInfos.ISAAC_SIZE, HeroInfos.ISAAC_SPEED, HeroInfos.ISAAC_LIFE, ImagePaths.ISAAC);
		GameWorld world = new GameWorld(isaac);				
		initializeDisplay();

		// Main loop of the game
		while (!world.gameOver())
		{
			processNextStep(world);
			
		}
		StdDraw.clear();
		StdDraw.picture(0.5,0.5,ImagePaths.LOSE_SCREEN,1.0,1.0,0);
		StdDraw.show();
	}

	private static void processNextStep(GameWorld world)
	{
		Timer.beginTimer();
		StdDraw.clear();
		world.processUserInput();
		world.updateGameObjects();
		world.drawGameObjects();
		world.drawHUD();
		StdDraw.show();
		Timer.waitToMaintainConstantFPS();
	}

	private static void initializeDisplay()
	{
		// Set the window's size, in pixels.
		// It is strongly recommended to keep a square window.
		StdDraw.setCanvasSize(RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE,
				RoomInfos.NB_TILES * DisplaySettings.PIXEL_PER_TILE);

		// Enables double-buffering.
		// https://en.wikipedia.org/wiki/Multiple_buffering#Double_buffering_in_computer_graphics
		StdDraw.enableDoubleBuffering();
	}
}
