
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
			GameWorld.plants.remove(this);

		}			
	}
	
	public static void place(double x, double y) {
		
		System.out.println("La souris a été cliquée en : "+x+" - "+y);
		Position here = Grid.where(x,y);
		Position place = Grid.getCoord(here.getX(),here.getY());
		GameWorld.enemies.add(new Zombie(1,place.getY()));
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			GameWorld.plants.add(new PeaShooter(place.getX(),place.getY()));
			Main.mapGroup.isTaken.put(""+i+j,true);
		}
		else 
			System.out.println("this case is ocupied");

	}
}


