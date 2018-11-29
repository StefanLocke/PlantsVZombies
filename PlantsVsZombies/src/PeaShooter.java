
public class PeaShooter extends Plant {
	public static final int PEAS_MAXHP = 1000;
	public static final double PROJ_MOVE_X = 0.005;
	public static final String PEAS_PIC = "Peashooter.png";
	double posPX;
	double posPY;
	public PeaShooter(double x, double y) {
		super(x, y,PEAS_PIC );
		posPX = x;
		posPY = y;
	}
	
	public void step() { //shoot
	}
}
