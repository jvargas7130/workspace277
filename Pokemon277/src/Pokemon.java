/**
 * Pokemon class creates a pokemon with special attributes *
 * 
 * @author Jesus Vargas
 *
 */
public abstract class Pokemon extends Entity {

	/**
	 * Pokemon level variable
	 */
	private int level = 2;

	/**
	 * pokemon exp variable
	 */
	private int exp = 10;

	/**
	 * Next Level Exp variable
	 */
	private int nextLevelExp;

	/**
	 * Pokemon constructor
	 * 
	 * @param name
	 *            String pokemonn name
	 * @param level
	 *            Integer pokemon level
	 */
	public Pokemon(String name, int level) {
		super(name, 68);
		this.level = level;
	}

	/**
	 * Get type abstract method used by pokemon classes
	 * 
	 * @return integer type
	 */
	abstract int getType();

	/**
	 * Special fight abastract method used by trainers and wildpokemon to choose
	 * style and move
	 * 
	 * @param move integer move
	 *           
	 * 
	 * @return integer hit
	 */
	abstract int specialFight(int move);

	/**
	 * Display Specail Menu displays menu
	 */
	abstract void displaySpecialMenu();

	/**
	 * Get level method gets the level of pokemon
	 * 
	 * @return integer level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Set level method icreases level by 1
	 */
	public void setLevel() {
		this.level = level + 1;
	}

	/**
	 * Get exp level gets the exp of pokemon
	 * 
	 * @return integer experience
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * Display Pokemon method displays the pokemon ,level, hp
	 */
	public void displayPokemon() {

		System.out.print(getName() + " Level: " + getLevel() + " HP: " + getHp() + "\n");
	}

	/**
	 * Gain exp increases hp(health)
	 * 
	 * @param exp
	 *            Integer experience
	 * @return Integer experience
	 */
	public int gainExp(int exp) {
		this.exp += exp;
		return exp;
	}

	/**
	 * Display Basic Menu displays the basic fight menu
	 */
	public void displayBasicMenu() {
		System.out.println("1. Slam");
		System.out.println("2. Tackle");
		System.out.println("3. Mega Punch");
	}

	/**
	 * Basic fight methdod chooses fight and returns hit
	 * 
	 * @param move
	 *            String move
	 * @return Integer hit
	 */
	public int basicFight(int move) {
		int hit = 0;

		if (move == 1) {
			hit = slam();
		} else if (move == 2) {
			hit = tackle();
		} else if (move == 3) {
			hit = megaPunch();
		}

		return hit;
	}

	/**
	 * Fight method choses move and style
	 * 
	 * @param style Integer Syle
	 *           
	 * @param move  Integer move
	 *          
	 * @return move Integer move
	 */
	public int fight(int style, int move) {

		if (style == 1) {
			move = basicFight(move);
		} else if (style == 2) {
			move = specialFight(move);
		}
		return move;

	}

	/**
	 * Slam method randomly picks damamge hit and returns it.
	 * 
	 * @return Integer hit
	 */
	public int slam() {
		int hit = (int) (Math.random() * 5) + 1;

		if (getName().equals("Bulbasaur")) {
			System.out.println("Bulbasaur runs up behind and Slams into him! SLAM! ");

		} else if (getName().equals("Charmander")) {
			System.out.println("Slamed to the ground by Charmander which devastates opponent ");
		} else if (getName().equals("Oddish")) {
			System.out.println("Oddish dashes and quckly grabs opponent and slams um");
		} else if (getName().equals("Pikachu")) {
			System.out.println("Pikachu uses his head to slam into opponent ");
		} else if (getName().equals("Ponyta")) {
			System.out.println("Ponyta slams opponent the Wall!! ");
		} else if (getName().equals("Squirtle")) {
			System.out.println("Squirtle jumps in the air and slams opponent to the floor");

		} else if (getName().equals("Staryu")) {
			System.out.println("Staryu dashes left and delivers a slam  ");

		} else if (getName().equals("Zapdos")) {
			System.out.println("Zapdos gets angry and slams opponent to the ground!! ");
		}

		return hit;
	}

	/**
	 * Tackle method randomly picks damamge hit and returns it.
	 * 
	 * @return Integer hit
	 */
	public int tackle() {
		int hit = (int) (Math.random() * 5) + 1;
		if (getName().equals("Bulbasaur")) {
			System.out.println("opponent is tackled by surprised bu Bulbaaur! WHAM ");

		} else if (getName().equals("Charmander")) {
			System.out.println("Tackled to the ground by Charmander! BAM! ");
		} else if (getName().equals("Oddish")) {
			System.out.println("Oddish dashes and quckly tackles opponent and slams um");
		} else if (getName().equals("Pikachu")) {
			System.out.println("Pickachu rushes and tackles opponent to the ground ! WHAM");
		} else if (getName().equals("Ponyta")) {
			System.out.println("Ponyta tackles opponent and stuns um!! ");
		} else if (getName().equals("Squirtle")) {
			System.out.println("Squirtle gains fury and tackles opponnnent to the floor! KAPOW!!!!");

		} else if (getName().equals("Staryu")) {
			System.out.println("Staryu dashes right and an tackles opponent to the wall!  ");

		} else if (getName().equals("Zapdos")) {
			System.out.println("Zapdos gets really Angry and tackles opponent to the ground!! ");
		}

		return 5;
	}

	/**
	 * Mega punch randomly picks damamge hit and returns it.
	 * 
	 * @return Integer Hit
	 */
	public int megaPunch() {
		int hit = (int) (Math.random() * 5) + 1;
		if (getName().equals("Bulbasaur")) {
			System.out.println("Bulbasaur spears in the air to delivera Powerfull punch! POWWW! ");

		} else if (getName().equals("Charmander")) {
			System.out.println("Charmander counters with a megaPunch! PUUUUNCH!!! STARS!!! ");
		} else if (getName().equals("Oddish")) {
			System.out.println("Oddish begisn Punches oponent multiple times!");
		} else if (getName().equals("Pikachu")) {
			System.out.println("Pikachu screams out PIKA!!! and runs taoward ooponent delivering a MegaPUnch");
		} else if (getName().equals("Ponyta")) {
			System.out.println("Ponyta Punches  opponent to the Wall!! ");
		} else if (getName().equals("Squirtle")) {
			System.out.println("Squirtle jumps in the air and drops down a MegaPunch");

		} else if (getName().equals("Staryu")) {
			System.out.println("Staryu dashes left and delivers a MegaPunch  ");

		} else if (getName().equals("Zapdos")) {
			System.out.println("Zapdos gets really realluy ANGRY and punches opponent to the air!! ");
		}
		return hit;
	}

}
