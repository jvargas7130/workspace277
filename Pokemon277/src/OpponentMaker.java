import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program makes 3 oppenents and returns that opponent
 * 
 * @author Jesus Vargas
 *
 */
public class OpponentMaker {
	
	private  ArrayList<Opponent> opponentList;
	
	
	
	public OpponentMaker(){
		 opponentList = new ArrayList<Opponent>();
		
			//Reads in Files
				try{
					Scanner read = new Scanner(new File("OpponentList.txt"));
					do{		
						
						//String line = read.nextLine();
						//String [] tokens =  attackSpeech.split("\n");
						String name = read.nextLine();
						String hp = read.nextLine();
						String attackSpeech = read.nextLine();
						attackSpeech = attackSpeech.replace('#','\n');
						String loseSpeech = read.nextLine();
						loseSpeech = loseSpeech.replace('#','\n');
						String winSpeech = read.nextLine();
						winSpeech = winSpeech.replace('#','\n');
						
						opponentList.add(new Opponent(name, Integer.parseInt(hp),
								attackSpeech, loseSpeech,winSpeech));
					}while (read.hasNext());
					read.close ();
				}catch (FileNotFoundException fnf){
					System.out.println ("File was not found");
				}
					
		
	}
	

	

	/**
	 * Make Random Opponent makes a random opponent and returns opponent
	 * 
	 * @return null
	 */
	public  Opponent MakeRandomOpponent() {
		//just have five lines of data for each opponent�s name,
		//hp, attack speech, lose speech, and win speech)
		

	int size = opponentList.size();

		int opponent = (int) (Math.random() * (size));
				
	

		Opponent opp = new Opponent(opponentList.get(opponent).getName(), opponentList.get(opponent).getHp(), opponentList.get(opponent).getattackSpeech()
				,opponentList.get(opponent).getlooseSpeech(), opponentList.get(opponent).getwinSpeech());
		int type = (int) (Math.random() * 4);
		// create
		opp.addPokemon(PokemonMaker.makeTypePokemon(type));
		
		// Team rocket gets a second pokemon
		if (opp.getName().equals("Team Rocket")) {
			int type1 = (int) (Math.random() * 4);
			opp.addPokemon(PokemonMaker.makeTypePokemon(type1));
		}
		// Rocket team has two
					
		
		return opp;
	}

}
