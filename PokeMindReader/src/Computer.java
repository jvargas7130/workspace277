import java.io.Serializable;
import java.util.HashMap;
import java.io.Serializable;

public class Computer implements Serializable {

	private HashMap<Pattern, Integer> description;
	private Pattern s;
	private int i = 1;

	public Computer() {
		description = new HashMap<Pattern, Integer>();
	}
	public String getPattern(){
		return s.getPattern();
	}

	public int makePrediction(String newP) {
		int prediction = 0;

		 s = storePattern(new Pattern(newP));
		
		System.out.println(s.getPattern());

		if (!description.isEmpty()) {

			String p1 = "";
			String p2 = "";
			String p3 = "";

			if (newP.length() == 4) {

				p1 = newP.substring(0, 3) + "f";
				p2 = newP.substring(0, 3) + "w";
				p3 = newP.substring(0, 3) + "g";

			} else if (newP.length() == 8) {

				p1 = newP.substring(0, 7) + "f";
				p2 = newP.substring(0, 7) + "w";
				p3 = newP.substring(0, 7) + "g";

			}
			Pattern pat1 = new Pattern(p1);
			Pattern pat2 = new Pattern(p2);
			Pattern pat3 = new Pattern(p3);

			if (description.containsKey(pat1) == false) {//Does not contain pattern 1
				description.put(pat1, 0);
			}

			if (description.containsKey(pat2) == false) {//does not contain pattern 2
				description.put(pat2, 0);
			}

			if (description.containsKey(pat3) == false) {//does not contain pattern 3
				description.put(pat3, 0);
			}

			// 1.fire beats 3.grass
			// 3.grass beats 2.water
			// 2.water beats 1.fire

			if ((description.get(pat1) > description.get(pat2)) && (description.get(pat1) > description.get(pat3))) {
				prediction = 2;// water

			} else if ((description.get(pat2) > description.get(pat1))
					&& (description.get(pat2) > description.get(pat3))) {
				prediction = 3;// grass
			} else if ((description.get(pat3) > description.get(pat1))
					&& (description.get(pat3) > description.get(pat2))) {
				prediction = 1;// fire
			} else {
				int randChoice = (int) (Math.random() * 3) + 1;
				prediction = randChoice;
			}

		}

		return prediction;
	}

	public Pattern storePattern(Pattern s) {

	

		if (description.containsKey(s) == true) {


			int i = description.get(s) + 1;
			System.out.println(description.get(s));
			description.put(s, i++);

		} else {
			description.put(s, i);
		}

	

		return s;
	}
}
