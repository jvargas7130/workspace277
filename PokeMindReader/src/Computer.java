import java.util.HashMap;

public class Computer {
	
	private HashMap <Pattern, Integer> description ;
	private Pattern p;
	
	public Computer(){
		description = new HashMap<Pattern, Integer>();
	}
	
	public int makePrediction(Pattern p){
	
		int prediction = 0;
		
		int randChoice = (int) (Math.random()*3) + 1;
		System.out.println("rand" +randChoice);
		prediction = randChoice;
		
		return prediction;
	}
 
	public void storePattern(Pattern p){
	
		int i = 1;
		
		if (description.containsKey(p)){
			description.put(p, i++);
		
		}else{
			description.put(p, i);
		}
	
	}
}
