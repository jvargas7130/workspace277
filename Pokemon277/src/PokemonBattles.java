/**
 * This program creates a Pokemon Battles class which creates trainer battles
 * and trainer vs wild Pokemon battles. It also creates angry trainer and angry
 * pokemon which the trainer encounters
 * 
 * @author Jesus Vargas
 *
 */
public class PokemonBattles {

	/**
	 * Fight table variable has the differnt element type damage
	 */
	private final static double[][] fightTable = { { .5, .5, 2, 1 }, { 2, .5, .5, 1 }, { .5, 2, .5, 1 },
			{ 1, 2, .5, .5 } };

	/**
	 * Trainer battle method is half a battle of trainer vs defender
	 * 
	 * @param attacker
	 *            Trainer attacker
	 * @param defender
	 *            Trainer defender
	 */
	private static void trainerBattle(Trainer attacker, Trainer defender) {

		// Player battle
		int playerHit = attacker.battle();

		// Type damage
		double damageType = fightTable[attacker.getCurrentPokemon().getType()][defender.getCurrentPokemon().getType()];

		// System.out.println("fighttable" + damageTypeP);
		// System.out.println("fighttable" + damageTypeW);

		// type damage times hit
		int playerDamage = (int) (damageType * playerHit * attacker.getCurrentPokemon().getLevel());

		defender.getCurrentPokemon().loseHp(playerDamage);

		// display stats

		System.out.println();

	}

	/**
	 * Method is used when you encounter a wild pokemon
	 * 
	 * @param player Player object player
	 *           
	 * @param poke Pokemon object poke
	 *           
	 */
	public static void wildPokeBattle(Player player, Pokemon poke) {

		// player chooses style and move
		int playerHit = player.battle();
		System.out.println("player hit" + playerHit);
		// wildpoke chooses
		int wildPokeHit = 0;

		// pokemon random movH
		int style = (int) (Math.random() * 2) + 1;
		int move = (int) (Math.random() * 3) + 1;
		// wild poke chooses between basic fight and special
		wildPokeHit = poke.fight(style, move);

		// Type damage
		double damageTypeP = fightTable[player.getCurrentPokemon().getType()][poke.getType()];
		double damageTypeW = fightTable[poke.getType()][player.getCurrentPokemon().getType()];
		// System.out.println("fighttable" + damageTypeP);
		// System.out.println("fighttable" + damageTypeW);

		// type damage times hit
		int playerDamage = (int) (damageTypeW * wildPokeHit);
		// levelcuse
		int wildPokeDamage = (int) (damageTypeP * playerHit * player.getCurrentPokemon().getLevel());

		// loose hp
		player.getCurrentPokemon().loseHp(playerDamage);
		poke.loseHp(wildPokeDamage);

		System.out.println();

	}

	/**
	 * Opponent Battle method has one full round of a battle
	 * 
	 * @param player  Player object player
	 *          
	 * @param opponent    Opponent object opponent
	 *        
	 */
	public static void opponentBattle(Player player, Opponent opponent) {

		trainerBattle(player, opponent);// player hit
		trainerBattle(opponent, player);

	}

	/**
	 * Angry Pokemon do damage to trainer
	 * 
	 * @param trainer  Trainer object trainer
	 *          
	 */
	public static void angryPokemon(Trainer trainer) {
		trainer.loseHp(2);

	}

	/**
	 * Angry trainer do damage to
	 * 
	 * @param trainer   Trainer object trainer
	 *         
	 */
	public static void angryTrainer(Trainer trainer) {
		trainer.loseHp(3);
	}
}
