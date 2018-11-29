
public class Plants extends Entite{

	
	
	private double MOVE_X;
	private double scale;
	public int HP;
	private int DPS;
	String fileName;
	
	public Plants(double x, double y, double move_X,double scaling,int maxhp,int dps, String filename) {
		super(x, y);
		MOVE_X = move_X;
		DPS = dps ;
		HP = maxhp;
		fileName = filename;	
		scale=scaling;
	}

	@Override
	public void step() { //shoots
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dessine() { // draws 
		// TODO Auto-generated method stub
		
	}
}
