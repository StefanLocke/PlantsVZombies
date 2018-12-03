
public class Sunflower extends Plant {
	public static final int SUN_MAXHP = 500;
	public static final String FILENAME = "sun.png";
	public static final double SCALE = Grid.GRID_SIZE;
	double posPX;
	double posPY;
	Timer timer;
	public Sunflower(double x, double y) {
		super(x, y,SCALE,SUN_MAXHP,FILENAME);	
		timer = new Timer(24000);
	}
	@Override
	public void step() {
		if (timer.hasFinished()) {
					
		}
	}
	public static void place(double x, double y) {
		System.out.println("La souris a été cliquée en : "+x+" - "+y);
		Position here = Grid.where(x,y);
		Position place = Grid.getCoord(here.getX(),here.getY());
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			GameWorld.plants.add(new Sunflower(place.getX(),place.getY()));
			Main.mapGroup.isTaken.put(""+i+j,true);
		}
		else 
			System.out.println("this case is ocupied");

	}
}
