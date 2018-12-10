
public class PeaShooter extends Plant {
	public static final int PEAS_MAXHP = 100;
	public static final int PRICE = 0;
	public static final String FILENAME = "pictures/Peashooter.png";
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
			Main.mapGroup.isTaken.put("" + Grid.whereX(this.getX()) + Grid.whereY(this.getY()) , false);

		}			
	}
	/*@Override
	public void dessine() {   // animation?
		if (i == 0)
			StdDraw.picture(position.getX(), position.getY(),"PSe1.png",Grid.convertX(scale),Grid.convertY(scale));
		if (i == 1)
			StdDraw.picture(position.getX(), position.getY(),"PSe2.png",Grid.convertX(scale),Grid.convertY(scale));
		if (i == 2)
			StdDraw.picture(position.getX(), position.getY(),"PSe3.png",Grid.convertX(scale),Grid.convertY(scale));
		if (i == 3)
			StdDraw.picture(position.getX(), position.getY(),"PSe4.png",Grid.convertX(scale),Grid.convertY(scale));
		i++;
		if (i==4)
			i=0;
	}*/
	public static void place(double x, double y) {
		Position here = Grid.where(x,y);
		GameWorld.enemies.add(new Zombie(1,Main.mapGroup.getDoubleCoordY((int)here.getY())));
		int i = (int)here.getX();
		int j = (int)here.getY();
		if (Grid.check(x, y)) {
		if (false == Main.mapGroup.isTaken.get(""+i+j)) {	
			if (PeaShooter.canBuy()) {
				GameWorld.sunPower -= PRICE;
				GameWorld.plants.add(new PeaShooter(Main.mapGroup.getDoubleCoordX((int)here.getX()),Main.mapGroup.getDoubleCoordY((int)here.getY())));
				Main.mapGroup.isTaken.put(""+i+j,true);
			}
			else
				System.out.println("you dont have enouth for this");
				
		}
		else 
			System.out.println("this case is ocupied");

		}
	}
	
	public static boolean canBuy() {
		return GameWorld.sunPower >= PRICE;
	}
}


