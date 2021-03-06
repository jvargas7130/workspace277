import java.awt.Point;

/**
 * This class is the attributes of what the trainer will do in his or her path
 * as pokemon trainer.
 * 
 * @author Jesus Vargas
 *
 */
public class Player extends Trainer {

	/**
	 * trainer amount of potions
	 */
	private int potions = 5;

	/**
	 * trainers amount of pokeballs
	 */
	private int pokeballs = 5;

	/**
	 * trainers amount of money
	 */
	private int money = 20;

	/*
	 * location of player
	 */
	private Point location;
	
	/**
	 * Map level
	 */
	private int level;

	/**
	 * Player constructor
	 * 
	 * @param name Trainer'sname being passed in
	 *           
	 * @param hpTrainer's
	 *            
	 */
	public Player(String name, int hp, Point start) {
		super(name, hp);
		location = start;
		level = 1;
	}
	
	/**
	 * Get level accessor
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Increase level increments level
	 */
	public void incLevel() {
		level++;
	}

	/**
	 * Location accessor
	 * 
	 * @return Point object loacation
	 */
	public Point getLocation() {
		return location;
	}

	public boolean setLocation(Point p) {
		location = p;
		return true;
	}

	/**
	 * NOrth travel method
	 * 
	 * @param m  Map object
	 *          
	 * @return c char
	 */
	public char goNorth(Map m) {
		Point p = getLocation();

		int x = (int) p.getX();
		int y = (int) p.getY();
		// boolean test = InputTest.checkInt(x,y-1);

		Point nextP = new Point(x, y - 1);

		char c = m.getCharAtLoc(nextP);

		if (c != 'k') {

			setLocation(nextP);
		}

		return c;

	}

	/**
	 * South travel method
	 * 
	 * @param m  Map object
	 *          
	 * @return c char
	 */
	public char goSouth(Map m) {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();

		Point nextP = new Point(x, y + 1);

		char c = m.getCharAtLoc(nextP);
		if (c != 'k') {

			setLocation(nextP);
		}

		return c;

	}

	/**
	 * East travel method
	 * 
	 * @param m Map object
	 *           
	 * @return c char
	 */
	public char goEast(Map m) {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();

		Point nextP = new Point(x + 1, y);

		char c = m.getCharAtLoc(nextP);
		if (c != 'k') {

			setLocation(nextP);
		}
		return c;

	}

	/**
	 * West travel method
	 * 
	 * @param m Map object
	 *           
	 * @return c char
	 */
	public char goWest(Map m) {
		Point p = getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();

		Point nextP = new Point(x - 1, y);

		char c = m.getCharAtLoc(nextP);
		if (c != 'k') {

			setLocation(nextP);
		}
		return c;

	}

	/**
	 * Trainer uses potion to heal pokemon
	 */
	public void usePotion() {

		if (getNumPotionsLeft() > 0) {
			potions -= 1;
		} else {
			potions = 0;
		}

	}

	/**
	 * Number of potions that are left
	 * 
	 * @return number of potions
	 */
	public int getNumPotionsLeft() {
		return potions;

	}

	/**
	 * Trainer uses pokeball
	 */
	public void usePokeball() {

		if (getNumPokeballsLeft() > 0) {
			pokeballs -= 1;
		} else {
			pokeballs = 0;
		}
	}

	/**
	 * gets the number of pokeballs
	 * 
	 * @return number of pokeballs
	 */
	public int getNumPokeballsLeft() {
		return pokeballs;

	}

	/**
	 * BuyPotion adds more potions to trainer inventory
	 */
	public void buyPotion() {

		potions += 2;
	}

	/**
	 *buyPokeball adds more pokeballs to trainer inventory
	 */
	public void buyPokeball() {
		pokeballs += 3;
	}

	/**
	 * Decrease the amount of money trainer has
	 * 
	 * @param price  amount of money being decreased
	 *          
	 */
	public void spendMoney(int price) {
		money -= price;
	}
	
	/**
	 * GainMoney accessor
	 * @param price is passed in
	 */
	public void gainMoney(int price) {
		money += price;
	}

	/**
	 * Gets the amount of money left
	 * 
	 * @return amount of money
	 */
	public int getMoneyLeft() {
		return money;
	}
	
	/**
	 * Attack speech methdod for player
	 */
	@Override
	void attackSpeach() {

		System.out.println("I choose you " + getCurrentPokemon().getName());

	}
	
	/**
	 * WinSpeech for player
	 */
	@Override
	void winSpeach() {
		if (getCurrentPokemon().getType() == 0) {
			System.out.println("We did it! " + getCurrentPokemon().getName());
		} else if (getCurrentPokemon().getType() == 1) {
			System.out.println("I AM THE VERY BEST, LIKE NO ONE EVER WAS!!");
		} else if (getCurrentPokemon().getType() == 2) {
			System.out.println("Yay!!");
		} else if (getCurrentPokemon().getType() == 3) {
			System.out.println("I won today but there is still tomorrow");
		}

	}
	//Loose Speech for player
	@Override
	void lossSpeach() {
		if (getCurrentPokemon().getType() == 0) {
			System.out.println(
					"Its ok " + getCurrentPokemon().getName() + " Everybody makes a wrong turn once in a while!! :(");
		} else if (getCurrentPokemon().getType() == 1) {
			System.out.println("We cant give up. Next Time!");
		} else if (getCurrentPokemon().getType() == 2) {
			System.out.println("I need more training!!");
		} else if (getCurrentPokemon().getType() == 3) {
			System.out.println("I will prove to the world that i am The greatest Pokemon Trainer!");
		}

	}

	/**
	 * Player chooses style of fight
	 * @return moves
	 */
	@Override
	int chooseStyle() {
		System.out.println("Chosse Attack Type");
		System.out.println("1. Basic");
		System.out.println("2. Special");
		int moves = CheckInput.checkInt(1, 2);
		return moves;
	}
	/**
	 * Choose move choose move
	 * @return move
	 */
	@Override
	int chooseMove(int style) {
		int move = 0;

		if (style == 1) {// basic attack
			getCurrentPokemon().displayBasicMenu();
			move = CheckInput.checkInt(1, 3);

		} else if (style == 2) {// special

			getCurrentPokemon().displaySpecialMenu();
			move = CheckInput.checkInt(1, 3);
		}

		return move;
	}

}
