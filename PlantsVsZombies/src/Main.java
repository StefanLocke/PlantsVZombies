
public class Main {
	public static final int X_RESO = 1280;
	public static final int Y_RESO = 720;
	public static final double RATIO = 16.0/9.0;
	public static void main(String[] args) {
		

		GameWorld world = new GameWorld();
		
		// reglage de la taille de la fenetre de jeu, en pixels (nb: un écran fullHD = 1980x1050 pixels)
		StdDraw.setCanvasSize(X_RESO, Y_RESO);
		
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
