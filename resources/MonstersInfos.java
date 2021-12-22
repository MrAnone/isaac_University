package resources;

import libraries.Vector2;

public class MonstersInfos {
	public static Vector2 FLY_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
	public static final double FLY_SPEED = 0.00125;
	public static final int FLY_LIFE = 3;
	public static final double LARMES_SPEED = 0.005;
	public static final int LARMES_SCOPE = 80;
	public static final int LARMES_FRAME = 50;
	public static int TimeLarmes = 10;
	public static boolean Shoot;
	
	public static final double SPIDER_SPEED = 0.02;
	public static Vector2 SPIDER_SIZE = RoomInfos.TILE_SIZE.scalarMultiplication(0.4);
	public static final int SPIDER_LIFE = 5;
	
	public static int dégat_corpsàcorps = 5;
	public static int dégats = 2;
	public static int portée = 20;
	public static double vitesse_projetiles = 0.001 ;
}
