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
	public int sunPower;
	public Timer timer;
	
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
		timer = new Timer(9000);

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
	public void processMouseClick(double x, double y) {   // MAKE A SWITCH  withs this.lastKey
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
			

		default:
			System.out.println("Touche non prise en charge");
			break;
		}
	}
	// on fait bouger/agir toutes les entites
	public void step() {
		
		if (timer.hasFinished())
		{
			double l;
			double k;			
				l = randX();
				k = randY();			
			Main.mapGroup.hasSun.put("" +l+k,true);
			entites.add(new SunPickup(l,k));
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
			if (enemy.toRemove && !toRemove.contains(enemies.indexOf(enemy)))
				toRemove.add(enemies.indexOf(enemy));			
		}
		for (Projectile proj :projectiles)
			proj.step();
		for (Integer inte :toRemove)
		{
			enemies.remove(inte.intValue());
			plants.remove(inte.intValue());
			
		}
		toRemove.clear();
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
	private int randYint() {
		return (int) (Math.random()*(GRID_WIDTH-1)+1);
	}
	private int randXint() {
		return (int) (Math.random()*(GRID_HEIGHT-1)+1);
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
