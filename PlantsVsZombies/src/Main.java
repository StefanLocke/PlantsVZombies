import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<Position,Position> cordtoC = new HashMap<Position,Position>();
		Map<Position,Position> cordtoc = new HashMap<Position,Position>();
		final Map<String,Integer> damage = new HashMap<String,Integer>();
		for (Entite enemy : GameWorld.entites) {
			if (enemy instanceof Enemy) {
				damage.put(enemy.getClass().getSimpleName(),30) ;
			}
		}
		
		
 
		GameWorld world = new GameWorld();
		
		// reglage de la taille de la fenetre de jeu, en pixels (nb: un écran fullHD = 1980x1050 pixels)
		StdDraw.setCanvasSize(1000, 1000);
		
		// permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();
		
		
		// la boucle principale de notre jeu
		while (!GameWorld.gameLost() && !GameWorld.gameWon()) {
			// Efface la fenetre graphique
			StdDraw.clear();
			
			
			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}
			
			if (StdDraw.isMousePressed()) {
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}

			
			world.step();
			
			
			// dessine la carte
			world.dessine();
			
			
			// Montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);
			
			
		}
		
		if (GameWorld.gameWon()) System.out.println("Game won !");
		if (GameWorld.gameLost()) System.out.println("Game lost...");

	}

}
