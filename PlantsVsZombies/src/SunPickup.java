
public class SunPickup extends Entite {

	public static final String FILENAME = "SUNPICKt.png";
	public static final double SCALE = Grid.GRID_SIZE;
	Timer timer;
	public SunPickup(double x, double y) {
		super(x,y);
		timer = new Timer(20000);
		toRemove = false;
		counted = false;
	}
	
	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),FILENAME,Grid.convertX(SCALE),Grid.convertY(SCALE));
	}


	@Override
	public void step() {
		if (timer.hasFinished() && !toRemove)
		{
			Position place = Grid.where(getX(),getY());
			GameWorld.sunPower += 25;
			toRemove = true;
			Main.mapGroup.hasSun.put(""+(int)place.getX()+(int)place.getY(), false);
		
	}
	
	}
}
