import java.util.*;
public class Grid extends Entite {

	public static final double GRID_SIZE= 0.9*Main.X_RESO;
	public static Map<Position,Position> mapToC = new HashMap<>();
	public static Map<Position,Position> mapToc = new HashMap<>();
	public Grid() {
		super();
		Position P;
		Position K;
		for (int x = 1 ; x<= GameWorld.GRID_WIDTH;x++) {
			for (int y = 1; y <= GameWorld.GRID_HEIGHT; y++ ) {
				mapToC.put(P= new Position(convertX(GRID_SIZE)*x+(convertX(GRID_SIZE)/2),convertY(GRID_SIZE)*y-convertY(GRID_SIZE)/2),K = new Position(x,y));
				//mapToc.put(K,P);
			}
		}
		System.out.println("1");
	}	
	@Override
	public void step() {		
	}

	@Override
	public void dessine() {
		for (int x = 1 ; x<= GameWorld.GRID_WIDTH;x++) {
			for (int y = 1; y <= GameWorld.GRID_HEIGHT; y++ ) {
				if ((y+x)%2 == 0)
					StdDraw.setPenColor(StdDraw.GREEN);
				else 
					StdDraw.setPenColor(StdDraw.DARK_GREEN);
				StdDraw.filledRectangle(convertX(GRID_SIZE)*x+(convertX(GRID_SIZE)/2),convertY(GRID_SIZE)*y-convertY(GRID_SIZE)/2,convertX(GRID_SIZE)/2,convertY(GRID_SIZE)/2);		
			}
		}
	}
	
	public static double convertX(double pixels) {
		return (((pixels)/GameWorld.GRID_WIDTH)/Main.X_RESO);
	}
	public static double convertY(double pixels) {
		return (((pixels)/GameWorld.GRID_WIDTH)/Main.Y_RESO);
	}
	
	public static Position where(double x,double y)
	{		
		double X = 0;
		double Y = 0;
		Position here = null;
		for (int i = 1 ; i<= GameWorld.GRID_WIDTH;i++) {
			for (int j = 1; j <= GameWorld.GRID_HEIGHT; j++ ) {
				if ((x>=(convertX(GRID_SIZE)*i -(convertX(GRID_SIZE)/2))+(convertX(GRID_SIZE)/2) ) && (x<=(convertX(GRID_SIZE)*i+(convertX(GRID_SIZE)/2))+(convertX(GRID_SIZE)/2)))
				{
					if ((y>=(convertY(GRID_SIZE)*j-(convertY(GRID_SIZE)/2))-convertY(GRID_SIZE)/2 ) && (y<=(convertY(GRID_SIZE)*j+(convertY(GRID_SIZE)/2))-convertY(GRID_SIZE)/2))
					{
					X=i;
					Y=j;
					}
				}
					
			}
		}
		return here = new Position(X, Y);
	}
	
	public static Position getCoord(double X,double Y) {
		Position here = null;
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
					System.out.println(x + " + " + y);
					}
				}
					
			}
		}
		
		
		
		return here = new Position(x,y);
	}
	
}
