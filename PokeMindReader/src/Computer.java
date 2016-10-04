import java.util.HashMap;

public class Computer {
	
	private HashMap <Pattern, Integer> description ;
	
	public Computer(){
		description = new HashMap<Pattern, Integer>();
	}
	
	public int makePrediction(){
		int prediction = 0;
		
		int randChoice = (int) (Math.random()*3) + 1;
		System.out.println("rand" +randChoice);
		prediction = randChoice;
		
		return prediction;
	}
 
	public void storePattern(Pattern p){
		
		description.put(p, 1);
		
	}
}
