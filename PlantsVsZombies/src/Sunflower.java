
public class Sunflower extends Plant {
	public static final int MAXHP = 300;
	public static final int PRICE = 50;
	public static final String FILENAME = "pictures/sunflower.png";
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
		if (HP<1){
			toRemove = true;
			Main.mapGroup.isTaken.put("" + Grid.whereX(this.getX()) + Grid.whereY(this.getY()) , false);
		}						
		}
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			if (Sunflower.canBuy()){
			GameWorld.sunPower -= PRICE;
			GameWorld.plants.add(new Sunflower(Main.mapGroup.getDoubleCoordX((int)here.getX()),Main.mapGroup.getDoubleCoordY((int)here.getY())));
			Main.mapGroup.isTaken.put(""+i+j,true);
			Plant.sunflowerCooldown.restart();
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
	public static boolean canBuy() {
		return (GameWorld.sunPower >= PRICE) && (Plant.sunflowerCooldown.hasFinished());
	}
}
