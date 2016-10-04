import java.io.Serializable;

public class Pattern implements Serializable{
	private String pattern;
	
	public Pattern(String p){
		pattern = p;
	}
	
	public String getPattern(){
		return pattern;
	}
	
	@Override
	public int hashCode(){
		return pattern.hashCode();
	}
	
	@Override
	public boolean equals ( Object o){
		if(o instanceof Pattern){
			Pattern p = (Pattern) o;
			return pattern.equals(p.pattern);
		}
		
		return false;
	}
	
}



	

