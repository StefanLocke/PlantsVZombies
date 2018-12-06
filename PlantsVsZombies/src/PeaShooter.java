
public class PeaShooter extends Plant {
	public static final int PEAS_MAXHP = 100;
	public static final String FILENAME = "Peashooter.png";
	public static final double SCALE = Grid.GRID_SIZE;
	Timer timer;
	public PeaShooter(double x, double y) {
		super(x, y,SCALE,PEAS_MAXHP,FILENAME);
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
			toRemove=true;

		}			
	}
	
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		GameWorld.enemies.add(new Zombie(1,Main.mapGroup.getDoubleCoordY((int)here.getY())));
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			GameWorld.plants.add(new PeaShooter(Main.mapGroup.getDoubleCoordX((int)here.getX()),Main.mapGroup.getDoubleCoordY((int)here.getY())));
			Main.mapGroup.isTaken.put(""+i+j,true);
		}
		else 
			System.out.println("this case is ocupied");

	}
}


