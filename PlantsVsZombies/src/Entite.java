
public abstract class Entite {
	
	// la position de l'entite
	protected Position position;
	boolean toRemove;
	boolean counted;
	
	public Entite(double x, double y) {
		position = new Position(x, y);
	}
	
	public Entite() {
	}
	

	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	
	
	public void setPosition(Position p){
		this.position = p;
	}
	
	// met a jour l'entite : déplacement, effectuer une action
	public abstract void step();
	
	// dessine l'entite, aux bonnes coordonnees
	public abstract void dessine();
	
	public boolean isFighting() {
		boolean R = false;
		for (Entite entite: GameWorld.entites) {
			if (!entite.equals(this)) {
				
			if (Math.abs((this.getX() - entite.getX())) < GameWorld.EPSI )
				R = true;
			}
		}
		return R;
	}

	

}
