
public class SunPickup extends Entite {

	public static final String FILENAME = "SUNPICK.png";
	public static final double SCALE = Grid.GRID_SIZE;
	Timer timer;
	public SunPickup(double x, double y) {
		super(x,y);
		timer = new Timer(6500);
	}
	
	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),FILENAME,Grid.convertX(SCALE),Grid.convertY(SCALE));
		
	}


	@Override
	public void step() {
		if (timer.hasFinished())
		{
			GameWorld.sunPower += 25;
			GameWorld.entites.remove(this);
		
	}
	
	}
}
