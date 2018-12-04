import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
 
public class GameWorld {
	public static final double EPSI = 0.02;
	public static final int GRID_HEIGHT= 6;
	public static final int GRID_WIDTH= 9;
	
	
	// l'ensemble des entites, pour gerer (notamment) l'affichage
	static List<Entite> entites;
	static List<Plant> plants;
	static List<Enemy> enemies;
	static List<Projectile> projectiles;
	static List<Integer> toRemove;
	public char lastKey;
	public static int sunPower;
	public Timer timer;
	int EtR;    // amount of enemies to remove
	
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
		toRemove = new LinkedList<Integer>();
		lastKey = 'e';
		sunPower = 50;
		timer = new Timer(6500);
		EtR = 0;

		// on rajoute une entite de demonstration
		Entite grid = new Grid();
		grid.dessine();
		
		
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
		Position here = Grid.where(x,y);
		Position place = Grid.getCoord(here.getX(),here.getY());
		int i = (int) here.getX();
		int j = (int) here.getY();
		if (Main.mapGroup.hasSun.get(""+i+j))
		{			
			for (Entite entite:entites)
			{
				if (entite.getX()== place.getX() && entite.getY() == place.getY())
					sunPower += 25;
					entites.remove(entite);
					Main.mapGroup.hasSun.put(""+i+j, false);
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
			break;
		case 'e' :
			break;

		default:
			System.out.println("Touche non prise en charge");
			break;
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
			GameWorld.entites.add(new SunPickup(Main.mapGroup.getDoubleCoordX(l),Main.mapGroup.getDoubleCoordY(k)));
			timer.restart();
		}
		
		for (Entite entite:entites)
			entite.step();
		for (Plant plant : plants) {
			plant.step();
			if (plant.toRemove && !toRemove.contains(plants.indexOf(plant)))
				toRemove.add(plants.indexOf(plant));	
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
		if (EtR > 0 )
			System.out.println(EtR);
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
		/*for (Integer inte :toRemove)
		{
			enemies.remove(inte.intValue());
			plants.remove(inte.intValue());
			
		}
		toRemove.clear();*/
	}
	// dessine les entites du jeu
	public void dessine() {
		// Ici vous pouvez afficher du décors
		// TODO
			Entite grid = new Grid();
			grid.dessine();		
		// affiche les entites
		for (Entite entite :entites)
			entite.dessine();	
		for (Enemy enemy :enemies)
			enemy.dessine();
		for (Plant plant : plants)
			plant.dessine();
		for (Projectile proj :projectiles)
			proj.dessine();
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
