import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
 
public class GameWorld  {
	public static final double EPSI = PeaProj.MOVE_X + Zombie.MOVE_X+0.02;
	public static final int GRID_HEIGHT= 6;
	public static final int GRID_WIDTH= 9;
	
	
	static List<Entite> entites;   // list of entites
	static List<Plant> plants;   // list of plants on the grid 
	static Map<Integer,LinkedList<Enemy>> enemies;  // map of a list of each zombie on each Y axis of the grid
	static List<Projectile> projectiles;  // list of projectiles on the grid 
	static List<SunPickup> suns;	// list of sun pickups on the grid
	public static char lastKey;	 // last key pressed
	public static int sunPower;   // amount of sun power
	public Timer sunTimer;   // time between each sun spawn
	public Timer waveTimer;  // time between each wave
	List<Integer> EtR;    	// amount of enemies to remove
	int PtR;		// amount of PLANTS to remove
	int StR;		// amount of SUNS to remove
	int PrtR;		// amount of proj to remove
	static int waveNb;
	static int kills;
	
	private static boolean gameWon;
	private static boolean gameLost;

	public GameWorld() {
		
		
		gameWon=false;
		gameLost=false;
		
		// on cree les collections
		entites = new LinkedList<Entite>();
		plants = new LinkedList<Plant>();
		enemies = build();
		System.out.println(enemies.toString());
		projectiles = new LinkedList<Projectile>();
		suns = new LinkedList<SunPickup>();
		lastKey = 'e';
		sunPower = 50;
		sunTimer = new Timer(6000);
		waveTimer = new Timer(20000);
		EtR = new LinkedList<Integer>();
		PrtR = 0;
		PtR = 0;
		StR = 0;
		waveNb=1;
		kills = 0;
		
		
		
		
	}

	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key
	 *            Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case 't':
			System.out.println("Le joueur veut planter un Tournesol...");
			lastKey = 't';
			break;
		case 'p':
			System.out.println("Le joueur veut planter un Tire-Pois...");
			lastKey = 'p';
			break;
		case 'n':
			System.out.println("Le joueur veut planter une Noix...");
			lastKey = 'n';
			break;
		case 's':
			System.out.println(Main.mapGroup.hasSun.toString());
			System.out.println(sunPower);
			System.out.println("str = " + StR);
			wave(3);
			enemies.get(3).add(new ArmoredZombie(0.5,Main.mapGroup.getDoubleCoordY(3)));
			break;
		default:
			System.out.println("Touche non prise en charge");
			break;
		}
	}
	
	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a été cliquée)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public void processMouseClick(double x, double y){   // MAKE A SWITCH  with this.lastKey
		System.out.println("La souris a été cliquée en : "+x+" - "+y);
		System.out.println("case : " + Grid.whereX(x) + Grid.whereY(y));
		if (Grid.check(x, y)) {
			Position here = Grid.where(x,y);
			Position place = Grid.getCoord(here.getX(),here.getY());
			int i = (int)here.getX();
			int j = (int)here.getY();
			if (Main.mapGroup.hasSun.get(""+i+j))
			{			
				System.out.println("sun is here");
				for (SunPickup sun:suns)
				{
					if ((sun.getX() == place.getX()) && (sun.getY() == place.getY())) {
						System.out.println("doing rm process on found sun");
						sunPower += 25;					
						sun.toRemove = true;
						Main.mapGroup.hasSun.put(""+i+j, false);
					}
				}
			}
				else {
					if (!zombiecheck(Grid.whereX(x), Grid.whereY(y))) {
				switch (lastKey) {                
				case 't':
					Sunflower.place(x, y);
					break;
				case 'p':
					PeaShooter.place(x, y);
					break;
				case 'n':
					Nut.place(x, y);
					break;
				case 'e' :
					break;
		
				default:
					System.out.println("Touche non prise en charge");
					break;
				}
					}
				}
		}
	}
	
	public void step() {
		if (sunTimer.hasFinished())
		{
			int l;
			int k;			
				l = randXint();
				k = randYint();			
			Main.mapGroup.hasSun.put("" +l+k,true);
			GameWorld.suns.add(new SunPickup(Main.mapGroup.getDoubleCoordX(l),Main.mapGroup.getDoubleCoordY(k)));
			sunTimer.restart();
		}
		
		if (waveTimer.hasFinished()) {
			wave(waveNb);
			waveNb++;
			waveTimer.restart();
		}
		
		for (Entite entite:entites) {
			entite.step();
		}
		
		for (SunPickup sun:suns){
			sun.step();
			if (sun.toRemove && !sun.counted)
			{
				StR++;
				sun.counted = true;
			}
		}
		
		for (Plant plant : plants) {
			plant.step();
			if (plant.toRemove && !plant.counted)
			{
				PtR++;
				plant.counted = true;
			}
		}
		for (Integer i : enemies.keySet()) {
			for (Enemy enemy : enemies.get(i)) {
				enemy.step();
				if (enemy.toRemove)
				{
					EtR.add(i);
				}
			}
		}
		
		for (Projectile proj :projectiles) {  // only used for out of bounts projs 
			proj.step();
			if (proj.toRemove && !proj.counted)
			{
				PrtR++;
				proj.counted = true;
			}
		}
		
		while (PrtR > 0 ) { 
			for (Projectile proj :projectiles) {
				boolean T = false;
				if (proj.toRemove == true) {
					projectiles.remove(proj);
					T = true;
				}
				if (T == true) {
					PrtR--;
					break;
				}
								
			}
		}
		
		while (!EtR.isEmpty()) { 
			for (Integer i: EtR) {
				removeZombie(i);
				kills++;
				}
			EtR.clear();							
			}
		while (PtR > 0 ) { 
			for (Plant plant: plants) {
				boolean T = false;
				if (plant.toRemove == true) {
					plants.remove(plant);
					T = true;
				}
				if (T == true) {
					PtR--;
					break;
				}
								
			}
		}
		while (StR > 0 ) { 
			for (SunPickup sun:suns) {
				boolean T = false;
				if (sun.toRemove == true) {
					suns.remove(sun);
					T = true;
				}
				if (T == true) {
					StR--;
					break;
				}
									
			}
		}
	}
	
	public void dessine() {
		Grid.draw();		
		for (Integer i : enemies.keySet()) {
			for (Enemy enemy : enemies.get(i))
				enemy.dessine();
		}		
		
		for (Plant plant : plants)
			plant.dessine();
		
		for (Projectile proj :projectiles)
			proj.dessine();
		for (Entite entite :entites)
			entite.dessine();			
		for (SunPickup sun:suns) {
			sun.dessine();
		}
	}

	public static boolean gameWon() {
		return gameWon;
	}
	
	public static boolean gameLost() {
		return gameLost;
	}
	
	public static void setGameLost() {
		gameLost = true;
	}
	
	
	private double randspawnX() {
		return Math.random()*0.2 +0.1;
	}
	/*private double randY() {
		return Main.mapGroup.coordYIntToDouble.get( (int) (Math.random()*(GRID_HEIGHT-1)+1));
	*/
	private int randXint() {   // int between 1 and the grid height
		return (int)(Math.random()*(GRID_WIDTH)+1);
	}
	
	private int randYint() {
		return (int)(Math.random()*(GRID_HEIGHT-1)+1); // int between 1 and the grid height without the ui grid
	}
	
	private void wave(int n)  {    // spawns n zombies at random boxes around the grid
		for (int i = 1 ; i <= n;i++) {
			int y = randYint();
			if (i%3 == 0)
				enemies.get(y).add(new ArmoredZombie(randspawnX()+1,Main.mapGroup.getDoubleCoordY(y)));
			else 
				enemies.get(y).add(new Zombie(randspawnX()+1,Main.mapGroup.getDoubleCoordY(y)));
		}
	}
	
	public Map<Integer,LinkedList<Enemy>> build(){
		Map<Integer,LinkedList<Enemy>> X = new HashMap<Integer,LinkedList<Enemy>>();
		for (int i = 1; i < GRID_HEIGHT; i++) 
		{
			X.put(i,new LinkedList<Enemy>());
		}		
		return X;	
	}
	
	public void addZombie(int y) {
		enemies.get(y).add(new Zombie(1,Main.mapGroup.getDoubleCoordY(y)));
	}
	
	public void removeZombie(int y) {
		enemies.get(y).removeFirst();
	}
	
	public boolean zombiecheck(int x,int y) {
		boolean b = false;
		for (Enemy enemy:GameWorld.enemies.get(y)) {
			if (enemy.getX() > Main.mapGroup.getDoubleCoordX(x)-Grid.convertX(Grid.GRID_SIZE)/2) {
				if (enemy.getX() < Main.mapGroup.getDoubleCoordX(x)+Grid.convertX(Grid.GRID_SIZE)/2) {
					b = true;
				}
			}		
		}
		return b;
	}
}

	
