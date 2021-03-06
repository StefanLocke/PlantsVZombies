
public class Grid {   // all the UI elements

	public static final double GRID_SIZE=Main.Y_RESO;
	public static final double ICONS_PADDING=30;
	public static final double OUTLINE_THICKNESS=0.01;
	public static void draw() {
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
		StdDraw.setFont(StdDraw.NEW_FONT);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(Main.mapGroup.getDoubleCoordX(1)+convertX(GRID_SIZE)/2,Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/3, "Flower Power :");
		StdDraw.setFont(StdDraw.FLOWERPOWERNUMBER);
		StdDraw.text(Main.mapGroup.getDoubleCoordX(1)+convertX(GRID_SIZE)/2,Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)-convertY(GRID_SIZE)/6, ""+GameWorld.sunPower);
		drawPlantBoxes();
		drawCounters();
		
	}
}
	
	public static double convertX(double pixels) {   // return a double that is between  0 and 1 represents the size of one grid box on the X axis
		return (((pixels)/GameWorld.GRID_HEIGHT)/Main.X_RESO);
	}
	public static double convertY(double pixels) {
		return (((pixels)/GameWorld.GRID_HEIGHT)/Main.Y_RESO);
	}
	
	public static Position where(double x,double y) // return position(int int) on the grid the doubles x qnd y qre
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
	
	public static int whereX(double x) {
		int X = 0;
		for (int i = 1; i <= GameWorld.GRID_WIDTH; i++ ) {
			if ((x >= Main.mapGroup.getDoubleCoordX(i)-convertX(GRID_SIZE)/2 ) && (x<=Main.mapGroup.getDoubleCoordX(i)+(convertX(GRID_SIZE)/2)))
				X=i;
		}
		return X;
	}
	public static int whereY(double y) {
		int Y = 0;
		for (int j = 1; j <= GameWorld.GRID_HEIGHT; j++ ) {
			if ((y>= Main.mapGroup.getDoubleCoordY(j)-convertY(GRID_SIZE)/2 ) && (y<=Main.mapGroup.getDoubleCoordY(j)+(convertY(GRID_SIZE)/2)))
				Y=j;
		}
		return Y;
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
	
	public static boolean check(double x, double y) {    // checks if the coords x y are part of the grid and prevents from placing on UI
		boolean Tx = false;
		boolean Ty = false;
		System.out.println(Main.mapGroup.getDoubleCoordX(GameWorld.GRID_WIDTH)+convertX(GRID_SIZE)/2);
		if ((x > Main.mapGroup.getDoubleCoordX(1)-convertX(GRID_SIZE)/2) && (x < Main.mapGroup.getDoubleCoordX(GameWorld.GRID_WIDTH)+convertX(GRID_SIZE)/2)) {
			Tx = true;
		}
		if ((y > Main.mapGroup.getDoubleCoordY(1)-convertY(GRID_SIZE)/2) && (y < Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT-1)+convertY(GRID_SIZE)/2)) {
			Ty = true;
		}
		return Tx && Ty;	
	}
	
	private static void drawCounters() {
		StdDraw.setFont(StdDraw.COUNTERTEXT);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(Main.mapGroup.getDoubleCoordX(GameWorld.GRID_WIDTH),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/4 ,"Wave Nb :" + (GameWorld.waveNb-1) );
		StdDraw.text(Main.mapGroup.getDoubleCoordX(GameWorld.GRID_WIDTH),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT) ,"Zombies killed: " + GameWorld.kills);
		StdDraw.setFont();
		StdDraw.setPenColor();
	}
	
	private static void drawPlantBoxes() {
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		for (int i = 3; i<=8;i++) {
			StdDraw.filledRectangle(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));
		}
		StdDraw.setFont(StdDraw.NEW_FONT);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setPenRadius(OUTLINE_THICKNESS);
		for (int i = 3; i<=8;i++) {
			if (i == 3) {
				StdDraw.picture(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),Sunflower.FILENAME,(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));
				StdDraw.textRight(Main.mapGroup.getDoubleCoordX(i)+convertX(GRID_SIZE)/3, Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/3, "" + Sunflower.PRICE);
			}
			if (i == 4) {
				StdDraw.picture(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),PeaShooter.FILENAME,(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));
				StdDraw.textRight(Main.mapGroup.getDoubleCoordX(i)+convertX(GRID_SIZE)/3, Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/3, "" + PeaShooter.PRICE);
			}
			if (i == 5) {
				StdDraw.picture(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),Nut.FILENAME,(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));	
				StdDraw.textRight(Main.mapGroup.getDoubleCoordX(i)+convertX(GRID_SIZE)/3, Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT)+convertY(GRID_SIZE)/3, "" + Nut.PRICE);
			}
			}
		for (int i = 3; i<=8;i++) {
			StdDraw.setPenColor(StdDraw.BLUE);
			if (i == 3 && Sunflower.canBuy()) {
				if (GameWorld.lastKey == 't')
					StdDraw.setPenColor(StdDraw.RED);
				StdDraw.rectangle(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));
			}
			StdDraw.setPenColor(StdDraw.BLUE);
			if (i == 4 && PeaShooter.canBuy()) {
				if (GameWorld.lastKey == 'p')
					StdDraw.setPenColor(StdDraw.RED);
				StdDraw.rectangle(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));
				}
			StdDraw.setPenColor(StdDraw.BLUE);
			if (i == 5 && Nut.canBuy()) {
				if (GameWorld.lastKey == 'n')
					StdDraw.setPenColor(StdDraw.RED);
				StdDraw.rectangle(Main.mapGroup.getDoubleCoordX(i),Main.mapGroup.getDoubleCoordY(GameWorld.GRID_HEIGHT),(convertX(GRID_SIZE)/2)-(convertX(ICONS_PADDING)),(convertY(GRID_SIZE)/2)-(convertY(ICONS_PADDING)));	
			}
		}
		StdDraw.setPenColor();
		StdDraw.setFont();
		StdDraw.setPenRadius();
	}
	
}
