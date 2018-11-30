public class Zombie extends Enemy {
	
	public static final double MOVE_X = 0.0005;
	public static final double scale = 0.1;
	public static final int MAX_HP = 100;
	public static final int DPS = 30;
	public static final String fileName = "Zbie.png";
	public Zombie(double x, double y) {
		super(x, y,MOVE_X, scale, MAX_HP, DPS, fileName);
	}
	
}