import java.util.HashMap;
import java.util.Map;

public class MapGroup {
	Map<Integer,Double> coordXIntToDouble;
	Map<Integer,Double> coordYIntToDouble;
	
	public MapGroup() {
		coordXIntToDouble = createCoordX();
		coordYIntToDouble = createCoordY();
		System.out.println(coordXIntToDouble.get(1));
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
}
