
public class Plant extends Entite {
	
	public double scale;
	public int HP;
	String fileName;
	boolean toRemove;
	boolean counted;
	
	public Plant(double x, double y,double scaling,int maxhp, String filename) {
		super(x, y);
		HP = maxhp;
		fileName = filename;	
		scale=scaling;
		toRemove = false;
		counted = false;
	}

	public void step() {
		if (HP<1)
		{
			toRemove = true;

		}			
	}

	public void dessine() {
		StdDraw.picture(position.getX(), position.getY(),fileName,Grid.convertX(scale),Grid.convertY(scale));
		
	}
	
	public void shoot() {
		GameWorld.projectiles.add(new PeaProj(this.getX(), this.getY()));
	}
}
	
