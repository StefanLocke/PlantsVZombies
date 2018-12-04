
public class Sunflower extends Plant {
	public static final int SUN_MAXHP = 300;
	public static final String FILENAME = "sun.png";
	public static final double SCALE = Grid.GRID_SIZE;
	double posPX;
	double posPY;
	Timer timer;
	public Sunflower(double x, double y) {
		super(x, y,SCALE,SUN_MAXHP,FILENAME);	
		timer = new Timer(2000);
	}
	@Override
	public void step() {
		if (timer.hasFinished()) {
				Position place = Grid.where(this.getX(), this.getY());
				Main.mapGroup.hasSun.put("" + place.getX()+place.getY(),true);
				GameWorld.entites.add(new SunPickup(this.getX(),this.getY()));
				timer.restart();
			}	
		}
	public static void place(double x, double y) {
		System.out.println("La souris a été cliquée en : "+x+" - "+y);
		Position here = Grid.where(x,y);
		Position place = Grid.getCoord(here.getX(),here.getY());
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			if (GameWorld.sunPower >=50){
			GameWorld.sunPower -= 50;
			GameWorld.plants.add(new Sunflower(place.getX(),place.getY()));
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
