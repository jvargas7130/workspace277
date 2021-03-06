import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main creates a trainer object. The default name is Ash Ketchum. The
 * program allows the user to choose a starting pok�mon (Charmander, Squirtle,
 * Bulbasaur, Pikachu), and add it to your player�s Arraylist of pok�mon.
 * Display a repeating menu of game options. Encounter random events in the
 * players travels (ex. battles with random trainers or Team Rocket, fights with
 * wild pok�mon, conversations with random people, going into town to shop or
 * heal, or even nothing at all).
 * 
 * @author jesus vargas
 *
 */
public class PokemonMain {

	public static void main(String[] args) {

		menu();
	}

	/**
	 * Read method reads in player file
	 * 
	 * @return player Player object
	 */
	public static Player readPlayer() {
		Player player = null;
		System.out.println("Welcome to Pokemon Game");
		File f = new File("Pokemon.dat");
		if (f.exists()) {
			System.out.println(" Would you like to continue your quest or start new game");
			System.out.println("1. yes");
			System.out.println("2. No");

			int game = CheckInput.checkInt(1, 2);

			if (game == 1) { // continue
				try {
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
					player = (Player) in.readObject();
					in.close();
				} catch (IOException e) {
					System.out.println("Error processing file.");
				} catch (ClassNotFoundException e) {
					System.out.println("Could not find class.");
				}

			} else if (game == 2) {// new game

				Map m = new Map();
				Point pt = m.findStartLocation();
				player = new Player("Ash Ketchum", 30, pt);

				System.out.println("You are a brand new trainer traveling the world in search"
						+ " of battles to win and pokemon to capture!");
				System.out.println("1. Charmander");
				System.out.println("2. Squirtle");
				System.out.println("3. Bulbasaur");
				System.out.println("4. Pikachu");

				int trainerChoice = CheckInput.checkInt(1, 4); // bound choice
				player.addPokemon(PokemonMaker.makeStartPokemon(trainerChoice)); // trainer
				// chooses
				// pokemon

				System.out.print(player.getName() + " ...You set off on your adventure with your buddy ");

				player.displayCurrentPokemon(); // displays current pokemon
				System.out.println();
			}
		} else {// new game

			Map m = new Map();
			Point pt = m.findStartLocation();
			player = new Player("Ash Ketchum", 30, pt);

			System.out.println("You are a brand new trainer traveling the world in search"
					+ " of battles to win and pokemon to capture!");
			System.out.println("1. Charmander");
			System.out.println("2. Squirtle");
			System.out.println("3. Bulbasaur");
			System.out.println("4. Pikachu");

			int trainerChoice = CheckInput.checkInt(1, 4); // bound choice
			player.addPokemon(PokemonMaker.makeStartPokemon(trainerChoice)); // trainer
			// chooses
			// pokemon

			System.out.print(player.getName() + " ...You set off on your adventure with your buddy ");

			player.displayCurrentPokemon(); // displays current pokemon
			System.out.println();
		}

		return player;
	}

	/**
	 * Method where trainer begins choosing pokemon and sets of to his or her
	 * adventure.
	 * 
	 */
	public static void menu() {
		Scanner in = new Scanner(System.in);

		// keeps track of what map you are in

		// previuos experience
		int exp = 30;

		// trainer choice
		int trainerChoice = 0;

		// ends or contines game
		boolean done = false;

		// get char to reveal place
		char c = ' ';

		// checks for saved files
		Player p = readPlayer();

		// map object
		Map m = new Map();
		System.out.println(p.getLevel());
		// generates the map area
		m.generateArea(p.getLevel());

		// begins start location
		Point pt = m.findStartLocation();
		p.setLocation(pt);

		// TestPokemon t = new TestPokemon();

		// t.main(null);

		// Player object

		// Continues pokemon adventure untill user is done
		while (!done) {
			Point location = p.getLocation();
			System.out.println("What would you like to do? ");
			System.out.println("1. Travel");
			System.out.println("2. Switch Pokemon");
			System.out.println("3. Heal Current Pokemon ");
			System.out.println("4. View Stats");
			System.out.println("5. Quit Game");

			// choice for first menu
			trainerChoice = CheckInput.checkInt(1, 5);

			if (trainerChoice == 1) {// travel
				System.out.println("Where would you like to go?");

				// Point is passed int to be used in order to display map with
				// player
				m.displayMap(location);

				System.out.println("Choose a Direction");
				System.out.println("1. North");
				System.out.println("2. South");
				System.out.println("3. East");
				System.out.println("4. West");

				// choice for traveling thorugh map
				trainerChoice = CheckInput.checkInt(1, 4);

				// picks a position to travel to and reveals place
				if (trainerChoice == 1) {
					c = p.goNorth(m);
				} else if (trainerChoice == 2) {
					c = p.goSouth(m);
				} else if (trainerChoice == 3) {
					c = p.goEast(m);
				} else if (trainerChoice == 4) {
					c = p.goWest(m);
				}
				// int encounter = (int) (Math.random() * 6) + 1;

				// test findopponet
				// int encounter = 4;

				if (c == 'S') {// start

				} else if (c == 'f') {// finish

					if (p.getLevel() == 1) {

						System.out.println("You traveled to Area 2 ");
					} else if (p.getLevel() == 2) {

						System.out.println("You traveled to Area 3 ");

					} else if (p.getLevel() == 3) {

						System.out.println("You beat the game.Thank you for playing");
						done = true;
						break;
					}

					p.incLevel();
					writeGame(p);
					m.generateArea(p.getLevel());

				} else if (c == 'n') {// found nothing
					System.out.println("You encountered nothing and keep following the path..."); // keep
					// foundSomthing(p);
				} else if (c == 'o') {// the player came across an opponent.

					// int rand = (int)(Math.random()*2) +1;
					int rand = 1;
					if (rand == 1) {
						findOpponent(p, m);
					} else if (rand == 2) {
						findAngryTrainer(p);
					}

				} else if (c == 'w') {// wildpokemon
					findWildPokemon(p, m);

				} else if (c == 'c') {// city
					findTown(p);
				} else if (c == 'k') {
					System.out.println("You cant go that way");
				}

			} else if (trainerChoice == 2) {// switch pokemon

				System.out.println("Choose Pokemon");
				p.listPokemon();

				int pokeChoice = CheckInput.checkInt();

				p.setCurrentPokemon(pokeChoice);

			} else if (trainerChoice == 3) {// heal current pokemon
				if (p.getNumPotionsLeft() > 0) {
					p.usePotion();
					p.getCurrentPokemon().gainHp(15);

				} else {
					System.out.println("You have no more potions. \n");

				}

			} else if (trainerChoice == 4) {// View Stats
				System.out.println("Trainer name: " + p.getName());
				System.out.println("Current Pokemon Experience: " + p.getCurrentPokemon().getExp());
				System.out.println("Money: " + p.getMoneyLeft());
				System.out.println("Trainer HP: " + p.getHp());
				System.out.println("Potions: " + p.getNumPotionsLeft());
				System.out.println("Pokeballs: " + p.getNumPokeballsLeft());
				System.out.println();

				p.listPokemon();

			} else if (trainerChoice == 5) {// Quit Game
				System.out.println("You quit the game. Saving Game......");
				writeGame(p);
				done = true;
			}

			// gains level
			// gains hp
			// icreases hits
			if (p.getCurrentPokemon().getExp() == exp) {
				p.displayCurrentPokemon();
				System.out.println();
				System.out.print(" HAS LEVELED UP!!");
				System.out.println();
				// gain level
				p.getCurrentPokemon().setLevel();
				// previous exp
				exp = exp + 20;
				// gain MaxHp
				p.incMaxHp();
				// icrease hits

			}

			// checks to see if players pokemon is healedp.
			if (done == false) {
				done = curePokemon(p);
			}

			// checks to see if player hp is 0
			if (p.getHp() == 0) {
				done = true;
				System.out.println("Player fainted. GAME OVER!");
			}

		}

	}

	/**
	 * Trainer finds a town that has pokemon center and pokemon mart
	 * 
	 * @param p
	 *            Player object is passed in
	 */
	public static void findTown(Player p) {

		boolean done = false;

		System.out.println("You have found a towm. What would you like to do here?");

		do {
			System.out.println("1. Go to the Pokemon Center");
			System.out.println("2. Go to the Pokemon Mart");
			System.out.println("3.Leave");

			int townChoice = 0;

			townChoice = CheckInput.checkInt(1, 3);

			if (townChoice == 1) {// Pokmon center wher e pokemon gets healed
				System.out.println("Welcome to the Poke Center. What would you like to do here?");
				System.out.println("Heal your pokemon free of charge?");
				System.out.println("1. Yes ");
				System.out.println("2. No");
				townChoice = CheckInput.checkInt(1, 2);

				if (townChoice == 1) {
					p.healAllPokemon();
				}

			} else if (townChoice == 2) { // pokemon mart
				boolean done2 = false;
				System.out.println("Welcome to the Poke Mart. What would you like to do here?");
				do {

					System.out.println("1. Buy 3 Pokeballs ($10)");
					System.out.println("2. Buy 2 Potions ($5)");
					System.out.println("3. Leave");

					townChoice = CheckInput.checkInt(1, 3);
					if (townChoice == 1) {
						if (p.getMoneyLeft() >= 10) {
							p.buyPokeball();
							p.spendMoney(10);
							System.out.println(
									"You bought 3 Pokeballs" + " You now have " + p.getMoneyLeft() + "$ left ");
						} else {
							System.out.println("You dont have enough money");
						}

					} else if (townChoice == 2) {
						if (p.getMoneyLeft() >= 5) {
							p.buyPotion();
							p.spendMoney(5);
							System.out
									.println("You bought 3 Potions" + " You now have " + p.getMoneyLeft() + "$ left ");
						} else {
							System.out.println("You dont have enough money");
						}

					} else if (townChoice == 3) {
						done2 = true;
						System.out.println("Thank you for stopping by. Have a great adventure");
					}

					// go in here if you havent left
					if (done2 == false) {
						System.out.println("Is there anything else you would like to purchase");
					}

				} while (!done2);

			} else if (townChoice == 3) { // leave
				done = true;
				System.out.println("Thank you for stopping by. Have a great adventure");
			}
		} while (!done);

	}

	/**
	 * Cure Pokemon method forces user to cure pokemon or swtich to a pokemon
	 * wtih hp before proceeding
	 * 
	 * @param p Player object p
	 *           
	 * @return Boolean done
	 */
	public static boolean curePokemon(Player p) {

		boolean done = false;

		// Choose potion or switch pokemon
		while (p.getCurrentPokemon().getHp() == 0) {
			System.out.println("You must cure current pokemon before porceeding or switch pokemon before proceeding");
			System.out.println("1. Use Potion? ");
			System.out.println("2. Switch Pokemon ");
			int trainerChoice = CheckInput.checkInt(1, 2);

			if (trainerChoice == 1) {
				// lose potion
				p.usePotion();
				// pokemon gains hp
				p.getCurrentPokemon().gainHp(15);
				if (p.getNumPotionsLeft() == 0) {
					int next = 1;
					System.out.println("You do not have enough potions");

					do {
						p.setCurrentPokemon(next + 1);
						if (p.getCurrentPokemon().getHp() > 0) {
							break;
						}
					} while (p.getCurrentPokemon().getHp() == 0);
					System.out.println("All your pokemon are hurt:GAME OVER");
					done = true;

				}
				// Choose pokemon
			} else if (trainerChoice == 2) {
				System.out.println("Choose Pokemon");
				p.listPokemon();

				int pokeChoice = CheckInput.checkInt();

				p.setCurrentPokemon(pokeChoice);
			}

		}

		return done;
	}

	/**
	 * Find Angry trainer uses to encounter many angry trainers
	 * 
	 * @param p Player object is passed in
	 *           
	 */
	public static void findAngryTrainer(Player p) {
		int encounter = (int) (Math.random() * 3) + 1;

		// Misty
		if (encounter == 1) {
			System.out.println("You run across Misty.");
			System.out.println("Where's my bike twerp?!");
			System.out.println("Misty whacks you in the head for 3 damage");
			PokemonBattles.angryTrainer(p);
			// Ekans
		} else if (encounter == 2) {
			System.out.println("You run across a wild Ekans .");
			System.out.println("ZZZZZZZZzzzzzz!");
			System.out.println("Snake byte causes 2 hitdamage!");
			PokemonBattles.angryPokemon(p);
			// Dragonite
		} else if (encounter == 3) {
			System.out.println("A dragonite wakes up angry and whips his tale at Ash, Ash takes 2 hit damage ");
			PokemonBattles.angryPokemon(p);
			// May
		} else if (encounter == 4) {
			System.out.println("You run across May.");
			System.out.println("You steeped on her pokemon");
			System.out.println("Hey watch out twerp!");
			System.out.println("May slams you to the ground for 3 hit damage");
			PokemonBattles.angryTrainer(p);
		}

	}

	/*
	 * Find opponent calls opponent maker to choose an opponent and begin battle
	 * if user does not choose to run
	 */
	public static void findOpponent(Player p, Map m) {

		int trainerChoice = 0;
		OpponentMaker mo = new OpponentMaker();

		Opponent opponent = mo.MakeRandomOpponent();
		// Creates and stores pokemon for random trainer passes in type to

		// makes type
	
		opponent.listPokemon();

		System.out.println("You came across a " + opponent.getName());
		opponent.attackSpeach();
		System.out.println("What would you like to do?");
		System.out.println("1. Run Away");
		System.out.println("2. Fight");

		trainerChoice = CheckInput.checkInt(1, 2);
		if (trainerChoice == 1) {
			int escape = (int) (Math.random() * 20) + 1;
			if (escape == 1 || escape == 15 || escape == 20) {
				System.out.println("You didnt escape so now you must fight");
				trainerChoice = 2;

			} else {

				System.out.println("You ran away");
				System.out.println("That was a close one");

			}
		}

		if (trainerChoice == 2) {// fight

			
			opponent.listPokemon();
			int prevPok = p.getCurrentPokemon().getHp();
			p.attackSpeach();
			System.out.println();
			boolean pokeFaint = false;
			do {

				// opponent battle
				PokemonBattles.opponentBattle(p, opponent);

				// checks stats for both pokemons
				p.getCurrentPokemon().displayPokemon();
				opponent.getCurrentPokemon().displayPokemon();
				System.out.println();

				if (opponent.getName().equals("Team Rocket") && opponent.getCurrentPokemon().getHp() == 0) {

					opponent.getNextCurPokemon();

					// when team rockets loose first pokemon
					if (opponent.getCurrentPokemon().getHp() != 0) {
						System.out.println("NOW WE CHOOSE OUR NEXT POKEMON!!!!!!!!!!!");
						System.out.println("");
					}

				}
				// Attacker poke faints
				if (p.getCurrentPokemon().getHp() == 0) {
					p.displayCurrentPokemon();
					System.out.println(" has fainted");
					opponent.winSpeach();// win speech
					p.lossSpeach();// loss speech
					pokeFaint = true;
				}
				// defender poke faints
				if (opponent.getCurrentPokemon().getHp() == 0) {
					Point point = p.getLocation();// current location of player
					m.removeOppAtLoc(point);// changes the loation to n since
											// player won
					opponent.displayCurrentPokemon();
					System.out.println(" has fainted");
					p.winSpeach();// win speech
					opponent.lossSpeach();// loss speech
					System.out.println("You gained experience and gained money");
					p.gainMoney(5);
					;
					p.getCurrentPokemon().gainExp(10);
					pokeFaint = true;
				}

			} while (!pokeFaint);

		}

	}

	/**
	 * Find WIld Pokemon calls make wildPokemon in pokemon maker to choose a
	 * wildpokemom battle if the user does not decide to run
	 * 
	 * @param p Player Object is passed in
	 *  @param m Passes in map Object          
	 */
	public static void findWildPokemon(Player p, Map m) {
		int trainerChoice = 0;

		Pokemon wildPokemon = PokemonMaker.makeWildPokemon();

		System.out.println("You came across a wild " + wildPokemon.getName());
		System.out.println("What would you like to do?");
		System.out.println("1. Run Away");
		System.out.println("2. Fight");

		trainerChoice = CheckInput.checkInt(1, 2);
		if (trainerChoice == 1) {
			int escape = (int) (Math.random() * 20) + 1;
			if (escape == 1 || escape == 15 || escape == 20) {
				System.out.println("You didnt escape so now you must fight");
				trainerChoice = 2;

			} else {

				System.out.println("You run away");
				System.out.println("That was a close one");

			}
		}

		if (trainerChoice == 2) {// fight
			boolean pokeFaint = false;
			boolean caughtPoke = false;
			System.out.println("I choose you ");
			p.displayCurrentPokemon();

			do {

				System.out.println();

				PokemonBattles.wildPokeBattle(p, wildPokemon);
				// when pokemon is weak, trainer has a chance to catch um
				if (wildPokemon.getHp() <= 19 && wildPokemon.getHp() != 0 && p.getCurrentPokemon().getHp() != 0) {

					System.out.println(wildPokemon.getName() + "is weak. would you like to try and catch um ");
					System.out.println("1. Yes");
					System.out.println("2. No");

					// Chance to catch pokemon
					int cPoke = CheckInput.checkInt(1, 2);
					if (cPoke == 1) {
						int caught = (int) (Math.random() * 100) + 1;
						if (caught >= 40) {
							pokeFaint = true;
							caughtPoke = true;
							Point point = p.getLocation();// current location of
															// player
							m.removeOppAtLoc(point);// changes the loation to n
													// since player won
							p.winSpeach();// loss speec
							System.out.println("You gained experience and gained money");
							p.getCurrentPokemon().gainExp(10);
							p.gainMoney(5);
						} else {
							System.out.println(" You did not catch pokemon");

						}

					}

				}
				// player pokemon fainted
				if (p.getCurrentPokemon().getHp() == 0) {
					System.out.println(p.getCurrentPokemon().getName() + " has fainted");
					p.lossSpeach();// loss speech
					pokeFaint = true;
				}

				if (wildPokemon.getHp() == 0) {

					wildPokemon.getName();
					if (wildPokemon.getHp() == 0) {
						System.out.println(" has fainted");
					}
					Point point = p.getLocation();// current location of player
					m.removeOppAtLoc(point);// changes the loation to n since
											// player won
					p.winSpeach();// loss speec
					System.out.println("You gained experience and gained money");
					p.gainMoney(5);

					p.getCurrentPokemon().gainExp(10);
					pokeFaint = true;
				}

				// poke stats
				p.getCurrentPokemon().displayPokemon();
				wildPokemon.displayPokemon();

			} while (!pokeFaint);

			// add pokemon to array
			if (p.getNumPokeballsLeft() > 0) {
				System.out.println(" You cought a pokemon");
				if (wildPokemon.getHp() == 0 || caughtPoke == true) {
					p.addPokemon(wildPokemon);
					p.usePokeball();
				}

			} else {
				System.out.println(" Sorry you ran out of pokeballs");
			}

		}

	}


	
	/**
	 * Write game method saves pokemon game
	 * @param p	Player object is passed in
	 */
	public static void writeGame(Player p) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Pokemon.Dat"));
			out.writeObject(p);
			out.close();
		} catch (IOException e) {
			System.out.println("Error processing file.");
		}
	}

}
