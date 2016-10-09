import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PokeTester {

	public static void main(String[] args) {
		menu();
	}

	public static Computer readFile(int game){
		boolean file = false;
		Computer c = null;
		int choose = 0;
		int savedGame = 0;


		File B = new File("Beginner.Dat");
		File V = new File("Veteran.Dat");
		File F = null;
		
		if (game == 1) {
			if (B.exists()) {
				System.out.println("Would you like to continue beginner game?");
				System.out.println("1.Yes");
				System.out.println("2.No");
				 savedGame= CheckInput.checkInt(1, 2);
				 if(savedGame == 1){
					 file = true;
					 	F = B;
				 }else if(savedGame == 2){
					 
				 }
				
			
			} else {
				System.out.println("New Game");
			}
		} else if (game == 2) {
			
				if (V.exists()) {
					System.out.println("Would you like to continue Veteran game?");
					System.out.println("1.Yes");
					System.out.println("2.No");
					 savedGame= CheckInput.checkInt(1, 2);
					 if(savedGame == 1){
						 file = true;
						 	F = V;
					 }else if(savedGame == 2){
						 
					 }
				
				} else {
					System.out.println("New Game");
				}
			
		}

		if (file) {

	
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(F));
					c = (Computer) in.readObject();
					in.close();
				} catch (IOException e) {
					System.out.println("Error processing file.");
				} catch (ClassNotFoundException e) {
					System.out.println("Could not find class.");
				}

	
		} else {// new game
			
			 c = new Computer();
			
		}

		return c;

	}
	
	


	public static void menu() {
		boolean done = false;
	
		int pWins = 0;
		int cWins = 0;


		int computer = 0;
		String write = "";
		// reads in saved file
		System.out.print("Welcome to Pokemon Mind Reader");
		System.out.println("Choose Difficulty");
		System.out.println("1. Beginner");
		System.out.println("2. Veteran");

		int choose = CheckInput.checkInt(1, 2);
		Computer c = readFile(choose);
		
		
		String pattern = "";
		
		try {
			pattern = c.getPattern();

		} catch (NullPointerException e) {
			pattern = "" ;
		
		}

	

		while (!done) {// beginner

			if (choose == 1) {
				System.out.println("beginner");
				
				write = "Beginner.Dat";
				if (pattern.length() == 5) {

					pattern = pattern.substring(1);
					computer = c.makePrediction(pattern);
				} else {
					int randChoice = (int) (Math.random() * 3) + 1;
					computer = randChoice;
				}

			} else if (choose == 2) {// veteran
				write = "Veteran.Dat";
				if (pattern.length() == 9) {

					pattern = pattern.substring(1);
					c.storePattern(new Pattern(pattern));

				} else {
					int randChoice = (int) (Math.random() * 3) + 1;
					computer = randChoice;
				}

			}

			// repeating menu
			computer = c.makePrediction(pattern);
			System.out.println("Choose Pokemon");
			System.out.println("1. Fire");
			System.out.println("2. Water");
			System.out.println("3. Grass");
			System.out.println("4. quit");

			// player chooses
			int player = CheckInput.checkInt(1, 4);
			if (player == 4) {
				writeGame(c, write);
				System.out.println("Saving..........");
				done = true;
			} else {
				pattern = playGame(player, pattern, computer);// plays game and
																// returens
																// pattern
				int win = winner(player, c, pattern, computer);// chooses winner
																// and returns
																// value

				if (win == 1) {// player wins
					pWins++;
				} else if (win == -1) {// computer wins
					cWins++;
				}
				showPercentage(cWins, pWins);// shows percentage

				writeGame(c, write);// save game every round
			}

		}
	}

	public static String playGame(int player, String pattern, int computer) {

		if (player == 1) {
			System.out.println("Player chose fire");
			pattern += "f";
		} else if (player == 2) {
			System.out.println("Player chose water");
			pattern += "w";
		} else if (player == 3) {
			System.out.println("Player chose grass");
			pattern += "g";
		}

		if (computer == 1) {
			System.out.println("Computer chose fire");
		} else if (computer == 2) {
			System.out.println("Computer chose water");
		} else if (computer == 3) {
			System.out.println("Computer chose grass");
		}

		System.out.println();

		return pattern;
	}

	public static int winner(int player, Computer c, String pattern, int computer) {

		int win = 0;
		// 1.fire beats 3.grass
		// 3.grass beats 2.water
		// 2.water beats 1.fire

		if ((player == 1 && computer == 3) || (player == 3 && computer == 2) || (player == 2 && computer == 1)) {// player
																													// wins
			System.out.println("player wins");
			win = 1;
		} else if ((computer == 1 && player == 3) || (computer == 3 && player == 2) || (computer == 2 && player == 1)) {// computer
																														// wins
			System.out.println("Computer wins");
			win = -1;
		} else if ((player == 1 && computer == 1) || (player == 2 && computer == 2) || (player == 3 && computer == 3)) {// ti3
			System.out.println("tie");
		}
		System.out.println();
		return win;
	}

	public static void showPercentage(int computer, int player) {
		int total = computer + player;
		System.out.println("total " + total);
		double percentage = 0.0;

		System.out.println("Player   score:" + player);
		System.out.println("Computer score:" + computer);

		if (total == 0) {
			System.out.println("Percentage of computer: 0 %");
		} else {

			percentage = ((double) computer) / total;
			System.out.println("Percentage of computer:" + percentage * 100 + "%");

		}

		System.out.println();

	}

	public static void writeGame(Computer c, String f) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(c);
			out.close();
		} catch (IOException e) {
			System.out.println("Error processing file.");
		}
	}

}
