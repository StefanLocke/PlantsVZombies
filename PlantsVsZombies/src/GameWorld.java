import java.util.LinkedList;
import java.util.List;
 
public class GameWorld  {
	public static final double EPSI = PeaProj.MOVE_X + Zombie.MOVE_X;
	public static final int GRID_HEIGHT= 6;
	public static final int GRID_WIDTH= 9;
	
	
	// l'ensemble des entites, pour gerer (notamment) l'affichage
	static List<Entite> entites;
	static List<Plant> plants;
	static List<Enemy> enemies;
	static List<Projectile> projectiles;
	static LinkedList<SunPickup> suns;
	public char lastKey;
	public static int sunPower;
	public Timer timer;
	int EtR;    // amount of enemies to remove
	int PtR;	 // amount of PLANTS to remove
	int StR;	 // amount of SUNS to remove
	Entite grid;
	
	//Pour savoir si la partie est gagnee ou pas
	private static boolean gameWon;
	// Idem pour savoir si le jeu est perdu (si le jeu n'est ni gagne ni perdu, il est en cours)
	private static boolean gameLost;

	// constructeur, il faut initialiser notre monde virtuel
	public GameWorld() {
		
		
		gameWon=false;
		gameLost=false;
		
		// on cree les collections
		entites = new LinkedList<Entite>();
		plants = new LinkedList<Plant>();
		enemies = new LinkedList<Enemy>();
		projectiles = new LinkedList<Projectile>();
		suns = new LinkedList<SunPickup>();
		lastKey = 'e';
		sunPower = 50;
		timer = new Timer(6000);
		EtR = 0;
		PtR = 0;
		StR = 0;

		// on rajoute une entite de demonstration
		grid = new Grid();
		
		
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
	public void processMouseClick(double x, double y){   // MAKE A SWITCH  withs this.lastKey
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
			switch (this.lastKey) {                
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
	// on fait bouger/agir toutes les entites
	public void step() {
		
		if (timer.hasFinished())
		{
			int l;
			int k;			
				l = randXint();
				k = randYint();			
			Main.mapGroup.hasSun.put("" +l+k,true);
			GameWorld.suns.add(new SunPickup(Main.mapGroup.getDoubleCoordX(l),Main.mapGroup.getDoubleCoordY(k)));
			timer.restart();
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
		for (Enemy enemy :enemies) {
			enemy.step();
			if (enemy.toRemove && !enemy.counted)
			{
				EtR++;
				enemy.counted = true;
			}
		}
		
		for (Projectile proj :projectiles)
			proj.step();
		
		while (EtR > 0 ) { 
			for (Enemy enemy: enemies) {
				boolean T = false;
				if (enemy.toRemove == true) {
					enemies.remove(enemy);
					T = true;
				}
				if (T == true) {
					EtR--;
					break;
				}
								
			}
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
		/*for (Integer inte :toRemove)
		{
			enemies.remove(inte.intValue());
			plants.remove(inte.intValue());
			
		}
		toRemove.clear();*/
	// dessine les entites du jeu
	public void dessine() {
		// Ici vous pouvez afficher du décors
		// TODO
			this.grid.dessine();		
		// affiche les entites
		
		for (Enemy enemy :enemies)
			enemy.dessine();
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
	
	private double randX() {
		return Main.mapGroup.coordXIntToDouble.get( (int) (Math.random()*(GRID_WIDTH)+1));
	}
	private double randY() {
		return Main.mapGroup.coordYIntToDouble.get( (int) (Math.random()*(GRID_HEIGHT-1)+1));
	}
	private int randXint() {
		return (int)(Math.random()*(GRID_WIDTH)+1);
	}
	private int randYint() {
		return (int)(Math.random()*(GRID_HEIGHT-1)+1);
	}
	/*public void spawnPlant(double x , double y, char lastKey) {   // x and y mouse clock position
		switch (lastKey) {                
		case 't':
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

		default:
			System.out.println("Touche non prise en charge");
			break;
		}
	}*/
	
	
}
