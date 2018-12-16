
public class PeaShooter extends Plant {
	public static final int PEAS_MAXHP = 300;
	public static final int PRICE = 100;
	public static final String FILENAME = "pictures/Peashooter.png";
	public static final double SCALE = Grid.GRID_SIZE;
	Timer timerToShoot;
	
	public PeaShooter(double x, double y) {
		super(x, y,SCALE,PEAS_MAXHP,FILENAME);
		timerToShoot = new Timer(1500);
	}
	
	@Override
	public void step() {
		if (timerToShoot.hasFinished()) {
			shoot();
			timerToShoot.restart();
		}
		if (HP<1)
		{
			toRemove=true;
			Main.mapGroup.isTaken.put("" + Grid.whereX(this.getX()) + Grid.whereY(this.getY()) , false);

		}			
	}
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (Grid.check(x, y)) {
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {	
			if (PeaShooter.canBuy()) {
				GameWorld.sunPower -= PRICE;
				GameWorld.plants.add(new PeaShooter(Main.mapGroup.getDoubleCoordX((int)here.getX()),Main.mapGroup.getDoubleCoordY((int)here.getY())));
				Main.mapGroup.isTaken.put(""+i+j,true);
				Plant.peashooterCooldown.restart();
			}
			else {
				if (GameWorld.sunPower < PRICE)
					System.out.println("You dont have the sun power to buy this");
				else 
					System.out.println("You cant buy this plant yet ");

			}
		}
		else 
			System.out.println("this case is ocupied");

		}
	}
	
	public static boolean canBuy() {
		return (GameWorld.sunPower >= PRICE) && (Plant.peashooterCooldown.hasFinished());
	}
}


