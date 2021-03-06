public class Zombie extends Enemy {
	
	public static final double MOVE_X = 0.0005;
	public static final double scale = Grid.GRID_SIZE;
	public static final int MAX_HP = 200;
	public static final int DPS = 30;
	public static final String fileName = "pictures/Zombie.png";
	public Zombie(double x, double y) {
		super(x, y,MOVE_X, scale, MAX_HP, DPS, fileName);
	}
	
}