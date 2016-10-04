
public class PokeTester {

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		boolean done = false;
		String pattern = "";

		Computer c = new Computer();
		
		
		
		System.out.print("Welcome to Pokemon Mind Reader");
		System.out.println("Choose Difficulty");
		System.out.println("1. Beginner");
		System.out.println("2. Advance");
		int choose = CheckInput.checkInt(1, 2);
		int computer = c.makePrediction(new Pattern(pattern));
		while (!done) {

			if (choose == 1) {
				pattern = playGame(pattern, c);
				if(pattern.length() == 4){
					c.storePattern(new Pattern(pattern));
					pattern = pattern.substring(1);
				}
			} else if (choose == 2) {
				pattern = playGame(pattern, c);
				if(pattern.length() == 8){
					c.storePattern(new Pattern(pattern));
					pattern = pattern.substring(1);
				}
			}

		}

	}

	public static String playGame(String pattern, Computer c) {
		System.out.println("Choose Pokemon");
		System.out.println("1. Fire");
		System.out.println("2. Water");
		System.out.println("3. Grass");
		int player = CheckInput.checkInt(1, 3);

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

		winner(player, c,pattern);
		System.out.println(pattern);
		
		return pattern;
	}

	public static void winner(int player,Computer c, String pattern){
	
		if(computer == 1){
			System.out.println("Computer chose fire");
		}else if(computer == 2){
			System.out.println("Computer chose water");
		}else if(computer == 3){
			System.out.println("Computer chose grass");
		}
			
		
	
		//1.fire beats 3.grass
		//3.grass beats 2.water 
		//2.water beats 1.fire
		
		
		//
		if((player == 1 && computer == 3) || (player == 3 && computer == 2) || (player == 2 && computer == 1 )){//player wins
			System.out.println("player wins");
		}else if((computer == 1 && player == 3) || (computer == 3 && player == 2 ) || (computer == 2 && player == 1 ) ){//computer wins
			System.out.println("Computer wins");
		}else if((player == 1 && computer == 1) || (player == 2 && computer == 2) || (player == 3 && computer == 3)){//ti3
			System.out.println("tie");
		}
	}
	
	
	
	

}
