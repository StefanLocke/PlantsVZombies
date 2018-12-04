
public class PeaProj extends Projectile{
	
	public static final String fileName = "greenPea.png";
	public static final int DMG = 55;
	public static final double MOVE_X = 0.005;
	public static final double SCALE = Grid.GRID_SIZE*0.25;
	public PeaProj(double x, double y) {
		super(x, y,fileName,MOVE_X,SCALE,DMG);
		// TODO Auto-generated constructor stub
	}

}
