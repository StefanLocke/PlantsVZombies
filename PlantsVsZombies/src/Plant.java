
public class Plant extends Entite {
	
	
	String type;
	public Plant(double x,double y,String type) {
		super(x,y);
		this.type=type;		
	}
	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		StdDraw.picture(position.getX(), position.getY(),this.type, 0.1,0.1);
		
	}
}
	
