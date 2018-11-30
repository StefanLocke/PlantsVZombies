public class Enemy extends Entite{		
	public double MOVE_X;
	public double scale;
	public int HP;
	private int DPS;
	String fileName;
	Timer dpsTimer;
	boolean toRemove;
	public Enemy(double x, double y, double move_X,double scaling,int maxhp,int dps, String filename) {
		super(x, y);
		MOVE_X = move_X;
		DPS = dps ;
		HP = maxhp;
		fileName = filename;	
		scale=scaling;
		dpsTimer = new Timer(1000);
	}

	@Override
	public void step() {	
		HP = HP - getHit();
		if (HP>1) {
			if (!this.isFighting())
				this.position.setX(this.position.getX() - MOVE_X);
			
			else {
				hit();
			}
		}
		else {
			toRemove = true;
		}
	}
	@Override
	public void dessine() {
		StdDraw.picture(position.getX(),position.getY(),fileName,scale,scale);
		
	}
	public boolean isFighting() {
		boolean R = false;
		for (Plant plant: GameWorld.plants) {
			if (this.getY() == plant.getY()) {
				
				if (Math.abs((this.getX() - plant.getX())) < GameWorld.EPSI )
					R = true;
			}
		}
		return R;
	}
	
	public void hit() {
		for (Plant plant: GameWorld.plants) {
			if (this.getY() == plant.getY()) {
				if ((Math.abs((this.getX() - plant.getX())) < GameWorld.EPSI)  && (dpsTimer.hasFinished()))
				{
					plant.HP = plant.HP-DPS; 
					dpsTimer.restart();
				}
				
			}
		}
	}
	
	public int getHit() {
		int take = 0;
	for (Projectile proj: GameWorld.projectiles) {
		if (this.getY() == proj.getY()) {
			if ((Math.abs((this.getX() - proj.getX())) <= (0.02)))
			{
				System.out.println("take dmg");
				take = proj.dmg;
				GameWorld.projectiles.remove(proj);
				System.out.println(HP);
				return take;
			}
			
		}
	}
	return take;
	}

}