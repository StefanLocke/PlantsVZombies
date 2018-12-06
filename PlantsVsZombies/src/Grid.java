
public class Grid extends Entite {

	public static final double GRID_SIZE=Main.Y_RESO;
	public Grid() {
		super();
		}	
	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		for (int x = 1 ; x<= GameWorld.GRID_WIDTH;x++) {
			for (int y = 1; y <= GameWorld.GRID_HEIGHT; y++ ) {
				if (y==GameWorld.GRID_HEIGHT)
					StdDraw.setPenColor(StdDraw.BLACK);
				else if ((y+x)%2 == 0)
					StdDraw.setPenColor(StdDraw.DARKISH_GREEN);
				else 
					StdDraw.setPenColor(StdDraw.DARK_GREEN);
				
				StdDraw.filledRectangle(Main.mapGroup.getDoubleCoordX(x),Main.mapGroup.getDoubleCoordY(y),convertX(GRID_SIZE)/2,convertY(GRID_SIZE)/2);		
			}
		}
		StdDraw.setFont(StdDraw.NEW_FONT);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(Main.mapGroup.getDoubleCoordX(1)+convertX(GRID_SIZE)/2,Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/3, "Flower Power :");
		StdDraw.setFont(StdDraw.NEW_FONTbis);
		StdDraw.text(Main.mapGroup.getDoubleCoordX(1)+convertX(GRID_SIZE)/2,Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)-convertY(GRID_SIZE)/6, ""+GameWorld.sunPower);
		
	}
	
	public static double convertX(double pixels) {   // return a double that is between  0 and 1
		return (((pixels)/GameWorld.GRID_HEIGHT)/Main.X_RESO);
	}
	public static double convertY(double pixels) {
		return (((pixels)/GameWorld.GRID_HEIGHT)/Main.Y_RESO);
	}
	
	public static Position where(double x,double y)
	{		
		double X = 0;
		double Y = 0;
		for (int i = 1 ; i<= GameWorld.GRID_WIDTH;i++) {
			for (int j = 1; j <= GameWorld.GRID_HEIGHT; j++ ) {
				if ((x>= Main.mapGroup.getDoubleCoordX(i)-(convertX(GRID_SIZE)/2) ) && (x<=Main.mapGroup.getDoubleCoordX(i)+(convertX(GRID_SIZE)/2)))
				{
					if ((y>=(Main.mapGroup.getDoubleCoordY(j)-convertY(GRID_SIZE)/2 ) && (y<=Main.mapGroup.getDoubleCoordY(j)+(convertY(GRID_SIZE)/2))))
					{
					X=i;
					Y=j;
					}
				}
					
			}
		}
		return new Position(X, Y);
	}
	
	public static Position getCoord(double X,double Y) { /// rend coord sur le grifd
		double x = 0;
		double y = 0;
		for (int i = 1 ; i<= GameWorld.GRID_WIDTH;i++) {
			for (int j = 1; j <= GameWorld.GRID_HEIGHT; j++ ) {
				if (X == i)
				{
					if (Y == j)
					{
					x = (convertX(GRID_SIZE)*i )+(convertX(GRID_SIZE)/2);
					y = (convertY(GRID_SIZE)*j-(convertY(GRID_SIZE)/2));
					System.out.println("" + x + "-" + y);
					}
				}
					
			}
		}
		
		
		
		return new Position(x,y);
	}
	
}
