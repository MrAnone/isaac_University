package resources;

import libraries.Keybinding;
import libraries.Keybinding.SpecialKeys;

public class Controls
{
	public static int goUp = Keybinding.keycodeOf('z');
	public static int goDown = Keybinding.keycodeOf('s');
	public static int goRight = Keybinding.keycodeOf('d');
	public static int goLeft = Keybinding.keycodeOf('q');
	
	public static int shootUp = Keybinding.keycodeOf(SpecialKeys.UP);
	public static int shootDown = Keybinding.keycodeOf(SpecialKeys.DOWN);
	public static int shootRight = Keybinding.keycodeOf(SpecialKeys.RIGHT);
	public static int shootLeft = Keybinding.keycodeOf(SpecialKeys.LEFT);
	
	public static int invisible = Keybinding.keycodeOf('i');
	public static int rapide = Keybinding.keycodeOf('l');
	public static int kill_all = Keybinding.keycodeOf('k');
	public static int dommage_max = Keybinding.keycodeOf('p');
	public static int pièces = Keybinding.keycodeOf('o');
}
