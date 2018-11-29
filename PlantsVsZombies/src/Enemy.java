public class Enemy extends Entite{		
	private double MOVE_X;
	private double scale;
	private int MAX_HP;
	private int DPS;
	private boolean fight;
	String fileName;
	public Enemy(double x, double y, double move_X,double scaling,int maxhp,int dps, String filename) {
		super(x, y);
		MOVE_X = move_X;
		DPS = dps ;
		MAX_HP = maxhp;
		fileName = filename;	
		scale=scaling;
	}
	@Override
	public void step() {			
		if (!fight)
		this.position.setX(this.position.getX()-MOVE_X);
	}
	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),fileName,scale,scale);		
	}	
	public void setFight(boolean b) {
		this.fight = b;
	}
}
