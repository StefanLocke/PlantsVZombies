
public class Sunflower extends Plant {
	public static final int MAXHP = 300;
	public static final String FILENAME = "sun.png";
	public static final double SCALE = Grid.GRID_SIZE;
	Timer timer;
	public Sunflower(double x, double y) {
		super(x, y,SCALE,MAXHP,FILENAME);	
		timer = new Timer(24000);
	}
	@Override
	public void step() {
		if (timer.hasFinished()) {
				Position place = Grid.where(this.getX(), this.getY());
				Main.mapGroup.hasSun.put("" + (int)place.getX()+(int)place.getY(),true);
				GameWorld.suns.add(new SunPickup(this.getX(),this.getY()));
				timer.restart();
			}	
		}
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			if (GameWorld.sunPower >=50){
			GameWorld.sunPower -= 50;
			GameWorld.plants.add(new Sunflower(Main.mapGroup.getDoubleCoordX((int)here.getX()),Main.mapGroup.getDoubleCoordY((int)here.getY())));
			Main.mapGroup.isTaken.put(""+i+j,true);
			}
			else {
				System.out.println("you dont have enough powa");
			}
		}
		else 
			System.out.println("this case is ocupied");

	}
}
