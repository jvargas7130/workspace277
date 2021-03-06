import java.io.Serializable;
import java.util.HashMap;
import java.io.Serializable;

/**
 * Computer program Stores hash map, stores a pattern in hashmap, and predicts
 * players nest move
 * 
 * @author jesus Vargas
 *
 */
public class Computer implements Serializable {

	/**
	 * Hashmap instant variables
	 */
	private HashMap<Pattern, Integer> description;

	/**
	 * Pattern object is stored
	 */
	private Pattern s;

	/**
	 * computer constructor
	 */
	public Computer() {
		description = new HashMap<Pattern, Integer>();
	}

	/**
	 * getPattern method accessor
	 * 
	 * @return String pattern
	 */
	public String getPattern() {
		return s.getPattern();
	}

	/**
	 * Make prediction predicts the pattern of player
	 * 
	 * @param newP String pattern is passed in to be used in prediction
	 *          
	 * @return prediction of player
	 */
	public char makePrediction(String newP) {

		char prediction = ' ';

		if (!description.isEmpty()) {

			String p1 = newP.substring(1) + "f";
			String p2 = newP.substring(1) + "w";
			String p3 = newP.substring(1) + "g";

			Pattern pat1 = new Pattern(p1);
			Pattern pat2 = new Pattern(p2);
			Pattern pat3 = new Pattern(p3);

			if (description.containsKey(pat1) == false) {// Does not contain
															// pattern 1
				description.put(pat1, 0);
			}

			if (description.containsKey(pat2) == false) {// does not contain
															// pattern 2
				description.put(pat2, 0);
			}

			if (description.containsKey(pat3) == false) {// does not contain
															// pattern 3
				description.put(pat3, 0);
			}

			// 1.fire beats 3.grass
			// 3.grass beats 2.water
			// 2.water beats 1.fire

			// pat1 = f
			// pat2 = w
			// pat3 = g
			System.out.println(description.get(pat1));
			System.out.println(description.get(pat2));
			System.out.println(description.get(pat3));

			// fire
			if ((description.get(pat1) > description.get(pat2)) && (description.get(pat1) > description.get(pat3))) {
				prediction = 'w';// water beats fire

				// water
			} else if ((description.get(pat2) > description.get(pat1))
					&& (description.get(pat2) > description.get(pat3))) {
				prediction = 'g';// grass beats water

				// grass
			} else if ((description.get(pat3) > description.get(pat1))
					&& (description.get(pat3) > description.get(pat2))) {
				prediction = 'f';// vfire beats grass
			} else {
				int randChoice = (int) (Math.random() * 3) + 1;
				
				if(randChoice == 1){
					prediction = 'f';
				}else if(randChoice == 2){
					prediction = 'g';
				}else if(randChoice == 3){
					prediction = 'g';
				}
				
		
			}

		} else {
			int randChoice = (int) (Math.random() * 3) + 1;
			System.out.println("rand" + randChoice);
			if(randChoice == 1){
				prediction = 'f';
			}else if(randChoice == 2){
				prediction = 'g';
			}else if(randChoice == 3){
				prediction = 'g';
			}
			
		}

		return prediction;
	}

	/**
	 * Store prediction stores the pattern of player in hashmap
	 * 
	 * @param s Patttern object is passed in
	 *           
	 */
	public void storePattern(Pattern s) {

		if (description.containsKey(s) == true) {

			int i = description.get(s) + 1;
			System.out.println(description.get(s));
			description.put(s, i);

		} else {
			description.put(s, 1);
		}

	}
}
