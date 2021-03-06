import java.util.ArrayList;

/**
 * This program creates a trainer and has has trainer actions to use in battle.
 * this class also is used to store pokemons, displays the current pokemon, and
 * gives the trainer actions for the pokemon to perform
 *
 * @author jesus vargas
 *
 */
public abstract class Trainer extends Entity {

	/**
	 * Stores and arraylist of pokemons
	 */
	private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

	/**
	 * Current pokemon varible
	 */
	private int currentPokemon = 0;

	/**
	 * Trainer constructor
	 * 
	 * @param name
	 *            stores name
	 * @param hp
	 *            stores jhp which is the trainers health.
	 */
	public Trainer(String name, int hp) {
		super(name, hp);

	}

	/**
	 * Attack speech abstract method used by trainers
	 */
	abstract void attackSpeach();

	/**
	 * winspeech abstract method used by trainers
	 */
	abstract void winSpeach();

	/**
	 * Loss Speech abstract method used by trainers
	 */
	abstract void lossSpeach();

	/**
	 * Choose Style abstract method used by trainers
	 * 
	 * @return Integer style
	 */
	abstract int chooseStyle();

	/**
	 * Choose move abstract method used by trainers
	 * 
	 * @param style Integer style
	 *           
	 * @return Integer move
	 */
	abstract int chooseMove(int style);

	/**
	 * This method displays the current pokemon
	 */
	public void displayCurrentPokemon() {
		System.out.print(pokemon.get(currentPokemon).getName());
	}

	/**
	 * Hits the current pokemon
	 * 
	 * @return Pokemon object pokemon.get(currentPokemon)
	 */
	public Pokemon getCurrentPokemon() {

		return pokemon.get(currentPokemon);
	}

	/**
	 * Get Size accesss the size
	 * 
	 * @return Integer Size
	 */
	public int getSize() {
		return pokemon.size();
	}

	/**
	 * Add Pokmon method Adds pokeon to arraylist
	 * 
	 * @param p  Pokemon object
	 *          
	 */
	public void addPokemon(Pokemon p) {
		pokemon.add(p);
	}

	/**
	 * List Pokemon method dispalys all the pokemon in the array
	 */
	public void listPokemon() {
		int counter = 0;

		System.out.println("Pokemon: ");
		for (int i = 0; i < pokemon.size(); i++) {
			counter++;
			System.out.print(counter + " " + pokemon.get(i).getName() + ": ");

			System.out.print("Level: " + pokemon.get(i).getLevel() + " HP: " + pokemon.get(i).getHp() + "\n");
		}
		System.out.println();

	}

	/**
	 * Heal All Pokemon method heals all pokemon
	 * 
	 */
	public void healAllPokemon() {
		for (int i = 0; i < pokemon.size(); i++) {
			pokemon.get(i).gainHp(-1);
			System.out.println(pokemon.get(i).getName());
		}
	}

	/**
	 * Set current Pokemon method is used to switch pokemon in arraylist
	 * 
	 * @param cur Integer cur(Current Pokemon you want in the array )
	 *           
	 */
	public int setCurrentPokemon(int cur) {

		cur = cur - 1;

		if (cur < pokemon.size() && cur >= 0) {
			Pokemon swap = pokemon.get(currentPokemon);
			pokemon.set(currentPokemon, pokemon.get(cur));
			pokemon.set(cur, swap);
		} else {
			System.out.print("Invalid- Retry:");
			cur = CheckInput.checkInt(1, pokemon.size());
		}

		// currentPokemon = cur;

		return currentPokemon;
	}

	/**
	 * s Get Next Pokemon gets next pokemon in the arraylist to make them the
	 * current pokemon
	 * 
	 * @return currentPokemon integger
	 */
	public int getNextCurPokemon() {
		setCurrentPokemon(2);
		getCurrentPokemon().displayPokemon();
		return currentPokemon;
	}

	/**
	 * Battle methdod is used by trainer to choose style and move of pokemon
	 * 
	 * @return hit Integer
	 */
	public int battle() {
		int hit = 0;

		int style = chooseStyle();
		int move = chooseMove(style);

		hit = getCurrentPokemon().fight(style, move);

		return hit;
	}

}
