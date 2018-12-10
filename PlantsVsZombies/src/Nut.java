
public class Nut extends Plant{
	public static final int MAXHP = 1500;
	public static final int PRICE = 50;
	public static final String FILENAME = "pictures/nut.png";
	public static final double SCALE = Grid.GRID_SIZE;
	public Nut(double x, double y) {
		super(x, y,SCALE,MAXHP,FILENAME);	
	}
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		Position place = Grid.getCoord(here.getX(),here.getY());
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {		
			if (Nut.canBuy()){
			GameWorld.sunPower -= PRICE;
			GameWorld.plants.add(new Nut(place.getX(),place.getY()));
			Main.mapGroup.isTaken.put(""+i+j,true);
			}
			else {
				System.out.println("you dont have enough powa");
			}
		}
		else 
			System.out.println("this case is ocupied");

	}
	public static boolean canBuy() {
		return GameWorld.sunPower >= PRICE;
	}
}
