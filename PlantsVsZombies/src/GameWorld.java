import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameWorld {


	// l'ensemble des entites, pour gerer (notamment) l'affichage
	private static List<Entite> entites;

	
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

		// on rajoute une entite de demonstration
		entites.add(new TrucQuiBouge(0, 0.5));
		entites.add(new Zombie(0.5,0.5));
		
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
			// TODO
			break;
		case 'p':
			System.out.println("Le joueur veut planter un Tire-Pois...");
			// TODO
			break;
		case 'n':
			System.out.println("Le joueur veut planter une Noix...");
			// TODO
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
	public void processMouseClick(double x, double y) {
		System.out.println("La souris a été cliquée en : "+x+" - "+y);
	}

	// on fait bouger/agir toutes les entites
	public void step() {
		for (Entite entite : this.entites)
			entite.step();
	}

	// dessine les entites du jeu
	public void dessine() {

		// Ici vous pouvez afficher du décors
		// TODO
		
		// affiche les entites
		for (Entite entite : entites)
			entite.dessine();
	}

	public static boolean gameWon() {
		return gameWon;
	}
	
	public static boolean gameLost() {
		return gameLost;
	}


}
