
public class SunPickup extends Entite {

	public static final String FILENAME = "SUNPICK.png";
	public static final double SCALE = Grid.GRID_SIZE;
	
	public SunPickup(double x, double y) {
		super(x,y);
	}
	
	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),FILENAME,Grid.convertX(SCALE),Grid.convertY(SCALE));
		
	}


	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	

}
