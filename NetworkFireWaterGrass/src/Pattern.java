import java.io.Serializable;

/**
 * Pattern class stores a pattern that is stored in a hashmap
 * 
 * @author jesus vargas
 *
 */
public class Pattern implements Serializable {

	/**
	 * String pattern object
	 */
	private String pattern;

	/**
	 * Pattern constructor
	 * 
	 * @param p
	 *            String pattern is passed in
	 */
	public Pattern(String p) {
		pattern = p;
	}

	/**
	 * String pattern method accessor
	 * 
	 * @return
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * Hash code hahses pattern
	 * 
	 * @return hashed pattern
	 */
	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

	/**
	 * equals method checks it the patterns are in order
	 * 
	 * @return true or false depending on the pattern
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pattern) {
			Pattern p = (Pattern) o;
			return pattern.equals(p.pattern);
		}

		return false;
	}

}
