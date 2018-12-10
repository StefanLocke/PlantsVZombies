
public class Projectile extends Entite{
	double scale;
	double speed;
	int dmg;
	String fileName;
	boolean toRemove;
	boolean Counted;
	public Projectile(double x, double y,String fileName,double speed,double scale,int DMG) {
		super(x,y);
		this.fileName=fileName;
		this.speed=speed;
		this.scale=scale;
		this.dmg = DMG;
		toRemove = false;
		counted = false;
	}
	@Override
	public void step() {
		this.position.setX(this.position.getX() + speed);
		if (getX() > 1.2) {
			toRemove = true;
		}
	}

	@Override
	public void dessine() {
		StdDraw.picture(this.position.getX(),this.position.getY(),fileName,Grid.convertX(scale),Grid.convertY(scale));
		
	}
	
	/*public void collide() {     //MOVING TO ENEMY
		for (Enemy enemy: GameWorld.enemies) {
			if ((this.getY() == enemy.getY()) && !enemy.equals(this)) {
				System.out.println("hello");
				if ((Math.abs((this.getX() - enemy.getX())) <= (speed+enemy.MOVE_X)))
				{
					System.out.println("hit");
					enemy.HP = enemy.HP - dmg;
					GameWorld.entites.remove(this);
				}
				
			}
		}
	}*/

}
