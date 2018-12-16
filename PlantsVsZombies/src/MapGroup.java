import java.util.HashMap;
import java.util.Map;

public class MapGroup {
	Map<Integer,Double> coordXIntToDouble;
	Map<Integer,Double> coordYIntToDouble;
	Map<String,Boolean> isTaken;
	Map<String,Boolean> hasSun;
	double homeCoord;

	public MapGroup() {
		coordXIntToDouble = createCoordX();
		coordYIntToDouble = createCoordY();
		isTaken = setEmpty();
		hasSun = setEmpty();
		homeCoord = Grid.convertX(Grid.GRID_SIZE);
	}
	public static Map<Integer,Double> createCoordX() {
		Map<Integer,Double> M = new HashMap<Integer,Double>();
		for (int x = 1 ; x<= GameWorld.GRID_WIDTH;x++) {
				M.put(x,Grid.convertX(Grid.GRID_SIZE)*x+Grid.convertX(Grid.GRID_SIZE)/2);
		}
		return M;
	}
	public static Map<Integer,Double> createCoordY() {
		Map<Integer,Double> M = new HashMap<Integer,Double>();
		for (int y = 1 ; y<= GameWorld.GRID_HEIGHT;y++) {
				M.put(y,Grid.convertY(Grid.GRID_SIZE)*y-Grid.convertY(Grid.GRID_SIZE)/2);
		}
		return M;
	}
	
	public double getDoubleCoordX(int x) {
		return this.coordXIntToDouble.get(x);
	}
	public double getDoubleCoordY(int y) {
		return this.coordYIntToDouble.get(y);
	}
	
	public static Map<String,Boolean> setEmpty(){
	Map<String,Boolean> X = new HashMap<String,Boolean>();
	for (int x = 1 ; x<= GameWorld.GRID_WIDTH;x++) {
		for (int y = 1; y <= GameWorld.GRID_HEIGHT; y++ ) {
			X.put(""+x+ y,false);
		}
	}
	return X;
	}
}
