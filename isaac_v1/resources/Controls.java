package resources;

import libraries.Keybinding;
import libraries.Keybinding.SpecialKeys;

public class Controls
{
	//Touches de d�placement du h�ro
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	
	//Touches de tirs du h�ro
	public static int shootUp = Keybinding.keycodeOf(SpecialKeys.UP);
	public static int shootDown = Keybinding.keycodeOf(SpecialKeys.DOWN);
	public static int shootRight = Keybinding.keycodeOf(SpecialKeys.RIGHT);
	public static int shootLeft = Keybinding.keycodeOf(SpecialKeys.LEFT);
	
	//Touches de triches
	public static int invincible = Keybinding.keycodeOf('i');
	public static int fast = Keybinding.keycodeOf('l');
	public static int kill_all = Keybinding.keycodeOf('k');
	public static int dommage_max = Keybinding.keycodeOf('p');
	public static int money = Keybinding.keycodeOf('o');
}
