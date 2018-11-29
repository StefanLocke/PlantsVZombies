
public class Enemy extends Entite{
	
	
	
	private double MOVE_X;
	private double scale;
	public int HP;
	public int DPS;
	String fileName;
	public Enemy(double x, double y, double move_X,double scaling,int maxhp,int dps, String filename) {
		super(x, y);
		MOVE_X = move_X;
		DPS = dps ;
		HP = maxhp;
		fileName = filename;	
		scale=scaling;
	}
	@Override
	public void step() {	
		if (HP<0) {
		if (!this.isFighting())
		this.position.setX(this.position.getX()- MOVE_X);
		else {
			fightingWith();
		}
		}
		else {
			GameWorld.enemies.remove(this);
		}
			
		
	}
	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),fileName,scale,scale);
		
	}
	@Override
	public boolean isFighting() {
		boolean R = false;
		for (Plants entite: GameWorld.plants) {
			if (!entite.equals(this)) {
				
			if (Math.abs((this.getX() - entite.getX())) < GameWorld.EPSI )
				R = true;
			}
		}
		return R;
	}
	
	public void fightingWith() {
		for (Plants entite: GameWorld.plants) {
			if (!entite.equals(this)) {
				if (Math.abs((this.getX() - entite.getX())) < GameWorld.EPSI )
				{
					entite.HP = entite.HP-DPS; 
					System.out.println();
				}
				
			}
		}
	}

}
