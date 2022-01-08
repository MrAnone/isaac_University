package resources;

import libraries.Vector2;

public class HeroInfos
{
	public static Vector2 ISAAC_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.7);
	public static Vector2 LARMES_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
	public static final double LARMES_SPEED = 0.01;
	public static final int LARMES_SCOPE = 40;
	public static final int LARMES_FRAME = 10;
	public static final int ISAAC_LIFE = 6;
	public static final double ISAAC_SPEED = 0.01;
	public static final double ISAAC_FAST = 0.05;
	public static final double Pierre_SPEED = 0.01;
}
