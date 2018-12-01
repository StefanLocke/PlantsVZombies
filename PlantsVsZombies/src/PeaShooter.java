
public class PeaShooter extends Plant {
	public static final int PEAS_MAXHP = 100;
	public static final String FILENAME = "Peashooter.png";
	public static final double SCALE = Grid.GRID_SIZE;
	double posPX;
	double posPY;
	Timer timer;
	public PeaShooter(double x, double y) {
		super(x, y,SCALE,PEAS_MAXHP,FILENAME);
		posPX = x;
		posPY = y;
		timer = new Timer(1500);
	}
	
	@Override
	public void step() {
		if (timer.hasFinished()) {
			shoot();
			timer.restart();
		}
		if (HP<1)
		{
			GameWorld.plants.remove(this);

		}			
	}
}

